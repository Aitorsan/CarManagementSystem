package autoUdima.model;

/**
 * Enumeration represent different avaliable car types
 * @author aitorSf
 *
 */
public enum Typology {
	FAMILIAR("Familiar"),
	DEPORTIVO("Deportivo"),
	FURGONETA("Furgoneta");

	private  String type;

	Typology(String type) {
		this.type = type;
	}

	@Override
	public String toString() {

		return type;
	}
	/**
	 * Return an string representation of the enumeration
	 * @param type
	 * @return
	 */
	public static Typology getTypeFromString(String type) {
		Typology carType = null;
		   
		if( type != null) {
			
			if( type.toLowerCase().equals("familiar")) {
				carType= FAMILIAR;
			}else if(type.toLowerCase().equals("deportivo")) {
				carType = DEPORTIVO;
			}else if(type.toLowerCase().equals("furgoneta")) {
				carType = FURGONETA;
			}
		}
			return carType;
	}

}
