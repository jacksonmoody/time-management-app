import java.awt.Component;
import javax.swing.JCheckBox;

public class CheckboxRenderer extends JCheckBox implements javax.swing.ListCellRenderer {

    public Component getListCellRendererComponent(javax.swing.JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        setComponentOrientation(list.getComponentOrientation());
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setEnabled(list.isEnabled());
        setSelected(isSelected);
        setText(value.toString());  

        return this;
    }
}

