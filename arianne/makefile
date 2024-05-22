JFLAGS = -g -implicit:none -encoding UTF-8
JC = javac
JVM = java
MAIN = Main
.SUFFIXES: .java .class
.java.class: ; $(JC) $(JFLAGS) $*.java

CLASSES = \
        Dir.java \
        Player.java \
        Fenetre.java \
        Fichier.java \
        ActionMenu.java \
        Bouton.java \
        MapEditor.java \
        ActionMapEditor.java \
        DessinGrille.java \
        SecondThread.java \
        Algorithme.java \
        ActionManuelle.java \
        Menu.java \
        Main.java 

default: classes

classes: $(CLASSES:.java=.class)

run: ; $(JVM) $(MAIN)

clean: ; $(RM) *.class