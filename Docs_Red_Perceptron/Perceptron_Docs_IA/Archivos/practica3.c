/*******************************************************************************

 Nombre del Archivo:    practica3.c
 Nombre del Programa:   Perceptron Simple
 Autor:                 Emanuel Garcia Perez
 Ubicacion:             Facultad de Ciencias, UAEM.
 Fecha:                 15 de Julio de 2012
 Version:               1.0
 Licencia:              Licencia Publica General Version 3 (GPLv3)


  Descripcion general del programa.

                          Perceptron Simple.
  El programa emula el funcionamiento de una Red Neuronal Artificial de tipo 
Perceptron Simple, mediante la cual se puede realizar un aprendizaje para 
poder clasificar problemas que son del tipo linealmente separables, esto es 
que dada una segmentacion del plano donde se ubican los puntos a clasificar
del problema, es posible dividir visualmente las clases en que se encuentran
ubicados cada uno de los puntos mediante el Trazo de una Frontera de Decision.

Opciones principales del Programa:

    a) Capturar Datos para el Entrenamiento del Perceptron.
    b) Iniciar y Mostrar el Entrenamiento del Perceptron.
    c) Ingresar Datos de Puntos de Prueba para Clasificar con el Perceptron.
    d) Salir del Programa.
    
  Capturar Datos para el Entrenamiento del Perceptron:
  En esta seccion del programa, se invoca una funcion definida por el usuario
  que permitira la captura de datos de entrada que seran utilizados para el
  respectivo entrenamiento del Perceptron, estos datos seran almacenados en 
  un archivo de datos para su posterior llamado cuando sean usados.
  
  Iniciar y Mostrar el Entrenamiento del Perceptron:
  Esta seccion permite ejecutar y mostrar en la pantalla el entrenamiento del
  Perceptron desde que se generan aleatoriamente el Peso(w) y el Umbral(b), 
  hasta que se concluyen las Epocas necesarias con las Iteraciones definidas 
  para poder asegurar que se ha logrado el aprendizaje de los patrones 
  necesarios para Clasificar un Punto de entrada arbitrario.
  
  Ingresar Datos de Puntos de Prueba para Clasificar con el Perceptron:
  Esta es la seccion del programa, donde se comprueba el correcto funcionamiento
  del Perceptron, una vez que se ha concluido con el entrenamiento del mismo, 
  para esto se ingresa en el programa datos de puntos arbitrarios para ser 
  evaluados por el programa y de esta manera se puedan Clasificar correctamente.
  
  Salir del Programa:
  Esta opcion del menu, permite la finalizacion correcta y en ejecucion del programa
  mientras no se seleccione esta opcion, el programa continuara su ejecucion, a menos
  que el programa sea detenido o interrumpido por fuerza bruta.


      "El conocimiento humano pertenece al mundo"
      'AntiTrust'

*********************************************************************************/


#include <stdio.h>
#include <stdlib.h>
#include <string.h>


  // Constante definida para el numero de datos de entrada para el entrenamiento del Perceptron.
#define TAM_V 100


  /* Estructura para la Captura/Lectura de los datos de entrada para el entrenamiento 
     del Perceptron desde/hacia un fichero. */
typedef struct sCapturaDatosE{
  int iPuntoX;
  int iPuntoY;
  int iClaseT;
}sCapturaDatos_E;


  // Declaracion de las funciones. 
void vConvierteCadenaF(char *cadena);
void vCapturaDatosEntrenamiento(int num);


  // Funcion Principal del Programa.
