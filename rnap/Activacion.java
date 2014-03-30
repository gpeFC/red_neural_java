/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Activacion

public class Activacion{
	private double activacionActual;
	private double activacionAnterior;
	private double propagacion;

	public Activacion(double activacionAnterior, double propagacion){
		this.activacionAnterior = activacionAnterior;
		this.propagacion = propagacion;
	}

	public double obtenerActivacion(){
		return activacionActual;
	}

	public void activacionIdentidad(){
	}

	public void activacionEscalonBinario(){
	}

	public void activacionEscalonBipolar(){
	}

	public void activacionLinealTramos(){
	}

	public void activacionSigmoideBinario(){
	}

	public void activacionSigmoideBipolar(){
	}
}