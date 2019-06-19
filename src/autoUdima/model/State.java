package autoUdima.model;

public enum State 
{
       NUEVO("Nuevo"),
       SEMINUEVO("Seminuevo");
	
	private final String estado;
	
	State(String estado)
	{
		this.estado = estado;
	}
	
	@Override
	public String toString() 
	{
		return estado;
	}
	
	public static State getStateFromString(String state) 
	{
		State carState= null ;
		if( state != null) 
		{
			state.toLowerCase();
			if( state.toLowerCase().equals("nuevo")) 
			{
				carState = State.NUEVO;
			}
			else if(state.toLowerCase().equals("seminuevo")) 
			{
				carState = State.SEMINUEVO;
			}
		}
		return carState;
	}
}
