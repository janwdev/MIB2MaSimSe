package objectClasses;

import maths.Vektorberechnung;

public class Vector {
	
	private double x;
	private double y;
	private double z;
	public int dim;		//ob 2 Dimensional oder 3 Dimensional
	private double wP;
	private double wO;
	
	
	public Vector(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
		dim = 3;
		winkelBerechnen();
		
	}
	public Vector(double wP, double wO) {
		this.wP = wP;
		this.wO = wO;
		koordinatenBerechnen();
	}
	
	
	public void winkelBerechnen() {
		
		Vector u = this;
		Vector v = new Vector(x,y,0); 		//u-Strich
		Vector xDach = new Vector(x,0,0);
		
		Vektorberechnung vekbe = new Vektorberechnung();
		wO = vekbe.winkelBerechnen(u, v);
		wP = vekbe.winkelBerechnen(u, xDach);
		
	}
	
	public void koordinatenBerechnen() {
		this.x = Math.cos(wO)*Math.cos(wP);
		this.y = Math.cos(wO)*Math.sin(wP);
		this.z = Math.sin(wO);
		dim = 3;
	}
	
	
	
	public double[] getVector(){
		return new double[]{x,y,z};
	}
	
	public void setVector(double x, double y, double z){
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
	public double getPWinkel(){
		return wP;
	}
	public double getOWinkel(){
		return wP;
	}
	
	
	
}


