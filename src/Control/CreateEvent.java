package Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static View.Panes.CREATE_EVENT;
import static View.Panes.createEventPane;
import static View.Panes.createPersonePane;

/**
 * Created by Алена on 28.11.2017.
 */
public class CreateEvent implements ActionListener {
    AddEvent addEvent = new AddEvent();

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame createEvent = new JFrame();
        createEvent.setTitle("Event parameters");

        createEvent.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                createEvent.dispose();
                addEvent=null;
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

        createEvent.setPreferredSize(new Dimension(300,120));
        createEvent.setSize(300,120);
        createEvent.pack();
        createEvent.setLocationRelativeTo(null);
        createEvent.setContentPane(createEventPane);
        addActions();

        createEvent.setVisible(true);
    }

    private void addActions(){
        CREATE_EVENT.addActionListener(addEvent);
    }
}
