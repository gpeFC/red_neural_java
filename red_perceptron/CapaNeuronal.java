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
	private double[][] incremento;	// Pesos anteriores de cada neurona.
	private Perceptron[] neuronas;	// Neuronas de la capa.

	/** Constructor para inicializar el objeto 'capa neuronal'. */
	public CapaNeuronal(int numNeurs, int numArgs){
		this.entradas = new double[numArgs];
		for(int i=0; i<numArgs; i++)
			this.entradas[i] = 0.0;
		this.funciones = new byte[numNeurs];
		this.delthas = new double[numNeurs];
		this.salidas = new double[numNeurs];
		this.incremento = new double[numNeurs][numArgs];
		this.neuronas = new Perceptron[numNeurs];
		for(int i=0; i<numNeurs; i++){
			this.funciones[i] = 0;
			this.delthas[i] = 0.0;
			this.salidas[i] = 0.0;
			this.neuronas[i] = new Perceptron(numArgs);
			for(int j=0; j<numArgs; j++)
				this.incremento[i][j] = 0.0;
		}
	}

	public void establecerFunciones(byte[] funciones){
		this.funciones = funciones;
	}

	public void establecerEntrada(double[] entradas){
		this.entradas = entradas;
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

	public byte[] obtenerFunciones(){
		return this.funciones;
	}

	public double[] obtenerDelthas(){
		return this.delthas;
	}

	public double[] obtenerSalidas(){
		return this.salidas;
	}

	public double[][] obtenerPesosActuales(){
		double[][] pesos = new double[this.neuronas.length][this.entradas.length];
		for(int i=0; i<this.neuronas.length; i++)
			pesos[i] =this.neuronas[i].obtenerPesos();
		return pesos;
	}

	public double[][] obtenerIncremento(){
		return this.incremento;
	}

	public Perceptron[] obtenerNeuronas(){
		return this.neuronas;
	}

	public void actualizarAlphas(){
	}

	/** Método para actualizar el valor del umbral\bias de cada una de las neuronas de la capa.(Perceptron Simple) */
	public void actualizarUmbrales(int indice, double error){
		this.neuronas[indice].establecerUmbral(this.neuronas[indice].obtenerUmbral() + (this.neuronas[indice].obtenerAlpha()*error));
	}

	/** Método para actualizar el valor del umbral\bias de cada una de las neuronas de la capa.(Perceptron Multicapa) */
	public void actualizarUmbrales(){
		double umbralAnterior, umbralNuevo=0.0;
		for(int i=0; i<this.neuronas.length; i++){
			umbralAnterior = this.neuronas[i].obtenerUmbral();
			umbralNuevo = umbralAnterior + (this.neuronas[i].obtenerAlpha() * this.delthas[i]);
			System.out.printf("\nUmb: (%f) = (%f) + [(%f)*(%f)]\n", umbralNuevo, umbralAnterior, this.neuronas[i].obtenerAlpha(), this.delthas[i]);
			this.neuronas[i].establecerUmbral(umbralNuevo);
		}
	}

	/** Método para actualizar el valor de los pesos sinapticos de cada una de las neuronas de la capa.(Perceptron Simple) */
	public void actualizarPesos(int indice, double error){
		double[] pesosAnteriores;
		double[] pesosNuevos = new double[this.entradas.length];
		pesosAnteriores = this.neuronas[indice].obtenerPesos();
		for(int j=0; j<this.entradas.length; j++)
			pesosNuevos[j] = pesosAnteriores[j] + (this.neuronas[indice].obtenerAlpha()*error*this.entradas[j]);
		this.neuronas[indice].establecerPesos(pesosNuevos);
	}

	/** Método para actualizar el valor de los pesos sinapticos de cada una de las neuronas de la capa.(Perceptron Multicapa) */
	public void actualizarPesos(double eta, int indice){
		double[] pesosAnteriores;
		double[] pesosNuevos = new double[this.entradas.length];
		for(int i=0; i<this.neuronas.length; i++){
			pesosAnteriores = this.neuronas[i].obtenerPesos();
			if(indice == 1){
				for(int j=0; j<this.entradas.length; j++)
					pesosNuevos[j] = pesosAnteriores[j] + (this.neuronas[i].obtenerAlpha() * this.delthas[i] * this.entradas[j]);
				this.neuronas[i].establecerPesos(pesosNuevos);
			}
			else if(indice == 2){
				for(int j=0; j<this.entradas.length; j++){
					pesosNuevos[j] = pesosAnteriores[j] + (this.neuronas[i].obtenerAlpha() * this.delthas[i] * this.entradas[j]);
					pesosNuevos[j] = pesosNuevos[j] + (eta * this.incremento[i][j]);
					this.incremento[i][j] = pesosAnteriores[j] - pesosNuevos[j];
				}
				this.neuronas[i].establecerPesos(pesosNuevos);
			}
		}
	}

	/** Método para calcular y establecer el deltha de cada neurona de la capa de salida. */
	public void calcularDelthas(double[] errores){
		for(int i=0; i<this.neuronas.length; i++){
			this.delthas[i] = errores[i] * Funcion.derivada(this.funciones[i], Propagacion.sumaPonderada(this.neuronas[i].obtenerUmbral(), this.entradas, this.neuronas[i].obtenerPesos()));
		}
	}

	/** Método para calcular y establecer el deltha de cada neurona de las capas ocultas. */
	public void calcularDelthas(double[] deltas, Perceptron[] neuronas){
		double sumaDeltha, pesos[];
		for(int i=0; i<this.neuronas.length; i++){
			sumaDeltha = 0.0;
			for(int j=0; j<neuronas.length; j++){
				pesos = neuronas[j].obtenerPesos();
				sumaDeltha = sumaDeltha + (deltas[j]*pesos[i]);
			}
			this.delthas[i] = Funcion.derivada(this.funciones[i], Propagacion.sumaPonderada(this.neuronas[i].obtenerUmbral(), this.entradas, this.neuronas[i].obtenerPesos()))*sumaDeltha;
		}
	}

	/** Método para calcular y establecer la salida de cada neurona de la capa en un mismo arreglo. */
	public void calcularSalidas(){
		for(int i=0; i<this.neuronas.length; i++){
			this.neuronas[i].calcularSalida(this.funciones[i], this.entradas);
			this.salidas[i] = this.neuronas[i].obtenerSalida();
		}
	}

	public void calcularIncremento(int indice, double[] pesosPrevios, double[] pesosActuales){
		for(int i=0; i<pesosPrevios.length; i++)
			this.incremento[indice][i] = pesosPrevios[i] - pesosActuales[i];
	}

	public void mostrarDatosCapa(){
		double[] pesos;
		for(int i=0; i<neuronas.length; i++){
			System.out.printf("\nNeurona (%d) {", i+1);
			System.out.printf("Alpha:(%f) |", neuronas[i].obtenerAlpha());
			System.out.printf(" Umbral:(%f) |", neuronas[i].obtenerUmbral());
			pesos = neuronas[i].obtenerPesos();
			System.out.printf(" Pesos:[");
			for(int j=0; j<pesos.length; j++)
				System.out.printf("(%f)", pesos[j]);
			System.out.printf("]}");
		}
	}
}