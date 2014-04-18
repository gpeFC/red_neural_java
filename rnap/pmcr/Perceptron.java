/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Perceptron ...

public class Perceptron{
	private double bias;
	private double error;
	private double salida;
	private double[] pesos;

	public Perceptron(int numArgs){
		this.error = 0.0;
		this.salida = 0.0;
		do{
			this.bias = (Math.random()*10 + 1)/10.0;
		}while(this.bias == 0.0);
		this.pesos = new double[numArgs];
		for(int i=0; i<numArgs; i++){
			do{
				this.pesos[i] = (Math.random()*10 + 1)/10.0;
			}while(this.pesos[i]==0.0);
		}
	}

	public double obtenerBias(){
		return this.bias;
	}

	public double obtenerError(){
		return this.error;
	}

	public double[] obtenerPesos(){
		return this.pesos;
	}

	public double obtenerSalida(){
		return this.salida;
	}

	public void actualizarBias(){
	}

	public void actualizarError(){
	}

	public void actualizarPesos(){
	}

	public void calcularSalida(int num, double[] entrada){
		double propagacion=0.0;
		for(int i=0; i<entrada.length; i++){
			propagacion = propagacion + (entrada[i] * this.pesos[i]);
		}
		if(num == 1){
			this.salida = Activacion.activacionEscalonBinario(propagacion);
		}
		else if(num == 2){
			this.salida = Activacion.activacionEscalonBipolar(propagacion);
		}
		else if(num == 3){
			this.salida = Activacion.activacionSigmoidalBinaria(propagacion);
		}
		else if(num == 4){
			this.salida = Activacion.activacionTangenteSigmoidal(propagacion);
		}
		else if(num == 5){
			this.salida = Activacion.activacionTangenteHiperbolica(propagacion);
		}
	}
}