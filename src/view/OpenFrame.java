package view;

import control.AddItemToPersone;
import control.AddPersoneToEvent;
import control.CreateEvent;
import control.CreateItem;
import control.CreatePersone;
import control.DeleteEvent;
import control.DeleteItem;
import control.DeleteItemAtPersone;
import control.DeletePersone;
import control.DeletePersoneAtEvent;
import control.InfoEvent;
import control.InfoItem;
import control.InfoPersone;
import model.DataAdapter;
import model.Event;
import model.Item;
import model.Person;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import static control.DataList.selectedEvent;
import static control.DataList.selectedItem;
import static control.DataList.selectedPersone;
import static view.Panes.ACTIONS_WITH_PERSONE_AT_EVENT;
import static view.Panes.ADD_EVENT;
import static view.Panes.ADD_ITEM;
import static view.Panes.ADD_ITEM_TO_PERSONE;
import static view.Panes.ADD_PERSONE;
import static view.Panes.ADD_PERSONE_TO_EVENT;
import static view.Panes.DELETE_EVENT;
import static view.Panes.DELETE_ITEM;
import static view.Panes.DELETE_ITEM_AT_PERSONE;
import static view.Panes.DELETE_PERSONE;
import static view.Panes.DELETE_PERSONE_AT_EVENT;
import static view.Panes.EVENTS_LIST;
import static view.Panes.EVENT_PERSONELIST;
import static view.Panes.INFO_EVENT;
import static view.Panes.INFO_ITEM;
import static view.Panes.INFO_PERSONE;
import static view.Panes.ITEMS_AT_PERSONE;
import static view.Panes.ITEMS_LIST;
import static view.Panes.PERSONE_LIST;
import static view.Panes.openPane;

/**
 * Created by Алена on 28.11.2017.
 */
public class OpenFrame extends JFrame {
    private static final int APPLICATION_WIDTH = 900;
    private static final int APPLICATION_HEIGHT = 400;

    private static final File file = new File("Warehouse.xml");
    private final CreateItem createItem = new CreateItem();  //объекты слушателей событий действия
    private final CreatePersone createPersone = new CreatePersone();
    private final CreateEvent createEvent = new CreateEvent();
    private final InfoPersone infoPersone;
    private final AddItemToPersone addItemToPersone = new AddItemToPersone(); //слушатель события нажатия на кнопку "Выдать"
    private final InfoItem infoItem = new InfoItem();
    private final DeleteItem deleteItem = new DeleteItem();
    private final DeletePersone deletePersone = new DeletePersone();
    private final InfoEvent infoEvent = new InfoEvent();
    private final AddPersoneToEvent addPersoneToEvent = new AddPersoneToEvent();
    private final DeleteItemAtPersone deleteItemAtPersone = new DeleteItemAtPersone();
    private final DeletePersoneAtEvent deletePersoneAtEvent = new DeletePersoneAtEvent();
    private final DeleteEvent deleteEvent = new DeleteEvent();

    public static void main(String[] args) {
        try {
            initDataStorage();
            new OpenFrame();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void initDataStorage() {
        if (!file.exists()) {
            DataAdapter.createNew(file);
        } else {
            DataAdapter.decodeXML(file);
        }
    }

    public OpenFrame() {
        super();

        setTitle("Warehouse");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DataAdapter.codeXML(file);
            }
        });
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
        setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        pack();
        setLocationRelativeTo(null);
        new Panes();
        setContentPane(openPane); //openPane  - статическое поле класса Panes
        infoPersone = new InfoPersone();
        addActions();
        setVisible(true);
    }

    private void addActions() {
        ADD_EVENT.addActionListener(createEvent);  //при нажатии на кнопку "добавить" оповещаются слушатели события действия
        ADD_PERSONE.addActionListener(createPersone);
        ADD_ITEM.addActionListener(createItem);

        INFO_PERSONE.addActionListener(infoPersone);
        INFO_ITEM.addActionListener(infoItem);
        INFO_EVENT.addActionListener(infoEvent);

        DELETE_ITEM.addActionListener(deleteItem);
        DELETE_PERSONE.addActionListener(deletePersone);
        DELETE_EVENT.addActionListener(deleteEvent);

        ADD_ITEM_TO_PERSONE.addActionListener(addItemToPersone);  //оповещается слушатель addItemToPersone
        DELETE_ITEM_AT_PERSONE.addActionListener(deleteItemAtPersone);

        ACTIONS_WITH_PERSONE_AT_EVENT.addActionListener(infoPersone);
        ADD_PERSONE_TO_EVENT.addActionListener(addPersoneToEvent);
        DELETE_PERSONE_AT_EVENT.addActionListener(deletePersoneAtEvent);

        PERSONE_LIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //модель выбора - единственный элемент
        PERSONE_LIST.addListSelectionListener(this::onPersonSelection); //при выборе человека из списка оповещается слушатель события выбора

        ITEMS_LIST.addListSelectionListener(this::onItemSelection);

        EVENTS_LIST.addListSelectionListener(this::onEventSelection);

        ITEMS_AT_PERSONE.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ITEMS_AT_PERSONE.addListSelectionListener(this::onItemAtPersonSelection);

        EVENT_PERSONELIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        EVENT_PERSONELIST.addListSelectionListener(this::onEventPersonSelection);
    }

    private void onPersonSelection(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            selectedPersone = (Person) PERSONE_LIST.getSelectedValue();
            System.out.println("Do actions with persone " + selectedPersone.toString());
            //INFO_PERSONE.addActionListener(infoPersone);
        }
    }

    private void onItemSelection(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            selectedItem = (Item) ITEMS_LIST.getSelectedValue();
            System.out.println("Do actions with item " + selectedItem.toString());
        }
    }

    private void onEventSelection(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            selectedEvent = (Event) EVENTS_LIST.getSelectedValue();
        }
    }

    private void onItemAtPersonSelection(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            selectedItem = (Item) ITEMS_AT_PERSONE.getSelectedValue();
            System.out.println("Do actions with " + selectedItem.toString());
        }
    }

    private void onEventPersonSelection(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            selectedPersone = (Person) EVENT_PERSONELIST.getSelectedValue();
            System.out.println("Do action with person " + selectedPersone.toString());
        }
    }
}
