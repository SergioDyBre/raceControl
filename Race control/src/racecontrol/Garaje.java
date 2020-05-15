package racecontrol;

import java.io.Serializable;
import java.util.ArrayList;

public class Garaje implements Serializable{

	private static final long serialVersionUID = -4691326003584287899L;
	private ArrayList<Coche> coches;
	private int id;
	private String escudería;

	public Garaje(int id, String escudería) {
		this.id = id;
		this.escudería = escudería;
		this.coches = new ArrayList<Coche>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEscudería() {
		return escudería;
	}

	public void setEscudería(String escudería) {
		this.escudería = escudería;
	}

	public ArrayList<Coche> getCoches() {
		return coches;
	}
	
	public void addCoche (Coche c) {
		coches.add(c);
	}

}
