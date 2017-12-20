package Control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Control.DataList.*;
import static View.Panes.ITEMS_AT_PERSONE;
import static View.Panes.ITEMS_LIST;

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
