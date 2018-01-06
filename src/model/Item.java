package model;

import data.DataItem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

/**
 * Created by Алена on 28.11.2017.
 */
//@XmlRootElement(name = "Item")
@XmlType(propOrder = {"id", "type", "maker", "name", "personId"})
public class Item extends DataItem<String> implements Comparable<Item> {
    //@XmlElement(name = "type")
    private ItemType type;
    //@XmlElement(name = "maker")
    private String maker;
    //@XmlElement(name = "nameItem")
    private String name;
    //@XmlElement(name = "persone")
    private Person person;
    //@XmlElement(name = "event")
    private Event event;
    private GroupItems group;
    private String personId;
    //private Calendar dateRemove;

    public Item() {
        type = ItemType.None;
        maker = "";
        name = "";
        person = null;
        event = null;
        group = null;
        //dateRemove = null;
    }

    public Item(ItemType type, String maker, String name) {
        this.type = type;
        this.maker = maker;
        this.name = name;
        person = null;
        event = null;
        group = null;
        //dateRemove = null;
    }

    @Override
    @XmlElement
    public String getId() {
        return getName();
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @XmlTransient
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    @XmlTransient
    public GroupItems getGroup() {
        return group;
    }

    public void setGroup(GroupItems group) {
        this.group = group;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public int compareTo(Item item) {
        if (getType().compareTo(item.getType()) > 0)
            return 1;
        else if (getType().compareTo(item.getType()) < 0)
            return -1;
        else
            return getName().compareTo(item.getName());
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(type).append(" ").append(maker).append(" ").append(name);
        return s.toString();
    }

    public String info() {
        StringBuffer s = new StringBuffer();
        s.append("Equip info:\n").append(type).append(" ").append(maker).append(" ").append(name).append("\n");
        s.append("At persone: ");
        if (person != null)
            s.append(person.toString()).append("\n");
        else
            s.append("none").append("\n");
        return s.toString();
    }

    public void clearItem() {
        if (person != null)
            person.removeItem(this);
        if (group != null)
            group.removeItem(this);
        event = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        return Objects.equals(name, item.name) &&
                Objects.equals(getType(), item.getType()) &&
                Objects.equals(getMaker(), item.getMaker()) &&
                Objects.equals(getPerson(), item.getPerson()) &&
                Objects.equals(getEvent(), item.getEvent());
    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getMaker() != null ? getMaker().hashCode() : 0);
        result = 31 * result + (getPerson() != null ? getPerson().hashCode() : 0);
        result = 31 * result + (getEvent() != null ? getEvent().hashCode() : 0);
        result = 31 * result + name.hashCode();
        return result;
    }
}
