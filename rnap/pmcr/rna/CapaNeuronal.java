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
	private double[] entrada;		// Entradas para cada neurona.
	private Perceptron[] neuronas;	// Neuronas de la capa.

	/** Constructor para inicializar el objeto 'capa neuronal'. */
	public CapaNeuronal(int numNeurs, int numArgs){
		this.entrada = new double[numArgs];
		for(int i=0; i<numArgs; i++){
			this.entrada[i] = 0.0;
		}
		this.funciones = new byte[numNeurs];
		this.salidas = new double[numNeurs];
		this.delthas = new double[numNeurs];
		this.neuronas = new Perceptron[numNeurs];
		for(int i=0; i<numNeurs; i++){
			this.funciones[i] = 0;
			this.salidas[i] = 0.0;
			this.delthas[i] = 0.0;
			this.neuronas[i] = new Perceptron(numArgs);
		}
	}

	public void establecerAlphas(double alpha){
		for(int i=0; i<this.neuronas.length; i++)
			this.neuronas[i].establecerAlpha(alpha);
	}

	public void establecerEntrada(double[] entrada){
		this.entrada = entrada;
	}

	public void establecerFuncionActivacion(byte[] funciones){
		this.funciones = funciones;
	}

	public double[] obtenerSalidas(){
		return this.salidas;
	}

	public double[] obtenerDelthas(){
		return this.delthas;
	}

	/** Método para actualizar el valor del umbral\bias de cada una de las neuronas de la capa. */
	public void actualizarUmbrales(double error){
		for(int i=0; i<this.neuronas.length; i++)
			this.neuronas[i].establecerUmbral(this.neuronas[i].obtenerUmbral() + (this.neuronas[i].obtenerUmbral()*error));
	}

	public void actualizarPesos(double error){
		double[] pesosAnteriores;
		double[] pesosNuevos = new double[this.entrada.length];
		for(int i=0; i<this.neuronas.length; i++){
			pesosAnteriores = this.neuronas[i].obtenerPesos();
			for(int j=0; j<this.entrada.length; j++)
				pesosNuevos[j] = pesosAnteriores[j] + (this.neuronas[i].obtenerAlpha()*error*this.entrada[j]);
			this.neuronas[i].establecerPesos(pesosNuevos);
		}
	}

	/** Método para actualizar el valor de los pesos sinapticos de cada una de las neuronas de la capa. */
	public void actualizarPesos(){
		double[] pesosAnteriores;
		double[] pesosNuevos = new double[this.entrada.length];
		for(int i=0; i<this.neuronas.length; i++){
			pesosAnteriores = this.neuronas[i].obtenerPesos();
			for(int j=0; j<this.entrada.length; i++)
				pesosNuevos[j] = pesosAnteriores[j] + (this.delthas[j] * this.entrada[j]);
			this.neuronas[i].establecerPesos(pesosNuevos);
		}
	}

	/** Método para calcular y establecer la salida de cada neurona de la capa en un mismo arreglo. */
	public void calcularSalidas(){
		for(int i=0; i<this.neuronas.length; i++){
			this.neuronas[i].calcularSalida(this.funciones[i], this.entrada);
			this.salidas[i] = this.neuronas[i].obtenerSalida();
		}
	}

	/** Método para calcular y establecer el deltha de cada neurona de la capa en un mismo arreglo. */
	public void calcularDelthas(double errorDelta){
		for(int i=0; i<this.neuronas.length; i++){
			/*
			if(this.funciones[i] == 1){
				this.delthas[i] = errorDelta * Derivada.logaritmoSigmoidal(Propagacion.sumaPonderada(this.neuronas[i].obtenerUmbral(), this.entrada, this.neuronas[i].obtenerPesos()));
			}
			else if(this.funciones[i] == 2){
				this.delthas[i] = errorDelta * Derivada.tangenteSigmoidal(Propagacion.sumaPonderada(this.neuronas[i].obtenerUmbral(), this.entrada, this.neuronas[i].obtenerPesos()));
			}
			else if(this.funciones[i] == 3){
				this.delthas[i] = errorDelta * Derivada.tangenteHiperbolica(Propagacion.sumaPonderada(this.neuronas[i].obtenerUmbral(), this.entrada, this.neuronas[i].obtenerPesos()));
			}
			*/
		}
	}
}
