
import java.awt.Color;

public class CalendarRenderer extends javax.swing.JList<String> implements javax.swing.table.TableCellRenderer {

    private int selectedColumn, selectedRow;
    private CalendarDate date;

    public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        //super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        //System.out.println("Selected column: " + selectedColumn + " Selected row: " + selectedRow);
        //System.out.println("Rendered column: " + column + " Rendered row: " + row);

        date = MainGUI.getCalendarDate();
        selectedColumn = MainGUI.getSelectedColumn();
        selectedRow = MainGUI.getSelectedRow();
        
        if (value instanceof String[]) {
            setListData((String[]) value);
        }

        if (selectedColumn == column && selectedRow == row) {
            setBackground(new Color(255, 220, 220));
        } else {
            setBackground(new Color(255, 255, 255));
        }

        try {
            String[] content = (String[]) value;
            try {
                int numberValue = Integer.parseInt(content[0]);
                if (numberValue == date.getRealDay() && (date.getSelectedMonth() - 1) == date.getRealMonth() && date.getSelectedYear() == date.getRealYear()) { //Today
                    setBackground(new Color(220, 220, 255));
                }
            } catch (NumberFormatException e) {
            }
        } catch (NullPointerException e) {
        }

        setBorder(null);
        setForeground(Color.black);
        return this;
    }
}
