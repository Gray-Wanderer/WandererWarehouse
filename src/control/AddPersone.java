package control;

import model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static control.DataList.personeList;
import static view.Panes.*;

/**
 * Created by Алена on 28.11.2017.
 */
public class AddPersone implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        //PERSONE_LIST.clearSelection();
        Person person = new Person(PERSONE_NAME_TEXTFIELD.getText(), PERSONE_SURNAME_TEXTFIELD.getText());
        personeList.add(person);
        System.out.println(personeList.toString());
        PERSONE_LIST.setListData(personeList.toArray());
        PERSONE_NAME_TEXTFIELD.setText("");
        PERSONE_SURNAME_TEXTFIELD.setText("");
        JOptionPane.showMessageDialog(null, "Success", "Creating done", JOptionPane.PLAIN_MESSAGE);
    }
}
