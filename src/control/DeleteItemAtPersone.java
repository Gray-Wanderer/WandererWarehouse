package control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static control.DataList.*;
import static view.Panes.ITEMS_AT_PERSONE;

/**
 * Created by Алена on 11.12.2017.
 */
public class DeleteItemAtPersone implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        selectedItem.clearItem();
        itemsWithoutPersone.add(selectedItem);
        ITEMS_AT_PERSONE.setListData(selectedPersone.getListItems().toArray());
        JOptionPane.showMessageDialog(null, "Success", "Deleting done", JOptionPane.PLAIN_MESSAGE);
    }
}
