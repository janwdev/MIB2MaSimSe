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

	double schritte = 360.0;
	double gschw = 10.0;

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
		ApplicationTime time = new ApplicationTime();

		// Deklaration zur Punktbewegung
		double winkel = ma.getWinkelZwischen(p, q);
		double degreeWinkel = Math.toDegrees(winkel);
		double r = ma.vektorLaenge(p);

		// schrittzaehler in der Strecke zwischen p und q (Phi)
		kurveZeichnen(winkel, pDach, u, gui);
		/*
		 * for (double t = 0; gschw * t < winkel; t = t + ((Math.PI * 2) / schritte)) {
		 * gui.drawVector(ma.berechnePunkt(pDach, u, t));
		 * 
		 * }
		 */

	}

	private void kurveZeichnen(double winkel, Vector pDach, Vector u, MainGUI gui) {

		/*
		 * for (double t = 0; gschw * t < winkel; t = t + ((Math.PI * 2) / schritte)) {
		 * gui.drawVector(ma.berechnePunkt(pDach, u, t)); }
		 */
		Timer timer = new Timer();

		TimerTask tt = new TimerTask() {
			double t = 0;

			@Override
			public void run() {
				gui.drawVector(ma.berechnePunkt(pDach, u, t));
				t = t + ((Math.PI * 2) / schritte);
				if (t > winkel) {
					timer.cancel();
				}
			};
		};
		timer.scheduleAtFixedRate(tt, 0, (long) (1000 / gschw));

	}

	// Beim Buttonstart
	private void startSimulation() {
		Timer timer = new Timer();
		TimerTask tt = new TimerTask() {
			@Override
			public void run() {

			};
		};
		timer.scheduleAtFixedRate(tt, 0, (long) (1000 * gschw));

	}
}