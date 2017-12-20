package Control;

import Model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static Control.DataList.*;
import static View.Panes.*;

/**
 * Created by Алена on 29.11.2017.
 */
public class AddItemToPersone implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame addItem = new JFrame();
        addItem.setTitle("Выберете снаряжение");

        addItem.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                addItem.dispose();
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

        addItem.setPreferredSize(new Dimension(300,100));
        addItem.setSize(300,100);
        addItem.pack();
        addItem.setLocationRelativeTo(null);
        //addItem.setContentPane(addItem.getContentPane()); //панель - статическое поле в классе Panes

        setItemsWithoutPersone(); //статический метод класса DataList, обновляющий список свободного снаряжения
        System.out.println(itemsList.toString());
        System.out.println(itemsWithoutPersone.toString());
        //LIST_ITEMS_WITHOUT_PERSONE.setListData(itemsWithoutPersone.toArray()); //связываем список доступного снаряжения с его отображением
        addActions(addItem.getContentPane());

        addItem.setVisible(true);
    }

    private void addActions(Container contentPane){
        JList<Object> listItemsWithoutPersone = new JList<>(itemsWithoutPersone.toArray());
        contentPane.setLayout(new GridLayout());
        contentPane.add(listItemsWithoutPersone);
        listItemsWithoutPersone.setBackground(Color.white);

        listItemsWithoutPersone.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listItemsWithoutPersone.addListSelectionListener(e -> {  //ЛЯМБДА
            System.out.println("Item selected");
            //LIST_ITEMS_WITHOUT_PERSONE.clearSelection();
            if(!e.getValueIsAdjusting()) {  //что-то произойдет только если есть выбранное значение в событии
                System.out.println("Adding item to persone");
                selectedItem = (Item) listItemsWithoutPersone.getSelectedValue();
                System.out.println("Selected item "+selectedItem);
                selectedPersone.setItem(selectedItem);
                selectedItem.setPerson(selectedPersone);
                System.out.println("Person itemsList " + selectedPersone.getListItems());
                System.out.println("Item added to persone" + selectedItem.getPerson());

                ITEMS_AT_PERSONE.setListData(selectedPersone.getListItems().toArray());

                itemsWithoutPersone.remove(selectedItem);
                listItemsWithoutPersone.setListData(itemsWithoutPersone.toArray());
                JOptionPane.showMessageDialog(null, "Готово", "Снаряжение выдано", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
}
