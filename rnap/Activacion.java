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

	public Activacion(double propagacion, double activacionAnterior){
		this.propagacion = propagacion;
		this.activacionAnterior = activacionAnterior;
	}

	public double obtenerActivacion(){
		return this.activacionActual;
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

	public void activacionSigmoideTangenteBipolar(){
	}

	public void activacionSigmoideTangenteHiperbolica(){
	}
}