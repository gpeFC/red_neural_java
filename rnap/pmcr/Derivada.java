/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Derivada

/**
* Clase final donde se definen las 'Derivadas' de las funciones de activacion
* que son diferenciables que se utilizan para el algoritmo 'Backpropagation'.
* @author Emanuel GP
*/
public final class Derivada{
	/** Derivada de la funcion 'Logaritmo Sigmoidal'. */
	public static double logaritmoSigmoidal(double potencial){
		double derivada = (Activacion.logaritmoSigmoidal(potencial) * (1 - Activacion.logaritmoSigmoidal(potencial)));
		return derivada;
	}

	/** Derivada de la funcion 'Tangente Sigmoidal'. */
	public static double tangenteSigmoidal(double potencial){
		double derivada = ((2*(Math.pow(Math.E, -potencial))) / Math.pow((1.0 + (Math.pow(Math.E, -potencial))), 2));
		return derivada;
	}

	/** Derivada de la funcion 'Tangente Hiperbolica'. */
	public static double tangenteHiperbolica(double potencial){
		double derivada = (1 - Math.pow(Activacion.tangenteHiperbolica(potencial), 2));
		return derivada;	
	}
}