void main(void){
  
      // Declaracion de las variables locales de la funcion principal del programa.
  int iNumeroPuntos, iContadorEpoca, iContadorIteracion, iContadorArreglo, iClase, iError, iSigno;
  int iContadorDatos, iBanderaError, iPuntoPrueba[2], iDatosCapturados[3][TAM_V];
  float fHardlims, fGanancia, fCambioSigno, fCoordenadaX, fCoordenadaY, fPesos[2];
  char cContinuar, cOpcionMenu[3], cNombreFichero[16];
  sCapturaDatos_E sNewDatosE;
  FILE *fichero;
  
  
  do{   // Ciclo principal de programa que se ejecutara hasta que se indique la salida del programa.
  
    system("clear");
    
    printf("\n \t\t\t Red Neuronal Artificial <PERCEPTRON SIMPLE> \n");
    
      // Menu principal de las opciones del programa.
    printf("\n El siguiente menu, indica las acciones del programa, introduzca la letra de la accion a realizar. \n\n");
    printf("\n\n a) Capturar Datos para el Entrenamiento del Perceptron.");
    printf("\n\n b) Iniciar y Mostrar el Entrenamiento del Perceptron.");
    printf("\n\n c) Ingresar Datos de Puntos de Prueba para Clasificar con el Perceptron.");
    printf("\n\n d) Salir del Programa. \n");
    
    printf("\n\n Seleccione la accion a realizar: ");
    fflush(stdin);
    gets(cOpcionMenu);
    vConvierteCadenaF(cOpcionMenu);
    
    if(!strcmp(cOpcionMenu, "A")){        // Capturar datos para el entrenamiento del Perceptron.
      system("clear");
      
      printf("\n \t Captura de Datos para el Entrenamiento del Perceptron.\n");
      printf("\n\n Indique el numero de datos a ingresar(Debe ser mayor a 0): ");
      scanf("%d", &iNumeroPuntos);
      
      if(iNumeroPuntos > 0){
        vCapturaDatosEntrenamiento(iNumeroPuntos);    // Llamada a la funcion de captura de los datos de entrada.
      }
      else{
        while(getchar()!='\n');
      }
      
    }
    
    else if(!strcmp(cOpcionMenu, "B")){   // Ejecutar y mostrar el entrenamiento del Perceptron.
      system("clear");
      
      printf("\n \t\t\t Entrenamiento del Perceptron... \n\n");
     
     
      /* Generacion de un numero aleatorio para decidir pseudo-arbitrariamente si alguno de los 
         valores del Peso(w) generado aleatoriamente se vuelve negativo. */
      iSigno = rand() % 101;
     
     
        // Ciclo para generar aleatoriamente el valor del vector Peso(w) y el Umbral(b) al iniciar el entrenamiento.
      while(1){
        srand(time(NULL));
        fGanancia = rand() % 10;
        fPesos[0] = rand() % 10;
        fPesos[1] = rand() % 10;
       if((fPesos[0] != 0) && (fPesos[1] != 0) && (fGanancia != 0))
          break;
      }
      
      fGanancia = fGanancia / 10;
      fPesos[0] = fPesos[0] / 10;
      fPesos[1] = fPesos[1] / 10;
      
      
          // Condiciones para establecer si uno de los valores del Peso(w) se vuelve negativo.
      if((iSigno > 50) && ((iSigno%2) != 0)){
          fCambioSigno = fPesos[0];
          fPesos[0] = 0.0 - fCambioSigno;
      }
      else if((iSigno <= 50) && ((iSigno%2) == 0)){
        fCambioSigno = fPesos[1];
        fPesos[1] = 0.0 - fCambioSigno;
      }
      
      printf("\n Ingresa el nombre de archivo donde se ubican los datos: ");
      gets(cNombreFichero);
      
      fichero =fopen(cNombreFichero, "r");    // Apertura del fichero de datos a usarse para el entrenamiento.
      
      if(fichero == NULL){
        system("clear");
        printf("\n\n Error de apertura del archivo de datos.\n");
      }
      else{
        
            // Ciclo para capturar los datos del fichero del entrenamiento en un arreglo para los datos de entrada.
        iContadorDatos = 0;
        while (!feof(fichero)){
          if(fread(&sNewDatosE, sizeof(sCapturaDatos_E), 1, fichero)){
            iDatosCapturados[0][iContadorDatos] = sNewDatosE.iPuntoX;
            iDatosCapturados[1][iContadorDatos] = sNewDatosE.iPuntoY;
            iDatosCapturados[2][iContadorDatos] = sNewDatosE.iClaseT;
            iContadorDatos++;
          }
        }
        fclose(fichero);    // Cerrado del fichero de datos del entrenamiento.
        
        printf("\n Peso(w) = [%.3f  %.3f] \t Ganancia(b) = %.3f \n", fPesos[0], fPesos[1], fGanancia);
        
        iBanderaError = 1;
        iContadorEpoca = 1;
        do{   // Ciclo de conteo para el numero de Epocas necesarias para el entrenamiento del Perceptron.
          
          iContadorArreglo = 0;
          iContadorIteracion = 1;
          do{   // Ciclo de conteo para el numero de Iteraciones realizadas durante el entrenamiento del Perceptron.
            
            
              // Calculo del valor de la funcion Hardlims del Perceptron: f(hardlims) = [Suma(Wi + Pj) + b]
            fHardlims = (((fPesos[0] * iDatosCapturados[0][iContadorArreglo]) + (fPesos[1] * iDatosCapturados[1][iContadorArreglo])) + fGanancia);
            
            
              // Asignacion de Clase para el punto de entrada evaluado con la funcion Hardlims.
            if(fHardlims > 0.0){
              iClase = 1;
            }
            else{
              iClase = -1;
            }
            
            
              // Calculo del Error(e) en la evaluacion del punto de entrada.
            iError = iDatosCapturados[2][iContadorArreglo] - iClase;
            
            
              // Calculo del Umbral(b) en la evaluacion del punto de entrada.
            fGanancia = fGanancia + (iError * 1.0);
            
            
              // Actualizaion del Peso(w) cuando el Error(e) generado sea distinto de 0: W(i+1) = W(i) + e[P(j)]
            if(iError != 0){
              fPesos[0] = fPesos[0] + ((iError * 1.0) * iDatosCapturados[0][iContadorArreglo]);
              fPesos[1] = fPesos[1] + ((iError * 1.0) * iDatosCapturados[1][iContadorArreglo]);
            }
            
            
              // Calculo de las Coordenadas(x, y) para el Trazo de la Frontera de Decision.
            fCoordenadaX = ((-1.0) * fGanancia) / fPesos[0];
            fCoordenadaY = ((-1.0) * fGanancia) / fPesos[1];
            
            
              // Impresion en la pantalla de los datos generados en la evaluacion de cada punto de entrada.
            printf("\n\nEpoca:%d", iContadorEpoca);
            printf("\nIteracion:%d", iContadorIteracion);
            printf("\nHardlims(f) = %.3f", fHardlims);
            printf("\nClase(a) = %d \t Error(e) = %d \t Ganancia(b) = %.3f", iClase, iError, fGanancia);
            if(iError != 0){
              printf("\nPeso(w+) = [%.3f  %.3f]", fPesos[0], fPesos[1]);
            }
            printf("\nCoordenada(x) = %.3f \t Coordenada(y) = %.3f", fCoordenadaX, fCoordenadaY);
            
            iContadorArreglo++;
            iContadorIteracion++;
          }while(iContadorIteracion <= iContadorDatos);
          
          
            // Verificacion que comprueba si el entrenamiento ya ha concluido.
          if(((iError == 0) && (iContadorEpoca != 1)) || (iContadorEpoca >= 100000)){
            iBanderaError = 0;
          }
          else{
            iContadorEpoca++;
          }
          
        }while(iBanderaError);
        
          // Condicion que verifica si el entrenamiento del Perceptron se Completo correctamente.
        if(iContadorEpoca >= 100000){
          printf("\n\n Entrenamiento del Perceptron Incompleto debido a demasiadas Epocas Iteradas.\n");
        }
        else{
          printf("\n\n Entrenamiento del Perceptron Completado.\n");
        }
        
      }
      
      printf("\n\n Presione <Enter> para continuar...");
      fflush(stdin);
      cContinuar = getchar();
      
    }
    
    else if(!strcmp(cOpcionMenu, "C")){   // Introducir datos de puntos de prueba para clasificar con el Perceptron.
      system("clear");
      
      printf("\n \t\t\t Prueba del Perceptron. \n");
      
      printf("\n Ingresa los valores del Punto a Clasificar... \n");
      
      
        // Captura de los datos del punto de prueba ingresado para clasificar con el Perceptron.
      printf("Punto(x): ");
      scanf("%d", &iPuntoPrueba[0]);
      printf("Punto(y): ");
      scanf("%d", &iPuntoPrueba[1]);
      
      
        // Evaluacion y Clasificacion del punto de entrada, mediante la funcion Hardlims del Perceptron.
      fHardlims = (((fPesos[0] * iPuntoPrueba[0]) + (fPesos[1] * iPuntoPrueba[1])) + fGanancia);
      if(fHardlims > 0.0){
        iClase = 1;
      }
      else{
        iClase = -1;
      }
      
      
        // Impresion en pantalla de la Clasificacion del punto de prueba ingresado.
      printf("\n\n Clase del Punto[%d  %d]: %d \n", iPuntoPrueba[0], iPuntoPrueba[1], iClase);
      
      printf("\n\n Presione <Enter> para continuar...");
      while(getchar()!='\n');
      cContinuar = getchar();
      
    }
    
    else if(!strcmp(cOpcionMenu, "D")){   // Salir del Programa.
      system("clear");
      exit(0);
      
    }
    
  }while(1);
  
  
}


  // Funcion para convertir una cadena ingresada a su equivalente en letras mayusculas.
