package com.application.idea.sourav.showcaseandroid.utils.CalendarView;

import java.util.Calendar;
import java.util.Date;

public class CalenderUtil {
    public static CalenderBean getCalender(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new CalenderBean(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.DAY_OF_WEEK) - 1, getWholeMonth(calendar));
    }

    /**
     * 获取当月天数
     *
     * @param calendar
     * @return
     */
    private static int getWholeMonth(Calendar calendar) {
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.get(Calendar.DATE);
    }

    public static CalenderBean getCalender(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return getCalender(calendar.getTime());
    }

    public static CalenderBean getCalender(int year, int month) {
        return getCalender(year, month, 1);
    }

    /**
     * 上个月
     *
     * @param year
     * @param month
     * @return
     */
    public static CalenderBean getPreCalender(int year, int month) {
        month--;
        if (month <= 0) {
            month = 12;
            year--;
        }
        return getCalender(year, month);
    }

    /**
     * 前一天
     *
     * @param year
     * @param month
     * @return
     */
    public static CalenderBean getPreDay(int year, int month, int day) {
        CalenderBean calenderBean;
        if (day - 1 <= 0) {
            calenderBean = getPreCalender(year, month);
            calenderBean = getCalender(calenderBean.getYear(), calenderBean.getMonth(), calenderBean.getWholeMonth());
        } else {
            calenderBean = getCalender(year, month, day - 1);
        }
        return calenderBean;
    }

    /**
     * 下个月
     *
     * @param year
     * @param month
     * @return
     */
    public static CalenderBean getNextCalender(int year, int month) {
        month++;
        if (month > 12) {
            month = 1;
            year++;
        }
        return getCalender(year, month);
    }

    /**
     * 前一天
     *
     * @param year
     * @param month
     * @return
     */
    public static CalenderBean getNextDay(int year, int month, int day) {
        CalenderBean calenderBean = getCalender(year, month, day);
        if (day + 1 > calenderBean.getWholeMonth()) {
            calenderBean = getNextCalender(year, month);
        } else {
            calenderBean = getCalender(year, month, day+1);
        }
        return calenderBean;
    }
}