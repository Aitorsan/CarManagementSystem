package autoUdima.systemUtilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;

public class StringConverter {
	
	
	/**
	 * Transform a String into a Calendar class
	 * @param date
	 * @return Calendar object
	 * @throws ParseException
	 */
		public static Calendar stringToCalendar(String date, DateFormat dateFormat) throws ParseException  {

	        Calendar cal = Calendar.getInstance();
	        cal.setTime(dateFormat.parse(date));
	        return cal;
		}
}
