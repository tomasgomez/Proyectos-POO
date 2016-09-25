CC = g++
INCLUDE = -I. 
.cpp.o:
#	$(CC)  -c    $<
	$(CC) -std=c++11  -c    $<

Objetivo = PhysicsLab
Dependencias = CVector.o PhysicsElement.o Ball.o MyWorld.o PhysicsLab3.o Container.o Wall.o Spring.o

all: $(Objetivo)

PhysicsLab: $(Dependencias)
	$(CC) -o $@ $^

# $@ es el nombre del objetivo (etiqueta) de la regla
# $< es el nombre del primer pre-requisito 
# $? es el nombre de todos los pre-requisitos que son mas nuevos que el nombre de la regla
# $^ es el nombre de todos los pre-requisitos


clean:
	rm *.o $(Objetivo)

