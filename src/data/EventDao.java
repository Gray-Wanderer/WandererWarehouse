package data;

import model.Event;
import model.Person;

import java.util.Collection;

/**
 * @author Gray-Wanderer on 07.01.2018.
 */
public class EventDao extends AbstractWarehouseDao<Event> {
    @Override
    protected Class<Event> getItemClass() {
        return Event.class;
    }

    public Collection<Person> getAllDependentPersons(Event event) throws DaoException {
        return storage.getDependentItems(event.getId(), "eventId", Person.class);
    }
}
