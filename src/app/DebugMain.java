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
		
		
		// !! Winkel radiant oder Grad
		
		
		// Deklaration
			
				
			
			// Vector mit Winkel
			Vector p = new Vector(0, 0);
			//p.setWinkel(0, 3.2);
			//Vector q = new Vector(Math.PI/3, 0); // minus geht nicht und �ber 90 grad
	        Vector q = new Vector(Math.PI*2/3, 0);
	        // Zeichnen der Vektoren
	        gui.drawVector(p);
	        gui.drawVector(q);
	        
	        formelBerechnung(p,q,gui);
	        //mitWinkelBerechnung1(p,q,gui);
	        //mitWinkelBerechnungTest(p,q,gui);
	        
				

	}
	
	
	private static void mitWinkelBerechnungTest(Vector p, Vector q, MainGUI gui) {
		Maths ma = new Maths();
        
        int schritte = 20;
        double dif = ma.getWinkelZwischen(p, q);
        double anfZustand = dif;
        double schrittePunkte = dif/schritte;
       
        
        for (int i = 0; i <= schritte; i++) {
            gui.drawVector(new Vector(anfZustand, 0));
            anfZustand = anfZustand + schrittePunkte; 
        }
	}
	
	
	private static void mitWinkelBerechnung1(Vector p, Vector q, MainGUI gui) {
		double pOW = p.getOWinkel();
        double pPW = p.getPWinkel();
        double actO = pOW;
        double actP = pPW;
        
        double qOW = q.getOWinkel();
        double qPW = q.getPWinkel();
        
        int schritte = 100;
        
        double difOW = pOW - qOW;
        double difPW = pPW - qPW;
        
        double pWPerTime = difPW / schritte;
        double oWPerTime = difOW / schritte;
        
        for (int i = 0; i <= schritte; i++) {
            gui.drawVector(new Vector(actP, actO));
            actP = actP + pWPerTime; // Das hier minus weil sonst falschherum l�uft
            actO = actO + oWPerTime;
        }
	}
	
	
	// muss man am Ende nicht noch zur�ck drehen ?
	private static void formelBerechnung(Vector p, Vector q, MainGUI gui) {
		// Maths
		Maths ma = new Maths();
		// Vectorl�nge (=radius) ist bei Berechnung mit Winkel immer 1
		Vector pDach = ma.vektorDivision(p, ma.vektorLaenge(p)); // Einheitsvektor hat immer die l�nge 1
		// ******
		
						System.out.println("l�nge p:"+ma.vektorLaenge(p));
						System.out.println("P = "+p.getVectorX()+" , "+p.getVectorY()+" , "+p.getVectorZ());
						//System.out.println("Q = "+q.getVectorX()+" , "+q.getVectorY()+" , "+q.getVectorZ());
						System.out.println("PDach = "+pDach.toString());
						
						//ma.getAbstand(p,q);
		// Vectorberechnung der Orthogonalen n und u
		Vector n = ma.berechneOrtho(p, q);
		Vector u = ma.berechneOrtho(n, pDach);
		
						System.out.println("Kreuz :"+ma.kreuzprodukt(p, q).getVectorZ());
						System.out.println("lange :"+ma.vektorLaenge(ma.kreuzprodukt(n, p)));
						//gui.drawVector(n);
						//gui.drawVector(u);
						System.out.println("n: "+n.getVectorX()+n.getVectorY()+n.getVectorZ());
						System.out.println("ux: "+u.getVectorX()+"uy: "+u.getVectorY()+"uZ: "+u.getVectorZ());
						System.out.println("u-kreuz: "+ma.kreuzprodukt(n, p).getVectorX());
				
		// Deklaration zur Punktbewegung 
		double schritte = 360.0;
		double gschw = 1.0;
		double winkel = ma.getWinkelZwischen(p, q);
		double degreeWinkel = Math.toDegrees(winkel);
		double r =     ma.vektorLaenge(p);
		ArrayList<Vector> points = new ArrayList<Vector>(); // Vektorenpunkte
		ArrayList<Double> posP = new ArrayList<Double>();	// Schrittz�hler t f�r phi
		
		// schrittzaehler in der Strecke zwischen p und q (Phi)
		for(double t=0; gschw*t<winkel; t=t+((Math.PI*2)/schritte)) {	// muss sp�ter in Echtzeit realisiert werden
			posP.add(t);
		}
		
		// Punkte in der Bahn in einem Array
		for(int i=0; i<posP.size(); i++) {
						//Vector v1 = new Vector(0,0,0);
						//Vector v2 = new Vector(0,0,0);
			
			double a = posP.get(i) ;
			
			
						//System.out.println("wert ist:  "+a);
						//System.out.println("wert sin:  "+Math.sin(a));
						//v1 = ma.vektorMultiplikation(p, r*Math.cos(a));
						//v2 = ma.vektorMultiplikation(u, r*Math.sin(a));
						//v3 = ma.vektorAddition(v1, v2);
			System.out.print("U WERR : " +u.toString());
			Vector v = new Vector(ma.berechnePunkt(pDach,u,a).getPWinkel(),ma.berechnePunkt(pDach,u,a).getOWinkel());
			points.add(v);
					// System.out.println("v1: "+v1.getVectorX() + "v1: "+v1.getVectorY());
		}

		//Punkte ausgeben
		for(int i=0; i<points.size(); i++) {
			//System.out.println("X: "+points.get(i).getVectorX());
			//System.out.println("Y: "+points.get(i).getVectorY());
			//System.out.println("Z: "+points.get(i).getVectorZ());
			gui.drawVector(points.get(i));
			
		}
		for(int i=0; i<posP.size(); i++) {
			System.out.println("Position: "+posP.get(i));
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
