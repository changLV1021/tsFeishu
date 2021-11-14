package com.hundsun.ts2dingding.utils;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间工具类 Created by litg on 2017/06/05.
 */
public class DateUtils {
	private static Logger LOG = Logger.getLogger("SYS");

	/**
	 * 判断时间是否落在区间内
	 * 
	 * @param currentTime
	 *            当前时间
	 * @param liveStartHour
	 *            区间开始
	 * @param liveEndHour
	 *            区间结束
	 * @return
	 */
	public static boolean isTimeInArea(Date currentTime, int liveStartHour, int liveEndHour) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			String liveTime = formatter.format(currentTime); // 将日期时间格式化

			int currentTimeInt = Integer.parseInt(liveTime.replace(":", "").trim());

			if (liveStartHour <= currentTimeInt && currentTimeInt <= liveEndHour) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 判断日期是否是今天
	 * @param datestr 日期字符串，格式为yyyy-MM-dd
	 * @return
	 */
	public static boolean isToday(String datestr) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String todaystr = formatter.format(new Date());
			if (todaystr.equals(datestr)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 根据格式要求，返回当前时间的字符串
	 *
	 * @param format 时间格式
	 * @return
	 */
	public static String formatCurrentDate(String format) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.format(new Date());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 把时间字符串转换为Date类型
	 * @param sDate 时间字符串
	 * @param format 时间格式
	 * @return
	 */
	public static Date dateStrToDate(String sDate, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			return formatter.parse(sDate);
		} catch (ParseException e) {
			LOG.error("转换日期"+sDate+"失败", e);
			return null;
		}
	}
}
