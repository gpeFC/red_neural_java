/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: CapaNeuronal

/**
* Clase definida para crear objetos 'capa neuronal' de tipo 'Perceptron' para 
* construir una Red Neuronal Artificial multicapa.
* @author Emanuel GP
*/
public class CapaNeuronal{
	private byte[] funciones;		// Indicador de funcion de activacion por neurona.
	private double[] delthas;		// Delthas calculados de cada neurona.
	private double[] salidas;		// Salidas obtenidas de cada neurona.
	private double[] entradas;		// Entradas para cada neurona.
	private double[][] pesos;		// Pesos anteriores de cada neurona.
	private Perceptron[] neuronas;	// Neuronas de la capa.

	/** Constructor para inicializar el objeto 'capa neuronal'. */
	public CapaNeuronal(int numNeurs, int numArgs){
		this.entradas = new double[numArgs];
		for(int i=0; i<numArgs; i++)
			this.entradas[i] = 0.0;
		this.funciones = new byte[numNeurs];
		this.delthas = new double[numNeurs];
		this.salidas = new double[numNeurs];
		this.pesos = new double[numNeurs][numArgs];
		this.neuronas = new Perceptron[numNeurs];
		for(int i=0; i<numNeurs; i++){
			this.funciones[i] = 0;
			this.delthas[i] = 0.0;
			this.salidas[i] = 0.0;
			this.neuronas[i] = new Perceptron(numArgs);
			for(int j=0; j<numArgs; j++)
				this.pesos[i][j] = 0.0;
		}
	}

	public void establecerFunciones(byte[] funciones){
		this.funciones = funciones;
	}

	public void establecerEntrada(double[] entradas){
		this.entradas = entradas;
	}

	public void establecerPesos(double[][] pesos){
		this.pesos = pesos;
	}

	public void establecerAlphas(double alpha){
		for(int i=0; i<this.neuronas.length; i++)
			this.neuronas[i].establecerAlpha(alpha);
	}

	public void establecerAlphas(){
		double alpha=0.0;
		for(int i=0; i<this.neuronas.length; i++){
			do{
				alpha = (Math.random()*10 + 1)/10.0;
			}while(alpha == 0.0);
			this.neuronas[i].establecerAlpha(alpha);
		}
	}

	public double[] obtenerDelthas(){
		return this.delthas;
	}

	public double[] obtenerSalidas(){
		return this.salidas;
	}

	public double[][] obtenerPesos(){
		return this.pesos;
	}

	public void actualizarAlphas(){
	}

	/** Método para actualizar el valor del umbral\bias de cada una de las neuronas de la capa. */
	public void actualizarUmbrales(int indice, double error){
		this.neuronas[indice].establecerUmbral(this.neuronas[indice].obtenerUmbral() + (this.neuronas[indice].obtenerAlpha()*error));
	}

	public void actualizarPesos(int indice, double error){
		double[] pesosAnteriores;
		double[] pesosNuevos = new double[this.entradas.length];
		pesosAnteriores = this.neuronas[indice].obtenerPesos();
		for(int j=0; j<this.entradas.length; j++)
			pesosNuevos[j] = pesosAnteriores[j] + (this.neuronas[indice].obtenerAlpha()*error*this.entradas[j]);
		this.neuronas[indice].establecerPesos(pesosNuevos);
	}

	/** Método para actualizar el valor de los pesos sinapticos de cada una de las neuronas de la capa. */
	public void actualizarPesos(){
		double[] pesosAnteriores;
		double[] pesosNuevos = new double[this.entradas.length];
		for(int i=0; i<this.neuronas.length; i++){
			pesosAnteriores = this.neuronas[i].obtenerPesos();
			for(int j=0; j<this.entradas.length; i++)
				pesosNuevos[j] = pesosAnteriores[j] + (this.delthas[j] * this.entradas[j]);
			this.neuronas[i].establecerPesos(pesosNuevos);
		}
	}

	/** Método para calcular y establecer el deltha de cada neurona de la capa en un mismo arreglo. */
	public void calcularDelthas(double errorDelta){
		for(int i=0; i<this.neuronas.length; i++){
			this.delthas[i] = errorDelta * Funcion.derivada(this.funciones[i], Propagacion.sumaPonderada(this.neuronas[i].obtenerUmbral(), this.entradas, this.neuronas[i].obtenerPesos()));
		}
	}

	/** Método para calcular y establecer el deltha de cada neurona de la capa en un mismo arreglo. */
	public void calcularDelthas(double[] deltas){
		for(int i=0; i<this.neuronas.length; i++){
			this.delthas[i] = Funcion.derivada(this.funciones[i], Propagacion.sumaPonderada(this.neuronas[i].obtenerUmbral(), this.entradas, this.neuronas[i].obtenerPesos()));
		}
	}

	/** Método para calcular y establecer la salida de cada neurona de la capa en un mismo arreglo. */
	public void calcularSalidas(){
		for(int i=0; i<this.neuronas.length; i++){
			this.neuronas[i].calcularSalida(this.funciones[i], this.entradas);
			this.salidas[i] = this.neuronas[i].obtenerSalida();
		}
	}

	public void imprimirDatos(){
		double[] pesos;
		for(int i=0; i<neuronas.length; i++){
			System.out.printf("\nNeurona: %d", i);
			System.out.printf("\n  Alpha: %f", neuronas[i].obtenerAlpha());
			System.out.printf("\n  Umbral: %f", neuronas[i].obtenerUmbral());
			System.out.printf("\n  Salida: %f", neuronas[i].obtenerSalida());
			pesos = neuronas[i].obtenerPesos();
			System.out.printf("\n  Pesos: ");
			for(int j=0; j<pesos.length; j++)
				System.out.printf("{%f}", pesos[j]);
		}
	}
}