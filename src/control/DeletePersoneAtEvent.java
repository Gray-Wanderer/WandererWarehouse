package control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static control.DataList.*;
import static view.Panes.EVENT_PERSONELIST;

/**
 * Created by Алена on 11.12.2017.
 */
public class DeletePersoneAtEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        selectedPersone.removeEvent();
        personeWithoutEvent.add(selectedPersone);
        EVENT_PERSONELIST.setListData(selectedEvent.getPersonList().toArray());
        JOptionPane.showMessageDialog(null, "Success", "Deleting done", JOptionPane.PLAIN_MESSAGE);
    }
}
