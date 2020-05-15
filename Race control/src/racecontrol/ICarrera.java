package racecontrol;

import java.util.List;

public interface ICarrera {

	public String getNombrePremio();

	public List<Coche> getCochesParticipantes();
	
	public void setNombrePremio (String nombre);
	
	public void addCoche (Coche c);

}