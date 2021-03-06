package racecontrol;

import java.io.Serializable;
import java.util.ArrayList;

public class Garaje implements Serializable{

	private static final long serialVersionUID = -4691326003584287899L;
	private ArrayList<Coche> coches;
	private int id;
	private String escuderķa;

	public Garaje(int id, String escuderķa) {
		this.id = id;
		this.escuderķa = escuderķa;
		this.coches = new ArrayList<Coche>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEscuderķa() {
		return escuderķa;
	}

	public void setEscuderķa(String escuderķa) {
		this.escuderķa = escuderķa;
	}

	public ArrayList<Coche> getCoches() {
		return coches;
	}
	
	public void addCoche (Coche c) {
		coches.add(c);
	}

}
