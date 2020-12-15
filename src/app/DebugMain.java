package app;

import java.util.ArrayList;

import com.formdev.flatlaf.FlatDarculaLaf;

import gui.MainGUI;
import maths.Maths;
import maths.ProjektionsMatrix;
import maths.VektorMatrixBerechnung;
import objectClasses.Matrix;
import objectClasses.Vector;

public class DebugMain {
	public static void main(String[] args) {

		FlatDarculaLaf.install();

		Control control = new Control();
		MainGUI gui = new MainGUI(control);
		control.setGUI(gui); // Noetig damit auf GUI zugegriffen werden kann
		
		
		// Vector malen
		//Vector v = new Vector(2,2,2);
		//gui.drawVector(v);
		// gemalte Vektoren loeschen, nur Drahtgitter bleibt
		// gui.clearDrawedVectors();
		
		
		// !! Berchnungen stimmen alle in MAths.java
		
		
		// Deklaration
				Vector p = new Vector(Math.PI/2, 0);
				Vector q = new Vector(Math.PI, 0);
				
				
				
				Vector n = new Vector(0, 0, 0);
				Vector u = new Vector(0, 0, 0);
				
				gui.drawVector(p);
				gui.drawVector(q);
				
				
				//Vektorberechnung vecBe = new Vektorberechnung();
				
				Maths ma = new Maths();
				
				ma.getAbstand(p,q);
				
				System.out.println("Kreuz :"+ma.kreuzprodukt(p, q).getVectorZ());
				System.out.println("lange :"+ma.vektorLaenge(ma.kreuzprodukt(n, p)));
				
				// Vectorberechnung
				n = ma.vektorDivision(ma.kreuzprodukt(p, q), ma.vektorLaenge(ma.kreuzprodukt(p, q)));
				u = ma.vektorDivision(ma.kreuzprodukt(n, p), ma.vektorLaenge(ma.kreuzprodukt(n, p)));
				
				//gui.drawVector(n);
				//gui.drawVector(u);
				
				System.out.println("n: "+n.getVectorX()+n.getVectorY()+n.getVectorZ());
				System.out.println("ux: "+u.getVectorX()+"uy: "+u.getVectorY()+"uZ: "+u.getVectorZ());
				System.out.println("u-kreuz: "+ma.kreuzprodukt(n, p).getVectorX());
						
				
				
				// Deklaration zur Punktbewegung

				double schritte = 100.0;
				double gschw = 1.0;
				double winkel = ma.getWinkelZwischen(p, q);
				double degreeWinkel = Math.toDegrees(winkel);
				double r =     ma.vektorLaenge(p);
				ArrayList<Vector> points = new ArrayList<Vector>();
				ArrayList<Double> posP = new ArrayList<Double>();
				
				// schrittz�hler in der Strecke zwischen p und q (Phi)
				for(double t=0; gschw*t<winkel; t=t+((2*Math.PI)/schritte)) {	// muss sp�ter in Echtzeit realisiert werden
					posP.add(t);
				}
				
				// Punkte in der Bahn in einem Array
				for(int i=0; i<posP.size(); i++) {
					
					Vector v1 = new Vector(0,0,0);
					Vector v2 = new Vector(0,0,0);
					double a = posP.get(i) ;
					
					//System.out.println("wert ist:  "+a);
					//System.out.println("wert sin:  "+Math.sin(a));
					
					v1 = ma.vektorMultiplikation(p, r*Math.cos(a));
					v2 = ma.vektorMultiplikation(u, r*Math.sin(a));
					//Vector v3 = new Vector();
					points.add(ma.vektorAddition(v1, v2));
					//System.out.println(ma.vektorAddition(v1, v2).getVectorX());
				}

				//Punkte ausgeben
				
				for(int i=0; i<points.size(); i++) {
					//System.out.println("X: "+points.get(i).getVectorX());
					//System.out.println("Y: "+points.get(i).getVectorY());
					//System.out.println("Z: "+points.get(i).getVectorZ());
					gui.drawVector(points.get(i));
					
				}
				for(int i=0; i<posP.size(); i++) {
					//System.out.println("Position: "+posP.get(i));
				}
				System.out.println("Winkel: "+winkel + " Winkeldegree: "+ degreeWinkel);
				

	}
	
	
	
	private static void toRun () {
				
				// Test Matrix Vektor Multiplikation
		//		try {
		//			Matrix matrix = new Matrix(2, 3);
		//			matrix.init(new int[] { 4, 3, 2, 1, 2, 3 });
		//			Vector vector = new Vector(2, 3, 4);
		//			Vector returnVec = new VektorMatrixBerechnung().multiply(vector, matrix);
		//			System.out.println("x: " + returnVec.getVectorX() + ", y: " + returnVec.getVectorY() + ", z: "
		//					+ returnVec.getVectorZ());
		//			//Erwartetes Ergebnis; x: 25.0, y: 20.0, z: 0.0
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
		//		try {
		//			Matrix matrix = new Matrix(3, 3);
		//			matrix.init(new int[] { 2, 5, 7, 8, 9, 10, 1, 2, 3 });
		//			Vector vector = new Vector(2, 4, 5);
		//			Vector returnVec = new VektorMatrixBerechnung().multiply(vector, matrix);
		//			System.out.println("x: " + returnVec.getVectorX() + ", y: " + returnVec.getVectorY() + ", z: "
		//					+ returnVec.getVectorZ());
		//			// Erwartetes Ergebnis; x: 59.0, y: 102.0, z: 25.0
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
	}
	
	

}
