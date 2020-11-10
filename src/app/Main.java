package app;

import com.formdev.flatlaf.FlatDarculaLaf;

import gui.MainGUI;
import maths.ProjektionsMatrix;
import maths.VektorMatrixBerechnung;
import objectClasses.Matrix;
import objectClasses.Vector;

public class Main {

	public static void main(String[] args) throws Exception {
		
		FlatDarculaLaf.install();
		
		Control control = new Control();
		MainGUI gui = new MainGUI(control);
		control.setGUI(gui);
		
		
		
		
		// Tests
		/*
		ProjektionsMatrix m = new ProjektionsMatrix();
		m.setProjektionsMatrix(0.8, 110);
		double[][]arr = m.getProjektionsMatrix();
		System.out.println("Zeile1 Spalte1 :" +arr[0][0]);
		System.out.println("Zeile1 Spalte2 :" +arr[0][1]);
		System.out.println("Zeile1 Spalte3 :" +arr[0][2]);
		System.out.println("Zeile1 Spalte4 :" +arr[0][3]);
		System.out.println("Zeile2 Spalte1 :" +arr[1][0]);
		System.out.println("Zeile2 Spalte2 :" +arr[1][1]);
		System.out.println("Zeile2 Spalte3 :" +arr[1][2]);
		System.out.println("Zeile2 Spalte4 :" +arr[1][3]);
		*/
		
	}
}
