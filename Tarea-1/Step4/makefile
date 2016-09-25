JFLAGS = -g
JC = javac
JVM= java  # Added by Agustín González
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = Ball.java Container.java MyWorld.java PhysicsElement.java PhysicsLab.java Spring.java Vector2D.java

# main variable: Added by Agustín González
MAIN = PhysicsLab

default: classes

classes: $(CLASSES:.java=.class)

# run tarjet added by Agustín González
run: 
	$(JVM) $(MAIN)

clean:
	$(RM) *.class