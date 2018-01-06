package data.xmldao;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import data.DaoException;
import data.DataItem;
import data.WarehouseDao;
import data.xmldao.saverstrategy.FileByClassStorageStrategy;
import data.xmldao.saverstrategy.SingleFileStorageStrategy;
import data.xmldao.saverstrategy.StorageStrategy;
import exceptions.DevelopmentException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author Gray_Wanderer on 05.01.2018.
 */
public class XmlWarehouseDao implements WarehouseDao {

    public static String MULTI_FILE_STORAGE_STRATEGY = "MULTI_FILE_STORAGE_STRATEGY";

    private StorageStrategy storageStrategy;

    private Map<Class<? extends DataItem>, Map<Object, DataItem>> inMemoryBase = null;

    @Override
    public void init(@Nullable Map<String, Object> params) {
        storageStrategy = getStorageStrategy(params);

        inMemoryBase = storageStrategy.load();
    }

    @NotNull
    private StorageStrategy getStorageStrategy(Map<String, Object> params) {
        Object multiFileStorageStrategy = params != null ? params.get(MULTI_FILE_STORAGE_STRATEGY) : null;
        if (multiFileStorageStrategy != null) {
            if (multiFileStorageStrategy instanceof Boolean) {
                if ((Boolean) multiFileStorageStrategy) {
                    return new FileByClassStorageStrategy();
                }
            } else {
                throw new DevelopmentException("Property 'MULTI_FILE_STORAGE_STRATEGY' have contains the boolean value");
            }
        }
        return new SingleFileStorageStrategy();
    }

    @Override
    public void addItem(@NotNull DataItem dataItem) throws DaoException {
        Map<Object, DataItem> objects = inMemoryBase.computeIfAbsent(dataItem.getClass(), k -> new HashMap<>());

        if (objects.containsKey(dataItem.getId()))
            throw new DaoException(dataItem.getClass().getSimpleName() + " with id '" + dataItem.getId() + "' already exists!");

        objects.put(dataItem.getId(), getClonedItem(dataItem));
    }

    private DataItem getClonedItem(@Nullable DataItem item) {
        if (item == null)
            return null;
        try {
            return item.clone();
        } catch (CloneNotSupportedException e) {
            throw new XmlWarehouseDaoException("Clone object error: " + item.getClass().getSimpleName(), e);
        }
    }

    @Override
    public void updateItem(Object oldId, DataItem dataItem) throws DaoException {
        if (oldId != dataItem.getId()) {
            if (getItem(dataItem.getId(), dataItem.getClass()).isPresent())
                throw new DaoException(dataItem.getClass().getSimpleName() + " with id '" + dataItem.getId() + "' already exists!");
        }
        deleteItem(oldId, dataItem.getClass());
        addItem(dataItem);
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public <T extends DataItem> Optional<T> getItem(@NotNull Object id, @NotNull Class<T> objClass) {
        Map<Object, DataItem> objects = inMemoryBase.get(objClass);

        return Optional.ofNullable(objects == null ? null : (T) getClonedItem(objects.get(id)));
    }

    @Override
    public void deleteItem(@NotNull DataItem dataItem) {
        deleteItem(dataItem.getId(), dataItem.getClass());
    }

    @Override
    public void deleteItem(@NotNull Object id, @NotNull Class<? extends DataItem> objClass) {
        Map<Object, DataItem> objects = inMemoryBase.get(objClass);

        if (objects != null) {
            objects.remove(id);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends DataItem> List<T> getRelativeItems(Object id, String relativePropertyName, Class<T> objClass) {
        Map<Object, DataItem> objects = inMemoryBase.get(objClass);

        if (objects == null)
            return Collections.emptyList();

        BiFunction<T, Object, Boolean> filter = getFieldResolverFunction(relativePropertyName, objClass);

        return objects.values().stream()
                .filter(item -> filter.apply((T) item, id))
                .map(item -> (T) getClonedItem(item))
                .collect(Collectors.toList());
    }

    private <T> BiFunction<T, Object, Boolean> getFieldResolverFunction(String fieldName, Class<T> itemClass) {
        try {
            Field field = itemClass.getField(fieldName);
            return (item, id) -> {
                try {
                    return id.equals(field.get(item));
                } catch (IllegalAccessException e) {
                    throw new XmlWarehouseDaoException("Field access exception", e);
                }
            };
        } catch (NoSuchFieldException ignored) {
        }

        String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

        try {
            Method method = itemClass.getMethod(getterName);
            return (item, id) -> {
                try {
                    return id.equals(method.invoke(item));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new XmlWarehouseDaoException("Method access exception", e);
                }
            };
        } catch (NoSuchMethodException e) {
            throw new DevelopmentException("'" + fieldName + "' field or getter is not found in class " + itemClass.getSimpleName());
        }
    }

    @Override
    public void end() {
        storageStrategy.save(inMemoryBase);
    }
}
