package data;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Gray_Wanderer on 05.01.2018.
 */
public interface WarehouseDao {

    void init(@Nullable Map<String, Object> params);

    void addItem(@NotNull DataItem dataItem) throws DaoException;

    void updateItem(@NotNull Object oldId, @NotNull DataItem dataItem) throws DaoException;

    @NotNull
    <T extends DataItem> Optional<T> getItem(@NotNull Object id, @NotNull Class<T> objClass) throws DaoException;

    void deleteItem(@NotNull DataItem dataItem) throws DaoException;

    void deleteItem(@NotNull Object id, @NotNull Class<? extends DataItem> objClass) throws DaoException;

    @NotNull
    <T extends DataItem> List<T> getRelativeItems(@NotNull Object id, @NotNull String relativePropertyName, @NotNull Class<T> objClass) throws DaoException;

    void end();

}
