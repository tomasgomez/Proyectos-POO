README


Diseño y Programación Orientados a Objetos
1er. Sem 2016

Proyecto Software Dental

Tomás Gómez
Johannes Rothkegel

--------------------------------------------------------------------------

Estrategia:
	
	Se busca implementar un software para una consulta dental que permita llevar
	el registro de los pacientes y un horario te atención organizado para la 
	consulta. Se enfatiza en la seguridad de la información del programa por lo
	que se espera crear un sistema de login.

--------------------------------------------------------------------------

Explicación del código:

	Etapa1:
	Se crea una interfaz gráfica con la clase IngresoUsuario donde se le pide al
	cliente ingresar usuario y contraseña para hacer uso del programa.

--------------------------------------------------------------------------
Errores no Abordados:
	* No se ve la opción de tener más de un usuario para la consulta, se define 
	un usuario admin con su contraseña predefinida.

--------------------------------------------------------------------------

Forma de Uso:
	Para ejecutar el programa solo se debe compilar los archivos por medio del 
	makefile de la forma:

	$ make

	Y luego correr el ejecutable con el comando:

	$ make run

	Para limpiar los ejecutables escribir:

	$ make clean

	Para ejecutar la documentación:

	$ make doc