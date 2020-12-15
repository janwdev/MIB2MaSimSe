package maths;

import app.Constants;
import objectClasses.Matrix;
import objectClasses.Vector;

public class Maths {
	private MatrixBerechnung bMatrix = new MatrixBerechnung();
	private ProjektionsMatrix projektionsMatrix = new ProjektionsMatrix();
	private Vektorberechnung bVektor = new Vektorberechnung();
	private VektorMatrixBerechnung bVektorMatrix = new VektorMatrixBerechnung();

	public Matrix getProjektionsMatrix() {
		return projektionsMatrix.getProMatrix();
	}

	public double[][] getProjektionsMatrixArray() {
		return projektionsMatrix.getProjektionsMatrix();
	}

	public Matrix multipy(Matrix ma1, Matrix ma2) {
		return bMatrix.multipy(ma1, ma2);
	}

	public double skalarProdukt(Vector u, Vector v) {
		return bVektor.skalarProdukt(u, v);
	}

	public double vektorLaenge(Vector v) {
		return bVektor.vecLength(v);
	}

	public double getWinkelZwischen(Vector u, Vector v) {
		return bVektor.winkelBerechnen(u, v);
	}

	public Vector rotateVector(Vector v, int winkelX, int winkelY, int winkelZ) throws Exception {
		Vector vec = bVektor.drehen(v, bMatrix.getDrehMatrix(winkelX, winkelY, winkelZ));
		return vec;
	}

	public Vector vektorDivision(Vector v, double wert) {
		return bVektor.vecDiv(v, wert);
	}

	public Vector vektorMultiplikation(Vector v, double wert) {
		return bVektor.vecMulti(v, wert);
	}

	public Vector vektorAddition(Vector v, Vector u) {
		return bVektor.vecAddition(v, u);
	}

	public Vector kreuzprodukt(Vector u, Vector v) {
		return bVektor.kreuzprodukt(u, v);
	}

	public double getAbstand(Vector u, Vector v) {
		return bVektor.abstandVector(u, v);
	}

	public Vector multiply(Vector v, Matrix m) throws Exception {
		return bVektorMatrix.multiply(v, m);
	}

	public int[] vektor2DToDisplayKoordinates(Vector v) {
		// TODO auf 2D Vektor pruefen
		int[] ret;
		double x = v.getVectorX();
		double y = v.getVectorY();
		x = Constants.xOffsetDraw + x * 100 * Constants.drawScaleFactor;
		y = Constants.yOffsetDraw + y * 100 * Constants.drawScaleFactor;
		int xDisp = (int) Math.round(x);
		int yDisp = (int) Math.round(y);
		ret = new int[] { xDisp, yDisp };
		return ret;
	}
}
