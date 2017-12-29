package control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static control.DataList.eventList;
import static control.DataList.selectedEvent;
import static view.Panes.EVENTS_LIST;

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
