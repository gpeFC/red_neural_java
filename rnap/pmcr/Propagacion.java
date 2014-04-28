/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Propagacion

public final class Propagacion{
	public static double sumaPonderada(double umbral, double[] entrada, double[] pesos){
		double propagacion = 0.0;
		for(int i=0; i<pesos.length; i++){
			propagacion = propagacion + (pesos[i] * entrada[i]);
		}
		propagacion = propagacion - umbral;
		return propagacion;
	}
}