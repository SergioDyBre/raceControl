package racecontrol;

import java.util.ArrayList;

public class CarreraEstandar extends Carrera {

	private static final long serialVersionUID = -2842560020870580396L;
	private int duracion;

	public CarreraEstandar(int id, String nombrePremio, int duracion) {
		super(id, nombrePremio);
		this.duracion = duracion;
	}

	public CarreraEstandar(int id, String nombrePremio) {
		this(id, nombrePremio, 3);
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Override
	protected void simular() {
		super.simular();
		for (int i = 0; i < duracion; i++) {
			for (Coche c : coches) {
				c.maniobrar();
			}
		}

		for (Coche c : coches) {
			c.getDistanciaRecorrida();
			// FIXME cáculo medianamente incorrecto
			if (primero == null || primero.getDistanciaRecorrida() < c.getDistanciaRecorrida()) {
				primero = c;
			}

			if (segundo == null || (segundo != primero && segundo.getDistanciaRecorrida() < c.getDistanciaRecorrida()
					&& segundo.getDistanciaRecorrida() <= primero.getDistanciaRecorrida())) {
				segundo = c;
			}

			if (tercero == null || (tercero != segundo && tercero != primero
					&& tercero.getDistanciaRecorrida() < c.getDistanciaRecorrida()
					&& tercero.getDistanciaRecorrida() <= segundo.getDistanciaRecorrida())) {
				tercero = c;
			}
		}
	}

}
