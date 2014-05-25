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

	public void establecerConfiguracionFunciones(byte funcion, byte numNeurs){
		byte[] funciones = new byte[numNeurs];
		CapaNeuronal capaNeuronal = this.perceptron.get(0);
		for(int i=0; i<numNeurs; i++)
			funciones[i] = funcion;
		capaNeuronal.establecerFunciones(funciones);
	}
}