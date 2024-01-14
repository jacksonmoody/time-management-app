
import java.util.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class CalendarDate {

    private int realDay;
    private int realMonth;
    private int realYear;
    private int selectedDay;
    private int selectedMonth;
    private int selectedYear;

    public CalendarDate() { //Create calendar
        GregorianCalendar cal = new GregorianCalendar();
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        selectedDay = realDay;
        selectedMonth = realMonth;
        selectedYear = realYear;
    }

    public void setDate(int month, int day, int year) {
        selectedDay = day;
        selectedMonth = month;
        selectedYear = year;
    }

    public void setDateDay(int day) {
        selectedDay = day;
    }

    public void setDateMonth(int month) {
        selectedMonth = month;
    }

    public void setDateYear(int year) {
        selectedYear = year;
    }

    public void setDate(int row, int column, int startOfMonth, boolean calToDate) {
        int day = (row * 7) + 2 - startOfMonth + column;
        selectedDay = day;
    }

    public int getRealDay() {
        return realDay;
    }

    public int getRealMonth() {
        return realMonth;

    }

    public int getRealYear() {
        return realYear;

    }

    public int getSelectedDay() {
        return selectedDay;
    }

    public int getSelectedMonth() {
        return selectedMonth;
    }

    public int getSelectedYear() {
        return selectedYear;
    }

    public int dayToRow(int day, int startOfMonth) {
        int answer = new Integer((day + startOfMonth - 2) / 7);
        return answer;
    }

    public int dayToColumn(int day, int startOfMonth) {
        int answer = (day + startOfMonth - 2) % 7;
        return answer;
    }

    public static LocalDate gregorianToLocalReal(CalendarDate date) {
        int monthNum = date.getRealMonth() + 1;
        if (monthNum < 10 && date.getRealDay() < 10){
            return LocalDate.parse(date.getRealYear() + "-0" + monthNum + "-0" + date.getRealDay());
        } else if (monthNum < 10) {
            return LocalDate.parse(date.getRealYear() + "-0" + monthNum + "-" + date.getRealDay());
        } else if (date.getRealDay() < 10){
            return LocalDate.parse(date.getRealYear() + "-" + monthNum + "-0" + date.getRealDay());
        } else {
            return LocalDate.parse(date.getRealYear() + "-" + monthNum + "-" + date.getRealDay());
        }
    }

    public static LocalDate gregorianToLocalSelected(CalendarDate date) {
        int monthNum = date.getSelectedMonth();
        if (monthNum < 10 && date.getSelectedDay() < 10){
            return LocalDate.parse(date.getSelectedYear() + "-0" + monthNum + "-0" + date.getSelectedDay());
        } else if (monthNum < 10) {
            return LocalDate.parse(date.getSelectedYear() + "-0" + monthNum + "-" + date.getSelectedDay());
        } else if (date.getSelectedDay() < 10){
            return LocalDate.parse(date.getSelectedYear() + "-" + monthNum + "-0" + date.getSelectedDay());
        } else {
            return LocalDate.parse(date.getSelectedYear() + "-" + monthNum + "-" + date.getSelectedDay());
        }
    }
    
    public String toString() {
        return selectedMonth + "/" + selectedDay + "/" + selectedYear;
    }
}
