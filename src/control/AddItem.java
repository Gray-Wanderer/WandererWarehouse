package control;

import model.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static control.DataList.itemsList;
import static control.SelectItemType.typeSelectedItem;
import static view.Panes.*;

/**
 * Created by Алена on 28.11.2017.
 */
public class AddItem implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Item item = new Item(typeSelectedItem, ITEM_MAKER_TEXTFIELD.getText(), ITEM_NUMBER_TEXTFIELD.getText());
        itemsList.add(item); //ВоркерЛист - АррэйЛист объектов !!!ВСЕХ!!! работников.
        System.out.println(itemsList.toString());
        ITEMS_LIST.setListData(itemsList.toArray()); //ЛистСтафф - Список всех работников строками для ЛистВью
        JOptionPane.showMessageDialog(null, "Success", "Creating done", JOptionPane.PLAIN_MESSAGE);
    }
}
