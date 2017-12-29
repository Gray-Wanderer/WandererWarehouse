package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static control.DataList.selectedEvent;
import static control.DataList.selectedPersone;
import static view.Panes.*;

/**
 * Created by Алена on 04.12.2017.
 */
public class InfoEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Selected Event");
        JFrame selectEvent = new JFrame();
        selectEvent.setTitle("Event");
        selectEvent.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                selectEvent.dispose();
            }
        });

        selectEvent.setPreferredSize(new Dimension(300, 250));
        selectEvent.setSize(300, 250);
        selectEvent.pack();
        selectEvent.setLocationRelativeTo(null);
        selectEvent.setContentPane(addPersoneToEvent); //панель - статическое поле класса Panes

        addActions();

        selectEvent.setVisible(true);
    }

    private void addActions() {
        selectedPersone = null;
        EVENT_NAME_TEXTFIELD.setText(selectedEvent.getName());
        EVENT_PERSONELIST.setListData(selectedEvent.getPersonList().toArray());
    }
}
