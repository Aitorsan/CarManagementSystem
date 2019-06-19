package autoUdima.systemUtilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;

public class StringConverter
{
	 public static Calendar stringToCalendar(String date, DateFormat dateFormat) throws ParseException  
	 {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(dateFormat.parse(date));
	        return cal;
	}
}
