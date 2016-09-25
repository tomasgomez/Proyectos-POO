README

Diseño y Programación Orientados a Objetos
1er. Sem 2016

Tarea 1: Bolas Libres y Enlazadas en Espacio Cerrado como Objetos de Software

Tomás Gómez
Jesús Márquez
Johannes Rothkegel

--------------------------------------------------------------------------

Estrategia:
	
	Se busca implementar un modelo de un sistema en el que interactuan bolas, contenedores y resortes unidos a bolas, todas con fuerzas naturales de un entorno que simula el real en dos dimensiones, aún así la simulación estará bajo varios supuestos. Primero las colisiones serán de tipo elástica, 
	luego los resortes no tendrán masa ni límite de elasticidad y las esferas serán modeladas sin roce ni momento angular.
	(IDEA) Para la implementación se busca crear, a partir de los constructores, el material entregado y las clases pedidas en la tarea, las clases
	Vector2D, Container y Spring, en ese orden. La tarea fue dividida en 4 etapas.

--------------------------------------------------------------------------

Explicación del código:

	Etapa1:
	Se crea la clase Vector2D, se construye la clase con las características de un vector en dos dimensiones (x,y), y sus métodos para extraer las funciones más comunes como la magnitud (size), la suma de vectores (plus), resta de vectores (minus), producto punto (dot) y la multiplicación por un escalar (times). Luego se utiliza la conservación del momento lineal en el choque de las bolas, para calcular el estado siguiente de la bola. Una bola se considerará chocando si la distancia al otro objeto es menor a su radio, se emplea la variable areTouching para ese fin, al mismo tiempo debemos estar seguros que se estan acercando, para eso se agrega la variable areApproaching, por último ambas condiciones se deben cumplir para estar colisionando.

	Etapa2:
	Se agrega la clase Container, lo que hace es generar un conjunto de rectas que dibujan un contenedor donde estarán las bolas chocando entre ellas y con los muros del contenedor. Al igual que en la Etapa1 se utiliza la conservación del momento lineal para agregar el choque con el muro, luego la opción de choque se agrega en la configuración del estado siguiente de la bola.

	Etapa3:
	Para la actual etapa se agrega la clase Spring donde se modela el resorte que como ya se mencionó no tendrán ni masa ni límite de elasticidad. Para ésta implementación al ser más compleja se comentó bastante el código (En la clase Spring de la Etapa4) con el fin de explicar la función de cada paso. Importante señalar que al momento de crear una instancia de la clase spring, esta no se vincula a nada. Para que logre llevar a cabo función, es necesario enganchar a las bolas manualmente, por ende, después de definidas las esferas a víncular
	y el resorte en sí en el archivo PhysicsLab, se debe llamar al metodo de la clase spring setBalls(), el cual recibe por argumento los dos objetos Ball a víncular, sin necesidad de un orden específico. Además, se agrega el método getElements() en MyWorld.java, el cual no posee argumentos y retorna un arraylist de elements. Esto es necesario para poder iterar sobre los elementos creados, y así en el paso 4 detectar si hay más de un resorte unido a una bola.

	Etapa4:
	La última etapa consta de agregar a una bola más de un resorte, requirió agregar un vector de enteros que guarda la id del resorte, y luego ir simulando al iterar sobre todos los elementos físicos, dentro de ellos cada uno de los resortes presentes en el mundo modelado, así se calcula la fuerza de cada uno sobre las bolas unidas a ellos. Todos los pasos están explicados dentro del código de Spring para esta etapa y la Etapa3 debido a su complejidad. (dentro de Spring Etapa4).

	Además, se implementa los fenomenos de gravedad y de fuerza de roce. Para ello, se permite pasar como parametros extras del ejecutable el valor de la gravedad y el valor de la constante de roce viscoso, en ese orden. Estos son optativos y no agregarlos no altera el funcionamiento del programa. Se puede pasar como parametro sólo el valor de la gravedad, pero no se puede agregar solo el valor del roce viscoso. Para que los valores de dichas constantes sean considerado por las instancias de ball, se debe llamar a los métodos de la clase Ball setGravity() y setFriction() en el main de PhysicsLab, quienes reciben por argumento la constante de gravedad y la constante de roce viscoso respectivamente.

	El efecto de la gravedad consiste en acelerar la bola en el eje y, siempre apuntando con un valor fijo hacia abajo (vector unitario -j) y el efecto del roce viscoso es frenar a la esfera en funcion de su velocidad, considerando el modelo F=-b*v, en donde F es la fuerza de roce, v es la velocidad de la esfera y b es la constante de roce viscoso.
--------------------------------------------------------------------------
Errores no Abordados:

-Colisión Simultanea Bola-Contenedor, solo detecta colisión con bola. 
 Ejemplo de situación: Contenedor con coordenadas uL(0,4) dR(4,0), origen de ball1 (0,0), velocidad ball1 (0,1), origen ball2 (4,4), velocidad ball2 (-1,0)

--------------------------------------------------------------------------

Forma de Uso:
	Para ejecutar el programa solo se debe compilar los archivos por medio del makefile de la forma:

	$ make

	Y luego correr el ejecutable indicando el delta de tiempo con que se modelará el programa, el tiempo total que se quiere simular y un periodo de muestreo, con la siguiente configuración:

	$ java PhysicsLab <delta tiempo>  <tiempo total a simular> <periodo de muestreo>

	En el paso 4, si se desea incorporar gravedad, o gravedad y roce viscoso, se llama con la siguiente configuración:

	$ java PhysicsLab <delta tiempo>  <tiempo total a simular> <periodo de muestreo> <constante de gravedad>

	ó

	$ java PhysicsLab <delta tiempo>  <tiempo total a simular> <periodo de muestreo> <constante de gravedad> <constante de roce viscoso>