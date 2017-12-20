package Control;

import Model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static Control.DataList.*;
import static Control.DataList.itemsWithoutPersone;
import static Control.DataList.selectedItem;
import static View.Panes.*;

/**
 * Created by Алена on 04.12.2017.
 */
public class InfoEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Selected Event");
        JFrame selectEvent = new JFrame();
        selectEvent.setTitle("Event");
        selectEvent.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                selectEvent.dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        selectEvent.setPreferredSize(new Dimension(300,250));
        selectEvent.setSize(300,250);
        selectEvent.pack();
        selectEvent.setLocationRelativeTo(null);
        selectEvent.setContentPane(addPersoneToEvent); //панель - статическое поле класса Panes

        addActions();

        selectEvent.setVisible(true);
    }

    private void addActions(){
        selectedPersone = null;
        EVENT_NAME_TEXTFIELD.setText(selectedEvent.getName());
        EVENT_PERSONELIST.setListData(selectedEvent.getPersonList().toArray());
    }
}
