/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: CapaNeuronal

public class CapaNeuronal{
	private int[] funciones;		// Indicador de funcion de activacion por neurona.
	private double[] delthas;		// Delthas calculados de cada neurona.
	private double[] salidas;		// Salidas obtenidas de cada neurona.
	private double[] entrada;		// Entradas para cada neurona.
	private Perceptron[] neuronas;	// Neuronas de la capa.

	public CapaNeuronal(int numNeurs, int numArgs){
		this.entrada = new double[numArgs];
		for(int i=0; i<numArgs; i++){
			this.entrada[i] = 0.0;
		}
		this.funciones = new int[numNeurs];
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

	public void establecerEntrada(double[] entrada){
		this.entrada = entrada;
	}

	public void establecerSalidas(){
		for(int i=0; i<this.salidas.length; i++){
			this.salidas[i] = this.neuronas[i].obtenerSalida();
		}
	}

	public void establecerFuncionActivacion(int[] funciones){
		this.funciones = funciones;
	}

	public double[] obtenerSalidas(){
		return this.salidas;
	}

	public double[] obtenerDelthas(){
		return this.delthas;
	}

	public void actualizarUmbrales(double alpha){
		for(int i=0; i<this.neuronas.length; i++)
			this.neuronas[i].establecerUmbral(this.neuronas[i].obtenerUmbral() + (-alpha*this.delthas[i]));
	}

	public void actualizarPesos(double alpha){
		double[] pesosAnteriores;
		double[] pesosNuevos = new double[this.entrada.length];
		for(int i=0; i<this.neuronas.length; i++){
			pesosAnteriores = this.neuronas[i].obtenerPesos();
			for(int j=0; j<this.entrada.length; i++)
				pesosNuevos[j] = pesosAnteriores[j] + (-alpha * this.delthas[j] * this.entrada[j]);
			this.neuronas[i].establecerPesos(pesosNuevos);
		}
	}

	public void calcularSalidas(){
		for(int i=0; i<this.neuronas.length; i++){
			this.neuronas[i].calcularSalida(this.funciones[i], this.entrada);
			this.salidas[i] = this.neuronas[i].obtenerSalida();
		}
	}

	public void calcularDelthas(double errorDelta){
		for(int i=0; i<this.neuronas.length; i++){
			if(this.funciones[i] == 1){
				this.delthas[i] = errorDelta * Derivada.logaritmoSigmoidal(Propagacion.sumaPonderada(this.neuronas[i].obtenerUmbral(), this.entrada, this.neuronas[i].obtenerPesos()));
			}
			else if(this.funciones[i] == 2){
				this.delthas[i] = errorDelta * Derivada.tangenteSigmoidal(Propagacion.sumaPonderada(this.neuronas[i].obtenerUmbral(), this.entrada, this.neuronas[i].obtenerPesos()));
			}
			else if(this.funciones[i] == 3){
				this.delthas[i] = errorDelta * Derivada.tangenteHiperbolica(Propagacion.sumaPonderada(this.neuronas[i].obtenerUmbral(), this.entrada, this.neuronas[i].obtenerPesos()));
			}
		}
	}
}
