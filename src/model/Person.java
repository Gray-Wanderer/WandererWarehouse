package model;

import data.DataItem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Created by Алена on 28.11.2017.
 */
@XmlType(propOrder = {"id", "name", "surname", "listItems", "eventId"})
public class Person extends DataItem<String> implements Comparable<Person> {
    private String name;
    private String surname;

    @XmlElementWrapper(name = "itemsList")
    @XmlElement(name = "item")
    private TreeSet<Item> listItems;
    private TreeSet<GroupItems> groupList;

    private String eventId;
    private Event event;

    public Person() {
        name = "";
        surname = "";
        listItems = new TreeSet<>();
        event = null;
        groupList = new TreeSet<>();
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        listItems = new TreeSet<>();
        event = null;
        groupList = new TreeSet<>();
    }

    @Override
    @XmlElement
    public String getId() {
        return getSurname() + " " + getName();
    }

    @XmlTransient
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @XmlTransient
    public TreeSet<Item> getListItems() {
        return listItems;
    }

    public void setListItems(TreeSet<Item> itemsList) {
        this.listItems = itemsList;
    }

    public void setItem(Item item) {
        listItems.add(item);
    }

    public void clearPersone() {
        Item item;
        while (listItems.size() != 0) {
            item = listItems.first();
            item.clearItem();
            removeItem(item);
        }
        if (event != null)
            event.removePersone(this);
        event = null;
    }

    public void removeItem(Item item) {
        listItems.remove(item);
        item.setPerson(null);
    }

    public void removeEvent() {
        event.removePersone(this);
        event = null;
    }

    @XmlTransient
    public TreeSet<GroupItems> getGroupList() {
        return groupList;
    }

    public void setGroupList(TreeSet<GroupItems> groupList) {
        this.groupList = groupList;
    }

    @Override
    public int compareTo(Person person) {
        if (getSurname().compareTo(person.getSurname()) > 0)
            return 1;
        else if (getSurname().compareTo(person.getSurname()) < 0)
            return -1;
        else
            return getName().compareTo(person.getName());
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(surname).append(" ").append(name);
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return Objects.equals(getName(), person.getName()) &&
                Objects.equals(getSurname(), person.getSurname()) &&
                Objects.equals(getListItems(), person.getListItems()) &&
                Objects.equals(event, person.event);
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getListItems() != null ? getListItems().hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }

    public String info() {
        StringBuffer s = new StringBuffer();
        s.append(name).append(" ").append(surname).append("").append(event.toString()).append(" ").append("( ").append(listItems.toString()).append(")");
        return s.toString();
    }
}
