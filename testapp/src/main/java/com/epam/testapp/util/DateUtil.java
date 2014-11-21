package com.epam.testapp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
	
	public static String getStringFromDate(Date date, Locale locale){
		
		ResourceBundle bundle = ResourceBundle.getBundle("com.epam.testapp.properties.ApplicationResources", locale);
		String pattern = bundle.getString("format.date");
		
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		String dateString = dateFormat.format(date);
		
		return dateString;
	}
	
	public static Date getDateFromString( String dateString, Locale locale ){
		
		ResourceBundle bundle = ResourceBundle.getBundle("com.epam.testapp.properties.ApplicationResources", locale);
		String pattern = bundle.getString("format.date");
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static boolean isDateCorrect( String dateString, String localeName ){
		
		ResourceBundle bundle = ResourceBundle.getBundle("com.epam.testapp.properties.ApplicationResources", new Locale( localeName ));
		String datePattern = bundle.getString("pattern.date");
		
		Pattern pattern = Pattern.compile( datePattern );
		Matcher matcher = pattern.matcher( dateString );
		if(matcher.find()){
			return true;
		}
		return false;
	}
	
	private DateUtil(){}
}
