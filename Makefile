SRC=src
BIN=bin
MAIN=Main

JAVAC=javac
JAVA=java

SOURCES=$(shell find $(SRC) -name "*.java")

all: clean compile run

compile:
	mkdir -p $(BIN)
	$(JAVAC) -d $(BIN) $(SOURCES)

run:
	$(JAVA) -cp $(BIN) $(MAIN)

clean:
	rm -rf $(BIN)

.PHONY: all compile run clean
