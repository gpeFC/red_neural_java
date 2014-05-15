/******************************************************************************
 * Seminario de Programacion I
 * Emanuel GP
 *
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/

// Clase: Entrenamiento

/**
* Clase definida para crear objetos 'entrenamiento' que aplica el algoritmo de 
* entrenamiento definido para la RNA indicada.
* @author Emanuel GP
*/
public class Entrenamiento{
	private int epocas;
	private double alpha;
	private double errorMinimo;
	private double errorCalculado;
	private double[] salidasPatrones;
	private double[][] entradasPatrones;

	public Entrenamiento(double[] salidasPatrones, double[][] entradasPatrones){
		this.epocas = 0;
		this.alpha = 0.0;
		this.errorMinimo = 0.0;
		this.errorCalculado = 0.0;
		this.salidasPatrones = salidasPatrones;
		this.entradasPatrones = entradasPatrones;
	}

	public void perceptronSimple(CapaNeuronal perceptron, int numNeuronas){
		int cont=0;
		double[] salidas = new double[this.salidasPatrones.length];
		do{
			this.alpha = (Math.random()*10 + 1)/10.0;
		}while(this.alpha == 0.0);
		perceptron.establecerAlphas(this.alpha);
		for(int i=0; i<this.salidasPatrones.length; i++){
			do{
				this.errorMinimo = 0.0;
				System.out.printf("\nPatron(%d)-Epoca(%d)", i, cont);
				perceptron.establecerEntrada(this.entradasPatrones[i]);
				perceptron.calcularSalidas();
				salidas = perceptron.obtenerSalidas();
				for(int j=0; j<salidas.length; j++)
					this.errorMinimo = this.errorMinimo + (this.salidasPatrones[j] - salidas[j]);
				this.errorMinimo = (this.errorMinimo) / ((double)salidas.length);
				if(this.errorMinimo == 0.0)
					break;
				else{
					perceptron.actualizarUmbrales(this.errorMinimo);
					perceptron.actualizarPesos(this.errorMinimo);
				}
				cont++;
			}while(true);
		}
	}

	public void algoritmoRetropropagacion(){}

	public void algoritmoMomento(){}

	public void algoritmoSilvaAlmeida(){}

	public void algoritmoDeltaBarDelta(){}
}
