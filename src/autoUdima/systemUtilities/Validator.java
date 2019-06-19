package autoUdima.systemUtilities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import autoUdima.model.Car;

public class Validator 
{
	public static boolean matchPattern(String pattern, String expresion)
	{
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(expresion);
		if (!m.matches())
		{
			return false;
		}
		return true;
	}
	/**
	 * Check if the frame number introduced by the user exist already in the data base
	 * @param FrameNumber
	 */
	public static boolean checkFrameNumberOriginality(String FrameNumber, ArrayList<Car> stockCoches)  
	{
		boolean isValid = true;
		if(!stockCoches.isEmpty()) 
		{
			for( Car c : stockCoches)
			{
				if(c.getFrameNumber().equals( FrameNumber))
				{
					isValid = false;
				}
			}
		}
	        return isValid;
	}
	/**
	 * Method that return the correct color for the letters.<br>
	 * It is call each time the user type somenthing in the number frame text field area.
	 * Return a <b>red</b>color if the pattern its not valid, <b>green</b> if its valid.
	 * @param frameFlag if its true, means that we are testing a frame number, so the algorithm its different
	 * @param pattern   the patter to match the string
	 * @param data      the string that should match the pattern
	 * @return Color    the resulting color
	 */
	public static Color getValidationColor(String pattern , String data, boolean frameFlag) 
	{
		Color backGroundColor = null;
		boolean valid = true;
		for(int i  = 0; i < data.length(); ++i) 
		{
			/*                                                   index     0 1 2 3 4 5 6 7 8 9
			 * the 3 and 6 are the indexes where the lines should go ( ej: 3 3 3 - 3 3 - 3 3 3);
			 */
			if((i == 3 || i == 6) && frameFlag) 
			{
				if(!(matchPattern("-", data.substring(i,i+1))))
					valid = false;
			}
			else
			{
				if(!(matchPattern(pattern, data.substring(i,i+1))))
					valid = false;
			}
		}

		if(valid ) 
		{
			backGroundColor = new Color(40,205,130);
		}
		else if(!valid )
		{
			backGroundColor=new Color(255,0,0);
		}
		return backGroundColor;
	}
}
