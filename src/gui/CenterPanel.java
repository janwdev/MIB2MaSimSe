package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import app.Constants;
import maths.Maths;
import objectClasses.Matrix;
import objectClasses.Vector;

public class CenterPanel extends JPanel {

	private Maths maths = new Maths();
	private Graphics g;
	ArrayList<VectorToDraw> vectorDrawList = new ArrayList<VectorToDraw>();
	VectorToDraw startVector;
	VectorToDraw endVector;

	private double xRot = 0; // um QuerAchse
	private double yRot = 0;
	double zRot = 1; // Um Hochachse

	public CenterPanel() {
		Dimension size = new Dimension(Constants.drawSizeXPixels, Constants.drawSizeYPixels);
		this.setMinimumSize(size);
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.repaint();
	}

	public Vector getStartVector() {
		return startVector.v;
	}

	public Vector getEndVector() {
		return endVector.v;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g = g;
		drahtgitter();
		umriss();

		try {
			if (vectorDrawList.size() > 0) {
				for (int i = 1; i < vectorDrawList.size() - 2; i++) {

					drawVector(maths.rotateVector(vectorDrawList.get(i).v, xRot, yRot, zRot), vectorDrawList.get(i).c,
							vectorDrawList.get(i).w, vectorDrawList.get(i).h);
				}
				drawVector(maths.rotateVector(vectorDrawList.get(vectorDrawList.size() - 1).v, xRot, yRot, zRot),
						Constants.COLORFLIGHT, Constants.FLIGHTVECWIDTH, Constants.FLIGHTVECHEIGHT);
			}
			if (startVector != null && endVector != null) {
				drawVector(maths.rotateVector(startVector.v, xRot, yRot, zRot), Constants.COLORSTARTEND,
						Constants.STARTENDVECWIDTH, Constants.STARTENDVECHEIGHT);
				drawVector(maths.rotateVector(endVector.v, xRot, yRot, zRot), Constants.COLORSTARTEND,
						Constants.STARTENDVECWIDTH, Constants.STARTENDVECHEIGHT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void drawVector(Vector v, Color color, int w, int h) {
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

	private void umriss() {
		double s1 = maths.getProjektionsMatrixClass().getS();
		double a = maths.getProjektionsMatrixClass().getA();
		double phiP = Math.atan(s1 * Math.sin(a));
		double thetaP = Math.atan(-s1 * Math.cos(a) * Math.cos(phiP));
		int schrittweiteUmriss = 360;
		for (int i = 0; i < schrittweiteUmriss; i++) {
			try {
				double iDouble = i;
				double t = (iDouble / schrittweiteUmriss) * 2 * Math.PI;
				umrissVektor(t, phiP, thetaP);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

	private void umrissVektor(double t, double phiP, double thetaP) throws Exception {
		Matrix drehMatrixRechtsLinks = new Matrix(3, 3);
		Matrix drehMatrixOben = new Matrix(3, 3);
		drehMatrixRechtsLinks.init(Math.cos(phiP), -Math.sin(phiP), 0, Math.sin(phiP), Math.cos(phiP), 0, 0, 0, 1);
		drehMatrixOben.init(Math.cos(thetaP), 0, -Math.sin(thetaP), 0, 1, 0, Math.sin(thetaP), 0, Math.cos(thetaP));
		// Projektionsmatrix wird nicht mitmultipliziert, da das bereits in drawVector
		// geschieht
		Vector u = maths.multiply(new Vector(0, Math.cos(t), Math.sin(t)),
				maths.multiply(drehMatrixRechtsLinks, drehMatrixOben));
		drawVector(u, Constants.EARTHOUTLINECOLOR, 2, 2);
	}

	private void drahtgitter() {
		// TODO farbe anpassen wenn hinter kugel verborgen
		// Kreise Horizontal

		double s1 = maths.getProjektionsMatrixClass().getS();
		double a = maths.getProjektionsMatrixClass().getA();
		double phiP = Math.atan(s1 * Math.sin(a));
		double thetaP = Math.atan(-s1 * Math.cos(a) * Math.cos(phiP));

		int dotPerCircle = 200;
		double abstandX = 2 * Math.PI / dotPerCircle;
		double winkelX = 0;
		double winkelY = 0;
		int anzCircles = 8;
		double abstandY = 2 * Math.PI / (anzCircles * 2);
		try {

			for (int iQuer = 0; iQuer < anzCircles * 2; iQuer++) {
				for (int i = 0; i < dotPerCircle; i++) {
					Vector v = new Vector(winkelX, winkelY);
					double test = Math.cos(phiP) * Math.cos(thetaP) * v.getVectorX()
							+ Math.sin(phiP) * Math.cos(thetaP) * v.getVectorY() + Math.sin(thetaP) * v.getVectorZ();
					if (test < 0) {
						drawVector(maths.rotateVector(v, xRot, yRot, zRot), Constants.EARTHCOLORBACKFACE, 2, 2);
					} else {
						drawVector(maths.rotateVector(v, xRot, yRot, zRot), Constants.EARTHCOLORFRONT, 2, 2);
					}
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
					double test = Math.cos(phiP) * Math.cos(thetaP) * v.getVectorX()
							+ Math.sin(phiP) * Math.cos(thetaP) * v.getVectorY() + Math.sin(thetaP) * v.getVectorZ();
					if (test < 0) {
						drawVector(maths.rotateVector(v, xRot, yRot, zRot), Constants.EARTHCOLORBACKFACE, 2, 2);
					} else {
						drawVector(maths.rotateVector(v, xRot, yRot, zRot), Constants.EARTHCOLORFRONT, 2, 2);
					}
					winkelY = winkelY + abstandY;

				}
				winkelY = 0;
				winkelX = winkelX + abstandX;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class VectorToDraw {
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