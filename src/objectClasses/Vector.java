package objectClasses;

import maths.Vektorberechnung;

public class Vector {
	
	private double x;
	private double y;
	private double z;
	private double wP;
	private double wO;
	
	
	public Vector(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
		winkelBerechnen();
		
	}
	public Vector(double wP, double wO) {
		this.wP = wP;
		this.wO = wO;
		koordinatenBerechnen();
	}
	
	
	public void winkelBerechnen() {
		
		double[]u = getVector();
		double[]v = {x,y,0}; 	// u-Strich
		double[]xDach = {x,0,0};
		
		Vektorberechnung vekbe = new Vektorberechnung();
		
		if(z >= 0) {
			wO = (double) Math.acos(vekbe.skalarProdukt(v, u)/(vekbe.wurzel(v)*vekbe.wurzel(u)));
		}else {
			wO = (double) -(Math.acos(vekbe.skalarProdukt(v, u)/(vekbe.wurzel(v)*vekbe.wurzel(u))));
		}
		
		if(y >= 0) {
			wP = (double) Math.acos(vekbe.skalarProdukt(v, xDach)/(vekbe.wurzel(v)*vekbe.wurzel(xDach)));
		}else {
			wP = (double) -(Math.acos(vekbe.skalarProdukt(v, xDach)/(vekbe.wurzel(v)*vekbe.wurzel(xDach))));
		}
	}
	
	public void koordinatenBerechnen() {
		this.x = Math.cos(wO)*Math.cos(wP);
		this.y = Math.cos(wO)*Math.sin(wP);
		this.z = Math.sin(wO);
	}
	
	
	
	public double[] getVector(){
		return new double[]{x,y,z};
	}
	
	public void setVector(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public void setWinkel(double wP, double wO) {
		this.wP = wP;
		this.wO = wO;
	}
	public double getPWinkel(){
		return wP;
	}
	public double getOWinkel(){
		return wP;
	}
	
	
	
}


