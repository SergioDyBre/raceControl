package racecontrol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
	public static void main(String args[]) {
		Control control;
		try {
			File ficheroDeDatos = new File("backup.dat");
			FileInputStream fi = new FileInputStream(ficheroDeDatos);
			ObjectInputStream oi = new ObjectInputStream(fi);
			control = (Control) oi.readObject();
			oi.close();
			fi.close();
		} catch (Exception e) {
			control = new Control();
		}

		control.mostrarMenuPrincipal();
		control.finalizar();
		try {
			File ficheroDeDatos = new File("backup.dat");
			FileOutputStream fi = new FileOutputStream(ficheroDeDatos);
			ObjectOutputStream oi = new ObjectOutputStream(fi);
			oi.writeObject(control);
			oi.close();
			fi.close();
		} catch (Exception e) {
			System.err.println("No se ha podido guardar el archivo");
			e.printStackTrace();
		}
	}

}
