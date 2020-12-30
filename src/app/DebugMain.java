package app;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.formdev.flatlaf.FlatDarculaLaf;

import gui.MainGUI;
import maths.Maths;
import maths.ProjektionsMatrix;
import maths.VektorMatrixBerechnung;
import objectClasses.Matrix;
import objectClasses.Vector;
//import utils.FrameUpdate;

public class DebugMain {
	static Maths ma = new Maths();
	static ApplicationTime timeThread = new ApplicationTime();
	static double schritte = 360.0;
	static double gschw = 10.0;
	
	public static void main(String[] args) {

		FlatDarculaLaf.install();

		Control control = new Control();
		MainGUI gui = new MainGUI(control);
		control.setGUI(gui); // Noetig damit auf GUI zugegriffen werden kann
		
		

		// Vector malen
		// Vector v = new Vector(2,2,2);
		// gui.drawVector(v);
		// gemalte Vektoren loeschen, nur Drahtgitter bleibt
		// gui.clearDrawedVectors();

		
		// - Echtzeit realisierung
			//-> Extra Klasse mit Methoden zu Funktionen
			//-> Methoden: kurveZeichnen,punktZeichnen
		// - Geschwindigkeitsregulierung
		// - Auswahl erste Koordinate wird mit Punkt nach auswahl angezeigt
		// - Auswahl des zweiten Punktes zeigt den zweiten Punkt mit der Kurve an die man an un ausschalten kann (also die kurve an und aus)
		// Klick auf start startet das Flugzeug
		
		// - ändern der Projektionsmatrix im GUI (gegebenfalls drehen können, aber nur falls Zeit)

		// Vector mit Winkel
		Vector p = new Vector(3.7, 1.2);
		// p.setWinkel(0, 3.2);
		// Vector q = new Vector(Math.PI/3, 0); // minus geht nicht und über 90 grad
		Vector q = new Vector(-2.3,-2.0);
		// Zeichnen der Vektoren
		gui.drawVector(p);
		gui.drawVector(q);

		formelBerechnung(p, q, gui);
		// mitWinkelBerechnung1(p,q,gui);
		// mitWinkelBerechnungTest(p,q,gui);
		

	}

	// muss man am Ende nicht noch zurück drehen ?
	private static void formelBerechnung(Vector p, Vector q, MainGUI gui) {

		// Vectorlänge (=radius) ist bei Berechnung mit Winkel immer 1
		Vector pDach = ma.vektorDivision(p, ma.vektorLaenge(p)); // Einheitsvektor hat immer die länge 1
		// ******

		Vector n = ma.berechneOrtho(p, q);
		Vector u = ma.berechneOrtho(n, pDach);
		ApplicationTime time = new ApplicationTime();

		// Deklaration zur Punktbewegung
		double winkel = ma.getWinkelZwischen(p, q);
		double degreeWinkel = Math.toDegrees(winkel);
		double r = ma.vektorLaenge(p);

		// schrittzaehler in der Strecke zwischen p und q (Phi)
		kurveZeichnen(winkel, pDach, u ,gui);
		/*
		for (double t = 0; gschw * t < winkel; t = t + ((Math.PI * 2) / schritte)) { 
			gui.drawVector(ma.berechnePunkt(pDach, u, t));
			
		}*/

		
	}
	
	private static void kurveZeichnen(double winkel, Vector pDach, Vector u, MainGUI gui) {
		
		/*for (double t = 0; gschw * t < winkel; t = t + ((Math.PI * 2) / schritte)) { 
			gui.drawVector(ma.berechnePunkt(pDach, u, t));
		}*/
		timeThread.start();// braucht man nicht, nur wenn wir andere Funktionen einfügen wie stoppen ...
		Timer timer = new Timer();
		
		TimerTask tt = new TimerTask() {  
			double t = 0;
			@Override  
		    public void run() {  
		    	gui.drawVector(ma.berechnePunkt(pDach, u, t)); 
		    	t = t + ((Math.PI * 2) / schritte);
		    	if(t > winkel) {
		    		timer.cancel();
		    	}
		    };  
		};  
		timer.scheduleAtFixedRate(tt,0,(long)(1000/gschw));
		
		
	}
	
	
	
	
	// Beim Buttonstart
	private static void startSimulation() {
		timeThread.start();
		Timer timer = new Timer();
		TimerTask tt = new TimerTask() {  
		    @Override  
		    public void run() {  
		        
		    };  
		};  
		timer.scheduleAtFixedRate(tt,0,(long)(1000*gschw));
		
	}
	

}
