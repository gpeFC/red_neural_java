/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Perceptron ...

public class Perceptron{
	private double bias;			// Umbral de activacion de la neurona.
	private double error;			// Error de aprendizaje actual.
	private double salida;			// Salida obtenida de la neurona.
	private double epsilon;			// Tasa de aprendizaje del entrenamiento.
	private double[] salidas;		// Salidas asociadas a los patrones de entrenamiento.
	private double[][] entradas;	// Patrones de entrenamiento de la red.
	private double[][] pesos;		// Patron de pesos asociados a las entradas.

	public Perceptron(int patrones, int argumentos, double[] entradas, double[] salidas){
		do{
			this.bias = (Math.random()*10 + 1)/10.0;	// Inicializacion aleatoria del bias.
			this.epsilon = (Math.random()*10 + 1)/10.0;	// Inicializacion aleatoria de la tasa de aprendizaje.
		}while(this.bias==0.0 || this.epsilon==0.0);
		this.entradas = new int[dimension];
		this.entradas = entradas;						// Asignacion del vector de las entradas.
		this.salidas = new int[dimension];				// Asignacion del vector de las salidas.
		this.salidas = salidas;
		this.pesos = new double[patrones][argumentos];
		for(int i=0; i<dimension; i++){
			do{
				this.pesos[i] = (Math.random()*10 + 1)/10.0;	// Inicializacion aleatoria de los pesos.
			}while(this.pesos[i]==0.0);
		}
	}

	public void establecerBias(double bias){
		this.bias = bias;
	}

	public void establecerError(double error){
		this.error = error;
	}

	public double[][] obtenerPesos(){
		return this.pesos;
	}

	public double obtenerUmbral(){
		return this.umbral;
	}

	public double obtenerSalida(){
		return this.salida;
	}
}