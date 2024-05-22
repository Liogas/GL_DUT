#Gaston LIONS

# CHAPITRE 1 : BUT FINAL

but : memory

# CHAPITRE 2 : VARIABLES

OFILES = menu.o \
		 game.o \
         main.o

CC = gcc

CFLAGS = -Wall -ansi -pedantic -g

# CHAPITRE 3 : DEPENDANCES (REGLES IMPLICITES)


menu.o : memory.h 

game.o : memory.h

main.o : memory.h


#CHAPITRE 4 : DEPENDANCES AVEC COMMANDES

memory : $(OFILES)
	$(CC) $(CFLAGS) -o memory $(OFILES) -lgraph

#CHAPITRE 5 : NETTOYAGE DES FICHIERS GENERES

clean :
	-rm -f $(OFILES) memory

run :
	./memory
	
#CHAPITRE 6 : BUTS FACTICES

.PHONY : but clean run
