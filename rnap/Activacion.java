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

	public Activacion(double activacion, double propagacion){
		activacionAnterior = activacion;
		this.propagacion = propagacion;
	}

	public double obtenerActivacion(){
		return activacionActual;
	}

	public void activacionIdentidad(){
	}

	public void activacionEscalonada(){
	}

	public void activacionLinealTramos(){
	}

	public void activacionSigmoidea(){
	}
}