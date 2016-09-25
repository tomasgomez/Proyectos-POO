JFLAGS = -g
JC = javac
JVM= java  # Added by Agustín González
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = Ball.java BallView.java Receptacle.java ReceptacleView.java MyWorld.java MyWorldView.java PhysicsElement.java Simulateable.java PhysicsLab.java Spring.java Vector2D.java Wall.java

# main variable: Added by Agustín González
MAIN = PhysicsLab

default: classes

classes: $(CLASSES:.java=.class)

# run tarjet added by Agustín González
run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class