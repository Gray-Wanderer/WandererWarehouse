package Control;

import Model.Event;
import Model.Item;
import Model.Person;

import javax.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * Created by Алена on 28.11.2017.
 */
@XmlRootElement(name = "Warehouse")
@XmlType(propOrder = {"eventList"})
public class DataList {
    public static Event defaultEvent = new Event();
    public static Person defaultPersone = new Person();

    @XmlElementWrapper(name = "eventList")
    @XmlElement(name = "event")
    public static TreeSet<Event> eventList = new TreeSet<>();

    public static TreeSet<Person> personeList = new TreeSet<>();
    public static TreeSet<Item> itemsList = new TreeSet<>();
    public static TreeSet<Item> itemsWithoutPersone = new TreeSet<>();
    public static TreeSet<Person> personeWithoutEvent = new TreeSet<>();

    public static Person selectedPersone = null;
    public static Item selectedItem = null;
    public static Event selectedEvent = null;

    public static void  setPersoneWithoutEvent(){
        personeWithoutEvent.clear();
        if(!personeList.isEmpty()) {
            Person person = personeList.first();
            for(int i=0; i!=personeList.size(); i++){
                if(person.getEvent()==null)
                    personeWithoutEvent.add(person);
                if(i!=personeList.size()-1)
                    person = personeList.higher(person);
            }
        }
    }

    public static void setItemsWithoutPersone(){
        itemsWithoutPersone.clear();
        if(!itemsList.isEmpty()) {
            Item item = itemsList.first();
            for(int i=0; i!=itemsList.size(); i++){
                if(item.getPerson()==null)
                    itemsWithoutPersone.add(item);
                if(i!=itemsList.size()-1)
                    item = itemsList.higher(item);
            }
        }
    }

    public static void prepareDataToCode(){
        setPersoneWithoutEvent();
        System.out.println("People without events " + personeWithoutEvent);
        setItemsWithoutPersone();
        System.out.println("Items without person " + itemsWithoutPersone);
        defaultEvent.setPersonList(personeWithoutEvent);
        defaultPersone.setListItems(itemsWithoutPersone);
        defaultEvent.addPersone(defaultPersone);
        System.out.println("DefaultEvent " + defaultEvent);
        eventList.add(defaultEvent);
        System.out.println("EventList " + eventList);
    }

    public static void setDecodeData(){
        Person person;
        Item item;
        Event event;
        defaultEvent = eventList.first();
        eventList.remove(defaultEvent);
        defaultPersone = defaultEvent.getPersonList().first();
        defaultEvent.getPersonList().remove(defaultPersone);
        if(!eventList.isEmpty()){
            event = eventList.first();
            for(int i=0; i!=eventList.size(); i++){
                if(!event.getPersonList().isEmpty()) {
                    person = event.getPersonList().first();
                    for(int j=0; j<event.getPersonList().size(); j++){
                        person.setEvent(event);
                        personeList.add(person);
                        System.out.println("To event " + event + " added person " + person);
                        if(!person.getListItems().isEmpty()){
                            item = person.getListItems().first();
                            for (int k=0; k<person.getListItems().size(); k++){
                                item.setPerson(person);
                                itemsList.add(item);
                                System.out.println("To person " + person + " added item " + item);
                                if(k!=person.getListItems().size()-1)
                                    item = person.getListItems().higher(item);
                            }
                        }
                        if(j!=event.getPersonList().size()-1)
                            person = event.getPersonList().higher(person);
                    }
                }
                if(i!=eventList.size()-1)
                    event = eventList.higher(event);
            }
        }

        System.out.println("Default event personList " + defaultEvent.getPersonList());

        if(!defaultEvent.getPersonList().isEmpty()){
            System.out.println("Do actions with default event");
            person = defaultEvent.getPersonList().first();
            for(int i=0; i!=defaultEvent.getPersonList().size(); i++) {
                personeList.add(person);
                System.out.println("Person " + person + " added as default");
                if (!person.getListItems().isEmpty()) {
                    item = person.getListItems().first();
                    for (int j = 0; j < person.getListItems().size(); j++) {
                        item.setPerson(person);
                        itemsList.add(item);
                        System.out.println("To person " + person + " added item " + item);
                        if (j != person.getListItems().size() - 1)
                            item = person.getListItems().higher(item);
                    }
                }
                if(i!=defaultEvent.getPersonList().size()-1)
                    person = defaultEvent.getPersonList().higher(person);
            }
        }
        defaultEvent.getPersonList().clear();

        if(!defaultPersone.getListItems().isEmpty()) {
            System.out.println("Do actions with default person");
            item = defaultPersone.getListItems().first();
            for(int i=0; i!=defaultPersone.getListItems().size(); i++){
                itemsList.add(item);
                System.out.println("Item " + item + " added as default");
                if(i!=defaultPersone.getListItems().size()-1)
                    item = defaultPersone.getListItems().higher(item);
            }
        }
        defaultPersone.getListItems().clear();

        /*Person person;
        Item item;
        if (!eventList.isEmpty()) {
            Event event = eventList.first();
            defaultEvent = eventList.first();
            if(!defaultEvent.getPersonList().isEmpty()) {
                defaultPersone = defaultEvent.getPersonList().first();
                defaultEvent.removePersone(defaultPersone);
            }
            eventList.remove(defaultEvent);
            int i = 0;
            int j = 0;
            int k = 0;
            while (i != eventList.size()) {
                if(!event.getPersonList().isEmpty()) {
                    person = event.getPersonList().first();
                    while (j != event.getPersonList().size()) {
                        person.setEvent(event);
                        personeList.add(person);
                        System.out.println("to event " + event + " added person " + person);
                        if (!person.getListItems().isEmpty()) {
                            item = person.getListItems().first();
                            while (k != person.getListItems().size()) {
                                item.setPerson(person);
                                itemsList.add(item);
                                System.out.println("to person " + person + " added item " + item);
                                if(k!=person.getListItems().size()-1)
                                    item = person.getListItems().higher(item);
                                k++;
                            }
                        }
                        if(j!=event.getPersonList().size()-1)
                            person = event.getPersonList().higher(person);
                        j++;
                    }
                }
                if(i!=eventList.size()-1)
                    event = eventList.higher(event);
                i++;
            }
            i = 0;
            j = 0;
            if (!defaultEvent.getPersonList().isEmpty()) {
                person = defaultEvent.getPersonList().first();
                while (i != defaultEvent.getPersonList().size()) {
                    personeList.add(person);
                    System.out.println("to default event added person " + person);
                    item = person.getListItems().first();
                    while (j != person.getListItems().size()) {
                        item.setPerson(person);
                        itemsList.add(item);
                        System.out.println("to person " + person.toString() + " added item " + item.toString());
                        item = person.getListItems().higher(item);
                        j++;
                    }
                    person = defaultEvent.getPersonList().higher(person);
                    i++;
                }
            }
            i = 0;
            if (!defaultPersone.getListItems().isEmpty()) {
                item = defaultPersone.getListItems().first();
                while (i != defaultPersone.getListItems().size()) {
                    itemsList.add(item);
                    System.out.println("to default person added item " + item.toString());
                    item = defaultPersone.getListItems().higher(item);
                    i++;
                }
            }
            defaultEvent.getPersonList().clear();
            defaultPersone.getListItems().clear();
        }*/
    }

    public static Event getDefaultEvent() {
        return defaultEvent;
    }

    public static Person getDefaultPersone() {
        return defaultPersone;
    }
}
