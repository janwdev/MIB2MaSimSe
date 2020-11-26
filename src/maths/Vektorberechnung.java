package maths;

import objectClasses.Matrix;
import objectClasses.Vector;

public class Vektorberechnung { // erbt von Vector.java
	VektorMatrixBerechnung vektorMatrix = new VektorMatrixBerechnung();

	public double skalarProdukt(Vector u, Vector v) {
		double skalarprodukt = 0;
		for (int i = 0; i < 3; i++) { // ändern
			skalarprodukt = skalarprodukt + u.getVector()[i] * v.getVector()[i];
		}
		return skalarprodukt;
	}

	public double vecLength(Vector v) {//norm oder length
		// Fehlermeldung hinzufügen, falls zu wenig werte im Array (oder zu viel)
		return Math.sqrt(Math.pow(v.getVector()[0], 2) + Math.pow(v.getVector()[1], 2) + Math.pow(v.getVector()[2], 2));
	}

	public double winkelBerechnen(Vector u, Vector v) {// hier muss noch bedingung hin wann -arccos oder +arccos, für Kugelkoordinaten p (y>=0) und O (z>=0) => Also noch eine Drehübergabeparameter (pan,tilt,roll) Nicht nötig
			// radiant wird zurückgegebene 0.90 zB
			return (double) Math.acos(skalarProdukt(u, v) / (vecLength(v) * vecLength(u)));
	
	}

	public Vector drehen(Vector vektor, Matrix drehMatrix) throws Exception {
		double oldZ = vektor.getVectorZ();
		vektor.setVector(vektor.getVectorX(), vektor.getVectorY(), 0);
		vektor = vektorMatrix.multiply(vektor, drehMatrix);
		vektor.setVector(vektor.getVectorX(), vektor.getVectorY(), oldZ);
		return vektor;
	}
	
	public Vector vecDiv(Vector vector, double wert) {
		Vector vec = vector;
		vec.setVector(vec.getVectorX()/wert, vec.getVectorY()/wert, vec.getVectorZ()/wert);
		return vec;
	}
	
	public Vector vecMulti(Vector vector, double wert) {
		Vector vec = vector;
		vec.setVector(vec.getVectorX()*wert, vec.getVectorY()*wert, vec.getVectorZ()*wert);
		return vec;
	}
	
	public Vector vecAddition(Vector v, Vector u) {
		
		Vector vec = new Vector(0,0,0);
		
		vec.setVector(v.getVectorX()+u.getVectorX(), v.getVectorY()+u.getVectorY(), v.getVectorZ()+u.getVectorZ());
		return vec;
		
	}


	public Vector kreuzprodukt(Vector u, Vector v) {
		
		
		double x = (u.getVectorY()*v.getVectorZ())-(u.getVectorZ()*v.getVectorY());
		double y = (u.getVectorZ()*v.getVectorX())-(u.getVectorX()*v.getVectorZ());
		double z = (u.getVectorX()*v.getVectorY())-(u.getVectorY()*v.getVectorX());
		Vector vec = new Vector(x,y,z);
		return vec;
		
	}
	
	public double abstandVector(Vector u, Vector v) {
		
		double r = vecLength(u);//radius r=konstante Erdradius
		return (2*Math.PI*r*Math.toDegrees(winkelBerechnen(u, v)))/360;
		
	}
	
	
}
