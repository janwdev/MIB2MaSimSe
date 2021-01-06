package maths;

import app.Constants;
import objectClasses.Matrix;
import objectClasses.Vector;

public class Vektorberechnung { // erbt von Vector.java
	VektorMatrixBerechnung vektorMatrix = new VektorMatrixBerechnung();
	
	// Skalarprodukt
	protected double skalarProdukt(Vector u, Vector v) {
		double skalarprodukt = 0;
		for (int i = 0; i < 3; i++) { // ändern
			skalarprodukt = skalarprodukt + u.getVector()[i] * v.getVector()[i];
		}
		return skalarprodukt;
	}
	// Länge eines Vektors berechnen
	protected double vecLength(Vector v) {
		return Math.sqrt(Math.pow(v.getVectorX(), 2) + Math.pow(v.getVectorY(), 2) + Math.pow(v.getVectorZ(), 2));
	}
	// Drehen eines Vektors durch multiplikation mit einer drehMatrix
	protected Vector drehen(Vector vektor, Matrix drehMatrix) throws Exception {
		vektor = vektorMatrix.multiply(vektor, drehMatrix);
		return vektor;
	}
	// Division von Vektor und wert
	protected Vector vecDiv(Vector vector, double wert) {
		Vector vec = new Vector(vector.getVectorX()/wert, vector.getVectorY()/wert, vector.getVectorZ()/wert);
		return vec;
	}
	// Multiplikation von Vektor und wert
	protected Vector vecMulti(Vector vector, double wert) {
		Vector vec = new Vector(vector.getVectorX()*wert, vector.getVectorY()*wert, vector.getVectorZ()*wert);
		return vec;
	}
	// Addition von Vektoren
	protected Vector vecAddition(Vector v, Vector u) {
		Vector vecNew = new Vector(v.getVectorX()+u.getVectorX(), v.getVectorY()+u.getVectorY(), v.getVectorZ()+u.getVectorZ());					
		return vecNew;
	}
	// Kreuzprodukt: Ergebniss ist ein Vektor der auf beiden geg. Vektoren senkrecht steht
	protected Vector kreuzprodukt(Vector u, Vector v) {
		
		double x = (u.getVectorY()*v.getVectorZ())-(u.getVectorZ()*v.getVectorY());
		double y = (u.getVectorZ()*v.getVectorX())-(u.getVectorX()*v.getVectorZ());
		double z = (u.getVectorX()*v.getVectorY())-(u.getVectorY()*v.getVectorX());
		
		return new Vector(x,y,z);
	}
	// Abstand zwischen 2 Vektoren auf der Kreisbahn
	protected double abstandVector(Vector u, Vector v) {
		
		double r = Constants.earthRadius;	//radius verändert ---------------------------------------------------------------------------------------------------------
		return (2*Math.PI*r*Math.toDegrees(winkelBerechnen(u, v)))/360;
		
	}
	// Punktberechnung *****************************************************************
	protected Vector berechneOrtho(Vector u, Vector v) {
		Vector vec = vecDiv(kreuzprodukt(u, v), vecLength(kreuzprodukt(u, v)));
		return vec;
		
	}
	// Kurvenparameter
	protected Vector berechnePunkt(Vector pDach, Vector u, double a) {
		double r = Constants.earthRadius; // ist hier immer 1
		Vector v = null;
		
		return vecAddition(vecMulti(pDach, r*Math.cos(a)),vecMulti(u, r*Math.sin(a))); // Formel pDach*r*cos(a) + u*r*sin(a);
	}
	// *********************************************************************************
	
	// Winkel zwischen 2 Vektoren
	public double winkelBerechnen(Vector u, Vector v) { 
		return (double) Math.acos(skalarProdukt(u, v) / (vecLength(v) * vecLength(u)));

}
	
	
}
