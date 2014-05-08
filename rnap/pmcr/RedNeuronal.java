/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: RedNeuronal

import java.util.ArrayList;

public class RedNeuronal{
	private int epocas;		
	private double errorMinimo;
	private double errorCalculado;
	private double alpha;
	private double[] salidasPatrones;
	private double[][] entradasPatrones;
	private ArrayList<CapaNeuronal> capasNeuronales;

	public RedNeuronal(int epocas, int numPatrs, int numArgs, int numCapas, int[] numNeursCapa){
		this.epocas = epocas;
		this.errorMinimo = 0.0;
		this.errorCalculado = 0.0;
		do{
			this.alpha = (Math.random()*10 + 1)/10.0;
		}while(this.alpha == 0.0);
		this.salidasPatrones = new double[numPatrs];
		this.entradasPatrones = new double[numPatrs][numArgs];
		for(int i=0; i<numPatrs; i++){
			for(int j=0; j<numArgs; j++){
				this.entradasPatrones[i][j] = 0.0;
			}
			this.salidasPatrones[i] = 0.0;
		}
		this.capasNeuronales = new ArrayList<CapaNeuronal>(numCapas);
		for(int i=0; i<numCapas; i++){
			CapaNeuronal capaNeuronal = new CapaNeuronal(numNeursCapa[i], numArgs);
			this.capasNeuronales.add(capaNeuronal);
		}
	}

	public void establecerErrorMinimo(int error, int indexSalida){
		this.errorMinimo = (this.salidasPatrones[indexSalida]*(double)error)/100.0;
	}

	public void establecerSalidas(double[] salidasPatrones){
		this.salidasPatrones = salidasPatrones;
	}

	public void establecerEntradas(double[][] entradasPatrones){
		this.entradasPatrones = entradasPatrones;
	}

	public void establecerConfiguracionActivacion(int funcion, int[] numNeursCapa){
		int[] funciones;
		CapaNeuronal capaActual;
		for(int i=0; i<this.capasNeuronales.size(); i++){
			capaActual = this.capasNeuronales.get(i);
			funciones = new int[numNeursCapa[i]];
			for(int j=0; j<numNeursCapa[i]; j++)
				funciones[j] = funcion;
			capaActual.establecerFuncionActivacion(funciones);
		}
	}

	public void establecerConfiguracionActivacion(int funcionSalida, int funcionOcultas, int[] numNeursCapa){
		int[] funciones;
		CapaNeuronal capaActual;
		for(int i=0; i<this.capasNeuronales.size(); i++){
			capaActual = this.capasNeuronales.get(i);
			if(i == this.capasNeuronales.size()-1){
				funciones = new int[numNeursCapa[i]];
				for(int j=0; j<numNeursCapa[i]; j++)
					funciones[j] = funcionSalida;
				capaActual.establecerFuncionActivacion(funciones);
			}
			else{
				funciones = new int[numNeursCapa[i]];
				for(int j=0; j<numNeursCapa[i]; j++)
					funciones[j] = funcionOcultas;
				capaActual.establecerFuncionActivacion(funciones);
			}
		}
	}

	public void establecerConfiguracionActivacion(int[] funcionCapa, int[] numNeursCapa){
		int[] funciones;
		CapaNeuronal capaActual;
		for(int i=0; i<this.capasNeuronales.size(); i++){
			capaActual = this.capasNeuronales.get(i);
			funciones = new int[numNeursCapa[i]];
			for(int j=0; j<numNeursCapa[i]; j++)
				funciones[j] = funcionCapa[i];
			capaActual.establecerFuncionActivacion(funciones);
		}
	}

	public double obtenerErrorMinimo(){
		return this.errorMinimo;
	}

	public double obtenerErrorCalculado(){
		return this.errorCalculado;
	}

	public void realizarPropagacion(int indexEntr, int[] numNeursCapa){
		System.out.printf("\nValor: %d\n", indexEntr);
		double[] salidasCalculadas = new double[numNeursCapa[indexEntr]];
		CapaNeuronal capaActual, capaAuxiliar;
		for(int i=0; i<this.capasNeuronales.size(); i++){
			capaActual = this.capasNeuronales.get(i);
			if(i == 0)
				capaActual.establecerEntrada(this.entradasPatrones[indexEntr]);
			else{
				capaAuxiliar = this.capasNeuronales.get(i-1);
				capaActual.establecerEntrada(capaAuxiliar.obtenerSalidas());
			}
			capaActual.calcularSalidas();
		}
		capaActual = this.capasNeuronales.get(this.capasNeuronales.size()-1);
		salidasCalculadas = capaActual.obtenerSalidas();
		for(int i=0; i<salidasCalculadas.length; i++)
			this.errorCalculado = this.errorCalculado + (this.salidasPatrones[i] - salidasCalculadas[i]);
		this.errorCalculado = this.errorCalculado / (double)(salidasCalculadas.length);
	}

	public void realizarRetroPropagacion(int[] numNeursCapa){
		double errorDeltha=0.0, delthas[];
		CapaNeuronal capaActual, capaAuxiliar;
		for(int i=this.capasNeuronales.size()-1; i>=0; i--){
			capaActual = this.capasNeuronales.get(i);
			if(i == this.capasNeuronales.size()-1)
				errorDeltha = this.errorCalculado;
			else{
				capaAuxiliar = this.capasNeuronales.get(i+1);
				delthas = new double[numNeursCapa[i+1]];
				delthas = capaAuxiliar.obtenerDelthas();
				for(int j=0; j<numNeursCapa[i+1]; j++)
					errorDeltha = errorDeltha + delthas[j];
				errorDeltha = errorDeltha / (double)(numNeursCapa[i+1]);
			}
			capaActual.calcularDelthas(errorDeltha);
		}
		for(int i=0; i<this.capasNeuronales.size(); i++){
			capaActual = this.capasNeuronales.get(i);
			capaActual.actualizarUmbrales(this.alpha);
			capaActual.actualizarPesos(this.alpha);
		}
	}
}
