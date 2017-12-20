package Control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Control.DataList.*;
import static View.Panes.EVENTS_LIST;
import static View.Panes.PERSONE_LIST;

/**
 * Created by Алена on 11.12.2017.
 */
public class DeleteEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        selectedEvent.clearEvent();
        eventList.remove(selectedEvent);
        EVENTS_LIST.setListData(eventList.toArray());
        JOptionPane.showMessageDialog(null, "Success", "Deleting done", JOptionPane.PLAIN_MESSAGE);

    }
}
