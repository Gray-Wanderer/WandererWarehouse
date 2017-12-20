package Control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Control.DataList.itemsList;
import static Control.DataList.selectedItem;
import static View.Panes.ITEMS_LIST;

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
