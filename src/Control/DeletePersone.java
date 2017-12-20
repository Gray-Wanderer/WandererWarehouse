package Control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Control.DataList.personeList;
import static Control.DataList.selectedItem;
import static Control.DataList.selectedPersone;
import static View.Panes.PERSONE_LIST;

/**
 * Created by Алена on 04.12.2017.
 */
public class DeletePersone implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        selectedPersone.clearPersone();
        personeList.remove(selectedPersone);
        PERSONE_LIST.setListData(personeList.toArray());
        JOptionPane.showMessageDialog(null, "Success", "Deleting done", JOptionPane.PLAIN_MESSAGE);

    }
}
