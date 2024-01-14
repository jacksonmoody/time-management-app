
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Assignment {

    //The below constants can be customized to modify the program's scheduling behavoir
    private static final int HIGH_PRIORITY_WEIGHT = 100;
    private static final int MEDIUM_PRIORITY_WEIGHT = 50;
    private static final int LOW_PRIORITY_WEIGHT = 10;
    private static final int DAYS_AWAY_WEIGHT = 31;
    private static final double MINUTES_WEIGHT = 1;

    private int id;
    private String name;
    private int time;
    private String priority;
    private String type;
    private CalendarDate date;
    private boolean complete;

    public Assignment(int id, String name, int time, String priority, String type, int month, int day, int year) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.priority = priority;
        this.type = type;
        date = new CalendarDate();
        date.setDate(month, day, year);
        complete = false;
    }

    public Assignment(int id, String name, int time, String priority, String type, int month, int day, int year, boolean complete) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.priority = priority;
        this.type = type;
        date = new CalendarDate();
        date.setDate(month, day, year);
        this.complete = complete;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public String getPriority() {
        return priority;
    }

    public String getType() {
        return type;
    }

    public CalendarDate getDate() {
        return date;
    }

    public boolean getComplete() {
        return complete;
    }

    public void subtractTime(int change) {
        time -= change;
    }

    public void setTime(int toSet) {
        time = toSet;
    }

    public void setStatus(boolean status) {
        complete = status;
    }

    public double getPriorityScore() {
        double scoreCalc1 = time * MINUTES_WEIGHT;

        int scoreCalc2 = 1;
        if (priority.equals("High")) {
            scoreCalc2 *= HIGH_PRIORITY_WEIGHT;

        } else if (priority.equals("Medium")) {
            scoreCalc2 *= MEDIUM_PRIORITY_WEIGHT;

        } else {//priority.equals("Low")
            scoreCalc2 *= LOW_PRIORITY_WEIGHT;
        }

        CalendarDate selectedDate = MainGUI.getCalendarDate();
        double scoreCalc3 = 1;

        LocalDate selectedDateCompare = CalendarDate.gregorianToLocalSelected(selectedDate);
        LocalDate dueDateCompare = CalendarDate.gregorianToLocalSelected(date);
        long noOfDaysBetween = ChronoUnit.DAYS.between(selectedDateCompare, dueDateCompare);
        if (noOfDaysBetween == 0) {
            scoreCalc3 = DAYS_AWAY_WEIGHT * 10; //Prioritize due date
        } else {
            if (noOfDaysBetween <= 0) {
                noOfDaysBetween = 1;
            }
            scoreCalc3 = DAYS_AWAY_WEIGHT / noOfDaysBetween;
        }

        double result = scoreCalc1 * scoreCalc2 * scoreCalc3;
        if (result > 0) {
            return result;
        } else {
            return 0;
        }
    }

    public String toString() {
        return "Name: " + name + ". Due: " + date.getSelectedMonth() + "/" + date.getSelectedDay() + "/" + date.getSelectedYear() + ". Priority: " + getPriorityScore();
    }

}
