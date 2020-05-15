package racecontrol;

import java.io.Serializable;
import java.util.ArrayList;

public class Garaje implements Serializable{

	private static final long serialVersionUID = -4691326003584287899L;
	private ArrayList<Coche> coches;
	private int id;
	private String escuder�a;

	public Garaje(int id, String escuder�a) {
		this.id = id;
		this.escuder�a = escuder�a;
		this.coches = new ArrayList<Coche>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEscuder�a() {
		return escuder�a;
	}

	public void setEscuder�a(String escuder�a) {
		this.escuder�a = escuder�a;
	}

	public ArrayList<Coche> getCoches() {
		return coches;
	}
	
	public void addCoche (Coche c) {
		coches.add(c);
	}

}
