README


Diseño y Programación Orientados a Objetos
1er. Sem 2016

Tarea 3: Bolas Libres y Enlazadas en Espacio Cerrado como Objetos de Software

Tomás Gómez
Johannes Rothkegel

--------------------------------------------------------------------------

Estrategia:
	
	Se busca implementar un modelo de un sistema en el que interactuan bolas, contenedores y resortes unidos a bolas, todas con fuerzas naturales de un entorno que simula el real en dos dimensiones, aún así la simulación estará bajo varios supuestos. Primero las colisiones serán de tipo elástica, 
	luego los resortes no tendrán masa ni límite de elasticidad y las esferas serán modeladas sin roce ni momento angular.
	(IDEA) Para la implementación se busca crear, a partir de los constructores, el material entregado y las clases pedidas en la tarea, las clases
	Ball, Container y Spring, en ese orden. La tarea fue dividida en 4 etapas.

--------------------------------------------------------------------------

Explicación del código:

	Etapa1:
	Luego se utiliza la conservación del momento lineal en el choque de las bolas, para calcular el estado siguiente de la bola modificando el método ComputeNextState. Una bola se considerará chocando si la distancia al otro objeto es menor a su radio, se emplea la variable areTouching para ese fin, al mismo tiempo debemos estar seguros que se estan acercando, para eso se agrega la variable areApproaching, por último ambas condiciones se deben cumplir para estar colisionando, todo esto dentro del método collideWith.

	Etapa2:
	Se agrega la clase Container, lo que hace es generar un conjunto de rectas que dibujan un contenedor donde estarán las bolas chocando entre ellas y con los muros del contenedor. Al igual que en la Etapa1 se utiliza la conservación del momento lineal para agregar el choque con el muro, luego la opción de choque se agrega en la configuración del estado siguiente de la bola.

	Etapa3:
	Se agrega la clase Spring donde se modela el resorte igual a la tarea 1, como ya se mencionó no tendrán ni masa ni límite de elasticidad.
	
	Etapa4:
	La última etapa consta de agregar más de un resorte a una bola, se define una bola con más de un resorte a la vez donde cada uno de manera individual calcula la fuerza ejercida por sobre la bola.

 Se hace una aclaración donde la etapa 3 y 4 son exactamente iguales ya que se creó directamente la opción de agregar más de un resorte a una bola.

--------------------------------------------------------------------------
Errores no Abordados:


--------------------------------------------------------------------------

Forma de Uso:
	Para ejecutar el programa solo se debe compilar los archivos por medio del makefile de la forma:

	$ make

	Y luego correr el ejecutable indicando el delta de tiempo con que se modelará el programa, el tiempo total que se quiere simular y un periodo de muestreo, con la siguiente configuración:

	$ ./PhysicsLab <delta tiempo>  <tiempo total a simular> <periodo de muestreo>

	Para limpiar los ejecutables escribir:

	$ make clean
