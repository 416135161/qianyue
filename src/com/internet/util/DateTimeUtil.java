package com.internet.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;

public class DateTimeUtil {

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";// "(dd/MM/yyyy)";
	private static final String QUERY_DATE_FORMAT = "yyyy-MM-dd";
	private static final String DEFAULT_WEEK_FORMAT = "E";

	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String TIME_FORMAT = "HH:mm";

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

	private static ThreadLocal<SimpleDateFormat> mYMDHMDateTime = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault());
		}

	};

	private static ThreadLocal<SimpleDateFormat> mYMDDateTime = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DEFAULT_DATE_FORMAT,
					Locale.getDefault());
		}

	};

	/**
	 * 
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String getCurrentDateTime() {
		return mYMDHMDateTime.get().format(System.currentTimeMillis());
	}

	public static String getCurrentDateTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		return sdf.format(System.currentTimeMillis());
	}

	public static String formatDateTime(long timeInMills, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		return sdf.format(timeInMills);
	}

	/**
	 * 
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String formatDateTime(long timemillis) {
		return mYMDHMDateTime.get().format(timemillis);
	}

	/**
	 * 
	 * @param selectedDate
	 *            yyyy-MM-dd
	 * @return
	 */
	public static long formatDate(String selectedDate) {
		SimpleDateFormat sdf = mYMDDateTime.get();
		try {
			Date date = sdf.parse(selectedDate);
			Calendar cal = Calendar.getInstance(Locale.getDefault());
			cal.setTime(date);
			return cal.getTimeInMillis();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String formatDate(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT,
				Locale.getDefault());
		return sdf.format(new Date(date));
	}

	/**
	 * 
	 * @param date
	 * @return E
	 */
	public static int formatWeek(long date) {
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.setTime(new Date(date));
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 
	 * @param step
	 * @param base
	 * @return (yyyy-MM-dd)
	 */
	public static String getDisplayDate(long initTime, int step, int base) {
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.setTime(new Date(initTime));
		cal.add(Calendar.DATE, step + base);
		Date time = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT,
				Locale.getDefault());
		return sdf.format(time);
	}

	public static Date getDefaultDate(long initTime, int step, int base) {
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.setTime(new Date(initTime));
		cal.add(Calendar.DATE, step + base);
		return cal.getTime();
	}

	public static int getCurrentDayOfWeek() {
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 
	 * @param step
	 * @param base
	 * @return E
	 */
	public static String getDisplayWeek(long initTime, int step, int base) {
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.setTime(new Date(initTime));
		cal.add(Calendar.DATE, step + base);
		Date timeTmp = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_WEEK_FORMAT,
				Locale.getDefault());
		return sdf.format(timeTmp);
	}

	public static String getDisplayWeek(Date initTime, int step, int base) {
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.setTime(initTime);
		cal.add(Calendar.DATE, step + base);
		Date timeTmp = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_WEEK_FORMAT,
				Locale.getDefault());
		return sdf.format(timeTmp);
	}

	/**
	 * For Booking
	 * 
	 * @param date
	 *            yyyy-MM-dd
	 * @return E
	 */
	public static String getDisplayWeek(String date) {
		try {
			String[] dates = date.split("-");
			Calendar cal = Calendar.getInstance(Locale.getDefault());
			cal.set(Integer.valueOf(dates[0]), Integer.valueOf(dates[1]) - 1,
					Integer.valueOf(dates[2]));
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_WEEK_FORMAT,
					Locale.getDefault());
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * @param date
	 *            yyyy
	 * @return
	 */
	public static Date formatStringToDate(String date) {
		try {
			String[] dates = date.split("-");
			Calendar cal = Calendar.getInstance(Locale.getDefault());
			cal.set(Integer.valueOf(dates[0]), Integer.valueOf(dates[1]) - 1,
					Integer.valueOf(dates[2]));
			return cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * For Booking
	 * 
	 * @param date
	 *            yyyy-MM-dd
	 * @return yyyy-MM-dd
	 */
	public static String getDisplayDate(String date, int step) {
		try {
			String[] dates = date.split("-");
			Calendar cal = Calendar.getInstance(Locale.getDefault());
			cal.set(Integer.valueOf(dates[0]), Integer.valueOf(dates[1]) - 1,
					Integer.valueOf(dates[2]));
			cal.add(Calendar.DATE, step);
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT,
					Locale.getDefault());
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * @param hour
	 * @param step
	 * @return HH
	 */
	public static String getDisplayHour(String hour, int step) {
		try {
			Calendar cal = Calendar.getInstance(Locale.getDefault());
			cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
			cal.add(Calendar.HOUR_OF_DAY, step);
			SimpleDateFormat sdf = new SimpleDateFormat("HH",
					Locale.getDefault());
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * @param minute
	 * @param step
	 * @return mm
	 */
	public static String getDisplayMinute(String minute, int step) {
		try {
			Calendar cal = Calendar.getInstance(Locale.getDefault());
			cal.set(Calendar.MINUTE, Integer.valueOf(minute));
			cal.add(Calendar.MINUTE, step);
			SimpleDateFormat sdf = new SimpleDateFormat("mm",
					Locale.getDefault());
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(QUERY_DATE_FORMAT,
				Locale.getDefault());
		return sdf.format(date);
	}

	/**
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT,
				Locale.getDefault());
		return sdf.format(System.currentTimeMillis());
	}

	public static long getCurrentTimeMillis() {
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		return cal.getTime().getTime();
	}

	/**
	 * 
	 * @return HH:mm
	 */
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT,
				Locale.getDefault());
		return sdf.format(System.currentTimeMillis());
	}

	/**
	 * 获取日期时差 单位：分钟
	 * 
	 * @param beginDateTime
	 * @param endDateTime
	 * @return
	 */
	public static String getSubMinute(String beginDateTime, String endDateTime) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm",
				Locale.getDefault());
		try {
			Date beginDate = myFormatter.parse(beginDateTime);
			Date endDate = myFormatter.parse(endDateTime);
			long time = (endDate.getTime() - beginDate.getTime()) / (60 * 1000);
			if (time < 0) {
				return "时间输入有误，无法计算在店时长";
			}
			int hour = (int) time / 60;
			int minute = (int) time % 60;
			if (hour == 0) {
				return minute + "分钟";
			} else {
				return hour + "小时" + minute + "分钟";
			}
		} catch (ParseException e) {
			return "时间输入有误，无法计算在店时长";
		}

	}

	/**
	 * 
	 * @param oldDate
	 *            format: yyyy-MM-dd
	 * @return
	 */
	public static String getAge(String oldDate) {
		if (oldDate == null) {
			return null;
		}
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd",
				Locale.getDefault());
		try {
			Date mydate = myFormatter.parse(oldDate);
			long day = (System.currentTimeMillis() - mydate.getTime())
					/ (24 * 60 * 60 * 1000) + 1;
			String year = new DecimalFormat().format(day / 365);
			return year;
		} catch (ParseException e) {
		}
		return "";
	}

	/**
	 * 
	 * @param orderTime
	 *            yyyy-MM-dd HH:mm
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String formatBookingTime(String orderTime) {
		try {
			String[] dateTime = orderTime.split(" ");
			String[] ymd = dateTime[0].split("-");
			String[] hm = dateTime[1].split(":");
			int h = Integer.valueOf(hm[0]);
			int minute = Integer.valueOf(hm[1]);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
					Locale.getDefault());
			Calendar cal = Calendar.getInstance(Locale.getDefault());
			cal.set(Integer.valueOf(ymd[0]), Integer.valueOf(ymd[1]) - 1,
					Integer.valueOf(ymd[2]));
			cal.set(Calendar.HOUR_OF_DAY, h);
			cal.set(Calendar.MINUTE, minute);
			cal.set(Calendar.SECOND, 0);
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return orderTime;
		}
	}

	/**
	 * 
	 * @param orderTime
	 *            yyyy-MM-dd HH:mm:ss
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String formatBookingTime2(String orderTime) {
		try {
			String[] dateTime = orderTime.split(" ");
			String[] ymd = dateTime[0].split("-");
			String[] hms = dateTime[1].split(":");
			int h = Integer.valueOf(hms[0]);
			int minute = Integer.valueOf(hms[1]);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",
					Locale.getDefault());
			Calendar cal = Calendar.getInstance(Locale.getDefault());
			cal.set(Integer.valueOf(ymd[0]), Integer.valueOf(ymd[1]) - 1,
					Integer.valueOf(ymd[2]));
			cal.set(Calendar.HOUR_OF_DAY, h);
			cal.set(Calendar.MINUTE, minute);
			cal.set(Calendar.SECOND, Integer.valueOf(hms[2]));
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return orderTime;
		}
	}

	/**
	 * 
	 * @param dateTime
	 *            yyyy-MM-dd HH:mm:ss
	 * @return yyyy-MM-dd
	 */
	public static String formatAppClientDateTime(String dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.getDefault());
		try {
			Date date = sdf.parse(dateTime);
			SimpleDateFormat sdf2 = new SimpleDateFormat(DEFAULT_DATE_FORMAT,
					Locale.getDefault());
			return sdf2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param dateTime
	 *            yyyy-MM-dd HH:mm:ss
	 * @return HH:mm
	 */
	public static String formatAppClientTime(String dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.getDefault());
		try {
			Date date = sdf.parse(dateTime);
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm",
					Locale.getDefault());
			return sdf2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param dateTime
	 *            yyyy-MM-dd HH:mm:ss
	 * @return HH:mm
	 */
	public static String formatYudingTime(String dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.getDefault());
		try {
			Date date = sdf.parse(dateTime);
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd\nHH:mm",
					Locale.getDefault());
			return sdf2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 格式化通话时间
	 * 
	 * @param ms
	 * @return
	 */
	public static String formatCalledTime(long ms) {
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss",
				Locale.getDefault());
		return sdf.format(ms);
	}

	/**
	 * 判断给定的时间是否早于当前时间
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @param hourOfDay
	 * @param minute
	 * @return
	 */
	public static boolean beforeCurrentDateTime(int year, int month, int date,
			int hourOfDay, int minute) {
		Calendar calParam = Calendar.getInstance(Locale.getDefault());
		calParam.set(year, month - 1, date, hourOfDay, minute);
		int r = calParam.compareTo(Calendar.getInstance(Locale.getDefault()));
		return r < 0;
	}

	/**
	 * 判断给定的日期是否晚于当前日期
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static boolean afterCurrentDate(int year, int month, int date) {
		Calendar calParam = Calendar.getInstance(Locale.getDefault());
		calParam.set(year, month - 1, date);
		int r = calParam.compareTo(Calendar.getInstance(Locale.getDefault()));
		return r > 0;
	}

	/**
	 * 设置系统自动确定日期、时间及时区
	 * 
	 * @param context
	 * @return
	 */
	public static boolean setSystemAutoDateTime(Context context) {
		boolean autoTimeEnabled = getAutoState(context,
				Settings.System.AUTO_TIME);
		boolean autoTimeZoneEnabled = getAutoState(context,
				Settings.System.AUTO_TIME_ZONE);
		if (!autoTimeEnabled) {
			Settings.System.putInt(context.getContentResolver(),
					Settings.System.AUTO_TIME, 1);// auto time
		}
		if (!autoTimeZoneEnabled) {
			Settings.System.putInt(context.getContentResolver(),
					Settings.System.AUTO_TIME_ZONE, 1);// auto time zone
		}
		autoTimeEnabled = getAutoState(context, Settings.System.AUTO_TIME);
		autoTimeZoneEnabled = getAutoState(context,
				Settings.System.AUTO_TIME_ZONE);
		return (autoTimeEnabled && autoTimeZoneEnabled);
	}

	private static boolean getAutoState(Context context, String name) {
		try {
			return Settings.System.getInt(context.getContentResolver(), name) > 0;
		} catch (SettingNotFoundException snfe) {
			return false;
		}
	}

	public final static String getCurrentTimeString() {
		return System.currentTimeMillis() + "";
	}

	/**
	 * 
	 * @param dateTime
	 *            yyyy-MM-dd HH:mm:ss
	 * @param amountSeconds
	 * @return
	 */
	public static boolean beforeCurrentDateTime(String dateTime,
			int amountSeconds) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.getDefault());
		try {
			Date date = sdf.parse(dateTime);
			Calendar calParam = Calendar.getInstance(Locale.getDefault());
			calParam.setTime(date);
			calParam.add(Calendar.SECOND, amountSeconds);
			return calParam.getTime().before(new Date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param dateTime
	 *            yyyy-MM-dd
	 * @return
	 */
	public static boolean afterYesterday(String dateArgs) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
				Locale.getDefault());
		try {
			Date date = sdf.parse(dateArgs);
			Calendar calParam = Calendar.getInstance(Locale.getDefault());
			calParam.setTime(date);
			Calendar calParamYesterday = Calendar.getInstance(Locale
					.getDefault());
			calParamYesterday.add(Calendar.DAY_OF_YEAR, -1);
			return calParam.getTime().after(calParamYesterday.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 计算两个时间点之间的分钟数，时间的格式必须是yyyy-MM-dd HH:mm:ss
	 * 
	 * @param afterTime
	 * @param beforeTime
	 * @return
	 */
	public static int getSecondBetweenTime(String afterTime, String beforeTime) {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.getDefault());
		Date begin = null;
		Date end = null;
		try {
			begin = dfs.parse(beforeTime);
			end = dfs.parse(afterTime);
		} catch (ParseException e) {
			return 0;
		}
		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒

		int second = (int) between / 60;
		return second;
	}

	/**
	 * 
	 * @return HH:mm
	 */
	public static String getHHmm(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT,
				Locale.getDefault());
		return sdf.format(time);
	}

	public static long getCurrentDayStart() {
		SimpleDateFormat dfs = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
		String time = dfs.format(System.currentTimeMillis());
		time += " 00:00:00";
		SimpleDateFormat dfs2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date begin = null;
		try {
			begin = dfs2.parse(time);
		} catch (ParseException e) {
			return 0;
		}
		return begin.getTime();
	}

	public static long getCurrentDayEnd() {
		SimpleDateFormat dfs = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
		String time = dfs.format(System.currentTimeMillis());
		time += " 23:59:59";
		SimpleDateFormat dfs2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date begin = null;
		try {
			begin = dfs2.parse(time);
		} catch (ParseException e) {
			return 0;
		}
		return begin.getTime();
	}
	
	/**
	 * 
	 * TODO:输入一个时间，获取该时间的时间戳
	 * 
	 * @param @param dateString
	 * @param @return
	 * @param @throws ParseException
	 */
	public static long string2Timestamp(String dateString) {
		long temp = 0;
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString);
			temp = date1.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
}
