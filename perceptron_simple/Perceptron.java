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
	private double[] pesos;			// Patron de pesos asociados a las entradas.

	public Perceptron(int patrones, int argumentos, double epsilon){
		this.error = 0.0;
		this.salida = 0.0;
		do{
			this.bias = (Math.random()*10 + 1)/10.0;			// Inicializacion aleatoria del bias.
		}while(this.bias == 0.0);
		this.epsilon = epsilon;	
		this.pesos = new double[argumentos];
		for(int i=0; i<argumentos; i++){
			do{
				this.pesos[i] = (Math.random()*10 + 1)/10.0;	// Inicializacion aleatoria de los pesos.
			}while(this.pesos[i]==0.0);
		}
	}

	public void imprimePesos(){
		for(int i=0; i<this.pesos.length; i++)
			System.out.printf("%f ", this.pesos[i]);
	}

	public void establecerBias(double bias){
		this.bias = bias;
	}

	public void establecerError(double error){
		this.error = error;
	}

	public double obtenerError(){
		return this.error;
	}

	public double obtenerEpsilon(){
		return this.epsilon;
	}

	public double obtenerBias(){
		return this.bias;
	}

	public double obtenerSalidaCalculada(){
		return this.salida;
	}

	public double[] obtenerPesos(){
		return this.pesos;
	}

	public void calcularSalida(int indice, double[][] entradas){	// Funcion de Activacion 'Harlims'.
		double salida;
		salida = 0.0;
		for(int i=0; i<this.pesos.length; i++)
			salida = salida + (this.pesos[i] * entradas[indice][i]);
		salida = salida + bias;
		if(salida > 0.0)
			this.salida = 1.0;
		else
			this.salida = -1.0;
	}

	public void actualizarBias(){
		this.bias = this.bias + this.error;
	}

	public void actualizarPesos(int indice, double[][] entradas){	// Regla para actualizar los pesos sinapticos.
		double[] aux_pesos = new double[this.pesos.length];
		for(int i=0;i<this.pesos.length; i++){
			aux_pesos[i] = this.epsilon * this.error * entradas[indice][i];
			this.pesos[i] = this.pesos[i] + aux_pesos[i];
		}
	}
}
