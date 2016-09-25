README

Diseño y Programación Orientados a Objetos
1er. Sem 2016

Tarea 2: Simulación Gráfica de Bolas Libres y Enlazadas en Espacio Cerrado como Objetos de Software.

Tomás Gómez
Johannes Rothkegel

--------------------------------------------------------------------------

Estrategia:
Se busca implementar una interfaz gráfica de un modelo de un sistema en el que interactuan bolas,
contenedores y resortes unidos a bolas, todas con fuerzas naturales de un entorno que simula el real en dos dimensiones,
realizada en la tarea-1.
(IDEA) Para la implementación se busca crear, a partir del material facilitado, la interfaz gráfica que reaccione a los
eventos definidos. Los eventos serán tales como, definir un nuevo tiempo de simulación, detener o correr la simulación,
agregar bolas, contenedores y elasticos y por último permitir guardar el escenario o cargar alguno ya guardado.
La tarea se divide en 4 etapas.
	

--------------------------------------------------------------------------

Explicación del código:

	Etapa1: Se arreglan los errores de compilación y se modificaron las medidas del contenedor con los ejes X e Y, para
	lograr una mejor adaptación a la pantalla de los computadores del grupo. Se definen las figuras a dibujar en las 
	clases gráficas de cada objeto.
	

	Etapa2: Se define el menu para que el usuario pueda modificar el lugar de trabajo agregando objetos o modificando 
	los tiempos. Se agregan dos clases que serán los listeners de cada menu, se crea la clase EditMenuListener y 
	WorldMenuListener, para controlar los objetos que se agregan al sistema y controlar los tiempos de simulación, 
	detenciones y comienzo del tiempo, respectivamente.
	

	Etapa3: Se agregó la opción que el usuario, al momento de estar el mundo dentenido, pueda trasladar los objetos 
	dentro del sistema, haciéndolos incluso interactuar entre ellos, como es el caso del elástico con la bola. Una 
	acotación es que el método contain sugerido en la tarea no fue utilizado ya que no se necesitó tal uso. Para mayor
	información acerca de esta etapa, los pasos más importantes están comentados en el código de las clases Ball, 
	Spring, Receptacle, MyWorld. Se agrega la clase MouseListener, con ésta clase se permite retener las acciones del 
	mouse e interpretarlas, luego transmitir las acciones a las clases antes mencionadas, tambíen está debidamente comen-
	tada.
	
	Etapa4: Se crea la clase FileMenuListener para recibir los eventos de guardado y cargado, luego se utilizó la clase
	JFileChooser para generar una interfaz donde el usuario puede definir dónde y con que nombre será guardado el archivo,
	o puede elegir el archivo en cualquier directorio del computador.
	
--------------------------------------------------------------------------
Errores no Abordados:

-Colisión desde la esquina, una bola que colisiona con un contenedor justo desde un ángulo de 45 grados con alguna esquina
 exterior, no es considerada como colisión y la bola ingresa en el contenedor.

-Al agregar objetos al sistema el objeto que tiene preferencia sobre el otro es el que fue creado después, en vez de definir
 limites más pequeños se mantuvo el orden de selección.

-Al insertar un elástico y luego una bola, no se permite interacción entre el elástico y esa bola pues la bola quedó "sobre"
el elástico, éste error se intentó manejar, pero luego de bastante tiempo perdido se decidió dejar de lado.

--------------------------------------------------------------------------

Forma de Uso:
	En cada etapa se puede correr el programa utilizando para compilar:
	$make
	Luego para ejecutar:
	$make run
	y para generar la documentación:
	$make doc
	para limpiar todos los .class:
	$make clean