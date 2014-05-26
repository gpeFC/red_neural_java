/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: RedNeuronal

import java.util.ArrayList;

public class RedNeuronal{
	ArrayList<CapaNeuronal> perceptron;

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
			capa = perceptron.get(i);
			funciones = new byte[numNeursCapa[i]];
			for(int j=0; j<numNeursCapa[i]; j++)
				funciones[j] = funcion;
			capa.establecerFunciones(funciones);
		}
	}
}