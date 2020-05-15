package racecontrol;

import java.io.Serializable;
import java.util.Random;

public class Coche implements Serializable{
	
	private static final long serialVersionUID = 3475191659138267173L;
	static final int velMax = 120;
	private int id;
	private String marca;
	private String modelo;
	private Garaje garaje;
	private float distanciaRecorrida;
	private int velocidad;

	public Coche(int id, String marca, String modelo, Garaje garaje) {
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.garaje = garaje;
		this.distanciaRecorrida = 0;
		this.velocidad = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public static float getVelmax() {
		return velMax;
	}

	public Garaje getGaraje() {
		return garaje;
	}

	public void setGaraje(Garaje garaje) {
		this.garaje = garaje;
	}

	public float getDistanciaRecorrida() {
		return distanciaRecorrida;
	}

	public void setDistanciaRecorrida(float distanciaRecorrida) {
		this.distanciaRecorrida = distanciaRecorrida;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public void reiniciar() {
		velocidad = 0;
		distanciaRecorrida = 0;
	}

	public void maniobrar() {
		//Asumimos que se maniobra unha vez por minuto.
		Random random = new Random();
		velocidad += random.nextBoolean() ? 10 : -10;
		velocidad = velocidad < 0 ? 0 : velocidad;
		velocidad = velocidad > velMax ? velMax : velocidad;
		
		distanciaRecorrida += velocidad/60f;
	}

}
