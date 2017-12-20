package Control;

import Model.Item;
import Model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static Control.DataList.*;
import static View.Panes.EVENT_PERSONELIST;
import static View.Panes.ITEMS_AT_PERSONE;

/**
 * Created by Алена on 11.12.2017.
 */
public class AddPersoneToEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame addPersone = new JFrame();
        addPersone.setTitle("Select Persone");

        addPersone.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                addPersone.dispose();
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

        addPersone.setPreferredSize(new Dimension(300,100));
        addPersone.setSize(300,100);
        addPersone.pack();
        addPersone.setLocationRelativeTo(null);
        //addItem.setContentPane(addItem.getContentPane()); //панель - статическое поле в классе Panes

        setPersoneWithoutEvent(); //статический метод класса DataList, обновляющий список свободного снаряжения
        System.out.println(personeList.toString());
        System.out.println(personeWithoutEvent.toString());
        //LIST_ITEMS_WITHOUT_PERSONE.setListData(itemsWithoutPersone.toArray()); //связываем список доступного снаряжения с его отображением
        addActions(addPersone.getContentPane());

        addPersone.setVisible(true);
    }

    private void addActions(Container contentPane){
        JList<Object> listPersoneWithoutEvent = new JList<>(personeWithoutEvent.toArray());
        contentPane.setLayout(new GridLayout());
        contentPane.add(listPersoneWithoutEvent);
        listPersoneWithoutEvent.setBackground(Color.white);

        listPersoneWithoutEvent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPersoneWithoutEvent.addListSelectionListener(e -> {  //ЛЯМБДА
            System.out.println("Item selected");
            //LIST_ITEMS_WITHOUT_PERSONE.clearSelection();
            if(!e.getValueIsAdjusting()) {  //что-то произойдет только если есть выбранное значение в событии
                System.out.println("Adding persone to event");
                selectedPersone = (Person) listPersoneWithoutEvent.getSelectedValue();
                System.out.println("Selected persone "+selectedPersone);
                selectedEvent.addPersone(selectedPersone);
                selectedPersone.setEvent(selectedEvent);
                System.out.println("Event personeList " + selectedEvent.getPersonList());
                System.out.println("Persone added to event" + selectedPersone);

                EVENT_PERSONELIST.setListData(selectedEvent.getPersonList().toArray());

                personeWithoutEvent.remove(selectedPersone);
                listPersoneWithoutEvent.setListData(personeWithoutEvent.toArray());
                JOptionPane.showMessageDialog(null, "Success", "Persone add", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
}
