/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: RedNeuronal

import java.util.ArrayList;

public class RedNeuronal{
	private String nombrePerceptron;
	private String funcionPerceptron;
	private String topologiaPerceptron;
	private String configuracionAlphas;
	private String configuracionFunciones;
	private ArrayList<CapaNeuronal> perceptron;

	public RedNeuronal(int numArgs, int numCapas, byte[] numNeursCapa, String nombrePerceptron, String topologiaPerceptron){
		this.nombrePerceptron = nombrePerceptron;
		if(topologiaPerceptron.equals("MULTICAPA"))
			this.funcionPerceptron = "";
		else
			this.funcionPerceptron = null;
		this.topologiaPerceptron = topologiaPerceptron;
		this.configuracionAlphas = null;
		this.configuracionFunciones = null;
		this.perceptron = new ArrayList<CapaNeuronal>(numCapas);
		for(int i=0; i<numCapas; i++){
			CapaNeuronal capaNeuronal = new CapaNeuronal(numNeursCapa[i], numArgs);
			this.perceptron.add(capaNeuronal);
		}
	}

	public String obtenerNombrePerceptron(){
		return this.nombrePerceptron;
	}

	public String obtenerFuncionPerceptron(){
		return this.funcionPerceptron;
	}

	public String obtenerTopologiaPerceptron(){
		return this.topologiaPerceptron;
	}

	public ArrayList<CapaNeuronal> obtenerPerceptron(){
		return this.perceptron;
	}

	public void establecerNombrePerceptron(String nombrePerceptron){
		this.nombrePerceptron = nombrePerceptron;
	}

	public void establecerFuncionPerceptron(String funcionPerceptron){
		this.funcionPerceptron = funcionPerceptron;
	}

	public void establecerConfiguracionAlphas(String configuracionAlphas){
		this.configuracionAlphas = configuracionAlphas;
	}

	public void establecerConfiguracionFunciones(String configuracionFunciones){
		this.configuracionFunciones = configuracionFunciones;
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

	public void actualizarParametrosNeuronales(){
		CapaNeuronal capa;
		for(int i=0; i<this.perceptron.size(); i++){
			capa = this.perceptron.get(i);
			capa.actualizarUmbrales();
			capa.actualizarPesos();
		}
	}

	public void mostrarDatosPerceptron(){
		CapaNeuronal capa;
		System.out.printf("\nNombre:                  %s", this.nombrePerceptron);
		System.out.printf("\nTopologia:               %s", this.topologiaPerceptron);
		System.out.printf("\nConfiguracion/TDA:       %s", this.configuracionAlphas);
		System.out.printf("\nConfiguracion/Funciones: %s", this.configuracionFunciones);
		if(this.topologiaPerceptron.equals("SIMPLE"))
			System.out.printf("\nFuncion/Activacion:      %s", this.funcionPerceptron);
		for(int i=0; i<this.perceptron.size(); i++){
			capa = this.perceptron.get(i);
			System.out.printf("\nCapa (%d)", i+1);
			capa.mostrarDatosCapa();
		}
	}
}