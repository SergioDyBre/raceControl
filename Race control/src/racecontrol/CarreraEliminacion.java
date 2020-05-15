package racecontrol;

import java.util.ArrayList;

public class CarreraEliminacion extends Carrera {

	private static final long serialVersionUID = 2537979460542621433L;
	static final int minsPrevios = 5;

	public CarreraEliminacion(int id, String nombrePremio) {
		super(id, nombrePremio);
	}

	public int getVueltas() {
		return coches.size() - 1;
	}

	@Override
	protected void simular() {
		super.simular();
		for (int i = 0; i < minsPrevios; i++) {
			for (Coche c : coches) {
				c.maniobrar();
			}
		}

		int nVueltas = getVueltas();
		ArrayList<Coche> copiaDeCoches = new ArrayList<>();
		for (Coche c : coches) {
			copiaDeCoches.add(c);
		}
		for (int vuelta = 0; vuelta < nVueltas; vuelta++) {
			for (Coche c : copiaDeCoches) {
				c.maniobrar();
			}
			Coche cochePeor = null;
			for (Coche c : copiaDeCoches) {
				if (cochePeor == null || c.getDistanciaRecorrida() < cochePeor.getDistanciaRecorrida()) {
					cochePeor = c;
				}
			}
			if (copiaDeCoches.size() == 3) {
				this.tercero = cochePeor;
			}

			if (copiaDeCoches.size() == 2) {
				this.segundo = cochePeor;
			}

			copiaDeCoches.remove(cochePeor);
		}

		this.primero = copiaDeCoches.get(0);
	}

}
