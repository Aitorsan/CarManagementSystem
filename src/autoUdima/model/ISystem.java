package autoUdima.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
/**
 *  Interface for all car management systems
 * @author aitorSf
 *
 */
public interface ISystem {
	
	/**
	 * Load the data from a file
	 * @throws IOException 
	 * @throws ClassNotFoundException,FileNotFoundException 
	 */
	public String cargarDatos() throws IOException, ClassNotFoundException,FileNotFoundException;

	/**
	 * Save data into a file
	 * @throws IOException
	 */
	public void saveData() throws IOException ;
	
	/**
	 * Get information for an specific car
	 * @return
	 */
	public String[][] getCarInformation();
	
	/**
	 * Set the system registration date
	 * @param date
	 */
	public void setSysRegistrationDate(String date);
	
	/**
	 * Register a new car on the system
	 * @param data
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	public void addNewCar(String[] data) throws NumberFormatException, ParseException;
	
	
	/**
	 * Search an specific car index 
	 * @param read Buffer de entrada
	 */
	public int searchCarIndex(String Id);
	
	/**
	 * Delete a car from the system
	 * @param index
	 * @throws IOException
	 */
	public void deleteCar(int index) throws IOException;
}
