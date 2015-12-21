package com.wechat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
public class DateUtil {
  
    /**
	 * 将页面上传过来的字符串型时间转化成long类型
	 * 
	 * @param dateInString
	 *            字符串型时间
	 * @return
	 * datecode == "yyyy-MM-dd" or datecode == "yyyy-MM-dd HH:mm:ss"
	 */
	public static Long convertDateInStringToLong(String dateInString, String datecode) {
		if (dateInString == null || dateInString.equals(""))
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(datecode);
		try {
			return new Long(dateFormat.parse(dateInString).getTime()/1000);
		} catch (ParseException parseException) {
			return new Long(new Date().getTime());
		}
	}
	
	public static Long convertDateInStringToLong2(String dateInString, String datecode) {
		if (dateInString == null || dateInString.equals("")){
			return System.currentTimeMillis();
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(datecode);
		try {
			return new Long(dateFormat.parse(dateInString).getTime());
		} catch (ParseException parseException) {
			return new Long(new Date().getTime());
		}
	}

	/**
	 * 将Long表示的时间转换为日期字符串
	 * 
	 * @param dateTime
	 * @return 转换后的字符串 如果Long为null，则返回null
	 * datecode == "yyyy-MM-dd" or datecode == "yyyy-MM-dd HH:mm:ss"
	 * @throws ParseException
	 */
	public static String convertLongToDateString(Long dateTime, String datecode) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(datecode);
		String dateStr = null;
		if (dateTime != null) {
			dateStr = sdf.format(new Date(dateTime*1000));
		}
		return dateStr;
	}
	
