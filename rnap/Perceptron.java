/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Perceptron ...

public class Perceptron{
	private int epocas; 
	private int errores;			// Contador de errores durante el entrenamiento.
	private double bias;			// Umbral de activacion de la neurona.
	private double error;			// Error de aprendizaje actual.
	private double salida;			// Salida obtenida de la neurona.
	private double epsilon;			// Tasa de aprendizaje del entrenamiento.
	private double[] salidas;		// Salidas asociadas a los patrones de entrenamiento.
	private double[] pesos;		// Patron de pesos asociados a las entradas.
	private double[][] entradas;	// Patrones de entrenamiento de la red.

	public Perceptron(int patrones, int argumentos, double bias, double epsilon, double[] salidas, double[] pesos, double[][] entradas){
		this.epocas = 0;
		this.errores = 0;
		this.error = 0.0;
		this.salida = 0.0;
		this.bias = bias;	// Inicializacion aleatoria del bias.
		this.epsilon = epsilon;	// Inicializacion aleatoria de la tasa de aprendizaje.
		this.salidas = new double[patrones];				// Asignacion del vector de las salidas.
		this.salidas = salidas;
		this.pesos = new double[argumentos];
		this.pesos = pesos;
		this.entradas = new double[patrones][argumentos];
		this.entradas = entradas;						// Asignacion del vector de las entradas.
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

	public void inicializarErrores(){
		this.errores = 0;
	}

	public int obtenerEpocas(){
		return this.epocas;
	}

	public int obtenerErrores(){
		return this.errores;
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

	public double obtenerSalidaEstablecida(int indice){
		return this.salidas[indice];
	}

	public double[] obtenerPesos(){
		return this.pesos;
	}

	public void calcularSalida(int indice){
		double salida;
		salida = 0.0;
		for(int i=0; i<this.pesos.length; i++)
			salida = salida + (this.pesos[i] * this.entradas[indice][i]);
		salida = salida + bias;
		if(salida > 0.0)
			this.salida = 1.0;
		else
			this.salida = -1.0;
	}

	public void actualizarBias(){
		this.bias = this.bias + this.error;
	}

	public void actualizarErrores(){
		this.errores++;
	}

	public void actualizarEpocas(){
		this.epocas++;
	}

	public void actualizarPesos(int indice){
		double[] aux_pesos = new double[this.pesos.length];
		for(int i=0;i<this.pesos.length; i++){
			aux_pesos[i] = this.epsilon*this.error*this.entradas[indice][i];
			this.pesos[i] = this.pesos[i] + aux_pesos[i];
		}
	}
}