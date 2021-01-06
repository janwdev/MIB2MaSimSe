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

	double zRot = 0; // rotation around vertical axis

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
		wiremesh();
		outline();

		try {
			if (vectorDrawList.size() > 0) {
				for (int i = 0; i < vectorDrawList.size() - 1; i++) {

					drawVector(maths.rotateVectorZ(vectorDrawList.get(i).v, zRot), vectorDrawList.get(i).c,
							vectorDrawList.get(i).w, vectorDrawList.get(i).h);
				}
				drawVector(maths.rotateVectorZ(vectorDrawList.get(vectorDrawList.size() - 1).v, zRot),
						Constants.COLORFLIGHT, Constants.FLIGHTVECWIDTH, Constants.FLIGHTVECHEIGHT);
			}
			if (startVector != null && endVector != null) {
				drawVector(maths.rotateVectorZ(startVector.v, zRot), Constants.COLORSTARTEND,
						Constants.STARTENDVECWIDTH, Constants.STARTENDVECHEIGHT);
				drawVector(maths.rotateVectorZ(endVector.v, zRot), Constants.COLORSTARTEND, Constants.STARTENDVECWIDTH,
						Constants.STARTENDVECHEIGHT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void drawVector(Vector v, Color color, int w, int h) {
		g.setColor(color);
		try {
			Vector vN = maths.multiply(v, maths.getProjektionsMatrix());
			int[] dispCoordinates = maths.vektor2DToDisplayKoordinates(vN);
			int x = dispCoordinates[0];
			int y = dispCoordinates[1];
			g.fillOval(x, y, w, h);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void outline() {
		double s1 = maths.getProjektionsMatrixClass().getS();
		double a = maths.getProjektionsMatrixClass().getA();
		double phiP = Math.atan(s1 * Math.sin(a));
		double thetaP = Math.atan(-s1 * Math.cos(a) * Math.cos(phiP));
		int stepWidthOutline = 360;
		for (int i = 0; i < stepWidthOutline; i++) {
			try {
				double iDouble = i;
				double t = (iDouble / stepWidthOutline) * 2 * Math.PI;
				outlineVector(t, phiP, thetaP);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

	private void outlineVector(double t, double phiP, double thetaP) throws Exception {
		Matrix rotMatRL = new Matrix(3, 3);
		Matrix rotMatTB = new Matrix(3, 3);
		rotMatRL.init(Math.cos(phiP), -Math.sin(phiP), 0, Math.sin(phiP), Math.cos(phiP), 0, 0, 0, 1);
		rotMatTB.init(Math.cos(thetaP), 0, -Math.sin(thetaP), 0, 1, 0, Math.sin(thetaP), 0, Math.cos(thetaP));
		// Projection matrix is not multiplied, because this is already done in
		// drawVector
		Vector u = maths.multiply(new Vector(0, Math.cos(t), Math.sin(t)), maths.multiply(rotMatRL, rotMatTB));
		drawVector(u, Constants.EARTHOUTLINECOLOR, 2, 2);
	}

	private void wiremesh() {
		// Circles Horizontal

		double s1 = maths.getProjektionsMatrixClass().getS();
		double a = maths.getProjektionsMatrixClass().getA();
		double phiP = Math.atan(s1 * Math.sin(a));
		double thetaP = Math.atan(-s1 * Math.cos(a) * Math.cos(phiP));

		int dotPerCircle = 200;
		double distanceX = 2 * Math.PI / dotPerCircle;
		double angleX = 0;
		double angleY = 0;
		int nbrCircles = 8;
		double distanceY = 2 * Math.PI / (nbrCircles * 2);
		try {

			for (int iLscp = 0; iLscp < nbrCircles * 2; iLscp++) {
				for (int i = 0; i < dotPerCircle; i++) {
					Vector v = new Vector(angleX, angleY);
					double test = Math.cos(phiP) * Math.cos(thetaP) * v.getVectorX()
							+ Math.sin(phiP) * Math.cos(thetaP) * v.getVectorY() + Math.sin(thetaP) * v.getVectorZ();
					if (test < 0) {
						drawVector(maths.rotateVectorZ(v, zRot), Constants.EARTHCOLORBACKFACE, 2, 2);
					} else {
						drawVector(maths.rotateVectorZ(v, zRot), Constants.EARTHCOLORFRONT, 2, 2);
					}
					angleX = angleX + distanceX;
				}
				angleX = 0;
				angleY = angleY + distanceY;
			}

			// Circles vertical
			dotPerCircle = 300;
			distanceY = 2 * Math.PI / dotPerCircle;
			angleX = 0;
			angleY = 0;
			nbrCircles = 6;
			distanceX = 2 * Math.PI / (nbrCircles * 2);
			for (int iVert = 0; iVert < nbrCircles * 2; iVert++) {
				for (int i = 0; i < dotPerCircle; i++) {
					Vector v = new Vector(angleX, angleY);
					double test = Math.cos(phiP) * Math.cos(thetaP) * v.getVectorX()
							+ Math.sin(phiP) * Math.cos(thetaP) * v.getVectorY() + Math.sin(thetaP) * v.getVectorZ();
					if (test < 0) {
						drawVector(maths.rotateVectorZ(v, zRot), Constants.EARTHCOLORBACKFACE, 2, 2);
					} else {
						drawVector(maths.rotateVectorZ(v, zRot), Constants.EARTHCOLORFRONT, 2, 2);
					}
					angleY = angleY + distanceY;

				}
				angleY = 0;
				angleX = angleX + distanceX;
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