	public static String convertLongToDateString2(Long dateTime, String datecode) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(datecode);
		String dateStr = null;
		if (dateTime != null) {
			dateStr = sdf.format(new Date(dateTime));
		}
		return dateStr;
	}
	
	/**
	 * 将long型的时间戳转化为日历类型
	 * <p>
	 * @param currentTimestamp:当前时戳
	 * @return Calendar 日历对象
	 * @author zhaochunjiao
	 */
	private static Calendar convertLongToCalendar(long currentTimestamp)
	{
		//将毫秒数转化为日期
		Date date = new Date(currentTimestamp);
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.setTime(date);
		//System.out.println("初始："+new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss").format(cal.getTime()));
		return cal;
	}
	
	/**
	 * 得到给定日期（long型的时间戳）所在星期的星期一的开始时戳和星期天的结束时戳
	 * <p>
	 * @param currentTimestamp:当前时戳
	 * @return 星期一的开始时戳和星期天的结束时戳组成的数组
	 * @author zhaochunjiao
	 */
	@SuppressWarnings("static-access")
	public static long[] getMondayAndSundayOfWeek(long currentTimestamp){
		Calendar cal = convertLongToCalendar(currentTimestamp);

		long[] dayArray = new long[2];
		//System.out.println("Tools--dayInWeek: ");
		try {
			int days = cal.get(cal.DAY_OF_WEEK) - 2;
			int dates = cal.get(cal.DAY_OF_MONTH);
			int months = cal.get(cal.MONTH);
			int years = cal.get(cal.YEAR);
			
			cal.set(years, months, dates - days + 0, 0, 0 ,0);
			long monday = cal.getTime().getTime();
			//System.out.println(new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss").format(cal.getTime()));
			cal.set(years, months, dates - days + 6, 23, 59, 59);
			long sunday = cal.getTime().getTime();
			//System.out.println(new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss").format(cal.getTime()));
			dayArray[0] = monday;
			dayArray[1] = sunday;
		} catch (Exception ex) {
			//System.out.println("Tools--dayInWeek: " + ex.toString());
		}
		return dayArray;
	}
	
	/**
	 * 得到给定日期（long型的时间戳）所在月份的第一天和最后一天的时戳
	 * <p>
	 * @param currentTimestamp:当前时戳
	 * @return 月份的第一天和最后一天的时戳组成的数组
	 * @author zhaochunjiao
	 */
	@SuppressWarnings("static-access")
	public static long[] getFirstAndLastDaysOfMonth(long currentTimestamp) {
		Calendar cal = convertLongToCalendar(currentTimestamp);
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		long[] days = new long[2];
		
		int months = cal.get(cal.MONTH);
		int years = cal.get(cal.YEAR);
		cal.set(years, months, 1, 0 ,0 ,0);
		long firstDay = cal.getTime().getTime();
		//System.out.println(new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss").format(cal.getTime()));
		cal.set(years, months, maxDay, 23, 59, 59);
		long lastDay = cal.getTime().getTime();
		//System.out.println(new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss").format(cal.getTime()));
		days[0] = firstDay;
		days[1] = lastDay;

		return days;
	}
	
	/**
	 * 得到给定日期（long型的时间戳）所在年份的第一天和最后一天的时戳
	 * <p>
	 * @param currentTimestamp:当前时戳
	 * @return 年份的第一天和最后一天的时戳组成的数组
	 * @author zhaochunjiao
	 */
	@SuppressWarnings("static-access")
	public static long[] getFirstAndLastDaysOfYear(long currentTimestamp) {
		Calendar cal = convertLongToCalendar(currentTimestamp);
		long[] days = new long[2];
		
		int years = cal.get(cal.YEAR);
		cal.set(years, 0, 1, 0 ,0 ,0);
		long firstDay = cal.getTime().getTime();
		//System.out.println(new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss").format(cal.getTime()));
		cal.set(years, 11, 31, 23, 59, 59);
		long lastDay = cal.getTime().getTime();
		//System.out.println(new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss").format(cal.getTime()));
		days[0] = firstDay;
		days[1] = lastDay;

		return days;
	}
	
    /** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getCurrYearFirst(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        Date currYearFirst = calendar.getTime();  
        return currYearFirst;  
    }  
      
    /** 
     * 获取某年最后一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getCurrYearLast(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
        Date currYearLast = calendar.getTime();  
          
        return currYearLast;  
    }  
	
	/**
	 * 得到给定日期（long型的时间戳） 的零点和24点的时戳
	 * <p>
	 * @param currentTimestamp:当前时戳
	 * @return 该日期的零点和24点的时戳数组
	 * @author zhaochunjiao
	 */
	@SuppressWarnings("static-access")
	public static long[] getOneDayTimestamp(long currentTimestamp) {
		Calendar cal = convertLongToCalendar(currentTimestamp);
		long[] timeArray = new long[2];
		
		int dates = cal.get(cal.DATE);
		int months = cal.get(cal.MONTH);
		int years = cal.get(cal.YEAR);
		cal.set(years, months, dates, 0, 0 ,0);
		long startTime = cal.getTime().getTime();
		//System.out.println(new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss").format(cal.getTime()));
		cal.set(years, months, dates, 23, 59, 59);
		long endTime = cal.getTime().getTime();
		//System.out.println(new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss").format(cal.getTime()));
		timeArray[0] = startTime;
		timeArray[1] = endTime;

		return timeArray;
	}
	
	/**
	 * 得到给定日期（long型的时间戳） 的n天后23:59:59的时戳<br >
	 * <font color = "red">
	 * 给定的日期算一天，如：给定日期是2012-2-15，要得到7天后23:59:59的时戳时戳 <br >
	 * 就是得到2012-2-21 23:59:59的时戳
	 * </font>
	 * @param currentTimestamp:当前时戳
	 * @param days: 天数
	 * @return n天后23:59:59的时戳
	 * @author zhaochunjiao
	 * @create 2012-2-15 上午09:08:47
	 */
	public static long getTimestampOfAfterDay(long currentTimestamp, int days) {
		long mills = (days-1)*24*60*60*1000;//n天的毫秒数
		long sevenDaysTime = currentTimestamp + mills;//n天后的毫秒数
		long[] oneDayTimestamp = getOneDayTimestamp(sevenDaysTime);//得到给定日期（long型的时间戳） 的零点和24点的时戳

		return oneDayTimestamp[1];
	}
	
	/***
	 * 计算两个日期的时间差
	 * @param date1
	 * @param date2
	 * @param datecode == "yyyy-MM-dd" or datecode == "yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String getTimeDifference(String date1, String date2, String datecode){
		String timeStr = "";
		try {
			SimpleDateFormat sdf=new SimpleDateFormat(datecode);
			Date d1=sdf.parse(date1);
			Date d2=sdf.parse(date2);
			long daysBetween=(d2.getTime()-d1.getTime()+1000000)/(3600*24*1000);
			timeStr = Math.abs(daysBetween)+"天";
			if(datecode.equals("yyyy-MM-dd HH:mm:ss")){
				String time1 = date1.split(" ")[1];
				String time2 = date2.split(" ")[1];
				int hour1 = Integer.parseInt(time1.split(":")[0]);
				int hour2 = Integer.parseInt(time2.split(":")[0]);
				int hour = 0;
				if(daysBetween == 0){
					if(date1.split(" ")[0].trim().equals(date2.split(" ")[0].trim())){
						hour = hour1 - hour2;
					}else{
						hour = 24 - (hour1 - hour2);
					}
				}else{
					hour = Math.abs(hour1 -hour2);
				}
				int minute1 = Integer.parseInt(time1.split(":")[1]);
				int minute2 = Integer.parseInt(time2.split(":")[1]);
				int minute = minute2 - minute1;
				if(minute < 0){
					minute = 60 - Math.abs(minute);
					hour = hour - 1;
				}
				if(hour < 0){
					hour = 0;
				}
				timeStr += Math.abs(hour)+"小时"+Math.abs(minute)+"分钟";
			}
			System.out.println(timeStr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return timeStr;
	}
	
    public static int printDifference(Date startDate, Date endDate){
    	int minute = 0;
    	try {
            long different = endDate.getTime() - startDate.getTime();
            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;
            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;
            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;
            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;
            long elapsedSeconds = different / secondsInMilli;
            minute = (int)elapsedDays;
		} catch (Exception e) {
			// TODO: handle exception
		}
        return minute;
    }
    
    public static int printDifference1(Date startDate, Date endDate){
    	int minute = 0;
    	try {
            long different = endDate.getTime() - startDate.getTime();
            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;
            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;
            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;
            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;
            long elapsedSeconds = different / secondsInMilli;
            minute = (int)elapsedHours;
		} catch (Exception e) {
			// TODO: handle exception
		}
        return minute;
    }
    
    public static int printDifference2(Date startDate, Date endDate){
    	int minute = 0;
    	try {
            long different = endDate.getTime() - startDate.getTime();
            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;
            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;
            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;
            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;
            long elapsedSeconds = different / secondsInMilli;
            minute = (int)elapsedMinutes;
		} catch (Exception e) {
			// TODO: handle exception
		}
        return minute;
    }
	
	public static Integer getMinuteDifference(String date1, String date2){
		Integer minute = 0;
		Date date = new Date();  //实例化日期类型  
		String dqsj = date.toLocaleString();   //获得当前系统时间  
		try {
			long startT = fromDateStringToLong(date1); //定义上机时间  
			long endT = fromDateStringToLong(date2);  //定义下机时间  
			  
			long ss=(startT-endT)/(1000); //共计秒数  
			int MM = (int)ss/60;   //共计分钟数  
			int hh=(int)ss/3600;  //共计小时数  
			int dd=(int)hh/24;   //共计天数  
//			System.out.println("共"+dd+"天 准确时间是："+hh+" 小时 "+MM+" 分钟"+ss+" 秒 共计："+ss*1000+" 毫秒");   
			System.out.println(MM+"分钟");
			minute = MM;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return minute;
	}
	
	public static long fromDateStringToLong(String inVal) { //此方法计算时间毫秒  
		  Date date = null;   //定义时间类型         
		  SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		  try {   
			  date = inputFormat.parse(inVal); //将字符型转换成日期型  
		  } catch (Exception e) {   
//			  e.printStackTrace();   
		  }   
		  return date.getTime();   //返回毫秒数  
	}   
	
	public static int getCurrentTime(){
		return (int) (System.currentTimeMillis()/1000);
	}
}
