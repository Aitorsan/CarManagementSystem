package autoUdima.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import autoUdima.systemUtilities.StringConverter;


/**
 * AutoUdimaSystem class 
 * @author aitorSf
 *
 */
public class AutoUdimaSystem implements ISystem {
	//Constants

	/*
	 *  Indexes of the array where the different information about the cars is save.
	 *  it's use to take data from the array,to show it in the table
	 */

	private static final int ID=0;
	private static final int DESCRIPTION=1;
	private static final int TYPOLOGY=2;
	private static final int BASTIDOR=3;
	private static final int FIRST_MATRICULATION_DATE=4;
	private static final int CAR_STATE = 5;
	private static final int DAYS_ON_THE_SYSTEM=6;
	private static final int BASE_PRICE=7;
	private static final int SALE_PRICE=8;
	private static final int REGISTER_DATE=9;

	/*
	 * Indexes to set the information to create a new car. Once we get the information
	 * From the user, we use an array to store it. After that we need to access the data,
	 * so it will always be save on the same position and always will have the same size.
	 * So for readability we use Those variables to to know what information we are getting 
	 * from the array.  
	 */
	static final int DESCRIPTION_DATA = 0;
	static final int TYPOLOGY_DATA = 1;
	static final int STATE =2;
	static final int CAR_FRAME_NUMBER = 3;
	static final int MATRICULATION_DATE = 4;
	static final int BASE_PRICE_DATA = 5;

	// Number of columns in the table, that correspond, to all the data that a car can contain.
	private static final int DATA_TABLE_COLUMNS = 10;

	// Attributes
	private String rutaFichero;//nombre o ruta del fichero sobre el que se quiere trabajar
	private ArrayList<Car> stockCoches; // array dinamico donde se almacenan los datos una vez cargados
	private DateFormat dateFormat; //formato para las fechas
	boolean exit = false; // se usa para acabar el programa
	private int maxIdentificationNumber = 9999;// el valor maximo que puede tener un número de identifiación
	private int minIdentificationNumber = 1000;// el valo mínimo que puede tener un número de identificación
	private ObjectOutputStream writer;

	/**
	 * Constructor de la clase Sistema, el constructor tiene como parámetros la ruta
	 * del fichero donde estan todos los datos relacionados con los coches.<br>
	 * Inicializa tambien algunas variables.
	 *
	 * @param rutaFichero
	 */
	public AutoUdimaSystem(String rutaFichero) {
		this.rutaFichero = rutaFichero;
		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
	}

	/**
	 * Ejecuta el inicio del programa esta funcion es la encargada de lanzar el
	 * programa
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */

	public  String cargarDatos() throws IOException, ClassNotFoundException ,FileNotFoundException{

		//creamos un ArrayList de Coches. Por defecto tendra una capacidad de 10 elementos
		stockCoches= new  ArrayList<Car>();
		String message = "Bienvenido !! El proceso de carga, de datos ha finalizado correctamente";

		// we open a file input stream and an ObjectInputstream to deserialize
		FileInputStream input = new FileInputStream(rutaFichero);
		ObjectInputStream objbuffer = new ObjectInputStream(input);


		while(input.available() != 0 ) {

			stockCoches.add( (Car) objbuffer.readObject());

		}

		input.close();
		objbuffer.close();

		if(stockCoches.isEmpty()) {
			message += ": La base de datos esta vacia";
		}

		return message;


	}

	/**
	 * Method use to save the data changes on the system
	 * @throws IOException
	 */
	@Override
	public void saveData() throws IOException {

		FileOutputStream file = new FileOutputStream("almacen-coches.txt");
		writer = new ObjectOutputStream(file);

		if(!stockCoches.isEmpty() || stockCoches != null) {

			for( Car c : stockCoches) {

				writer.writeObject(c);
			}


		}

		file.close();
		writer.close();

	}




	/**
	 * Gets all the information available on the list and return it in a String two dimensional array form
	 * @return String[][] car information
	 */

