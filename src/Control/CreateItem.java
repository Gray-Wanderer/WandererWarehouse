package Control;

import Model.Item;
import Model.ItemType;

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
public class CreateItem implements ActionListener{
    AddItem addItem = new AddItem();
    SelectItemType selectItemType = new SelectItemType();

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame createItem = new JFrame();
        createItem.setTitle("Item parameters");

        createItem.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                createItem.dispose();
                addItem=null;
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

        createItem.setPreferredSize(new Dimension(300,200));
        createItem.setSize(300,200);
        createItem.pack();
        createItem.setLocationRelativeTo(null);
        createItem.setContentPane(createItemPane);
        addActions();

        createItem.setVisible(true);
    }

    private void addActions(){
        BOX_ITEM.addActionListener(selectItemType);
        CREATE_ITEM.addActionListener(addItem);
    }
}
