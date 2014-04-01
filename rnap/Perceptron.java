/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Perceptron ...

public class Perceptron{
	private double bias;		// Umbral de activacion de la neurona.
	private double epsilon;		// Tasa de aprendizaje del entrenamiento.
	private double activacion;	// Valor de activacion de la neurona.
	private double propagacion;	// Valor de propagacion de la neurona.
	private double[] entradas;	// Patron de entradas de la red.
	private double[] salidas;	// Patron de salidas asociadas a las entradas.
	private double[] pesos;		// Patron de pesos asociados a las entradas.

	public Perceptron(int dimension, double[] entradas, double[] salidas){
		this.activacion = 0.0;							// Inicializacion en 0 del valor de activacion.
		this.propagacion = 0.0;							// Inicializacion en 0 del valor de propagacion.
		do{
			this.bias = (Math.random()*10 + 1)/10.0;	// Inicializacion aleatoria del bias.
			this.epsilon = (Math.random()*10 + 1)/10.0;	// Inicializacion aleatoria de la tasa de aprendizaje.
		}while(this.bias==0.0 || this.epsilon==0.0);
		this.entradas = new int[dimension];
		this.entradas = entradas;						// Asignacion del vector de las entradas.
		this.salidas = new int[dimension];				// Asignacion del vector de las salidas.
		this.salidas = salidas;
		this.pesos = new double[dimension];
		for(int i=0; i<dimension; i++){
			do{
				this.pesos[i] = (Math.random()*10 + 1)/10.0;	// Inicializacion aleatoria de los pesos.
			}while(this.pesos[i]==0.0);
		}
	}

	public double[][] obtenerPesos(){
		return this.pesos;
	}

	public double obtenerUmbral(){
		return this.umbral;
	}

	public double obtenerPropagacion(){
		return this.propagacion;
	}

	public double obtenerActivacion(){
		return this.activacion;
	}

	public double obtenerSalida(){
		return this.salida;
	}

	public void calcularPropagacion(){
	}

	public void calcularActivacion(){
	}

	public void calcularSalida(){
	}
}