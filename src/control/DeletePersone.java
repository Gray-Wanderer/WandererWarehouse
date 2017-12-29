package control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static control.DataList.personeList;
import static control.DataList.selectedPersone;
import static view.Panes.PERSONE_LIST;

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
