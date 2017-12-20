package Control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Control.DataList.*;
import static View.Panes.EVENT_PERSONELIST;
import static View.Panes.ITEMS_AT_PERSONE;

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
