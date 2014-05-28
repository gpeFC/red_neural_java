/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: RedNeuronal

import java.util.ArrayList;

public class RedNeuronal{
	private ArrayList<CapaNeuronal> perceptron;

	public RedNeuronal(int numArgs, int numCapas, byte[] numNeursCapa){
		this.perceptron = new ArrayList<CapaNeuronal>(numCapas);
		for(int i=0; i<numCapas; i++){
			CapaNeuronal capaNeuronal = new CapaNeuronal(numNeursCapa[i], numArgs);
			this.perceptron.add(capaNeuronal);
		}
	}

	public ArrayList<CapaNeuronal> obtenerPerceptron(){
		return this.perceptron;
	}

	public void establecerConfiguracionAlphas(byte indice){
		double alpha=0.0;
		CapaNeuronal capa;
		if(indice == 1){
			do{
				alpha = (Math.random()*10 + 1)/10.0;
			}while(alpha == 0.0);
			for(int i=0; i<this.perceptron.size(); i++){
				capa = this.perceptron.get(i);
				capa.establecerAlphas(alpha);
			}
		}
		else if(indice == 2){
			for(int i=0; i<this.perceptron.size(); i++){
				capa = this.perceptron.get(i);
				do{
					alpha = (Math.random()*10 + 1)/10.0;
				}while(alpha == 0.0);
				capa.establecerAlphas(alpha);
			}
		}
		else if(indice == 3){
			for(int i=0; i<this.perceptron.size(); i++){
				capa = this.perceptron.get(i);
				capa.establecerAlphas();
			}
		}
	}

	public void establecerConfiguracionFunciones(byte funcion, byte numNeurs){
		byte[] funciones = new byte[numNeurs];
		CapaNeuronal capaNeuronal = this.perceptron.get(0);
		for(int i=0; i<numNeurs; i++)
			funciones[i] = funcion;
		capaNeuronal.establecerFunciones(funciones);
	}

	public void establecerConfiguracionFunciones(byte funcion, byte[] numNeursCapa){
		byte[] funciones;
		CapaNeuronal capa;
		for(int i=0; i<numNeursCapa.length; i++){
			capa = this.perceptron.get(i);
			funciones = new byte[numNeursCapa[i]];
			for(int j=0; j<numNeursCapa[i]; j++)
				funciones[j] = funcion;
			capa.establecerFunciones(funciones);
		}
	}

	public void establecerConfiguracionFunciones(byte funcionSalida, byte funcionOcultas, byte[] numNeursCapa){
		byte[] funciones;
		CapaNeuronal capa;
		for(int i=0; i<numNeursCapa.length; i++){
			capa = this.perceptron.get(i);
			funciones = new byte[numNeursCapa[i]];
			if(i == numNeursCapa.length-1){
				for(int j=0; j<numNeursCapa[i]; j++)
					funciones[j] = funcionSalida;
				capa.establecerFunciones(funciones);
			}
			else{
				for(int j=0; j<numNeursCapa[i]; j++)
					funciones[j] = funcionOcultas;
				capa.establecerFunciones(funciones);
			}
		}
	}

	public void establecerConfiguracionFunciones(int indice, byte[] funciones){
		CapaNeuronal capa;
		capa = this.perceptron.get(indice);
		capa.establecerFunciones(funciones);
	}

	public void realizarPropagacion(double[] entradas){
		CapaNeuronal capa, previa;
		for(int i=0; i<this.perceptron.size(); i++){
			capa = this.perceptron.get(i);
			if(i == 0){
				capa.establecerEntrada(entradas);
				capa.calcularSalidas();
			}
			else{
				previa = this.perceptron.get(i-1);
				capa.establecerEntrada(previa.obtenerSalidas());
				capa.calcularSalidas();
			}
		}
	}

	public void realizarRetroPropagacion(double[] salidas){
		double[] salidaRed, deltas;
		Perceptron[] neuronas;
		CapaNeuronal capa, previa;
		for(int i=this.perceptron.size()-1; i>=0; i++){
			capa = this.perceptron.get(i);
			if(i == this.perceptron.size()-1){
				salidaRed = capa.obtenerSalidas();
				deltas = new double[salidaRed.length];
				for(int j=0; j<salidas.length; j++)
					deltas[j] = salidas[j] - salidaRed[j];
				capa.calcularDelthas(deltas);
			}
			else{
				previa = this.perceptron.get(i+1);
				deltas = previa.obtenerDelthas();
				neuronas = previa.obtenerNeuronas();
				capa.calcularDelthas(deltas, neuronas);
			}
		}
	}
}