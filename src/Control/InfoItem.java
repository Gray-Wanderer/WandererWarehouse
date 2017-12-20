package Control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Control.DataList.selectedItem;

/**
 * Created by Алена on 04.12.2017.
 */
public class InfoItem implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, selectedItem.info(), "Информация о снаряжении", JOptionPane.PLAIN_MESSAGE);
    }
}
