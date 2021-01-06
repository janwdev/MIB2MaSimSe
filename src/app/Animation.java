package app;

import gui.CenterPanel;
import gui.MainGUI;
import maths.Maths;
import objectClasses.Vector;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Point Flight Animation
 * 
 * @author Luca,Jannik
 *
 */
public class Animation {
	MainGUI gui;
	CenterPanel centerPanel;
	Maths ma = new Maths();

	double steps = 360.0; // Steps per Circle track
	double speed = 10.0; // StepCounter Speed
	boolean pause = false; // Pause/Continue Animation Value
	Timer timer = new Timer();

	/**
	 * Constructor
	 * 
	 * @param gui
	 * @param centerPanel
	 */
	public Animation(MainGUI gui, CenterPanel centerPanel) {
		this.gui = gui;
		this.centerPanel = centerPanel;
	}

	/**
	 * start Animation with startVector, endVector and Surface Area (Gui)
	 */
	public void startAnimation() {
		formulaCalculation(centerPanel.getStartVector(), centerPanel.getEndVector(), gui);
	}

	/**
	 * Pause/Continue Animation
	 */
	public void pauseContinue() {
		pause = !pause;
	}

	/**
	 * Cancel Animation
	 */
	public void cancel() {
		timer.cancel();
		timer = new Timer();
		pause = false;
		gui.animEnded();
	}

	/**
	 * Speed regulation
	 * 
	 * @param speed value
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Calculation of the curve between start and end vector with the parameter
	 * formula
	 * 
	 * @param p   startVector
	 * @param q   endVector
	 * @param gui Surface
	 */
	private void formulaCalculation(Vector p, Vector q, MainGUI gui) {

		Vector pRoof = ma.vectorDivision(p, ma.vectorLength(p));

		Vector n = ma.calculateOrtho(p, q);
		Vector u = ma.calculateOrtho(n, pRoof);

		// Point movement declaration
		double angel = ma.getAngelBetween(p, q);
		double degreeAngel = Math.toDegrees(angel);
		double r = ma.vectorLength(p);

		pointAnimation(angel, pRoof, u, gui);

	}

	private void pointAnimation(double angel, Vector pRoof, Vector u, MainGUI gui) {

		TimerTask tt = new TimerTask() {
			double t = 0;

			@Override
			public void run() {

				if (!pause) {
					gui.drawVector(ma.calculatePoint(pRoof, u, t)); // Draw Point
					t = t + ((Math.PI * 2) / steps); // increase Step Counter

				}
				if (t > angel) {
					timer.cancel();
					timer = new Timer();
					pause = false;
					gui.animEnded();
				}
			};
		};
		// tt task is executed with 0millisek delay and every (1000/speed) millisek
		// executed
		timer.scheduleAtFixedRate(tt, 0, (long) (1000 / speed));

	}
}
