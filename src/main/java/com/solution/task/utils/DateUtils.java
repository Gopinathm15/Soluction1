package com.solution.task.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static String setExpireTimeForOtp(int seconds) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, seconds);
		return dateFormat.format(calendar.getTime());
	}

	public static boolean isExpired(String expire) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date expireTime = dateFormat.parse(expire);
		if (expireTime.compareTo(new Date()) < 0) {
			return true;
		}
		return false;
	}
}
