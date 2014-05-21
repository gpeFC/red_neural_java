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
	/** Regla de potencial para redes feedforward multicapas: Suma Ponderada */
	public static double sumaPonderada(double umbral, double[] entrada, double[] pesos){
		double potencial = 0.0;
		for(int i=0; i<pesos.length; i++){
			potencial = potencial + (pesos[i] * entrada[i]);
		}
		potencial = potencial + umbral;
		return potencial;
	}
}