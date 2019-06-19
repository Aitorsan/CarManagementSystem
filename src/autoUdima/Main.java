package autoUdima;

import autoUdima.control.WindowHandlerControl;

 /** @author Aitor Sanmartin Ferreira (AA2) Metodologia de la programacion */
public class Main 
{
	public static void main(String[] args) 
	{
		java.awt.EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				new WindowHandlerControl();  
			}
		});
	}
}
