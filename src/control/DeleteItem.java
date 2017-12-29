package control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static control.DataList.itemsList;
import static control.DataList.selectedItem;
import static view.Panes.ITEMS_LIST;

/**
 * Created by Алена on 04.12.2017.
 */
public class DeleteItem implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        selectedItem.clearItem();
        itemsList.remove(selectedItem);
        ITEMS_LIST.setListData(itemsList.toArray());
        JOptionPane.showMessageDialog(null, "Success", "Deleting done", JOptionPane.PLAIN_MESSAGE);
    }
}
