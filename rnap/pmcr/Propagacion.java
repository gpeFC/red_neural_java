/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Propagacion

/**
* Clase final donde se definen las diversas 'Reglas de Propagacion' existentes
* y más comunes para las Redes Neuronales Artificiales mas usadas.
* @author Emanuel GP
*/
public final class Propagacion{
	/** Regla de propagacion para redes feedforward multicapas: Suma Ponderada */
	public static double sumaPonderada(double umbral, double[] entrada, double[] pesos){
		double propagacion = 0.0;
		for(int i=0; i<pesos.length; i++){
			propagacion = propagacion + (pesos[i] * entrada[i]);
		}
		propagacion = propagacion - umbral;
		return propagacion;
	}
}