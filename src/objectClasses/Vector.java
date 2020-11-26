package objectClasses;

import maths.Vektorberechnung;

public class Vector { 

	private double x;
	private double y;
	private double z;
	private double wPhi; //lšngengrad xDach und v'
	private double wTheta; //breitengrad v und v'

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
		if (sollteWinkel)
			winkelBerechnen();
	}

	public Vector(double wP, double wO) {
		this.wPhi = wP;
		this.wTheta = wO;
		koordinatenBerechnen();
	}

	public void winkelBerechnen() {
		Vektorberechnung vekbe = new Vektorberechnung();
		wTheta = vekbe.winkelBerechnen(this, new Vector(false, x, y, 0));
		wPhi = vekbe.winkelBerechnen(this, new Vector(false, x, 0, 0));
	}

	public void koordinatenBerechnen() {
		this.x = Math.cos(wTheta) * Math.cos(wPhi);
		this.y = Math.cos(wTheta) * Math.sin(wPhi);
		this.z = Math.sin(wTheta);
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
		this.wPhi = wP;
		this.wTheta = wO;
		koordinatenBerechnen();
	}

	public double getPWinkel() {
		return wPhi;
	}

	public double getOWinkel() {
		return wPhi;
	}

}
