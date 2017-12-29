package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static control.DataList.selectedPersone;
import static view.Panes.*;

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
        selectPersone.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                selectPersone.dispose();
            }
        });

        selectPersone.setPreferredSize(new Dimension(300, 200));
        selectPersone.setSize(300, 200);
        selectPersone.pack();
        selectPersone.setLocationRelativeTo(null);
        selectPersone.setContentPane(selectPersonePane); //панель - статическое поле класса Panes

        addActions();

        selectPersone.setVisible(true);
    }

    private void addActions() {
        if (selectedPersone.getEvent() != null)
            PERSONE_EVENT_LABEL.setText("Event: " + selectedPersone.getEvent().toString());
        else
            PERSONE_EVENT_LABEL.setText("Event: null");
        ITEMS_AT_PERSONE.setListData(selectedPersone.getListItems().toArray());
    }
}
