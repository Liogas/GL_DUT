FlAGS = -implicit:none 
COMPIL= javac
EXECUTE = java


.SUFFIXES: .java .class

.java.class:
	$(COMPIL) $(FLAGS) -encoding UTF8 $*.java


CLASS = \
	controller/jeu/*.java	\
	controller/listener/*.java \
	controller/reseau/*.java \
	controller/*.java \
	model/algorithme/*.java \
	model/evaluation/*.java \
	model/grille/*.java \
	model/joueur/*.java \
	model/reseau/*.java \
	model/*.java \
	view/*.java \

default: class

class: $(CLASS:.java=.class)

run:
	$(EXECUTE) controller/Main

serveur:
	$(EXECUTE) controller/reseau/StartServeur

simulation:
	$(EXECUTE) controller/Simulation

clean: 
	$(RM) controller/*.class 
	$(RM) controller/jeu/*.class	
	$(RM) controller/listener/*.class 
	$(RM) controller/reseau/*.class 
	$(RM) controller/*.class 
	$(RM) model/algorithme/*.class 
	$(RM) model/evaluation/*.class 
	$(RM) model/grille/*.class 
	$(RM) model/joueur/*.class 
	$(RM) model/reseau/*.class 
	$(RM) model/*.class 
	$(RM) view/*.class 
