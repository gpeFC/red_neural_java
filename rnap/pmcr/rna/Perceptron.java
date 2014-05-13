/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Perceptron

/**
* Clase definida para crear objetos 'neurona' de tipo 'Perceptron' para 
* construir una Red Neuronal Artificial simple o multicapa.
* @author Emanuel GP
*/
public class Perceptron{
	private double alpha;	// Tasa de aprendizaje de la neurona.
	private double umbral;	// Umbral\Bias de la neurona.
	private double salida;	// Respuesta de salida de la neurona.
	private double[] pesos; // Pesos sinapticos de la neurona.

	/** Constructor para inicializar el objeto 'neurona'. */
	public Perceptron(int numArgs, double alpha){
		this.alpha = alpha;
		this.salida = 0.0;
		do{
			this.umbral = (Math.random()*10 + 1)/10.0;	// Inicialización aleatoria del umbral\bias de la neurona.
		}while(this.umbral == 0.0);
		this.pesos = new double[numArgs];
		for(int i=0; i<numArgs; i++){
			do{
				this.pesos[i] = (Math.random()*10 + 1)/10.0;	// Inicialización aleatoria de los pesos sinapticos de la neurona.
			}while(this.pesos[i]==0.0);
		}
	}

	public void establecerAlpha(double alpha){
		this.alpha = alpha;
	}

	public void establecerUmbral(double umbral){
		this.umbral = umbral;
	}

	public void establecerPesos(double[] pesos){
		this.pesos = pesos;
	}

	public double obtenerAlpha(){
		return this.alpha;
	}

	public double obtenerUmbral(){
		return this.umbral;
	}

	public double[] obtenerPesos(){
		return this.pesos;
	}

	public double obtenerSalida(){
		return this.salida;
	}

	/** Método para calcular la respuesta de salida de la neurona respecto a los parámetros actuales. */
	public void calcularSalida(int funcion, double[] entrada){
		if(funcion == 1){
			this.salida = Activacion.identidad(Propagacion.sumaPonderada(this.umbral, entrada, this.pesos));
		}
		else if(funcion == 2){
			this.salida = Activacion.escalonBinario(Propagacion.sumaPonderada(this.umbral, entrada, this.pesos));
		}
		else if(funcion == 3){
			this.salida = Activacion.escalonBipolar(Propagacion.sumaPonderada(this.umbral, entrada, this.pesos));
		}
		else if(funcion == 4){
			this.salida = Activacion.logaritmoSigmoidal(Propagacion.sumaPonderada(this.umbral, entrada, this.pesos));
		}
		else if(funcion == 5){
			this.salida = Activacion.tangenteSigmoidal(Propagacion.sumaPonderada(this.umbral, entrada, this.pesos));
		}
		else if(funcion == 6){
			this.salida = Activacion.tangenteHiperbolica(Propagacion.sumaPonderada(this.umbral, entrada, this.pesos));
		}
	}
}