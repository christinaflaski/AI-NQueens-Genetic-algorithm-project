import java.util.Scanner;

public class main {
    public static void main(String args[]) {
        long startTime = System.nanoTime();

        genetic_algorithm algorithm = new genetic_algorithm();
        // variables in  run : populationSize, mutationProbability, maximumSteps, minimumFitness
        queens solution = algorithm.run(0.08, 10000);
        //print the solution board
        solution.print();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        System.out.println("That took " + duration + " milliseconds");

    }
}
