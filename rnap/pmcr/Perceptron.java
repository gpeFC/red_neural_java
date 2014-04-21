/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Perceptron ...

public class Perceptron{
	private double umbral;
	private double salida;
	private double[] pesos;

	public Perceptron(int numArgs){
		this.salida = 0.0;
		do{
			this.umbral = (Math.random()*10 + 1)/10.0;
		}while(this.umbral == 0.0);
		this.pesos = new double[numArgs];
		for(int i=0; i<numArgs; i++){
			do{
				this.pesos[i] = (Math.random()*10 + 1)/10.0;
			}while(this.pesos[i]==0.0);
		}
	}

	public double obtenerUmbral(){
		return this.umbral;
	}

	public double obtenerSalida(){
		return this.salida;
	}

	public double[] obtenerPesos(){
		return this.pesos;
	}

	public void actualizarUmbral(){
	}

	public void actualizarPesos(){
	}

	public void calcularSalida(){
	}
}