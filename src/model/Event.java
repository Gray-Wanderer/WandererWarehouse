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
@XmlType(propOrder = {"id", "name", "personList"})
public class Event extends DataItem<String> implements Comparable<Event> {
    private String name;

    @XmlElementWrapper(name = "listPersone")
    @XmlElement(name = "persone")
    private TreeSet<Person> personList;

    public Event() {
        personList = new TreeSet<>();
        name = "";
    }

    public Event(String name) {
        this.name = name;
        personList = new TreeSet<>();
    }

    public Event(String name, TreeSet<Person> personList) {
        this.name = name;
        this.personList = personList;
    }

    @Override
    @XmlElement
    public String getId() {
        return getName();
    }

    @XmlTransient
    public TreeSet<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(TreeSet<Person> personList) {
        this.personList = personList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPersone(Person persone) {
        personList.add(persone);
    }

    public void removePersone(Person person) {
        personList.remove(person);
    }

    public void clearEvent() {
        Person person;
        while (personList.size() != 0) {
            person = personList.first();
            person.removeEvent();
            removePersone(person);
        }
    }

    @Override
    public int compareTo(Event event) {
        if (getName().compareTo(event.getName()) > 0)
            return 1;
        else if (getName().compareTo(event.getName()) < 0)
            return -1;
        else
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        return Objects.equals(getName(), event.getName()) &&
                Objects.equals(getPersonList(), event.getPersonList());
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getPersonList() != null ? getPersonList().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(name);
        return s.toString();
    }

    public String info() {
        StringBuffer s = new StringBuffer();
        s.append(name).append(" ").append(personList.toString());
        return s.toString();
    }
}
