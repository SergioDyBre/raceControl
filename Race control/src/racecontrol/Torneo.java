package racecontrol;

import java.util.List;
import java.util.Random;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Torneo implements Serializable {

	private static final long serialVersionUID = 8492849116245583022L;
	private ArrayList<Carrera> carreras;
	private HashMap<Coche, Integer> puntuacion;
	private ArrayList<Coche> cochesParticipantes;
	private int id;
	private String nombreTorneo;
	private int cantidadCarrerasNecesarias;
	private boolean acabado;

	public Torneo(int id, String nombreTorneo, int cantidadCarrerasNecesarias, ArrayList<Garaje> garajes) {
		carreras = new ArrayList<>();
		puntuacion = new HashMap<>();
		cochesParticipantes = new ArrayList<>();
		this.id = id;
		this.nombreTorneo = nombreTorneo;
		this.cantidadCarrerasNecesarias = cantidadCarrerasNecesarias;
		this.acabado = false;
		addCochesByListaGarajes(garajes);
	}

	public Torneo(int id, String nombreTorneo, ArrayList<Garaje> garajes) {
		this(id, nombreTorneo, 10, garajes);
	}

	public List<Coche> getGanadores() {
		ArrayList<Coche> ganadores = new ArrayList<>();
		int i = -1;
		for (Entry<Coche, Integer> e : puntuacion.entrySet()) {
			if (e.getValue() > i) {
				ganadores.clear();
				i = e.getValue();
				ganadores.add(e.getKey());
			} else {
				if (i == e.getValue()) {
					ganadores.add(e.getKey());
				}
			}
		}

		return ganadores;
	}

	public void addCochesByGaraje(Garaje garaje) {
		for (Coche c : garaje.getCoches()) {
			this.addCoche(c);
		}
	}

	public void addCochesByListaGarajes(ArrayList<Garaje> garajes) {
		if (garajes.size() == 1) {
			addCochesByGaraje(garajes.get(0));
		} else {
			for (Garaje g : garajes) {
				Random r = new Random();
				Coche c = g.getCoches().get(r.nextInt(garajes.size()));
				this.addCoche(c);
			}
		}

	}

	public void addCoche(Coche c) {
		cochesParticipantes.add(c);
	}

	public List<Carrera> getCarreras() {
		return carreras;
	}

	public HashMap<Coche, Integer> getPuntuacion() {
		return puntuacion;
	}

	public void addCarrera(Carrera c) {
		for (Coche coche : this.cochesParticipantes) {
			c.addCoche(coche);
		}

		carreras.add(c);
	}

	public void addPuntuacion(Coche coche, int puntuacion) {
		this.puntuacion.put(coche, puntuacion);
	}

	public boolean isValido() {
		return carreras.size() == cantidadCarrerasNecesarias;
	}

	public String getNombreTorneo() {
		return nombreTorneo;
	}

	public void setNombreTorneo(String nombreTorneo) {
		this.nombreTorneo = nombreTorneo;
	}

	public int getCantidadCarrerasNecesarias() {
		return cantidadCarrerasNecesarias;
	}

	public void setCantidadCarrerasNecesarias(int cantidadCarrerasNecesarias) {
		this.cantidadCarrerasNecesarias = cantidadCarrerasNecesarias;
	}

	public boolean isAcabado() {
		return acabado;
	}

	public void setAcabado(boolean acabado) {
		this.acabado = acabado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void simular() {
		puntuacion.clear();
		for (Carrera c : this.getCarreras()) {
			c.simular();
			puntuacion.put(c.getPrimero(), puntuacion.getOrDefault(c.getPrimero(), 0) + 3);
			puntuacion.put(c.getSegundo(), puntuacion.getOrDefault(c.getSegundo(), 0) + 2);
			puntuacion.put(c.getTercero(), puntuacion.getOrDefault(c.getTercero(), 0) + 1);
		}

	}

}
