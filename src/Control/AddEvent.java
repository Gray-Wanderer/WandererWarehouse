package Control;

import Model.Event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Control.DataList.eventList;
import static Control.DataList.personeList;
import static View.Panes.*;

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
