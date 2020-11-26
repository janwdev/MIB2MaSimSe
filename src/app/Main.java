package app;

import java.util.ArrayList;

import com.formdev.flatlaf.FlatDarculaLaf;

import gui.MainGUI;
import maths.ProjektionsMatrix;
import maths.VektorMatrixBerechnung;
import maths.Vektorberechnung;
import objectClasses.Matrix;
import objectClasses.Vector;

public class Main {

	public static void main(String[] args) throws Exception {
		
		FlatDarculaLaf.install();
		
		Control control = new Control();
		MainGUI gui = new MainGUI(control);
		control.setGUI(gui);
		
		toRun();
		
		/*
		Vector u = new Vector(2.0, 3.0, 4.0);
		Vector v = new Vector(3.0, 0.0, 1.0);
		
		Vektorberechnung vecBe = new Vektorberechnung();
		double winkel = vecBe.winkelBerechnen(u, v);
		
		System.out.println(winkel);
		
		
		
		// Tests
		
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
	
	private static void toRun () {
		// Deklaration
		Vector p = new Vector(2.0, 0.0, 0.0);
		Vector q = new Vector(-2.0, 3.0, 0.0);
		Vector n = new Vector(0, 0, 0);
		Vector u = new Vector(0, 0, 0);
		
		
		Vektorberechnung vecBe = new Vektorberechnung();
		ArrayList<Vector> points = new ArrayList<Vector>();
		
		// Vectorberechnung
		n = vecBe.vecDiv(vecBe.kreuzprodukt(p, q), vecBe.vecLength(vecBe.kreuzprodukt(p, q)));
		u = vecBe.vecDiv(vecBe.kreuzprodukt(n, p), vecBe.vecLength(vecBe.kreuzprodukt(n, p)));
				
		
		
		// Deklaration zur Punktbewegung

		double schritte = 100.0;
		double gschw = 1.0;
		double winkel = vecBe.winkelBerechnen(p, q);
		double degreeWinkel = Math.toDegrees(winkel);
		double r = vecBe.vecLength(u);
		ArrayList<Double> posP = new ArrayList<Double>();
		
		// schrittzähler in der Strecke zwischen p und q (Phi)
		for(double t=0; gschw*t<winkel; t=t+((2*Math.PI)/schritte)) {	// muss später in Echtzeit realisiert werden
			posP.add(t);
		}
		
		// Punkte in der Bahn in einem Array
		for(int i=0; i<posP.size(); i++) {
			points.add( vecBe.vecAddition( (vecBe.vecMulti(p, r*Math.cos(posP.get(i)))),  (vecBe.vecMulti(u, r*Math.cos(posP.get(i))))    ));
		}

		// 2D Projektion alles Punkte im Array
		try {
			ProjektionsMatrix proMatrix = new ProjektionsMatrix();
			Matrix matrix = new Matrix(2,4);
			matrix = proMatrix.getProMatrix();
			VektorMatrixBerechnung vecMatrix = new VektorMatrixBerechnung();
			
			for(int i=0; i<posP.size(); i++) {
				Vector vecNew = new Vector(0,0,0);
				vecNew = vecMatrix.multiply(points.get(i), matrix);
				points.add(vecNew);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Punkte ausgeben
		
		for(int i=0; i<points.size(); i++) {
			System.out.println("X: "+points.get(i).getVectorX());
			System.out.println("Y: "+points.get(i).getVectorY());
			System.out.println("Z: "+points.get(i).getVectorZ());
		}
		
		
	}
	
}
