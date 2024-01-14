
import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Calculations {

    private ArrayList<Assignment> workedOn;
    private ArrayList<Assignment> pastAndFutureOverdue;
    private ArrayList<Integer> workedTimes;

    public Calculations() {
        workedOn = new ArrayList<Assignment>();
        workedTimes = new ArrayList<Integer>();
        pastAndFutureOverdue = new ArrayList<Assignment>();
    }

    public static ArrayList<Assignment> searchEvents(String sql) {
        //SQL of form "WHERE ____= ____"
        ArrayList<Assignment> toSend = new ArrayList<Assignment>();
        try {
            Connection con = MainGUI.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM Events " + sql);
            rs.next();
            int lastID = rs.getInt(1);

            String SQL2 = "SELECT * FROM Events " + sql;
            Statement stmt2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs2 = stmt2.executeQuery(SQL2);

            if (rs2.first()) {

                for (int id = 1; id <= lastID; id++) {
                    Assignment toAdd = new Assignment(rs2.getInt("ID"), rs2.getString("Assignment_Name"), rs2.getInt("Estimated_Time"), rs2.getString("Priority_Level"), rs2.getString("Event_Type"), rs2.getInt("Due_Month"), rs2.getInt("Due_Day"), rs2.getInt("Due_Year"), rs2.getBoolean("Status"));
                    toSend.add(toAdd);
                    if (rs2.next()) {
                    } else {
                        rs2.previous();
                    }
                }
            }
            return toSend;
        } catch (SQLException err) {
            System.out.println("SQL Error: " + err);
            return toSend;
        }
    }

    public static ArrayList<Assignment> sortNearFar(ArrayList<Assignment> passedList) {

        ArrayList<Assignment> list = new ArrayList<Assignment>(passedList);

        int smallestIndex = 0;

        for (int i = 0; i < list.size() - 1; i++) {
            smallestIndex = i;

            for (int j = i + 1; j < list.size(); j++) {
                LocalDate first = CalendarDate.gregorianToLocalSelected(list.get(j).getDate());
                LocalDate second = CalendarDate.gregorianToLocalSelected(list.get(smallestIndex).getDate());
                if (first.isBefore(second)) {
                    smallestIndex = j;
                }
            }

            Assignment temp = list.get(i);
            list.set(i, list.get(smallestIndex));
            list.set(smallestIndex, temp);
        }

        return list;
    }

    public static ArrayList<Assignment> sortFarNear(ArrayList<Assignment> list) {

        ArrayList<Assignment> original = sortNearFar(list);
        ArrayList<Assignment> reversed = new ArrayList<Assignment>();

        for (int i = original.size() - 1; i >= 0; i--) {

            reversed.add(original.get(i));
        }

        return reversed;
    }

    public static ArrayList<Assignment> sortPriority(ArrayList<Assignment> passedList) {
        ArrayList<Assignment> list = new ArrayList<Assignment>(passedList);

        int largestIndex = 0;

        for (int i = 0; i < list.size() - 1; i++) {
            largestIndex = i;

            for (int j = i + 1; j < list.size(); j++) {
                double first = list.get(j).getPriorityScore();
                double second = list.get(largestIndex).getPriorityScore();
                if (first > second) {
                    largestIndex = j;
                }
            }

            Assignment temp = list.get(i);
            list.set(i, list.get(largestIndex));
            list.set(largestIndex, temp);
        }

        return list;
    }

    public static String findOverdueHtml() {
        String html = "";
        ArrayList<Assignment> allAssignments = searchEvents("");
        ArrayList<Assignment> overdue = new ArrayList<Assignment>();
        //System.out.println(overdue);
        for (int i = 0; i < allAssignments.size(); i++) {
            if (CalendarDate.gregorianToLocalSelected(allAssignments.get(i).getDate()).isBefore(CalendarDate.gregorianToLocalSelected(MainGUI.getCalendarDate())) && !allAssignments.get(i).getComplete()) {
                overdue.add(allAssignments.get(i));
            }
        }

        if (overdue.size() >= 1) {
            html += "<h2><span style=\"color: #ff0000;\">Overdue Assignments:</span></h2>";
            ArrayList<Assignment> overdueSorted = Calculations.sortNearFar(overdue);

            for (int i = 0; i < overdueSorted.size(); i++) {
                html += "<li>" + overdueSorted.get(i).getName() + "</li>";
            }
            html += ("</ul>");
        } else {
            //System.out.println("No overdue assignments");
            html += "<h2>No Overdue Assignments!</h2>";
        }
        return html;
    }

    public static ArrayList<Assignment> findOverdueList(String sql) {
        String html = "";
        ArrayList<Assignment> allAssignments = searchEvents(sql);
        ArrayList<Assignment> overdue = new ArrayList<Assignment>();
        //System.out.println(overdue);
        for (int i = 0; i < allAssignments.size(); i++) {
            if (CalendarDate.gregorianToLocalSelected(allAssignments.get(i).getDate()).isBefore(CalendarDate.gregorianToLocalSelected(MainGUI.getCalendarDate())) && !allAssignments.get(i).getComplete()) {
                overdue.add(allAssignments.get(i));
            }
        }
        return overdue;
    }

    public static String findDueOnDateHtml(CalendarDate dueDate) {
        String html = "";
        String SQL = "WHERE Due_Month = " + (dueDate.getSelectedMonth()) + " AND Due_Day = " + dueDate.getSelectedDay() + " AND Due_Year = " + dueDate.getSelectedYear();
        ArrayList<Assignment> dueToday = searchEvents(SQL);
        if (dueToday.size() >= 1) {
            html += "<h2>Assignments Due Today:</h2><ul>";
            ArrayList<Assignment> dueTodaySorted = Calculations.sortPriority(dueToday);

            for (int i = 0; i < dueTodaySorted.size(); i++) {
                if (dueTodaySorted.get(i).getComplete()) {
                    html += "<li>" + dueTodaySorted.get(i).getName() + " - <span style=\"color: #00ff00;\">Complete</span></li>";
                } else {
                    html += "<li>" + dueTodaySorted.get(i).getName() + " - " + "<strong>" + dueTodaySorted.get(i).getTime() + "</strong> minutes remaining</li>";
                }
            }
            html += ("</ul>");
        } else {
            //System.out.println("No assignments due");
            html += "<h2>No More Assignments Due Today!</h2>";
        }
        return html;
    }

    public static ArrayList<Assignment> findDueOnDateList(CalendarDate dueDate) {
        String SQL = "WHERE Due_Month = " + (dueDate.getSelectedMonth()) + " AND Due_Day = " + dueDate.getSelectedDay() + " AND Due_Year = " + dueDate.getSelectedYear();
        ArrayList<Assignment> dueToday = searchEvents(SQL);
        if (dueToday.size() >= 1) {
            ArrayList<Assignment> dueTodaySorted = Calculations.sortPriority(dueToday);
            return dueTodaySorted;
        } else {
            return new ArrayList<Assignment>();
        }
    }

    public static String findMajorTasks(CalendarDate start, CalendarDate end) {
        String html = "";
        ArrayList<Assignment> allEvents = searchEvents("WHERE Priority_Level = 'High'");
        html += "<h2>Major Tasks Due:</h2>";
        ArrayList<Assignment> majorTasks = new ArrayList<Assignment>();
        for (int i = 0; i < allEvents.size(); i++) {
            CalendarDate event = allEvents.get(i).getDate();
            if (CalendarDate.gregorianToLocalSelected(event).isAfter(CalendarDate.gregorianToLocalSelected(start)) && CalendarDate.gregorianToLocalSelected(event).isBefore(CalendarDate.gregorianToLocalSelected(end))) {
                majorTasks.add(allEvents.get(i));
            }
        }
        if (majorTasks.size() >= 1) {
            html += "<ul>";
            majorTasks = sortNearFar(majorTasks);
            for (int i = 0; i < majorTasks.size(); i++) {
                html += "<li>" + majorTasks.get(i).getName() + " - " + "<strong>" + majorTasks.get(i).getTime() + "</strong> minutes remaining</li>";
            }
            html += ("</ul>");
        } else {
            html += "<p>No Major Tasks Due!</p><br>";
        }

        return html;

    }

    public static String findMinorTasks(CalendarDate start, CalendarDate end) {

        String html = "";
        ArrayList<Assignment> allEvents = searchEvents("WHERE Priority_Level = 'Low' OR Priority_Level = 'Medium'");
        html += "<h2>Minor Tasks Due:</h2>";
        ArrayList<Assignment> minorTasks = new ArrayList<Assignment>();
        for (int i = 0; i < allEvents.size(); i++) {
            CalendarDate event = allEvents.get(i).getDate();
            if (CalendarDate.gregorianToLocalSelected(event).isAfter(CalendarDate.gregorianToLocalSelected(start)) && CalendarDate.gregorianToLocalSelected(event).isBefore(CalendarDate.gregorianToLocalSelected(end))) {
                minorTasks.add(allEvents.get(i));
            }
        }
        if (minorTasks.size() >= 1) {
            minorTasks = sortNearFar(minorTasks);
            html += "<ul>";
            for (int i = 0; i < minorTasks.size(); i++) {
                html += "<li>" + minorTasks.get(i).getName() + " - " + "<strong>" + minorTasks.get(i).getTime() + "</strong> minutes remaining</li>";
            }
            html += ("</ul>");
        } else {
            html += "<p>No Minor Tasks Due!</p><br>";
        }
        return html;
    }

    public static int getTimeAllocation(String type) {
        int toReturn = 0;
        try {
            Connection con = MainGUI.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM Settings");
            rs.next();

            if (type.equals("Academic")) {
                toReturn = rs.getInt("Time_Academic");
            } else if (type.equals("Extracurricular")) {
                toReturn = rs.getInt("Time_Extracurricular");
            } else { //type.equals("Miscellaneous")
                toReturn = rs.getInt("Time_Miscellaneous");
            }
        } catch (SQLException err) {
            System.out.println("Error getting preferences from database: " + err);
        }
        return toReturn;
    }

    public static long calculateTimeBetween(String type, CalendarDate start, CalendarDate end) {
        int dailyAllocation = getTimeAllocation(type);
        LocalDate startLocal = CalendarDate.gregorianToLocalSelected(start);
        LocalDate endLocal = CalendarDate.gregorianToLocalSelected(end);
        long noOfDaysBetween = ChronoUnit.DAYS.between(startLocal, endLocal);
        long toReturn = Math.abs(dailyAllocation * noOfDaysBetween);
        return toReturn;
    }

    public static int calculateTimeSpent(String type, CalendarDate start, CalendarDate end) {
        int timeSpent = 0;
        ArrayList<Assignment> allEvents = searchEvents("WHERE Event_Type = '" + type + "'");
        if (allEvents.size() > 0) {
            for (int i = 0; i < allEvents.size(); i++) {
                CalendarDate event = allEvents.get(i).getDate();
                if (CalendarDate.gregorianToLocalSelected(event).isAfter(CalendarDate.gregorianToLocalSelected(start)) && CalendarDate.gregorianToLocalSelected(event).isBefore(CalendarDate.gregorianToLocalSelected(end))) {
                    timeSpent += allEvents.get(i).getTime();
                }
            }
        }
        return timeSpent;
    }

    public String calculateWork(String type, String timeFrame) {
        //System.out.println("Calculation start");
        workedOn.clear();
        workedTimes.clear();

        int timeRemaining = 0;
        String html = "<ul>";

        timeRemaining = getTimeAllocation(type);

        String SQL = "WHERE Event_Type = '" + type + "' AND Status = false";
        ArrayList<Assignment> allEvents = searchEvents(SQL);
        ArrayList<Assignment> overdue = findOverdueList(SQL);
        ArrayList<Assignment> dueToday = findDueOnDateList(MainGUI.getCalendarDate());
        ArrayList<Assignment> toComplete = new ArrayList<Assignment>();

        for (int i = 0; i < allEvents.size(); i++) {
            if (CalendarDate.gregorianToLocalSelected(allEvents.get(i).getDate()).isAfter(CalendarDate.gregorianToLocalSelected(MainGUI.getCalendarDate())) && !allEvents.get(i).getComplete() && !(allEvents.get(i).getPriorityScore() == 0)) {
                toComplete.add(allEvents.get(i));
            }
        }

        for (int i = 0; i < dueToday.size(); i++) {
            if (!dueToday.get(i).getComplete() && dueToday.get(i).getType().equals(type)) {
                toComplete.add(dueToday.get(i));
            }
        }

        if (allEvents.size() > 0) {

            int i = 0;
            while (timeRemaining > 0 && i < overdue.size()) {
                if (overdue.size() > 0) {
                    //System.out.println("Overdue: " + overdue);
                    if ((timeRemaining - overdue.get(i).getTime()) > 0) {
                        timeRemaining -= overdue.get(i).getTime();
                        html += "<li>" + overdue.get(i).getName() + " - " + "<strong>Work Until Complete: </strong>" + overdue.get(i).getTime() + " minutes </li>";
                        //System.out.println("Finished (overdue) " + overdue.get(i).getName());
                        workedOn.add(overdue.get(i));
                        workedTimes.add(overdue.get(i).getTime());
                        i++;
                    } else {
                        html += "<li>" + overdue.get(i).getName() + " - " + "<strong>" + timeRemaining + "</strong> minutes</li>";
                        workedOn.add(overdue.get(i));
                        workedTimes.add(timeRemaining);
                        //System.out.println("Worked on but didn't finish (overdue) " + overdue.get(i).getName());
                        timeRemaining = 0;
                    }
                }
            }
            ArrayList<Assignment> toCompletePrioritized = sortPriority(toComplete);
            int j = 0;
            if (toCompletePrioritized.size() > 0) {
                //System.out.println("To complete: " + toCompletePrioritized);
                while (timeRemaining > 0 && j < toCompletePrioritized.size()) {
                    if ((timeRemaining - toCompletePrioritized.get(j).getTime()) > 0) {
                        timeRemaining -= toCompletePrioritized.get(j).getTime();
                        html += "<li>" + toCompletePrioritized.get(j).getName() + " - " + "<strong>Work Until Complete: </strong>" + toCompletePrioritized.get(j).getTime() + " minutes </li>";
                        //System.out.println("Finished " + toCompletePrioritized.get(j).getName());
                        workedOn.add(toCompletePrioritized.get(j));
                        workedTimes.add(toCompletePrioritized.get(j).getTime());
                        j++;
                    } else {
                        html += "<li>" + toCompletePrioritized.get(j).getName() + " - " + "<strong>" + timeRemaining + "</strong> minutes</li>";
                        //System.out.println("Worked on but didn't finish " + toCompletePrioritized.get(j).getName());
                        workedOn.add(toCompletePrioritized.get(j));
                        workedTimes.add(timeRemaining);
                        timeRemaining = 0;
                    }
                }
            } 
         System.out.println(toCompletePrioritized);
        } 
        if (overdue.size() == 0 && toComplete.size() == 0) {
            html += "No assignments!";
        }
        html += ("</ul>");
        return html;
    }

    public ArrayList<Assignment> getWorkedOn() {
        return workedOn;
    }

    public ArrayList<Integer> getWorkedTimes() {
        return workedTimes;
    }

    public ArrayList<Assignment> getPastAndFutureOverdue() {
        return pastAndFutureOverdue;
    }

    public String toString() {
        return "Worked on: " + workedOn;
    }
}
