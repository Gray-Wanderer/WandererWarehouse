package Model;

import sun.reflect.generics.tree.Tree;

import javax.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

/**
 * Created by Алена on 28.11.2017.
 */
@XmlType(propOrder = {"name", "surname", "listItems"})
public class Person implements Comparable<Person>{
    private String name;
    private String surname;

    @XmlElementWrapper(name = "itemsList")
    @XmlElement(name = "item")
    private TreeSet<Item> listItems;
    private TreeSet<GroupItems> groupList;

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

    @XmlTransient
    public TreeSet<Item> getListItems() {
        return listItems;
    }

    public void setListItems(TreeSet<Item> itemsList) {
        this.listItems = itemsList;
    }

    public void setItem(Item item){
        listItems.add(item);
    }

    public void clearPersone(){
        Item item;
        while(listItems.size()!=0){
            item = listItems.first();
            item.clearItem();
            removeItem(item);
        }
        if(event!=null)
            event.removePersone(this);
        event = null;
    }

    public void removeItem(Item item){
        listItems.remove(item);
        item.setPerson(null);
    }

    public void removeEvent(){
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
        if(getSurname().compareTo(person.getSurname())>0)
            return 1;
        else if(getSurname().compareTo(person.getSurname())<0)
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

        if (getName() != null ? !getName().equals(person.getName()) : person.getName() != null) return false;
        if (getSurname() != null ? !getSurname().equals(person.getSurname()) : person.getSurname() != null)
            return false;
        if (getListItems() != null ? !getListItems().equals(person.getListItems()) : person.getListItems() != null)
            return false;
        return event != null ? event.equals(person.event) : person.event == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getListItems() != null ? getListItems().hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }

    public String info(){
        StringBuffer s = new StringBuffer();
        s.append(name).append(" ").append(surname).append("").append(event.toString()).append(" ").append("( ").append(listItems.toString()).append(")");
        return s.toString();
    }
}
