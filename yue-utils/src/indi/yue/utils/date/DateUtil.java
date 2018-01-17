package indi.yue.utils.date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {

	/** The Constant CM_SSS_DATE_FORMAT. */
	public static final String CM_SSS_DATE_FORMAT = "yyMMddHHmmssSSS";
	
	/** The Constant CM_LONG_DATE_FORMAT. */
	public static final String CM_LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** The Constant CM_ONLY_DATE_FORMAT. */
	public static final String CM_ONLY_DATE_FORMAT = "yyyy-MM-dd";
	
	/** The Constant CM_SHORT_DATE_FORMAT. */
	public static final String CM_SHORT_DATE_FORMAT = "yyyyMMdd";

	/** The Constant CM_SHORT_MONTH_FORMAT. */
	public static final String CM_SHORT_MONTH_FORMAT = "yyyy-MM";

	/** The Constant CM_SHORT_YEAR_FORMAT. */
	public static final String CM_SHORT_YEAR_FORMAT = "yyyy";

	/** The Constant YEAR_MONTH. */
	public static final String YEAR_MONTH = "yyyyMM";

	/** The Constant MONTH. */
	public final static String[] MONTH = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	/** The Constant DAY. */
	public final static String[] DAY = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

	/** The date format. */
	public static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
	
	public static String todayStartTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat(CM_ONLY_DATE_FORMAT);
		String sortDate = sdf.format(new Date());
		return sortDate + " 00:00:00";
	}
	/**
	 * new date format yyyy-MM-dd HH:mm:ss
	 * @return Date
	 */
	public static String currentDateString() {
		Date date = new Date();
		return DateToString(date, CM_LONG_DATE_FORMAT);
	}
	/**
	 * @param stringTime
	 * @return
	 */
	public static Timestamp StringTOTimestamp(String stringTime){
		return Timestamp.valueOf(stringTime);
	}
	
	/**
	 * 获取当前
	 * 
	 * @return yyMMddHHmmssSSS
	 */
	public static String getCurrTime(){
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(CM_SSS_DATE_FORMAT);
		Date date = new Date();
		return sdf.format(date);
	}

	/**
	 * 天数加减运算
	 * @param Date source
	 * @param int value 
	 * @return 日期时间 Date
	 */
	public static Date dateArith(Date source,int value){
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(source); 
		calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+value);  
		return calendar.getTime();
	}
	
	/**
	 * 秒钟加减运算
	 * @param Date source
	 * @param int value 
	 * @return 日期时间 Date
	 */
	public static Date secondArith(Date source,int value){
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(source); 
		calendar.set(Calendar.SECOND,calendar.get(Calendar.SECOND)+value);  
		return calendar.getTime();
	}
	
	/**
	 * 分钟加减运算
	 * @param Date source
	 * @param int value 
	 * @return 日期时间 Date
	 */
	public static Date minuteArith(Date source,int value){
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(source); 
		calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)+value);  
		return calendar.getTime();
	}
	
	/**
	 * 分钟加减运算
	 * @param Timestamp source
	 * @param int value 
	 * @return 日期时间 Timestamp
	 */
	public static Timestamp minuteArith(Timestamp source, int value){
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(source); 
		calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)+value);  
		return dateToTimstamp(calendar.getTime());
	}
	
	/**
	 * 当前时间转换成java.sql.Timestamp
	 * @return
	 */
	public static Timestamp currentTimstamp(){
		return dateToTimstamp(new Date());
	}
	
	/**
	 * java.sql.Timestamp to java.sql.Timestamp
	 * @param source
	 * @return
	 */
	public static Timestamp dateToTimstamp(Date source){
		return new Timestamp(source.getTime());
	}
	
	/**
	 * 取得今天的日期.
	 * 
	 * @return the today
	 */
	public static String getToday() {
		Date myDate = new Date();
		String today = DateUtil.DateToString(myDate, CM_SHORT_DATE_FORMAT);
		return today;
	}

	/**
	 * 取得今天的日期.
	 * 
	 * @return timeformat
	 */
	public static long getTodayInTimeFormat() {
		Date myDate = new Date();
		long today = myDate.getTime();
		return today;
	}

	/**
	 * 取得今年年份.
	 * 
	 * @return the now year
	 */
	public static String getNowYear() {
		Date myDate = new Date();
		String nowYear = DateUtil.DateToString(myDate, CM_SHORT_YEAR_FORMAT);
		return nowYear;
	}
	
	
	/**
	 * 得到 两日期相差的天数 
	 * @param smdate   小日期
	 * @param bdate	        大日期
	 * @return
	 */
	public static int getBetweenDays(Date smdate,Date bdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);

			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -999;
	}

	/**
	 * 获得当前时间.
	 * 
	 * @return String
	 */
	public static java.sql.Timestamp getNowTime() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

	/**
	 * 取得当月的月份.
	 * 
	 * @return the month
	 */
	public static String getMonth() {
		Date myDate = new Date();
		String month = DateUtil.DateToString(myDate, CM_SHORT_MONTH_FORMAT);
		return month;
	}

	/**
	 * 取得当月的年月.
	 * 
	 * @param ymFormat
	 *            the ym format
	 * @return the month
	 */
	public static String getMonth(String ymFormat) {
		Date myDate = new Date();
		String month = DateUtil.DateToString(myDate, ymFormat);
		return month;
	}

	/**
	 * 取得下月的月份,形式如yyyy-MM.
	 * 
	 * @return the next month
	 */
	public static String getNextMonth() {
		Date myDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.MONTH, 1);

		String nextmonth = DateUtil.DateToString(cal.getTime(), CM_SHORT_MONTH_FORMAT);
		return nextmonth;
	}

	/**
	 * 取得下月的月份,形式如y.
	 * 
	 * @param ymFormat
	 *            格式如:yyyyMM
	 * @return the next month
	 */
	public static String getNextMonth(String ymFormat) {
		Date myDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.MONTH, 1);

		String nextmonth = DateUtil.DateToString(cal.getTime(), ymFormat);
		return nextmonth;
	}

	/**
	 * Gets the month date.
	 * 
	 * @param myDate
	 *            the my date
	 * @param month
	 *            the month
	 * @return the month date
	 */
	public static java.sql.Date getMonthDate(Date myDate, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.MONTH, month);
		java.util.Date newDate = cal.getTime();
		return new java.sql.Date(newDate.getTime());
	}

	/**
	 * 取得上月的月份.
	 * 
	 * @return the up month
	 */
	public static String getUpMonth() {
		Date myDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.MONTH, -1);

		String nextmonth = DateUtil.DateToString(cal.getTime(), CM_SHORT_MONTH_FORMAT);
		return nextmonth;
	}

	/**
	 * 取得参数指定年月的上个月.
	 * 
	 * @param year
	 *            指定年
	 * @param month
	 *            指定月
	 * @param format
	 *            指定格式 如: "yyyyMMdd"
	 * @return the up month
	 */
	public static String getUpMonth(String year, String month, String format) {
		Date myDate = DateUtil.getDate(year, month, "01");
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.MONTH, -1);

		String nextmonth = DateUtil.DateToString(cal.getTime(), format);
		return nextmonth;
	}

	/**
	 * 取得参数指定年月的下个月.
	 * 
	 * @param year
	 *            指定年
	 * @param month
	 *            指定月
	 * @param format
	 *            指定格式 如: "yyyyMMdd"
	 * @return the next month
	 */
	public static String getNextMonth(String year, String month, String format) {
		Date myDate = DateUtil.getDate(year, month, "01");
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.MONTH, 1);

		String nextmonth = DateUtil.DateToString(cal.getTime(), format);
		return nextmonth;
	}

	/**
	 * 取得从某一时间开始的一段年份.
	 * 
	 * @param currdate
	 *            Date
	 * @param len
	 *            int
	 * @return List
	 */
	public static List<String> getYear(Date currdate, int len) {
		List<String> lists = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		int ln = Math.abs(len);
		if (len >= 0) {
			for (int i = 0; i < len; i++) {
				cal.setTime(currdate);
				cal.add(Calendar.YEAR, i);

				String year = DateUtil.DateToString(cal.getTime(), CM_SHORT_YEAR_FORMAT);
				lists.add(year);
			}
		} else {
			for (int i = 1; i <= ln; i++) {
				cal.setTime(currdate);
				cal.add(Calendar.YEAR, -i);
				String year = DateUtil.DateToString(cal.getTime(), CM_SHORT_YEAR_FORMAT);
				lists.add(year);
			}

		}
		return lists;
	}

	/**
	 * 取得明日的日期.
	 * 
	 * @return the tomorrow
	 */
	public static String getTomorrow() {
		Date myDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.DATE, 1);
		String tomorrow = DateUtil.DateToString(cal.getTime(), CM_SHORT_DATE_FORMAT);
		return tomorrow;
	}

	/**
	 * 取得后天的日期.
	 * 
	 * @return the day after tomorrow
	 */
	public static String getDayAfterTomorrow() {
		Date myDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.DATE, 2);
		String tomorrow = DateUtil.DateToString(cal.getTime(), CM_SHORT_DATE_FORMAT);
		return tomorrow;
	}

	/**
	 * 取得昨日的日期.
	 * 
	 * @return the yesterday
	 */
	public static String getYesterday() {
		Date myDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.DATE, -1);
		String yesterday = DateUtil.DateToString(cal.getTime(), CM_SHORT_DATE_FORMAT);
		return yesterday;
	}

	/**
	 * 取得日期的完整打印格式.
	 * 
	 * @param date
	 *            the date
	 * @return the full date string
	 */
	public static String getFullDateString(String date) {
		Date myDate = DateUtil.StringToDate(date);
		return dateFormat.format(myDate);
	}

	/**
	 * 日期变为字符串.
	 * 
	 * @param date
	 *            the date
	 * @param iso
	 *            the iso
	 * @return the string
	 */
	public static String DateToString(Date date, String iso) {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(iso);
		return format.format(date);
	}

	/**
	 * 字符串变为日期.
	 * 
	 * @param date
	 *            the date
	 * @return the date
	 */
	public static Date StringToDate(String date) {
		Date myDate = new Date();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(DateUtil.CM_SHORT_DATE_FORMAT);
		try {
			myDate = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myDate;
	}

	/**
	 * 字符串变为日期.
	 * 
	 * @param date
	 *            the date
	 * @param formatStr
	 *            the format str
	 * @return the date
	 */
	public static Date StringToDate(String date, String formatStr) {
		Date myDate = new Date();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(formatStr);
		try {
			myDate = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myDate;
	}

	/**
	 * 根据起始日期 及 间隔时间 得到结束日期.
	 * 
	 * @param startDate
	 *            起始日期
	 * @param offset
	 *            间隔时间
	 * @return 结束日期
	 */
	public static String getEndDate(String startDate, int offset) {
		Calendar cal = Calendar.getInstance();
		Date day = DateUtil.StringToDate(startDate);
		cal.setTime(day);
		cal.add(Calendar.DATE, offset);

		return DateUtil.DateToString(cal.getTime(), CM_SHORT_DATE_FORMAT);
	}

	/**
	 * 根据起始日期 及 间隔时间 得到结束日期 得到的格式是yyyy-MM-dd.
	 * 
	 * @param startDate
	 *            起始日期
	 * @param offset
	 *            间隔时间
	 * @return 结束日期
	 */

	public static String getEndDateForSQLDate(String startDate, int offset) {
		Calendar cal = Calendar.getInstance();
		Date day = DateUtil.StringToDateByFormat(startDate, CM_SHORT_DATE_FORMAT);
		cal.setTime(day);
		cal.add(Calendar.DATE, offset);

		return DateUtil.DateToString(cal.getTime(), CM_SHORT_DATE_FORMAT);
	}

	/**
	 * 把指定格式的字符串变为日期型.
	 * 
	 * @param date
	 *            the date
	 * @param iso
	 *            the iso
	 * @return the date
	 */
	public static Date StringToDateByFormat(String date, String iso) {
		Date myDate = new Date();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(iso);
		try {
			myDate = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myDate;
	}

	/**
	 * 取得指定年月所有星期五的日期的集合.
	 * 
	 * @param year
	 *            指定年
	 * @param month
	 *            指定月
	 * @return list 星期五的日期的集合
	 */
	public static List<String> getEndWeekDayOfMonth(String year, String month) {
		List<String> list = new ArrayList<String>();
		int days = daysInMonth(year, month);
		int weekday = 0;
		for (int i = 1; i <= days; i++) {
			weekday = getWeekOfMonth(year, month, String.valueOf(i));
			if (weekday == 5) {
				if (i < 10) {
					list.add(year + month + "0" + String.valueOf(i));
				} else {
					list.add(year + month + String.valueOf(i));
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println("end week list[" + i + "]:" + list.get(i));
		}

		return list;
	}

	/**
	 * 获得指定年月的天数.
	 * 
	 * @param argYear
	 *            the arg year
	 * @param argMonth
	 *            the arg month
	 * @return int 天数
	 */
	public static int daysInMonth(String argYear, String argMonth) {
		int year = Integer.parseInt(argYear);
		int month = Integer.parseInt(argMonth);

		GregorianCalendar c = new GregorianCalendar(year, month, 0);
		int[] daysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		daysInMonths[1] += c.isLeapYear(c.get(GregorianCalendar.YEAR)) ? 1 : 0;
		return daysInMonths[c.get(GregorianCalendar.MONTH)];
	}

	/**
	 * 得到日期中是星期几.
	 * 
	 * @param year
	 *            String
	 * @param month
	 *            String
	 * @param day
	 *            String
	 * @return String
	 */
	public static int getWeekOfMonth(String year, String month, String day) {
		java.sql.Date myDate = getDate(year, month, day);
		int index = 0;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(myDate);
			index = cal.get(Calendar.DAY_OF_WEEK);
			if (index <= 1) {
				index = 7;
			} else {
				index = index - 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return index;

	}
	
	/**
	 * 得到指定日期是星期几.返回中文星期
	 * @param myDate
	 * @return String
	 */
	public static String getWeekdayByDate(Date myDate) {
		int index = 0;
		String result = "星期";
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(myDate);
			index = cal.get(Calendar.DAY_OF_WEEK);
			if (index <= 1) {
				index = 7;
			} else {
				index = index - 1;
			}
		 String [] weekdays={"一","二","三","四","五","六","日"};
		 result = result+weekdays[index-1];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * 根据年月日得到日期.
	 * 
	 * @param year
	 *            String 年 YYYY
	 * @param month
	 *            String 月MM
	 * @param day
	 *            String 日dd
	 * @return Date
	 */
	public static java.sql.Date getDate(String year, String month, String day) {
		java.sql.Date result = null;
		try {
			String str = year + "-" + month + "-" + day;
			java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date1 = dateFormat.parse(str);
			result = new java.sql.Date(date1.getTime());
		} catch (Exception e) {
			System.out.println("Exception " + e);
		}
		return result;
	}

	/**
	 * 取得当前月的第一天.
	 * 
	 * @return string format
	 */
	public static String getFirstDayOfMonth() {
		StringBuffer buff = new StringBuffer();
		String today = DateUtil.getToday();
		buff.append(today.substring(0, 6)).append("01");
		return buff.toString();
	}

	/**
	 * 取得当前月的第一天.
	 * 
	 * @return long format
	 */
	public static long getFirstDayOfMonthInTimeFormat() {
		StringBuffer buff = new StringBuffer();
		String today = DateUtil.getToday();
		buff.append(today.substring(0, 6)).append("01");
		long time = (DateUtil.StringToDateByFormat(buff.toString(), "yyyyMMdd")).getTime();

		return time;
	}

	/**
	 * 取得距离当前月月底的n个月的第一天.
	 * 
	 * @param offSet
	 *            the off set
	 * @return the first day of offset month
	 */
	public static String getFirstDayOfOffsetMonth(int offSet) {
		StringBuffer buff = new StringBuffer();
		Date myDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.MONTH, offSet);

		String nextmonth = DateUtil.DateToString(cal.getTime(), YEAR_MONTH);
		buff.append(nextmonth).append("01");

		return buff.toString();
	}

	/**
	 * 把字符型时间转化成长整型时间.
	 * 
	 * @param argStr
	 *            the arg str
	 * @return the long
	 */
	public static long StingToLong(String argStr) {
		return (DateUtil.StringToDateByFormat(argStr, DateUtil.CM_SHORT_DATE_FORMAT)).getTime();
	}

	/**
	 * 按日期格式formatStr,把字符型时间转化成长整型时间.
	 * 
	 * @param argStr
	 *            the arg str
	 * @param formatStr
	 *            the format str
	 * @return the long
	 */
	public static long StingToLong(String argStr, String formatStr) {
		return (DateUtil.StringToDateByFormat(argStr, formatStr)).getTime();
	}

	/**
	 * 取得参数日期上个月的最后一天.
	 * 
	 * @param argDate
	 *            the arg date
	 * @return endDateOfUpMonth 格式如"yyyyMMdd"
	 */
	public static String getEndDateOfUpMonth(Date argDate) {
		StringBuffer buff = new StringBuffer();
		// java.util.Date --> String
		String date = DateUtil.DateToString(argDate, DateUtil.CM_SHORT_DATE_FORMAT);
		// 得到参数日期的上个年月
		String upMonth = DateUtil.getUpMonth(date.substring(0, 4), date.substring(4, 6), DateUtil.YEAR_MONTH);
		// 上个月的最后一天 = 上个月(yyyyMM) + 上个月总天数
		buff.append(upMonth).append(DateUtil.daysInMonth(upMonth.substring(0, 4), upMonth.substring(4)));
		date = buff.toString();
		buff = null;
		return date;
	}

	/**
	 * 取得参数日期下个月的最后一天.
	 * 
	 * @param argDate
	 *            the arg date
	 * @return endDateOfUpMonth 格式如"yyyyMMdd"
	 */
	public static String getEndDateOfNextMonth(Date argDate) {
		StringBuffer buff = new StringBuffer();
		// java.util.Date --> String
		String date = DateUtil.DateToString(argDate, DateUtil.CM_SHORT_DATE_FORMAT);
		// 得到参数日期的上个年月
		String nextMonth = DateUtil.getNextMonth(date.substring(0, 4), date.substring(4, 6), DateUtil.YEAR_MONTH);
		// 上个月的最后一天 = 上个月(yyyyMM) + 上个月总天数
		buff.append(nextMonth).append(DateUtil.daysInMonth(nextMonth.substring(0, 4), nextMonth.substring(4)));
		date = buff.toString();
		buff = null;
		return date;
	}

	/**
	 * Adds the specified (signed) amount of time to the given time field, based
	 * on the calendar's rules.
	 * 
	 * @param date
	 *            the date
	 * @param field
	 *            the field
	 * @param amount
	 *            the amount
	 * @return the date
	 */
	public static Date add(Date date, int field, long amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, (int) amount);
		return calendar.getTime();
	}

	/**
	 * Gets the first day of offset month.
	 * 
	 * @param date
	 *            the date
	 * @param formatStr
	 *            the format str
	 * @param offSet
	 *            the off set
	 * @return the first day of offset month
	 */
	public static String getFirstDayOfOffsetMonth(String date, String formatStr, int offSet) {
		Date myDate = DateUtil.StringToDate(date, formatStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, offSet);
		String ret = DateUtil.DateToString(cal.getTime(), DateUtil.CM_SHORT_DATE_FORMAT);
		return ret;
	}

	/**
	 * Gets the first day of month.
	 * 
	 * @param date
	 *            the date
	 * @param cm_short_date_format2
	 *            the cm_short_date_format2
	 * @return the first day of month
	 */
	public static Date getFirstDayOfMonth(String date, String cm_short_date_format2) {

		return null;
	}

	/**
	 * Get the time several months ago.
	 * 
	 * @param months
	 * @return
	 */
	public static Date getTimeMonthsAgo(int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, 0 - months);

		return cal.getTime();
	}
	
	/**
	 * 获取目标日期月份计算
	 * 
	 * @param months
	 * @return
	 */
	public static Date getParamTimeMonthsAgo(Date date,int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 0 - months);
		return cal.getTime();
	}
	

	/**
	 *获取时间减去月，得到目标月第一天
	 */
	public static Date getFirstDayDecreaseMonth(Date myDate,  int offSet) {
		Date ResultDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, offSet);
		ResultDate = cal.getTime();
		return ResultDate;
	}
	
	/**
	 * date 转化 string类型
	 * @param date
	 * @return
	 */
	public static String formatDate2String(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(CM_ONLY_DATE_FORMAT);
		String sortDate = sdf.format(date);
		return sortDate;
	}
	/**
	 * 获取某星期几的日期
	 * @param n ,weekDay(默认周日为1.周一为2,依次类推)
	 * @return
	 */
	public static String getWeekDayDate(int n,int weekDay){
		  String day;
		  Calendar cal = Calendar.getInstance();
		  //n为推迟的周数，0本周，-1向前推迟一周，	1下周，依次类推
		  cal.add(Calendar.DATE, n*7);
		  switch(weekDay)
		  {
		  	case 1:
		  		cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		  		break;
		  	case 2:
		  		cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		  		break;
		  	case 3:
		  		cal.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY);
		  		break;
		  	case 4:
		  		cal.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
		  		break;
		  	case 5:
		  		cal.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);
		  		break;
		  	case 6:
		  		cal.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
		  		break;
		  	case 7:
		  		cal.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
		  		break;
		  }
		  day = DateUtil.DateToString(cal.getTime(),"yyyy-MM-dd");
		  return day;
	}
	
	/**
	 * 获取上一个月最后一天
	 * @return
	 */
	public static String getLastDayOfBeforeMonth(){
		  Calendar c = Calendar.getInstance();
		  c.add(Calendar.MONTH, -1);
		  //得到一个月最后一天日期(31/30/29/28)
		  int MaxDay=c.getActualMaximum(Calendar.DAY_OF_MONTH);
		  //按你的要求设置时间
		  c.set( c.get(Calendar.YEAR), c.get(Calendar.MONTH), MaxDay, 23, 59, 59);
		  //按格式输出
		  String gtime = DateUtil.DateToString(c.getTime(),"yyyy-MM-dd"); //上月最后一天
		  return gtime;
	}
	/**
	 * 获取当前月第一天
	 * @return
	 */
	public static String getFirstDayOfMonth2(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = DateUtil.DateToString(c.getTime(), "yyyy-MM-dd");
		return first;
	}
	 /** 
     * 当前上季度的结束时间
     * 
     * @return 
     */ 
    public static String getBeforeQuarterEndTime() { 
        Calendar c = Calendar.getInstance(); 
        int currentMonth = c.get(Calendar.MONTH) + 1; 
        Date now = null; 
        Date before = null;
        try { 
            if (currentMonth >= 1 && currentMonth <= 3) 
                c.set(Calendar.MONTH, 0); 
            else if (currentMonth >= 4 && currentMonth <= 6) 
                c.set(Calendar.MONTH, 3); 
            else if (currentMonth >= 7 && currentMonth <= 9) 
                c.set(Calendar.MONTH, 4); 
            else if (currentMonth >= 10 && currentMonth <= 12) 
                c.set(Calendar.MONTH, 9); 
            c.set(Calendar.DATE, 1); 
            now = c.getTime(); 
            Calendar c2 = Calendar.getInstance(); 
            c2.setTime(now);
            c2.set(Calendar.DATE, c2.get(Calendar.DATE) - 1);
            before = c2.getTime();
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return DateUtil.DateToString(before, "yyyy-MM-dd"); 
    } 
    /** 
     * 当前季度的第一天
     * 
     * @return 
     */ 
    public static String getQuarterFirstTime() { 
        Calendar c = Calendar.getInstance(); 
        int currentMonth = c.get(Calendar.MONTH) + 1; 
        Date now = null; 
        try { 
            if (currentMonth >= 1 && currentMonth <= 3) 
                c.set(Calendar.MONTH, 0); 
            else if (currentMonth >= 4 && currentMonth <= 6) 
                c.set(Calendar.MONTH, 3); 
            else if (currentMonth >= 7 && currentMonth <= 9) 
                c.set(Calendar.MONTH, 4); 
            else if (currentMonth >= 10 && currentMonth <= 12) 
                c.set(Calendar.MONTH, 9); 
            c.set(Calendar.DATE, 1); 
            now = c.getTime(); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return DateUtil.DateToString(now, "yyyy-MM-dd"); 
    } 
    
    /**
	 * 得到 两日期相差的天数 (精确到小时)
	 * @param smdate   小日期
	 * @param bdate	        大日期
	 * @return
	 */
	public static int getBetweenDays2(Date smdate,Date bdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		try {
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);

			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -999;
	}



    /**
     * 计算工作日
     * <p/>
     * 具体节日包含哪些,可以在HolidayMap中修改
     *
     * @param src     日期(源)
     * @param adddays 要加的天数
     * @throws throws [违例类型] [违例说明]
     * @version [s001, 2010-11-19]
     * @author shenjunjie
     */

    public static Calendar addDateByWorkDay(Calendar src, int adddays){
        boolean holidayFlag = false;
        for (int i = 0; i < adddays; i++)
        {
            //把源日期加一天
            src.add(Calendar.DAY_OF_MONTH, 1);
            holidayFlag = checkHoliday(src);
            if (holidayFlag)
            {
                i--;
            }
        }
        return src;

    }

    /**
     * 校验指定的日期是否在节日列表中
     * <p/>
     * 具体节日包含哪些,可以在HolidayMap中修改
     *
     * @param src 要校验的日期(源)
     * @version [s001, 2010-11-19]
     * @author shenjunjie
     */

    public static boolean checkHoliday(Calendar src){
        boolean result = false;
        //先检查是否是周六周日(有些国家是周五周六)
        if (src.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || src.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }
        /*for (Calendar c : holidayList)
        {
            if (src.get(Calendar.MONTH) == c.get(Calendar.MONTH) && src.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH)) {
                result = true;
            }
        }*/
        return result;
    }


    /**
     * 初始化节日List,如果需要加入新的节日,请在这里添加
     * <p/>
     * 加的时候请尽量使用Calendar自带的常量而不是魔鬼数字
     * <p/>
     * 注:年份可以随便写,因为比的时候只比月份和天
     *
     * @version [s001, 2010-11-19]
     * @author shenjunjie
     */

    @SuppressWarnings("unused")
	private static List<Calendar>  initHolidayList() {
        List<Calendar> holidayList = new ArrayList<Calendar>(); //五一劳动节 Calendar may1 = Calendar.getInstance(); may1.set(Calendar.MONTH, Calendar.MAY); may1.set(Calendar.DAY_OF_MONTH, 1); holidayList.add(may1); Calendar may2 = Calendar.getInstance(); may2.set(Calendar.MONTH, Calendar.MAY); may2.set(Calendar.DAY_OF_MONTH, 2); holidayList.add(may2); Calendar may3 = Calendar.getInstance(); may3.set(Calendar.MONTH, Calendar.MAY); may3.set(Calendar.DAY_OF_MONTH, 3); holidayList.add(may3);
        Calendar h3 = Calendar.getInstance();
        h3.set(2000, 1, 1);
        holidayList.add(h3);
        Calendar h4 = Calendar.getInstance();
        h4.set(2000, 12, 25);
        holidayList.add(h4);
        //中国母亲节：五月的第二个星期日
        Calendar may5 = Calendar.getInstance();
        //设置月份为5月
        may5.set(Calendar.MONTH, Calendar.MAY);
        //设置星期:第2个星期
        may5.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);

        //星期日
        may5.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
//        System.out.println(may5.getTime());
        holidayList.add(may5);
        return holidayList;
    }
    
    public static boolean isWeekEnd(Date src){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(src);
    	
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }
    
    public static Date getBeginDateOfDay(Date source){
    	Calendar calendar=Calendar.getInstance();   
		calendar.setTime(source); 
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
    }
    
    public static Date getEndDateOfDay(Date source){
    	Calendar calendar=Calendar.getInstance();   
		calendar.setTime(source); 
		calendar.set(Calendar.HOUR_OF_DAY,23);
		calendar.set(Calendar.MINUTE,59);
		calendar.set(Calendar.SECOND,59);
		calendar.set(Calendar.MILLISECOND,999);
		return calendar.getTime();
    }
    
    public static double secondTimeDifference(Date d1,Date d2){
    	
    	return (d1.getTime()-d2.getTime())/1000;
    }
    
    public static double minuteTimeDifference(Date d1,Date d2){
    	
    	return DateUtil.secondTimeDifference(d1,d2)/60;
    }
    
    public static double hourTimeDifference(Date d1,Date d2){
    	
    	return DateUtil.minuteTimeDifference(d1,d2)/60;
    }

}
