package control;

import model.Event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static control.DataList.eventList;
import static view.Panes.EVENTS_LIST;
import static view.Panes.NAME_EVENT_TEXTFIELD;

/**
 * Created by Алена on 29.11.2017.
 */
public class AddEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Event event = new Event(NAME_EVENT_TEXTFIELD.getText());
        eventList.add(event);
        System.out.println(eventList.toString());
        EVENTS_LIST.setListData(eventList.toArray()); //ЛистСтафф - Список всех работников строками для ЛистВью
        NAME_EVENT_TEXTFIELD.setText("");
        JOptionPane.showMessageDialog(null, "Success", "Creating done", JOptionPane.PLAIN_MESSAGE);
    }
}
