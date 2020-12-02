package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import app.Constants;
import maths.Maths;
import objectClasses.Vector;

public class CenterPanel extends JPanel {

	private Graphics g;
	private Maths maths = new Maths();

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
	}
	
	protected void drawVector(Vector v, Color color, int w, int h) {
		g.setColor(color);
		try {
			v = maths.multiply(v, maths.getProjektionsMatrix());
			int[] dispKoordinaten = maths.vektor2DToDisplayKoordinates(v);
			int x = dispKoordinaten[0];
			int y = dispKoordinaten[1];
			g.fillOval(x, y, w, h);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void drahtgitter() {
		// TODO farbe anpassen wenn hinter kugel verborgen
		g.setColor(Color.BLACK);
		// Kreise Horizontal
		int dotPerCircle = 200;
		double abstandX = 2 * Math.PI / dotPerCircle;
		double winkelX = 0;
		double winkelY = 0;
		int anzCircles = 8;
		double abstandY = 2 * Math.PI / (anzCircles*2);

		for (int iQuer = 0; iQuer < anzCircles*2; iQuer++) {
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
		abstandX = 2 * Math.PI / (anzCircles*2);
		for (int iQuer = 0; iQuer < anzCircles*2; iQuer++) {
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
