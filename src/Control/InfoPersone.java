package Control;

import Model.Person;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static Control.DataList.selectedEvent;
import static Control.DataList.selectedPersone;
import static View.Panes.*;

/**
 * Created by Алена on 03.12.2017.
 */
public class InfoPersone implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Selected Persone");
            /*selectedPersone = (Person) PERSONE_LIST.getSelectedValue();
            System.out.println("Do actions with persone "+PERSONE_LIST.getSelectedValue().toString());*/
            JFrame selectPersone = new JFrame();
            selectPersone.setTitle(selectedPersone.toString());
            selectPersone.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {

                }

                @Override
                public void windowClosing(WindowEvent e) {
                    selectPersone.dispose();
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

            selectPersone.setPreferredSize(new Dimension(300,200));
            selectPersone.setSize(300,200);
            selectPersone.pack();
            selectPersone.setLocationRelativeTo(null);
            selectPersone.setContentPane(selectPersonePane); //панель - статическое поле класса Panes

            addActions();

            selectPersone.setVisible(true);
    }

    private void addActions(){
        if(selectedPersone.getEvent()!=null)
            PERSONE_EVENT_LABEL.setText("Event: " + selectedPersone.getEvent().toString());
        else
            PERSONE_EVENT_LABEL.setText("Event: null");
        ITEMS_AT_PERSONE.setListData(selectedPersone.getListItems().toArray());
    }
}
