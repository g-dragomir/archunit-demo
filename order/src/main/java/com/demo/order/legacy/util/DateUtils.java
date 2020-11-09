package com.demo.order.legacy.util;

import java.util.Calendar;
import java.util.Date;

@Deprecated
public class DateUtils {

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static long currentDate = System.currentTimeMillis();

	public static Date addDays(Date date, int days)	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); 
		return cal.getTime();
	}
	
	public static int getDayOfMonthForToday() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}
}
