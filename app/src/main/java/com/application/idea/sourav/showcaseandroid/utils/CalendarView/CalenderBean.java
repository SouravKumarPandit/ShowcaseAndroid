package com.application.idea.sourav.showcaseandroid.utils.CalendarView;

import java.util.Locale;

public class CalenderBean {
    private int year;
    private int month;
    private int day;
    private int week;
    private int wholeMonth;

    public CalenderBean(int year, int month, int day, int week, int wholeMonth) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.week = week;
        this.wholeMonth = wholeMonth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWholeMonth() {
        return wholeMonth;
    }

    public void setWholeMonth(int wholeMonth) {
        this.wholeMonth = wholeMonth;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CalenderBean)) {
            return false;
        }
        CalenderBean calenderBean = (CalenderBean) obj;
        return (year == calenderBean.getYear()) && (month == calenderBean.getMonth()) && (day == calenderBean.getDay());
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%d-%d-%d", year, month, day);
    }

    public int compareTo(CalenderBean calenderBean) {
        if (year != calenderBean.getYear()) {
            return year > calenderBean.getYear() ? 1 : -1;
        } else if (month != calenderBean.getMonth()) {
            return month > calenderBean.getMonth() ? 1 : -1;
        } else if (day != calenderBean.getDay()) {
            return day > calenderBean.getDay() ? 1 : -1;
        }
        return 0;
    }
}