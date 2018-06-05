package autoUdima;

import autoUdima.control.WindowHandlerControl;
/**
 * All methos of the program are comented in blue, also the inner clases.<br>
 * The rest of the code its commented in green, algorithms, functions and some <br>
 * other relevant points of the system.
 * @author Aitor Sanmartin Ferreira (AA2) Metodologia de la programacion
 *
 */

public class Main {

	public static void main(String[] args) {
	
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
		
                  new WindowHandlerControl();
   
		  
			}
			
		});
		
		
	}

}
