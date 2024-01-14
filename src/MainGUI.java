
import java.util.*;
import java.awt.Color;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class MainGUI extends javax.swing.JFrame {

    private static CalendarDate date = new CalendarDate();
    private static int selectedRow = 100;
    private static int selectedColumn = 100;
    private static int mode = 0; //0 = day 1 = week 2 = month
    private CalendarRenderer myRenderer;
    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private GregorianCalendar cal;

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static int lastID = 0;

    private static Calculations academic = new Calculations();
    private static Calculations extracurricular = new Calculations();
    private static Calculations miscellaneous = new Calculations();

    public MainGUI() {
        initComponents();
        connect();
        customInitiation();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        calendar = new javax.swing.JTable();
        calendarBack = new javax.swing.JButton();
        calendarForward = new javax.swing.JButton();
        monthName = new javax.swing.JLabel();
        yearSelector = new javax.swing.JComboBox<>();
        todayButton = new javax.swing.JButton();
        displayPanel = new javax.swing.JPanel();
        addEventButton = new javax.swing.JButton();
        modeDisplay = new javax.swing.JLabel();
        dateDisplay = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        weekSelect = new javax.swing.JButton();
        monthSelect = new javax.swing.JButton();
        daySelect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextPane();
        removeEventButton = new javax.swing.JButton();
        markDoneButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        settingsButton = new javax.swing.JButton("");
        String iconfilePath = this.getClass().getClassLoader().getResource("settingsIcon.png").getFile();
        settingsButton.setIcon(new javax.swing.ImageIcon(iconfilePath));
        settingsButton.setBounds(10, 438, 39, 31);
        settingsButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        settingsButton.setContentAreaFilled(false);
        settingsButton.setFocusable(false)
        ;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Time Manager");
        setResizable(false);
        setSize(new java.awt.Dimension(560, 520));

        calendar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        calendar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        calendar.setAutoscrolls(false);
        calendar.setCellSelectionEnabled(true);
        calendar.setFillsViewportHeight(true);
        calendar.setRowHeight(85);
        calendar.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        calendar.setGridColor(Color.black); calendar.setShowGrid(true);
        calendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calendarMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(calendar);

        calendarBack.setText("<<");
        calendarBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calendarBackActionPerformed(evt);
            }
        });

        calendarForward.setText(">>");
        calendarForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calendarForwardActionPerformed(evt);
            }
        });

        monthName.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        monthName.setText("January 2021");

        yearSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearSelectorActionPerformed(evt);
            }
        });

        todayButton.setText("Today");
        todayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todayButtonActionPerformed(evt);
            }
        });

        displayPanel.setBackground(new java.awt.Color(255, 255, 255));

        addEventButton.setBackground(new java.awt.Color(153, 255, 102));
        addEventButton.setText("Add Activity");
        addEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventButtonActionPerformed(evt);
            }
        });

        modeDisplay.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        modeDisplay.setText("Day");

        dateDisplay.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        dateDisplay.setText("Date");
        dateDisplay.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        weekSelect.setBackground(new java.awt.Color(255, 255, 255));
        weekSelect.setText("Week");
        weekSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weekSelectActionPerformed(evt);
            }
        });

        monthSelect.setBackground(new java.awt.Color(255, 255, 255));
        monthSelect.setText("Month");
        monthSelect.setToolTipText("");
        monthSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthSelectActionPerformed(evt);
            }
        });

        daySelect.setBackground(new java.awt.Color(255, 255, 255));
        daySelect.setText("Day");
        daySelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daySelectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(daySelect, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(weekSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(monthSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(daySelect)
                    .addComponent(weekSelect)
                    .addComponent(monthSelect))
                .addContainerGap())
        );

        output.setEditable(false);
        output.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(output);

        removeEventButton.setBackground(new java.awt.Color(255, 102, 102));
        removeEventButton.setText("Remove Activity");
        removeEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeEventButtonActionPerformed(evt);
            }
        });

        markDoneButton.setBackground(new java.awt.Color(0, 153, 255));
        markDoneButton.setText("Mark Progress");
        markDoneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markDoneButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(removeEventButton)
                        .addGap(18, 18, 18)
                        .addComponent(addEventButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(markDoneButton)
                        .addGap(76, 76, 76))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addComponent(modeDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dateDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modeDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateDisplay))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(displayPanelLayout.createSequentialGroup()
                            .addComponent(markDoneButton)
                            .addGap(35, 35, 35))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                            .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)))
                    .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(removeEventButton)
                        .addComponent(addEventButton))))
        );

        settingsButton.setText(null);
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(monthName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(settingsButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(calendarBack)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(calendarForward)
                                .addGap(252, 252, 252)
                                .addComponent(todayButton)
                                .addGap(259, 259, 259)
                                .addComponent(yearSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(settingsButton)
                    .addComponent(monthName))
                .addGap(24, 24, 24)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calendarBack)
                    .addComponent(calendarForward)
                    .addComponent(todayButton)
                    .addComponent(yearSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void connect() {
        try {
            String host = "jdbc:derby://localhost:1527/Events";
            String username = "Jackson";
            String password = "password";
            con = DriverManager.getConnection(host, username, password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String SQL = "SELECT * FROM Events";
            rs = stmt.executeQuery(SQL);

            rs.next();

        } catch (SQLException error) {
            System.out.println("Error. Unable to connect to the database.");
            System.exit(0);
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public static Statement getStatement() {
        return stmt;
    }

    public static ResultSet getResultSet() {
        return rs;
    }

    public static int getLastID() {
        return lastID;
    }

    public static CalendarDate getCalendarDate() {
        CalendarDate toReturn = new CalendarDate();
        toReturn.setDate(date.getSelectedMonth() + 1, date.getSelectedDay(), date.getSelectedYear());
        return toReturn;
    }

    public static int getSelectedRow() {
        return selectedRow;
    }

    public static int getSelectedColumn() {
        return selectedColumn;
    }

    public static Calculations getCalculation(String type) {
        if (type.equals("academic")) {
            return academic;
        } else if (type.equals("extracurricular")) {
            return extracurricular;
        } else {
            return miscellaneous;
        }
    }

    private void calendarBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calendarBackActionPerformed

        if (date.getSelectedMonth() == 0) { //Back one year
            date.setDateMonth(11);
            date.setDateYear(date.getSelectedYear() - 1);
        } else { //Back one month
            date.setDateMonth(date.getSelectedMonth() - 1);
        }
        selectedRow = 100;
        selectedColumn = 100;
        markDoneButton.setEnabled(false);
        refreshCalendar(date.getSelectedMonth(), date.getSelectedYear());
    }//GEN-LAST:event_calendarBackActionPerformed

    private void calendarForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calendarForwardActionPerformed
        if (date.getSelectedMonth() == 11) { //Foward one year
            date.setDateMonth(0);
            date.setDateYear(date.getSelectedYear() + 1);
        } else { //Foward one month
            date.setDateMonth(date.getSelectedMonth() + 1);
        }
        selectedRow = 100;
        selectedColumn = 100;
        markDoneButton.setEnabled(false);
        refreshCalendar(date.getSelectedMonth(), date.getSelectedYear());
    }//GEN-LAST:event_calendarForwardActionPerformed

    private void yearSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearSelectorActionPerformed
        //if (yearSelector.getSelectedItem() != null) {
        String b = yearSelector.getSelectedItem().toString();
        date.setDateYear(Integer.parseInt(b));
        //selectedRow = 100;
        //selectedColumn = 100;
        markDoneButton.setEnabled(false);
        refreshCalendar(date.getSelectedMonth(), date.getSelectedYear());
        //}
    }//GEN-LAST:event_yearSelectorActionPerformed

    private void calendarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendarMouseClicked

        selectedRow = calendar.getSelectedRow();
        selectedColumn = calendar.getSelectedColumn();
        date.setDate(selectedRow, selectedColumn, cal.get(GregorianCalendar.DAY_OF_WEEK), true);

        if (evt.getClickCount() % 2 == 0) {
            //System.out.println("Double clicked");
            //String[] content = {Integer.toString(date.getSelectedDay()), "Test Event"};
            //calendar.setValueAt(content, selectedRow, selectedColumn);
            CalendarDate inputDate = new CalendarDate();
            inputDate.setDateMonth(date.getSelectedMonth());
            inputDate.setDateYear(date.getSelectedYear());
            inputDate.setDate(selectedRow, selectedColumn, cal.get(GregorianCalendar.DAY_OF_WEEK), true);
            InputAssignment calendarDialog = new InputAssignment(new javax.swing.JFrame(), true, inputDate, this);
            calendarDialog.setVisible(true);
        }

        //refreshHeader();
        refreshCalendar(date.getSelectedMonth(), date.getSelectedYear());
    }//GEN-LAST:event_calendarMouseClicked

    private void todayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todayButtonActionPerformed
        date = new CalendarDate();
        selectedRow = 100;
        selectedColumn = 100;
        refreshCalendar(date.getSelectedMonth(), date.getSelectedYear());
    }//GEN-LAST:event_todayButtonActionPerformed

    private void addEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventButtonActionPerformed
        InputAssignment dialog = new InputAssignment(new javax.swing.JFrame(), true, this);
        dialog.setVisible(true);
    }//GEN-LAST:event_addEventButtonActionPerformed

    private void daySelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daySelectActionPerformed
        mode = 0;
        refreshHeader();
        refreshDisplayBox();
    }//GEN-LAST:event_daySelectActionPerformed

    private void weekSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weekSelectActionPerformed
        mode = 1;
        refreshHeader();
        refreshDisplayBox();
    }//GEN-LAST:event_weekSelectActionPerformed

    private void monthSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthSelectActionPerformed
        mode = 2;
        refreshHeader();
        refreshDisplayBox();
    }//GEN-LAST:event_monthSelectActionPerformed

    private void removeEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeEventButtonActionPerformed
        RemoveAssignment dialog = new RemoveAssignment(new javax.swing.JFrame(), true, this);
        dialog.setVisible(true);
    }//GEN-LAST:event_removeEventButtonActionPerformed

    private void markDoneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markDoneButtonActionPerformed
        MarkDone dialog = new MarkDone(new javax.swing.JFrame(), true, this);
        dialog.setVisible(true);
    }//GEN-LAST:event_markDoneButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        Settings dialog = new Settings(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_settingsButtonActionPerformed

    private void refreshHeader() {
        switch (mode) {
            case 0:
                modeDisplay.setText("Day:");
                dateDisplay.setText(date.getSelectedMonth() + 1 + " / " + date.getSelectedDay() + " / " + date.getSelectedYear());
                //System.out.println("Selected date: " + (date.getSelectedMonth() + 1) + " / " + date.getSelectedDay() + " / " + date.getSelectedYear());
                break;
            case 1:
                modeDisplay.setText("Week of:");
                dateDisplay.setText(date.getSelectedMonth() + 1 + " / " + date.getSelectedDay() + " / " + date.getSelectedYear());
                break;
            case 2:
                modeDisplay.setText("Month of:");
                dateDisplay.setText(MONTHS[date.getSelectedMonth()]);
                break;
        }
    }

    private void refreshDisplayBox() {
        //TODO: Automatically assign today, future, or past based on current date and selected day
        String html = "<html>";
        String view = "";
        switch (mode) {
            case 0: //Day
                String overdue = Calculations.findOverdueHtml();
                String dueToday = Calculations.findDueOnDateHtml(getCalendarDate());

                if (date.getRealDay() == date.getSelectedDay() && date.getRealMonth() == date.getSelectedMonth() && date.getRealYear() == date.getSelectedYear()) {
                    view = "Today";
                    markDoneButton.setEnabled(true);
                } else if (CalendarDate.gregorianToLocalSelected(getCalendarDate()).isBefore(CalendarDate.gregorianToLocalReal(getCalendarDate()))) {
                    markDoneButton.setEnabled(false);
                } else if (CalendarDate.gregorianToLocalSelected(getCalendarDate()).isAfter(CalendarDate.gregorianToLocalReal(getCalendarDate()))) {
                    view = "Future";
                    markDoneButton.setEnabled(false);
                }

                String academicWork = academic.calculateWork("Academic", view);
                String extracurricularWork = extracurricular.calculateWork("Extracurricular", view);
                String miscellaneousWork = miscellaneous.calculateWork("Miscellaneous", view);
                output.setText(html + overdue + dueToday + "<h1>Recommended Schedule:</h1>" + "<h3><span style=\"color: #999999;\">Academic</span></h3>" + academicWork + "<h3><span style=\"color: #999999;\">Extracurricular</span></h3>" + extracurricularWork + "<h3><span style=\"color: #999999;\">Miscellaneous</span></h3>" + miscellaneousWork);
                output.setCaretPosition(0);
                break;

            case 1: //Week
                refreshDisplayWeekMonth("Week");
                modeDisplay.setText("Week of:");
                dateDisplay.setText(date.getSelectedMonth() + 1 + " / " + date.getSelectedDay() + " / " + date.getSelectedYear());
                break;
            case 2: //Month
                refreshDisplayWeekMonth("Month");
                modeDisplay.setText("Month of:");
                dateDisplay.setText(MONTHS[date.getSelectedMonth()]);
                break;
        }
    }

    private void refreshDisplayWeekMonth(String displayType) {
        String html = "<html>";
        CalendarDate date1 = new CalendarDate();
        CalendarDate date2 = new CalendarDate();
        date1.setDate(date.getSelectedMonth() + 1, date.getSelectedDay() - 1, date.getSelectedYear());
        if (displayType.equals("Week")) {
            date2.setDate(date.getSelectedMonth() + 1, date.getSelectedDay() + 8, date.getSelectedYear());
        } else if (displayType.equals("Month")) {
            date2.setDate(date.getSelectedMonth() + 2, date.getSelectedDay() - 1, date.getSelectedYear());
        }
        html += Calculations.findMajorTasks(date1, date2);
        html += Calculations.findMinorTasks(date1, date2);

        html += "<h2>Academic Time</h2>";
        int academicTimeSpent = Calculations.calculateTimeSpent("Academic", date1, date2);
        int academicTimeRemaining = (int) Calculations.calculateTimeBetween("Academic", date1, date2) - academicTimeSpent;
        String hours1 = String.valueOf(academicTimeSpent / 60);
        String minutes1 = String.valueOf(academicTimeSpent % 60);
        String hours2 = String.valueOf(academicTimeRemaining / 60);
        String minutes2 = String.valueOf(academicTimeRemaining % 60);
        if (minutes1.equals("0")) {
            minutes1 = "00";
        }
        if (minutes2.equals("0")) {
            minutes2 = "00";
        }
        html += "<p><strong>Busy:</strong> " + hours1 + ":" + minutes1 + " | <strong>Free:</strong> " + hours2 + ":" + minutes2 + "</p><br>";
        html += "<h2>Extracurricular Time</h2>";
        int extracurricularTimeSpent = Calculations.calculateTimeSpent("Extracurricular", date1, date2);
        int extracurricularTimeRemaining = (int) Calculations.calculateTimeBetween("Extracurricular", date1, date2) - extracurricularTimeSpent;
        hours1 = String.valueOf(extracurricularTimeSpent / 60);
        minutes1 = String.valueOf(extracurricularTimeSpent % 60);
        hours2 = String.valueOf(extracurricularTimeRemaining / 60);
        minutes2 = String.valueOf(extracurricularTimeRemaining % 60);
        if (minutes1.equals("0")) {
            minutes1 = "00";
        }
        if (minutes2.equals("0")) {
            minutes2 = "00";
        }
        html += "<p><strong>Busy:</strong> " + hours1 + ":" + minutes1 + " | <strong>Free:</strong> " + hours2 + ":" + minutes2 + "</p><br>";
        html += "<h2>Miscellaneous Time</h2>";
        int miscellaneousTimeSpent = Calculations.calculateTimeSpent("Miscellaneous", date1, date2);
        int miscellaneousTimeRemaining = (int) Calculations.calculateTimeBetween("Miscellaneous", date1, date2) - miscellaneousTimeSpent;
        hours1 = String.valueOf(miscellaneousTimeSpent / 60);
        minutes1 = String.valueOf(miscellaneousTimeSpent % 60);
        hours2 = String.valueOf(miscellaneousTimeRemaining / 60);
        minutes2 = String.valueOf(miscellaneousTimeRemaining % 60);
        if (minutes1.equals("0")) {
            minutes1 = "00";
        }
        if (minutes2.equals("0")) {
            minutes2 = "00";
        }
        html += "<p><strong>Busy:</strong> " + hours1 + ":" + minutes1 + " | <strong>Free:</strong> " + hours2 + ":" + minutes2 + "</p><br>";
        html += "<h1>Total Time</h1>";
        int totalTimeSpent = academicTimeSpent + extracurricularTimeSpent + miscellaneousTimeSpent;
        int totalTimeRemaining = ((int) Calculations.calculateTimeBetween("Academic", date1, date2) + (int) Calculations.calculateTimeBetween("Extracurricular", date1, date2) + (int) Calculations.calculateTimeBetween("Miscellaneous", date1, date2)) - totalTimeSpent;
        hours1 = String.valueOf(totalTimeSpent / 60);
        minutes1 = String.valueOf(totalTimeSpent % 60);
        hours2 = String.valueOf(totalTimeRemaining / 60);
        minutes2 = String.valueOf(totalTimeRemaining % 60);
        if (minutes1.equals("0")) {
            minutes1 = "00";
        }
        if (minutes2.equals("0")) {
            minutes2 = "00";
        }
        html += "<p><strong>Busy:</strong> " + hours1 + ":" + minutes1 + " | <strong>Free:</strong> " + hours2 + ":" + minutes2 + "</p><br>";
        output.setText(html);
        output.setCaretPosition(0);
    }

    private void customInitiation() {
        getContentPane().setBackground(new java.awt.Color(255, 255, 255));
        calendar.getTableHeader().setResizingAllowed(false);
        calendar.getTableHeader().setReorderingAllowed(false);

        for (int i = date.getRealYear() - 100; i <= date.getRealYear() + 100; i++) {
            yearSelector.addItem(String.valueOf(i));
        }
        myRenderer = new CalendarRenderer();
        calendar.setDefaultRenderer(calendar.getColumnClass(0), myRenderer);
        refreshCalendar(date.getRealMonth(), date.getRealYear());

    }

    public void refreshCalendar(int month, int year) {

        date.setDateMonth(month);
        date.setDateYear(year);

        try {
            String SQL = "select count(*) from Events";
            Statement stmt2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs2 = stmt2.executeQuery(SQL);
            rs2.next();
            lastID = rs2.getInt(1);

            String SQL2 = "SELECT * FROM Events";
            rs = stmt.executeQuery(SQL2);

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(this, error.getMessage());
        }

        cal = new GregorianCalendar(year, month, 1);
        int numberOfDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        int startOfMonth = cal.get(GregorianCalendar.DAY_OF_WEEK);

        //Clear table
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                String[] empty = {""};
                calendar.setValueAt(empty, i, j);
            }
        }
        monthName.setText(MONTHS[month] + " " + year);
        //jLabel1.setBounds(575-jLabel1.getPreferredSize().width/2, 25, 180, 25);
        yearSelector.setSelectedItem(String.valueOf(year));

        for (int i = 1; i <= numberOfDays; i++) {
            int row = new Integer((i + startOfMonth - 2) / 7);
            int column = (i + startOfMonth - 2) % 7;

            try {
                rs.first();
                String[] content = new String[100];
                content[0] = Integer.toString(i);
                int count = 0;

                for (int id = 1; id <= lastID; id++) {
                    int dueMonth = rs.getInt("Due_Month");
                    int dueDay = rs.getInt("Due_Day");
                    int dueYear = rs.getInt("Due_Year");

                    if (dueDay == i && dueMonth == month + 1 && dueYear == year) {
                        count++;
                        String assignmentName = rs.getString("Assignment_Name");
                        content[count] = (assignmentName);
                    }

                    if (count > 3) {
                        content[3] = "+ " + (count - 2) + " More";
                        content[4] = "";
                    }

                    calendar.setValueAt(content, row, column);

                    if (rs.next()) {
                    } else {
                        rs.previous();
                    }
                }

            } catch (SQLException err) {
                JOptionPane.showMessageDialog(this, err.getMessage());
            }
        }

        refreshHeader();
        refreshDisplayBox();
        calendar.repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEventButton;
    private javax.swing.JTable calendar;
    private javax.swing.JButton calendarBack;
    private javax.swing.JButton calendarForward;
    private javax.swing.JLabel dateDisplay;
    private javax.swing.JButton daySelect;
    private javax.swing.JPanel displayPanel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton markDoneButton;
    private javax.swing.JLabel modeDisplay;
    private javax.swing.JLabel monthName;
    private javax.swing.JButton monthSelect;
    private javax.swing.JTextPane output;
    private javax.swing.JButton removeEventButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton settingsButton;
    private javax.swing.JButton todayButton;
    private javax.swing.JButton weekSelect;
    private javax.swing.JComboBox<String> yearSelector;
    // End of variables declaration//GEN-END:variables
}
