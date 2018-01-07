package data;

import control.DataList;
import model.Item;
import model.Person;
import view.OpenFrame;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Gray-Wanderer on 07.01.2018.
 */
public class PersonDao extends AbstractWarehouseDao<Person> {
    @Override
    protected Class<Person> getItemClass() {
        return Person.class;
    }

    public Collection<Person> getAllPersonsWithoutEvent() throws DaoException {
        checkInitialized();

        return super.getAllItems().stream()
                .filter(item -> item.getEventId() == null)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteItem(Person dataItem) throws DaoException {
        checkInitialized();

        ItemDao itemDao = OpenFrame.getDao(ItemDao.class);
        for (Item item : getAllDependentItems(DataList.getSelectedPersonNN(false))) {
            item.setPersonId(null);
            itemDao.updateItem(item);
        }

        super.deleteItem(dataItem);
    }

    public Collection<Item> getAllDependentItems(Person person) throws DaoException {
        checkInitialized();

        return storage.getDependentItems(person.getId(), "personId", Item.class);
    }
}
