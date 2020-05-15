package racecontrol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class Carrera implements ICarrera, Serializable {

	private static final long serialVersionUID = -4025399538381949351L;
	protected ArrayList<Coche> coches;
	protected HashSet<Garaje> garajes;
	protected int id;
	protected String nombrePremio;
	protected Coche primero;
	protected Coche segundo;
	protected Coche tercero;

	protected Carrera(int id, String nombrePremio) {
		this.id = id;
		this.nombrePremio = nombrePremio;
		coches = new ArrayList<>();
		garajes = new HashSet<>();
		this.primero = null;
		this.segundo = null;
		this.tercero = null;
	}

	@Override
	public String getNombrePremio() {
		return nombrePremio;
	}

	@Override
	public List<Coche> getCochesParticipantes() {
		return coches;
	}

	@Override
	public void setNombrePremio(String nombrePremio) {
		this.nombrePremio = nombrePremio;
	}

	@Override
	public void addCoche(Coche c) {
		coches.add(c);
		garajes.add(c.getGaraje());
	}

	public Coche getPrimero() {
		return primero;
	}

	public void setPrimero(Coche primero) {
		this.primero = primero;
	}

	public Coche getSegundo() {
		return segundo;
	}

	public void setSegundo(Coche segundo) {
		this.segundo = segundo;
	}

	public Coche getTercero() {
		return tercero;
	}

	public void setTercero(Coche tercero) {
		this.tercero = tercero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	protected void simular() {
		for (Coche c : coches) {
			c.reiniciar();
		}
	}

}
