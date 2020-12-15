package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import app.Constants;
import maths.Maths;
import objectClasses.Vector;

public class CenterPanel extends JPanel {

	private Maths maths = new Maths();
	private Graphics g;
	ArrayList<VectorToDraw> vectorDrawList = new ArrayList<VectorToDraw>();

	public CenterPanel() {
		Dimension size = new Dimension(Constants.drawSizeXPixels, Constants.drawSizeYPixels);
		this.setMinimumSize(size);
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g = g;
		drahtgitter();
		for (VectorToDraw vectorToDraw : vectorDrawList) {
			drawVector(vectorToDraw.v, vectorToDraw.c, vectorToDraw.w, vectorToDraw.h);
		}
	}

	protected void drawVector(Vector v, Color color, int w, int h) {
		while (g == null) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		g.setColor(color);
		try {
			Vector vN = maths.multiply(v, maths.getProjektionsMatrix());
			int[] dispKoordinaten = maths.vektor2DToDisplayKoordinates(vN);
			int x = dispKoordinaten[0];
			int y = dispKoordinaten[1];
			g.fillOval(x, y, w, h);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void drahtgitter() {
		// TODO farbe anpassen wenn hinter kugel verborgen
		// Kreise Horizontal
		int dotPerCircle = 200;
		double abstandX = 2 * Math.PI / dotPerCircle;
		double winkelX = 0;
		double winkelY = 0;
		int anzCircles = 8;
		double abstandY = 2 * Math.PI / (anzCircles * 2);

		for (int iQuer = 0; iQuer < anzCircles * 2; iQuer++) {
			for (int i = 0; i < dotPerCircle; i++) {
				Vector v = new Vector(winkelX, winkelY);
				drawVector(v, Color.BLACK, 2, 2);
				winkelX = winkelX + abstandX;
			}
			winkelX = 0;
			winkelY = winkelY + abstandY;
		}

		// Kreise vertikal
		dotPerCircle = 300;
		abstandY = 2 * Math.PI / dotPerCircle;
		winkelX = 0;
		winkelY = 0;
		anzCircles = 6;
		abstandX = 2 * Math.PI / (anzCircles * 2);
		for (int iQuer = 0; iQuer < anzCircles * 2; iQuer++) {
			for (int i = 0; i < dotPerCircle; i++) {
				Vector v = new Vector(winkelX, winkelY);
				drawVector(v, Color.BLACK, 2, 2);
				winkelY = winkelY + abstandY;

			}
			winkelY = 0;
			winkelX = winkelX + abstandX;
		}

	}

}

class VectorToDraw{
	Vector v;
	Color c;
	int w;
	int h;
	public VectorToDraw(Vector v, Color c, int w, int h) {
		this.v = v;
		this.c = c;
		this.w = w;
		this.h = h;
	}
}