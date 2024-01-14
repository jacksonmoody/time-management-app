
import java.awt.Component;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class MarkDone extends javax.swing.JDialog {

    Connection con = MainGUI.getConnection();
    ResultSet rs = MainGUI.getResultSet();
    Statement stmt = MainGUI.getStatement();
    ArrayList<Integer> added = new ArrayList<Integer>();
    MainGUI caller;
    Calculations academic;
    Calculations extracurricular;
    Calculations miscellaneous;
    ArrayList<Assignment> allActivities;
    ArrayList<Integer> allWorkedOn;
    int numOfFields; 
    JTextField textFields[];
    JLabel labels[];

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        textFields = new JTextField[numOfFields];
        labels = new JLabel[numOfFields];
        for (int i = 0; i < numOfFields; i++) {
            labels[i] = new JLabel(allActivities.get(i).getName() + " - " + allActivities.get(i).getType() + " Activity");
            labels[i].setHorizontalAlignment(JLabel.CENTER);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridx = 0;
            c.gridy = i;
            panel.add(labels[i], c);    

            textFields[i] = new JTextField();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridx = 1;
            c.gridy = i;
            c.ipadx = 40;
            panel.add(textFields[i], c);    
        }
        panel.setBackground(Color.white);
        jScrollPane1 = new JScrollPane(panel);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());

        jLabel1 = new javax.swing.JLabel();
        doneButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mark Done");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mark Activity Progress");

        doneButton.setBackground(new java.awt.Color(102, 255, 102));
        doneButton.setText("Done");
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Input the amount of time (in minutes) that you worked on each assignment today");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(178, 178, 178))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(doneButton)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                //.addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doneButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public MarkDone(java.awt.Frame parent, boolean modal, MainGUI caller) {
        super(parent, modal);
        this.caller = caller;
        academic = caller.getCalculation("academic");
        extracurricular = caller.getCalculation("extracurricular");
        miscellaneous = caller.getCalculation("miscellaneous");
        allActivities = academic.getWorkedOn();
        allActivities.addAll(extracurricular.getWorkedOn());
        allActivities.addAll(miscellaneous.getWorkedOn());

        allWorkedOn = academic.getWorkedTimes();
        allWorkedOn.addAll(extracurricular.getWorkedTimes());
        allWorkedOn.addAll(miscellaneous.getWorkedTimes());

        numOfFields = allActivities.size();
        initComponents();
        getContentPane().setBackground(new java.awt.Color(255, 255, 255));
    }

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed

        try {

            for (int i = 0; i < allActivities.size(); i++) {

                String query = "SELECT * FROM Events WHERE ID = " + allActivities.get(i).getID();
                Statement stmt2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = stmt2.executeQuery(query);
                rs.next();
                if (!textFields[i].getText().equals("")){
                int newTime = rs.getInt("Estimated_Time") - Integer.parseInt(textFields[i].getText());
                if (newTime <= 0) {
                    rs.updateBoolean("Status", true);
                    rs.updateInt("Estimated_Time", 0);
                }
                rs.updateInt("Estimated_Time", newTime);
                rs.updateRow();
            }

            }

        } catch (Exception err) {
            //JOptionPane.showMessageDialog(this, "Error with data input: " + err.getMessage());
        }

        //System.out.println(allActivities);
        //System.out.println(allWorkedOn);

        CalendarDate date = new CalendarDate();
        caller.refreshCalendar(date.getSelectedMonth(), date.getSelectedYear());

        Component component = (Component) evt.getSource();
        JDialog dialog = (JDialog) javax.swing.SwingUtilities.getRoot(component);
        dialog.dispose();
    }//GEN-LAST:event_doneButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton doneButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private JPanel panel;
    // End of variables declaration//GEN-END:variables
}
