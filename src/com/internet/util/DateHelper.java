package com.internet.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author yangjian
 * 
 */

public class DateHelper {

	private static DateHelper util;

	synchronized public static DateHelper getInstance() {

		if (util == null) {
			util = new DateHelper();
		}
		return util;

	}

	private DateHelper() {
		super();
	}

	public static SimpleDateFormat date_point_hours = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm");
	public static SimpleDateFormat date_days = new SimpleDateFormat("MM月dd日");
	public static SimpleDateFormat date_hours = new SimpleDateFormat("HH:mm");

	public static SimpleDateFormat date_point = new SimpleDateFormat(
			"yyyy.MM.dd");
	public static SimpleDateFormat date_text_month = new SimpleDateFormat(
			"yyyy年MM月");
	public static SimpleDateFormat date_text_hours = new SimpleDateFormat(
			"yyyy年MM月dd日 HH:mm");
	public static SimpleDateFormat date_text_hours_noyear = new SimpleDateFormat(
			"MM月dd日 HH:mm");

	public static SimpleDateFormat date_line_day = new SimpleDateFormat(
			"yyyy-MM-dd");

	public Date getDate(SimpleDateFormat df, String dateStr) {
		Date date = null;
		if (TextUtils.isEmpty(dateStr)) {
			return date;
		}
		try {
			date = df.parse(dateStr);

		} catch (ParseException e) {
			e.printStackTrace();

		}
		return date;

	}

	public String getDataStringByDateFormat(SimpleDateFormat df, Date date) {
		if (date == null) {
			date = new Date();
		}
		String str = df.format(date);
		return str;

	}

	/**
	 * @param df1
	 *            与date对应的SimpleDateFormat
	 * @param df2
	 *            df1去掉小时部分的SimpleDateFormat
	 * @param date
	 *            时间参数
	 * @return
	 */
	public String getRencentTime(SimpleDateFormat df1, SimpleDateFormat df2,
			String date) {
		Date time = getDate(df1, date);
		if (time == null) {
			return date;
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		String curDate = df2.format(cal.getTime());
		String paramDate = df2.format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			else
				ftime = hour + "小时以前";
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0) {

				long m = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1);
				if (m < 2) {
					ftime = "刚刚";
				} else {
					ftime = m + "分钟以前";
				}

			} else {
				ftime = hour + "小时以前";
			}
		} else if (days < 7) {
			ftime = days + "天以前";

		} else {
			ftime = df2.format(time);
		}
		return ftime;
	}

	public String getRencentTimeSimple(SimpleDateFormat df1,
			SimpleDateFormat df2, String date) {
		Date time = getDate(df1, date);
		if (time == null) {
			return date;
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		String stringHours = getDataStringByDateFormat(date_hours, time);
		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			ftime = "今天 " + stringHours;
		} else if (days == 1) {

			ftime = "昨天 " + stringHours;
		} else {
			ftime = df1.format(time);
		}
		return ftime;
	}

	/**
	 * 日期时间格式转换
	 * 
	 * @param typeFrom
	 *            原格式
	 * @param typeTo
	 *            转为格式
	 * @param value
	 *            传入的要转换的参数
	 * @return
	 */
	public String stringDateToStringData(String typeFrom, String typeTo,
			String value) {
		String re = value;
		SimpleDateFormat sdfFrom = new SimpleDateFormat(typeFrom);
		SimpleDateFormat sdfTo = new SimpleDateFormat(typeTo);

		re = stringDateToStringData(sdfFrom, sdfTo, value);

		return re;
	}

	public String stringDateToStringData(SimpleDateFormat sdfFrom,
			SimpleDateFormat sdfTo, String value) {
		String re = value;
		if (TextUtils.isEmpty(value)) {
			return re;
		}

		try {
			re = sdfTo.format(sdfFrom.parse(re));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 得到这个月有多少天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public int getMonthLastDay(int year, int month) {
		if (month == 0) {
			return 0;
		}
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 得到年份
	 * 
	 * @return
	 */
	public String getCurrentYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR) + "";
	}

	/**
	 * 得到月份
	 * 
	 * @return
	 */
	public String getCurrentMonth() {
		Calendar c = Calendar.getInstance();
		return (c.get(Calendar.MONTH) + 1) + "";
	}

	/**
	 * 获得当天的日期
	 * 
	 * @return
	 */
	public String getCurrDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_MONTH) + "";
	}

	/**
	 * 得到几天/周/月/年后的日期，整数往后推,负数往前移动
	 * 
	 * @param calendar
	 * @param calendarType
	 *            Calendar.DATE,Calendar.WEEK_OF_YEAR,Calendar.MONTH,Calendar.
	 *            YEAR
	 * @param next
	 * @return
	 */
	public String getDayByDate(Calendar calendar, int calendarType, int next) {

		calendar.add(calendarType, next);
		Date date = calendar.getTime();
		String dateString = date_point_hours.format(date);
		return dateString;

	}

	/**
	 * 将yyyy年MM月dd日 HH:mm转成常用最近日期格式
	 * 
	 * @param time
	 * @return
	 */
	public String getRencentTimeByText(String time) {

		String str = stringDateToStringData(date_text_hours, date_point_hours,
				time);
		// return getRencentTimeSimple(date_point_hours,date_point,str);
		return getRencentTime(date_point_hours, date_point, str);

	}

	/**
	 * 得到本周周一
	 * 
	 * @return
	 */
	public Calendar getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return c;
	}

	/**
	 * 得到本周周日
	 * 
	 * @return
	 */
	public Calendar getSundayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 7);
		return c;
	}

	/**
	 * 得到上周周日
	 * 
	 * @return
	 */
	public Calendar getSundayOfLastWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week);
		return c;
	}
}
