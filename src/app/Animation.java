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
	private double speed = 10.0;
	boolean pause = false;
	Timer timer = new Timer();

	public Animation(MainGUI gui, CenterPanel centerPanel) {
		this.gui = gui;
		this.centerPanel = centerPanel;
	}

	public void startAnimation() {
		formelBerechnung(centerPanel.getStartVector(), centerPanel.getEndVector(), gui);
	}
	
	public void pauseContinue() {
		pause = !pause;
	}

	public void cancel() {
		timer.cancel();
		timer = new Timer();
		pause = false;
		gui.animEnded();
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}


	// muss man am Ende nicht noch zurück drehen ?
	private void formelBerechnung(Vector p, Vector q, MainGUI gui) {

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

		TimerTask tt = new TimerTask() {
			double t = 0;

			@Override
			public void run() {
				if (!pause) {
					gui.drawVector(ma.berechnePunkt(pDach, u, t));
					t = t + ((Math.PI * 2) / schritte);
				}
				if (t > winkel) {
					timer.cancel();
					timer = new Timer();
					pause = false;
					gui.animEnded();
				}
			};
		};
		timer.scheduleAtFixedRate(tt, 0, (long) (1000 / speed));

	}
}
