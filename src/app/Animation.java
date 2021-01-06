package app;

import gui.CenterPanel;
import gui.MainGUI;
import maths.Maths;
import objectClasses.Vector;
import java.util.Timer;
import java.util.TimerTask;

public class Animation {
	MainGUI gui;
	CenterPanel centerPanel;
	Maths ma = new Maths();

	double schritte = 360.0;	// Schritte pro Kreisbahn
	double gschw = 10.0;		// Geschwindigkeit des Schrittz�hlers
	boolean pause = false;		// Animation pausieren
	Timer timer = new Timer();

	public Animation(MainGUI gui, CenterPanel centerPanel) {
		this.gui = gui;
		this.centerPanel = centerPanel;
	}

	public void startAnimation() {
		formelBerechnung(centerPanel.getStartVector(), centerPanel.getEndVector(), gui);
	}

	// muss man am Ende nicht noch zur�ck drehen ?
	private void formelBerechnung(Vector p, Vector q, MainGUI gui) {

		// Vectorl�nge (=radius) ist bei Berechnung mit Winkel immer 1
		Vector pDach = ma.vektorDivision(p, ma.vektorLaenge(p)); // Einheitsvektor hat immer die l�nge 1
		// ******

		Vector n = ma.berechneOrtho(p, q);
		Vector u = ma.berechneOrtho(n, pDach);

		// Deklaration zur Punktbewegung
		double winkel = ma.getWinkelZwischen(p, q);
		double degreeWinkel = Math.toDegrees(winkel);
		double r = ma.vektorLaenge(p);

		// Kurze zeichnen (Animation)
		kurveZeichnen(winkel, pDach, u, gui);

	}

	private void kurveZeichnen(double winkel, Vector pDach, Vector u, MainGUI gui) {
<<<<<<< HEAD
		
=======

		/*
		 * for (double t = 0; gschw * t < winkel; t = t + ((Math.PI * 2) / schritte)) {
		 * gui.drawVector(ma.berechnePunkt(pDach, u, t)); }
		 */

>>>>>>> Luca
		TimerTask tt = new TimerTask() {
			double t = 0;

			@Override
			public void run() {
<<<<<<< HEAD
				if(!pause) {
					gui.drawVector(ma.berechnePunkt(pDach, u, t));	// Punkt zeichnen
					t = t + ((Math.PI * 2) / schritte);				// Schrittz�hler erh�hen
=======
				if (!pause) {
					gui.drawVector(ma.berechnePunkt(pDach, u, t));
					t = t + ((Math.PI * 2) / schritte);
>>>>>>> Luca
				}
				if (t > winkel) {
					timer.cancel();
				}
			};
		};
		// tt-Task wird mit 0millisek Verz�gerung und jede (1000/gschw) millisek ausgef�hrt
		timer.scheduleAtFixedRate(tt, 0, (long) (1000 / gschw));

	}

	public void pauseContinue() {
		pause = !pause;
	}

	public void cancel() {
		timer.cancel();
	}
<<<<<<< HEAD
	
=======
>>>>>>> Luca

	// pausieren, abbrechen und weiter

}
