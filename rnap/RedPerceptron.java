/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: RedPerceptron

public class RedNeuronalPerceptron{
	private int numCapasOcultas;
	private int numNeuronasCapaSalida;
	private int numNeuronasCapasOcultas;

	private String arquitecturaPerceptron;
	private String rangoSalida;
	private String algoritmoPropagacion;
	private String aprendizajeActualizacion;
	private String activacionPerceptron;
	
	private Perceptron[] capaEntradaPerceptron;
	private Perceptron[] capaSalidaPerceptron;
	private Perceptron[][] capasOcultasPerceptron;
}
