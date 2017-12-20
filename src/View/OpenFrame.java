package View;

import Control.*;
import Model.*;
import Model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;


import static Control.DataList.*;
import static Model.DataAdapter.codeXML;
import static Model.DataAdapter.decodeXML;
import static View.Panes.*;

/**
 * Created by Алена on 28.11.2017.
 */
public class OpenFrame extends JFrame {
    private static File file = new File("Warehouse.xml");
    private CreateItem createItem = new CreateItem();  //объыекты слушателей событий действия
    private CreatePersone createPersone = new CreatePersone();
    private CreateEvent createEvent = new CreateEvent();
    private InfoPersone infoPersone;
    private AddItemToPersone addItemToPersone = new AddItemToPersone();; //слушатель события нажатия на кнопку "Выдать"
    private InfoItem infoItem = new InfoItem();
    private DeleteItem deleteItem = new DeleteItem();
    private DeletePersone deletePersone = new DeletePersone();
    private InfoEvent infoEvent = new InfoEvent();
    private AddPersoneToEvent addPersoneToEvent = new AddPersoneToEvent();
    private DeleteItemAtPersone deleteItemAtPersone = new DeleteItemAtPersone();
    private DeletePersoneAtEvent deletePersoneAtEvent = new DeletePersoneAtEvent();
    private DeleteEvent deleteEvent = new DeleteEvent();

    public static void main(String[] args) {
        try {
            if (!file.exists())
                file.createNewFile();
            else
                decodeXML(file);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        new OpenFrame();
    }
    public OpenFrame(){
        super();

        setTitle("Warehouse");

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                codeXML(file);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(900,400));
        setSize(900,400);

        pack();

        setLocationRelativeTo(null);

        new Panes();

        setContentPane(openPane); //openPane  - статическое поле класса Panes

         infoPersone = new InfoPersone();

        addActions();

        setVisible(true);
    }

    private void addActions(){

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
        PERSONE_LIST.addListSelectionListener(e ->{
            if(!e.getValueIsAdjusting()) {
                selectedPersone = (Person) PERSONE_LIST.getSelectedValue();
                System.out.println("Do actions with persone "+selectedPersone.toString());
                //INFO_PERSONE.addActionListener(infoPersone);
            }
        }); //при выборе человека из списка оповещается слушатель события выбора

        ITEMS_LIST.addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()) {
                selectedItem = (Item) ITEMS_LIST.getSelectedValue();
                System.out.println("Do actions with item " + selectedItem.toString());
            }
        });

        EVENTS_LIST.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting())
                selectedEvent = (Event) EVENTS_LIST.getSelectedValue();
        });

        ITEMS_AT_PERSONE.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ITEMS_AT_PERSONE.addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()) {
                selectedItem = (Item) ITEMS_AT_PERSONE.getSelectedValue();
                System.out.println("Do actions with " + selectedItem.toString());
            }
        });

        EVENT_PERSONELIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        EVENT_PERSONELIST.addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()) {
                selectedPersone = (Person) EVENT_PERSONELIST.getSelectedValue();
                System.out.println("Do action with person " + selectedPersone.toString());
            }
        });
    }
}
