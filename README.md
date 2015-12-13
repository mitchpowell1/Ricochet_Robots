# Ricochet_Robots
An implementation and collection of solver algorithms for the ricochet robots game

Included in this repository are several compiled .jar files of the Breadth-First Search solver at various stages of optimization.

Also included are several pictures of the original Ricochet Robots game board and a file called boardAdjacencies, which corresponds exactly with the picture of board side A.

Command line usage of the Ricochet Robots solver is as follows:
	java -jar <Jar File> <board file> <number of iterations> <verbose>

For example:
	java -jar RicochetRobotsBFSHash.jar boardAdjacencies 1 true

This call will use the jar that utilizes memoization via a hashset (this is the most optimized version of the solver), pull in square adjacency information from the file "boardAdjacencies", place the robots randomly on the board, pick a random target square, and find the optimum solution for the given target square, it will do this 1 time as per the call. The verbosity argument is "true" so the program will print each step to the console for each iteration, and then print out statistics at the end. If the <number of iterations> argument is large to gather more accurate statistics, <verbose> should be set to false.
