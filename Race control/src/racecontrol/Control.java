package racecontrol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Control implements Serializable {

	private static final long serialVersionUID = -7706554147332200198L;
	private static Scanner scanner = new Scanner(System.in);
	private ArrayList<Garaje> garajes;
	private ArrayList<Torneo> torneos;

	public Control() {
		garajes = new ArrayList<>();
		torneos = new ArrayList<>();
	}

	public void mostrarMenuPrincipal() {
		int opcion = 0;
		boolean finalizarPrograma = false;
		while (!finalizarPrograma) {
			do {
				// FIXME controlar introducción dos usuarios e facer xeración dos id
				System.out.println("Teclea la opción que desees hacer, 0 para salir");
				System.out.println("0 - Salir de la aplicación");
				System.out.println("1 - Gestionar un Torneo");
				System.out.println("2 - Gestionar garajes");
				try {
					opcion = scanner.nextInt();
					scanner.nextLine();
				} catch (Exception e) {
					System.err.println("Teclea una opción válida porfavor");
					opcion = 15;
				}

			} while (opcion < 0 || opcion > 2);
			switch (opcion) {
			case 0:
				finalizarPrograma = true;
				break;
			case 1:
				gestionarTorneos();
				break;
			case 2:
				gestionarGarajes();
				break;

			}
		}
	}

	private void gestionarTorneos() {
		int opcion;
		boolean finalizarOpcion = false;
		while (!finalizarOpcion) {
			listarTorneos();
			System.out.println("---------------------------------------------------------------------------------");
			do {
				System.out.println("Teclea la opción que desees hacer");
				System.out.println("0 - Volver atrás");
				System.out.println("1 - Crear un torneo");
				System.out.println("2 - Ejecutar un torneo");
				try {
					opcion = scanner.nextInt();
					scanner.nextLine();
				} catch (Exception e) {
					System.err.println("Teclea una opción válida porfavor");
					opcion = 15;
				}
			} while (opcion < 0 || opcion > 3);
			switch (opcion) {
			case 0:
				finalizarOpcion = true;
				break;
			case 1:
				crearTorneo();
				break;
			case 2:
				ejecutarTorneos();
				break;

			}

		}
	}

	private void listarTorneos() {
		System.out.println("Esta es la lista de torneos disponibles: ");
		for (Torneo t : torneos) {
			System.out.println("ID: " + t.getId() + " - Nombre: " + t.getNombreTorneo() + " (Carreras: "
					+ t.getCantidadCarrerasNecesarias() + ")");
		}
	}

	private void ejecutarTorneos() {
		if (torneos.isEmpty()) {
			System.out.println("No hay torneos");
			return;
		}
		listarTorneos();
		System.out.println("Qué torneo quieres ejecutar?");
		int id = scanner.nextInt();
		scanner.nextLine();
		for (Torneo t : torneos) {
			if (id == t.getId()) {
				t.simular();
				System.out.println("El torneo ha terminado, el/los ganador/es es/son: ");
				for (Coche c : t.getGanadores()) {
					System.out.println("ID: " + c.getId() + " Marca: " + c.getMarca() + " Modelo: " + c.getModelo()
							+ ", del garaje: " + c.getGaraje().getEscudería());
				}
			}
		}
	}

	private void crearTorneo() {
		int idTorneo;
		String nombreTorneo;
		int numeroCarreras;
		System.out.println("Teclea el id del torneo");
		idTorneo = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Teclea el nombre del torneo");
		nombreTorneo = scanner.nextLine();
		System.out.println("Indica el numero de carreras que forman el torneo");
		numeroCarreras = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Dime el id del garaje o garajes que participan en este torneo");
		listarGarajes();
		System.out.println("Introduce los valores separados por comas: (12,23,4,7) o simplemente (1)");
		String idGarajesParticipantes = scanner.nextLine();

		ArrayList<Garaje> garajesParticipantes = new ArrayList<>();
		for (String stringId : idGarajesParticipantes.split(",")) {
			for (Garaje g : garajes) {
				if (g.getId() == Integer.parseInt(stringId)) {
					garajesParticipantes.add(g);
				}
			}
		}
		Torneo torneo = new Torneo(idTorneo, nombreTorneo, numeroCarreras, garajesParticipantes);
		System.out.println("Añade " + torneo.getCantidadCarrerasNecesarias() + " carreras al torneo");
		while (!torneo.isValido()) {
			int id;
			String nombrePremio;
			int opcion;
			do {
				System.out.println("Que tipo de carrera quieres añadir?");
				System.out.println("1 - Carrera de eliminación");
				System.out.println("2 - Carrera estándar");
				try {
					opcion = scanner.nextInt();
					scanner.nextLine();
				} catch (Exception e) {
					System.err.println("Teclea una opción válida porfavor");
					opcion = 15;
				}
			} while (opcion < 1 || opcion > 2);

			System.out.println("Teclea el id de la carrera");
			id = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Teclea el nombre del premio");
			nombrePremio = scanner.nextLine();
			Carrera carrera = null;
			switch (opcion) {
			case 1:
				carrera = new CarreraEliminacion(id, nombrePremio);
				break;
			case 2:
				System.out.println("Indica la duración de la carrera");
				int duracion = scanner.nextInt();
				scanner.nextLine();
				carrera = new CarreraEstandar(id, nombrePremio, duracion);
				break;
			}
			torneo.addCarrera(carrera);

		}
		torneos.add(torneo);
	}

	private void listarGarajes() {
		System.out.println("Esta es la lista de garajes disponibles: ");
		for (Garaje g : garajes) {
			System.out
					.println("ID: " + g.getId() + " - " + g.getEscudería() + " (Coches: " + g.getCoches().size() + ")");
		}
	}

	private void gestionarGarajes() {
		int opcion;
		boolean finalizarOpcion = false;
		while (!finalizarOpcion) {
			listarGarajes();
			System.out.println("---------------------------------------------------------------------------------");
			do {
				System.out.println("Teclea la opción que desees hacer");
				System.out.println("0 - Volver atrás");
				System.out.println("1 - Crear un garaje");
				System.out.println("2 - Añadir un coche a un garaje");
				try {
					opcion = scanner.nextInt();
					scanner.nextLine();
				} catch (Exception e) {
					System.err.println("Teclea una opción válida porfavor");
					opcion = 15;
				}
			} while (opcion < 0 || opcion > 2);
			switch (opcion) {
			case 0:
				finalizarOpcion = true;
				break;
			case 1:
				int id;
				String escuderia;
				System.out.println("Teclea id para tu nuevo garaje");
				id = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Teclea la escudería del garaje");
				escuderia = scanner.nextLine();
				garajes.add(new Garaje(id, escuderia));
				break;
			case 2:
				int idCoche;
				String modelo;
				String marca;
				int idGaraje;
				System.out.println("Teclea id del coche");
				idCoche = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Teclea su modelo");
				modelo = scanner.nextLine();
				System.out.println("Teclea su marca");
				marca = scanner.nextLine();
				System.out.println("Teclea el id del garaje al que pertenece");
				idGaraje = scanner.nextInt();
				scanner.nextLine();
				Garaje garaje = null;
				for (Garaje g : garajes) {
					if (g.getId() == idGaraje) {
						garaje = g;
						break;
					}
				}

				if (garaje == null) {
					System.out.println("El garaje que has introducido no existe");
					break;
				}

				Coche coche = new Coche(idCoche, modelo, marca, garaje);
				garaje.addCoche(coche);
				break;

			}
		}
	}

	public void finalizar() {
		scanner.close();
	}

}
//<3