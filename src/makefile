
JCC = javac

JFLAGS = -g

default: CigaretteSmokerProblem.class Smokers.class Agatha.class Cached.class 
	java CigaretteSmokerProblem 
CigaretteSmokerProblem.class: CigaretteSmokerProblem.java
	$(JCC) $(JFLAGS) CigaretteSmokerProblem.java
Smokers.class: Smokers.java
	$(JCC) $(JFLAGS) Smokers.java

Agatha.class: Agatha.java
	$(JCC) $(JFLAGS) Agatha.java

Cached.class: Cached.java
	$(JCC) $(JFLAGS) Cached.java

# To start over from scratch, type 'make clean'.  
# Removes all .class files, so that the next make rebuilds them
#
clean: 
	$(RM) *.class