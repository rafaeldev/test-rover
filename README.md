# Premises (summarized)

Interpret the positioning of a rover within a given plateau. The details are described [here](https://github.com/rafaeldev/test-rover/blob/development/PREMISES.md#problem).

# Result

The idea is very simple! I needed to create only three POJOs: 
* Rover
* Plateau 
* Coordinates

**Rover** and **Plateau** are actors taken from the explanatory text of the project. **Coordinates** is a structure about the input data, where have 1 Plateau for several Rovers.

## Decisions

* A Rover which exit inside the Plateau, moving to ```Rover.X < 0 | Rover.Y < 0 | Rover.X > Plateau.X | Rover.X > Plateau.Y```, throw an Exception;
* A Rover which start position is out the Plateau (```Rover.X < 0 | Rover.Y < 0 | Rover.X > Plateau.X | Rover.X > Plateau.Y```), throw an Exception;
* A Command which does not exist, throw an Exception;
* A Guidance which does not exist, throw an Exception;

# Project dependencies

* [Download and install Java 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jre8-downloads-2133155.html)
* [Download maven](https://maven.apache.org/download.cgi) and [set up the Windows environment PATH](https://maven.apache.org/guides/getting-started/windows-prerequisites.html)

# Build and run

1. Open the prompt command;
1. Clone the project: ```"git clone https://github.com/rafaeldev/test-rover.git"```;
1. Enter on root folder project and run ```"clean mvn package"```;
1. Access the **target** path. A jar file with name "rover-1.0-SNAPSHOT.jar" will be created;
1. Run the JAR: ```"java -jar rover-1.0-SNAPSHOT.jar"```;

![Running the JAR](https://github.com/rafaeldev/test-rover/blob/development/running.png)