	public String[][] getCarInformation(){

		if(stockCoches.isEmpty()) {
			return new String[][] {
				{"datos","datos","datos","datos","datos","datos","datos","datos","datos","dat"},

			};
		}
		String[][] data= new String[stockCoches.size()][DATA_TABLE_COLUMNS];

		System.out.println( data.length);
		System.out.println( stockCoches.size());
		for(int carIndex = 0; carIndex < stockCoches.size();++carIndex) {

			data[carIndex][ID]= String.valueOf(stockCoches.get(carIndex).getIdentificador());
			data[carIndex][DESCRIPTION]= stockCoches.get(carIndex).getDescription();
			data[carIndex][TYPOLOGY]= stockCoches.get(carIndex).getCarType();//LOS ENUM ME ESTAN DANDO PROBLEMAS
			data[carIndex][BASTIDOR]= stockCoches.get(carIndex).getFrameNumber();
			data[carIndex][FIRST_MATRICULATION_DATE]= stockCoches.get(carIndex).getRegistrationDate();
			data[carIndex][CAR_STATE]= stockCoches.get(carIndex).getState();//LOS ENUM ME ESTAN DANDO PROBLEMAS POR ALGUNA RAZON QUE DESCONOZCO
			data[carIndex][DAYS_ON_THE_SYSTEM]= String.valueOf(stockCoches.get(carIndex).antiguedad());
			data[carIndex][BASE_PRICE]= String.valueOf(stockCoches.get(carIndex).getBasePrice());
			data[carIndex][SALE_PRICE]= String.valueOf(stockCoches.get(carIndex).sellPrice());
			data[carIndex][REGISTER_DATE]= stockCoches.get(carIndex).getSystemRegistrationDate();

		}

		return data;

	}

	/**
	 * Set the date of Registation, of a new car on the system
	 * @param date
	 */

	public void setSysRegistrationDate(String date) {

		DateFormat fromat = new SimpleDateFormat("dd/MM/yyyy");
		Date temp;
		try {
			temp = fromat.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(temp);

		} catch (ParseException e) {

			e.printStackTrace();
		}

	}		

	/**
	 * This method create a new car on the system
	 * @param data
	 * @throws NumberFormatException 
	 * @throws ParseException 
	 */
	@Override
	public void addNewCar(String[] data) throws NumberFormatException, ParseException {

		// if the the data its null  we do not proceed to check the second condiction
		if((data != null ) | (data.length != 0)) {

			stockCoches.add(new Car (  generateIdentificationNumber(),
					data[DESCRIPTION_DATA],
					Typology.getTypeFromString(data[TYPOLOGY_DATA]),
					State.getStateFromString(data[STATE]),
					data[CAR_FRAME_NUMBER],
					StringConverter.stringToCalendar(data[MATRICULATION_DATE], dateFormat),
					Double.parseDouble(data[BASE_PRICE_DATA]),
					Calendar.getInstance())
					);

		}else {


			stockCoches.add(new Car (  generateIdentificationNumber(),
					data[DESCRIPTION_DATA],
					Typology.getTypeFromString(data[TYPOLOGY_DATA]),
					State.getStateFromString(data[STATE]),
					data[CAR_FRAME_NUMBER],
					StringConverter.stringToCalendar(data[MATRICULATION_DATE], dateFormat),
					Double.parseDouble(data[BASE_PRICE_DATA]),
					Calendar.getInstance())
					);
		}


	}		


	/**
	 * Get the data in a form of array 
	 * @return
	 */
	public ArrayList<Car>getDataFromList() {
		return stockCoches;
	}



	/**
	 * Search an specific car index 
	 * @param car Id
	 */
	public int searchCarIndex(String Id) {
		int indexToDelete= -1;

		if( !stockCoches.isEmpty()) {

			for( int i = 0; i < stockCoches.size() ; ++ i) {
				if(stockCoches.get(i).getIdentificador().equals(Id)) {
					indexToDelete = i;
				}
			}  


		}

		return indexToDelete;

	}

	/**
	 * Delete a car at a given index position
	 * @param index position
	 * @throws IOException 
	 */
	@Override
	public void deleteCar(int index) throws IOException {
		stockCoches.remove(index);

		this.saveData();


	}

	/**
	 * This method generates an unique identification Number of 4 digits.<br>
	 * The method, returns random numbers between 0 y 9000 (
	 * 9000 it is not included), the minimum possible value is 1001 and  (8999 + 1000) the maximum value<br>
	 * that is 9999, the last 4 digits positive number avaliable on the system.
	 * @return 4 digits identfication number
	 */
	private int generateIdentificationNumber() {
		Random rand = new Random();
		return rand.nextInt((maxIdentificationNumber - minIdentificationNumber) + 1) + minIdentificationNumber;
	}



}// End AutoUdimaSystem class