void vConvierteCadenaF(char *cadena){
  
  int iContador;
  char *p;
  
  p = cadena;
  for(iContador=0;iContador<strlen(cadena);iContador++){
	  *(p + iContador) = toupper(*(p + iContador));
	}
  
}


  // Funcion para captura los datos de entrada para el entrenamiento del Perceptron.
void vCapturaDatosEntrenamiento(int num){
  
  int iContadorDatos, iNumDatos;
  char cSalir, cNombreArchivo[16];
  sCapturaDatos_E sNewCapturaDatosE;
  FILE *fichero;
  
  iNumDatos = num;
  
  printf("\n Escriba un nombre para el fichero donde se guardaran los datos: ");
  while(getchar()!='\n');
  gets(cNombreArchivo);
  
  fichero =fopen(cNombreArchivo, "a+");   // Apertura del fichero de datos.
  
  if(fichero == NULL){
    system("clear");
    printf("\n Error de apertura del archivo de datos.\n Presiona <Enter> para continuar...");
    while(getchar()!='\n');
    cSalir = getchar();
  }
  else{
    system("clear");
    printf("\n A continuacion deberas ingresar los valores de la informacion de los puntos \n");
    printf(" para el entrenamiento de la red Perceptron... \n");
    iContadorDatos = 1;
    do{
      
          // Captura de los datos de entrada ingresados por el usuario.
      printf("\n\n Punto%d.\n", iContadorDatos);
      printf("Coordenada(x): ");
      scanf("%d", &sNewCapturaDatosE.iPuntoX);
      printf("Coordenada(y): ");
      scanf("%d", &sNewCapturaDatosE.iPuntoY);
      printf("Clase(t): ");
      scanf("%d", &sNewCapturaDatosE.iClaseT);
      
          // Escritura de los datos de entrada en el fichero.
      fwrite(&sNewCapturaDatosE, sizeof(sCapturaDatos_E), 1, fichero);
      
      iContadorDatos++;
    }while(iContadorDatos <= iNumDatos);
    
     fclose(fichero);   // Cerrado del fichero de datos.
     
     system("clear");
     printf("\n Los datos fueron ingresados correctamente, presiona <Enter> para continuar...");
     while(getchar()!='\n');
     cSalir = getchar();
  }
  
}


