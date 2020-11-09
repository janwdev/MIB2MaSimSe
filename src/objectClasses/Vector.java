package objectClasses;

import maths.Vektorberechnung;

public class Vector {

	private double x;
	private double y;
	private double z;
	public int dim; // ob 2 Dimensional oder 3 Dimensional
	private double wP;
	private double wO;

	public Vector(double x, double y, double z) {
		initVector(true, x, y, z);
	}

	private Vector(boolean sollteWinkel, double x, double y, double z) {
		initVector(sollteWinkel, x, y, z);
	}

	private void initVector(boolean sollteWinkel, double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		dim = 3;
		if (sollteWinkel)
			winkelBerechnen();
	}

	public Vector(double wP, double wO) {
		this.wP = wP;
		this.wO = wO;
		koordinatenBerechnen();
	}

	public void winkelBerechnen() {
		Vektorberechnung vekbe = new Vektorberechnung();
		wO = vekbe.winkelBerechnen(this, new Vector(false, x, y, 0));
		wP = vekbe.winkelBerechnen(this, new Vector(false, x, 0, 0));
	}

	public void koordinatenBerechnen() {
		this.x = Math.cos(wO) * Math.cos(wP);
		this.y = Math.cos(wO) * Math.sin(wP);
		this.z = Math.sin(wO);
		dim = 3;
	}

	public double[] getVector() {
		return new double[] { x, y, z };
	}

	public double getVectorX() {
		return x;
	}

	public double getVectorY() {
		return y;
	}

	public double getVectorZ() {
		return z;
	}

	public void setVector(Double[] werte) {
		if (werte[0] != null)
			this.x = werte[0];
		if (werte[1] != null)
			this.y = werte[1];
		if (werte[2] != null)
			this.z = werte[2];
		winkelBerechnen();
	}

	public void setVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		winkelBerechnen();
	}

	public void setWinkel(double wP, double wO) {
		this.wP = wP;
		this.wO = wO;
		koordinatenBerechnen();
	}

	public double getPWinkel() {
		return wP;
	}

	public double getOWinkel() {
		return wP;
	}

}
