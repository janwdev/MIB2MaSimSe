package maths;

public class Vektorberechnung {

	
	public Vektorberechnung() {
		
	}
	
	
	public double skalarProdukt(double[] u, double[] v) {
		
		double skalarprodukt = 0;
		for (int i = 0; i < u.length; i++) {
			skalarprodukt = skalarprodukt + u[i] * v[i];
		}

		return skalarprodukt;
	}
	
	public double wurzel(double[]v) {
		// Fehlermeldung hinzufügen, falls zu wenig werte im Array (oder zu viel)
		return Math.sqrt(Math.pow(v[0], 2) + Math.pow(v[1], 2) + Math.pow(v[2], 2));
	}
	
					
	
}
