package Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static View.Panes.*;

/**
 * Created by Алена on 28.11.2017.
 */
public class CreatePersone implements ActionListener {
    AddPersone addPersone = new AddPersone();

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame createPersone = new JFrame();
        createPersone.setTitle("Persone parameters");

        createPersone.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                createPersone.dispose();
                addPersone=null;
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

        createPersone.setPreferredSize(new Dimension(300,150));
        createPersone.setSize(300,150);
        createPersone.pack();
        createPersone.setLocationRelativeTo(null);
        createPersone.setContentPane(createPersonePane);
        addActions();

        createPersone.setVisible(true);
    }

    private void addActions(){
        CREATE_PERSONE.addActionListener(addPersone);
    }
}
