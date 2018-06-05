package autoUdima.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Class Car
 * <p>Clase car, this class define all the methods and
 * data related to all cars that are managed by the system.<br>
 * this class implements serialazable interface.</p>
 * @author Aitor Sanmartin Ferreira
 * @see <a https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html</a>
 */
public class Car implements Serializable {

	/**
	 * serialVersionUId to be able to identify
	 * an specific car data during deserialization procces
	 * @see <a https://docs.oracle.com/javase/9/docs/api/javax/naming/Name.html#serialVersionUID">https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html</a>
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Atributo estatico
	transient private static final double IVA = 1.21;

	//Atributos
	private String description;
	private State state;
	private String framNumber;
	private Calendar registrationDate;
	private Typology type;
	private double basePrice;
	private int identificationNumber;
	private Calendar systemRegistrationDate;

	/*-------------------------------------------*
	 * Constructor                               *
	 *-------------------------------------------*/
	public Car( int identificationNumber){

		this.identificationNumber = identificationNumber;

	}

    //would be better to make a builder next time to avoid such a big list of parameters
	public Car( int identificationNumber, String description, Typology typology, State state, String bastidor, Calendar registrationDate, double basePrice, Calendar registationDate  ){

		this.identificationNumber = identificationNumber;
		this.description = description;
		this.type = typology;
		this.state = state;
		this.framNumber = bastidor;
		this.registrationDate = registrationDate;
		this.basePrice = basePrice;
		this.systemRegistrationDate = registationDate;

	}
	/*-------------------------------------------*
	 * Setters                                   *
	 *-------------------------------------------*/

	public void setSystemRegistartionDate(Calendar systemRegistrationDate) {
		this.systemRegistrationDate = systemRegistrationDate;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setState(State state) {
		this.state = state;
	}
	public void setFrameNumber(String frameNumber) {
		this.framNumber = frameNumber;
	}

	public void setRegistrationDate(Calendar registrationDate) {
		this.registrationDate= registrationDate;
	}
	public void  setCarType(Typology typology) {
		this.type=typology;

	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}


	/*-------------------------------------------*
	 * Getters                                   *
	 *-------------------------------------------*/

	/*
	 * Get descripcion
	 */
	public String getDescription() {
		return description;
	}
	/*
	 * Get state
	 */
	public String getState() {
		return state.toString();
	}

	/*
	 * Get frameNumber
	 */
	public String getFrameNumber() {
		return framNumber;
	}

	/*
	 * return the registration date
	 */
	public String getRegistrationDate(){
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(registrationDate.getTime());
	}
	/*
	 * Get tipology
	 */
	public String getCarType() {

		return type.toString();
	}
	

	/*
	 * Get base price
	 */
	public double getBasePrice() {
		return basePrice;
	}

	/*
	 * Get identificador
	 */
	public String getIdentificador() {
		return String.valueOf(identificationNumber);
	}

	/*
	 * Get system Date registration
	 */
	public String getSystemRegistrationDate() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss a");
		return format.format(systemRegistrationDate.getTime());
	}

	/*-------------------------------------------*
	 * Methods                                   *
	 *-------------------------------------------*/
	/**
	 * This method compute the final price
	 * <br>after apply taxes.
	 * @return basPrice: sell price
	 */
	public double sellPrice() {
		return basePrice *IVA;
	}



	/*
	 * 	 
	public String getFechaMatriculacion(){
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(fechaMatriculacion.getTime());
	}


		public int antiguedad() {

		Calendar date = Calendar.getInstance();
		date.setLenient(false);

		Date actual = date.getTime() ;
		Date altaSistema =fechaAltaEnSistema.getTime();
		long dif = Math.abs(altaSistema.getTime() - actual.getTime());
		return (int) TimeUnit.MILLISECONDS.toDays(dif);
	}
	 */
	/**
	 * retorna la antiguedad del coche desde que se dio de
	 * alta en el sistema, hasta el dia actual 
	 * @return antiguadad: en dias
	 */
	public int antiguedad() {

		Calendar date = Calendar.getInstance();
		date.setLenient(false);

		Date actual = date.getTime() ;
		Date primeraMatriculacion =date.getTime();
		long dif = Math.abs(primeraMatriculacion.getTime() - actual.getTime());
		return (int) TimeUnit.MILLISECONDS.toDays(dif);
	}

	/*
	 * Metodo toString sobreescrito para la clase Coche
	 */
	@Override
	public String toString() {

		return String.format("Coche:\n->Identificador:%d\n->Descripcion:%s\n->tipologia:%s\n->NumeroBastidor:%s\n->FechaPrimeraMatriculacion:%s"
				+ "\n->Antiguedad(dias):%d\n->Estado:%s\n->PrecioBase:%.2f\n->PrecioVenta:%.2f\n->FechaDeCreacion:%s", getIdentificador(),
				getDescription(),getCarType(),getFrameNumber(),getRegistrationDate(),antiguedad(),getState(),getBasePrice(),sellPrice(),getSystemRegistrationDate());
	}


}
