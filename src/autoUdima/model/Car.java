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
public class Car implements Serializable 
{
	private static final long serialVersionUID = 1L;
	transient private static final double IVA = 1.21;
	private String description;
	private State state;
	private String framNumber;
	private Calendar registrationDate;
	private Typology type;
	private double basePrice;
	private int identificationNumber;
	private Calendar systemRegistrationDate;

	public Car( int identificationNumber)
	{
		this.identificationNumber = identificationNumber;
	}

	public Car( int identificationNumber, String description, Typology typology, State state, String bastidor, Calendar registrationDate, double basePrice, Calendar registationDate )
	{
		this.identificationNumber = identificationNumber;
		this.description = description;
		this.type = typology;
		this.state = state;
		this.framNumber = bastidor;
		this.registrationDate = registrationDate;
		this.basePrice = basePrice;
		this.systemRegistrationDate = registationDate;
	}
	
	public void setSystemRegistartionDate(Calendar systemRegistrationDate)
	{
		this.systemRegistrationDate = systemRegistrationDate;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public void setState(State state) 
	{
		this.state = state;
	}
	
	public void setFrameNumber(String frameNumber) 
	{
		this.framNumber = frameNumber;
	}

	public void setRegistrationDate(Calendar registrationDate) 
	{
		this.registrationDate = registrationDate;
	}
	
	public void  setCarType(Typology typology) 
	{
		this.type = typology;
	}
	
	public void setBasePrice(double basePrice) 
	{
		this.basePrice = basePrice;
	}

	public String getDescription() 
	{
		return description;
	}
	
	public String getState() 
	{
		return state.toString();
	}

	public String getFrameNumber() 
	{
		return framNumber;
	}

	public String getRegistrationDate()
	{
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(registrationDate.getTime());
	}

	public String getCarType() 
	{
		return type.toString();
	}

	public double getBasePrice() 
	{
		return basePrice;
	}

	public String getIdentificador() 
	{
		return String.valueOf(identificationNumber);
	}

	public String getSystemRegistrationDate() 
	{
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss a");
		return format.format(systemRegistrationDate.getTime());
	}

	public double sellPrice() 
	{
		return basePrice *IVA;
	}

	public int antiguedad() 
	{
		Calendar date = Calendar.getInstance();
		date.setLenient(false);
		Date actual = date.getTime() ;
		Date primeraMatriculacion =date.getTime();
		long dif = Math.abs(primeraMatriculacion.getTime() - actual.getTime());
		return (int) TimeUnit.MILLISECONDS.toDays(dif);
	}

	@Override
	public String toString() 
	{
		return String.format("Coche:\n->Identificador:%d\n->Descripcion:%s\n->tipologia:%s\n->NumeroBastidor:%s\n->FechaPrimeraMatriculacion:%s"
				+ "\n->Antiguedad(dias):%d\n->Estado:%s\n->PrecioBase:%.2f\n->PrecioVenta:%.2f\n->FechaDeCreacion:%s", getIdentificador(),
				getDescription(),getCarType(),getFrameNumber(),getRegistrationDate(),antiguedad(),getState(),getBasePrice(),sellPrice(),getSystemRegistrationDate());
	}

}
