JFLAGS = -g
JC = javac
JVM= java  # Added by Agustín González
JDOC = javadoc -d SoftwareDentalDoc
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = SoftwareDental.java IngresoUsuarioListener.java IngresoUsuario_GUI.java 

# main variable: Added by Agustín González
MAIN = SoftwareDental

default: classes

classes: $(CLASSES:.java=.class)

# run tarjet added by Agustín González
run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class

doc:
	$(JDOC) *.java