import java.util.*;

public class genetic_algorithm {


    private ArrayList<queens> population; // population with chromosomes
    private ArrayList<Integer> x; // list with chromosomes (indices) based on fitness score me poses den kanei attack

    genetic_algorithm()
    {
        this.population = null;
        this.x =null;
    }

    queens run(double mutationProbability, int maxSteps)
    {
        int n=taken(); // populationsize
        int minFitness=fact(n);
        //We initialize the population
        this.initializePopulation(n);
        Random r = new Random();
        for(int step = 0; step < maxSteps; step++)
        {
            //Initialize the new generated population
            ArrayList<queens> newPopulation = new ArrayList<>();
            for(int i = 0; i < n / 2; i++)
            {
                //We choose two chromosomes from the population
                //Due to how fitnessBounds ArrayList is generated, the probability of
                //selecting a specific chromosome depends on its fitness score
                int xIndex = this.x.get(r.nextInt(this.x.size()));
                queens xParent = this.population.get(xIndex);
                int yIndex = this.x.get(r.nextInt(x.size()));
                // prosexv na mhn einai idia ta dyo chromossomes
                while(xIndex == yIndex)
                {
                    yIndex = this.x.get(r.nextInt(x.size()));
                }

                queens yParent = this.population.get(yIndex);
                //We generate the children of the two chromosomes
                queens[] children = this.reproduce(xParent, yParent, n);

                //We might then mutate the children
                if(r.nextDouble() < mutationProbability)
                {
                    children[0].newPos(n);
                    children[1].newPos(n);
                }
                //...and finally add them to the new population
                newPopulation.add(children[0]);
                newPopulation.add(children[1]);
            }
            this.population = new ArrayList<>(newPopulation);
            //We sort the population so the one with the greater fitness is first
            this.population.sort(Collections.reverseOrder());
            //If the chromosome with the best fitness is acceptable we return it
            if(this.population.get(0).getScore() >= minFitness) return this.population.get(0);
            //We update the occurrences list
            this.updateOccurrences();
        }

        return this.population.get(0);
    }

    // dimension of the board ask by the user
    int taken(){
        System.out.println("GIVE THE N OF THE BOARD :");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        return N;
    }

    //user defined method for calculate factorial
    //The maximum score of queen pairs that are NOT threatened is (n-1) + (n-2) + ... + (n-n) .. it calculates the maxfitness in run
    int fact(int num)//user defined method for calculate factorial
    {
        int sum=0;
        for (int i = 1; i <= num; i++) {
            sum =(num-i)+sum;//calculate the wanted function
        }
        return sum;
    }

    //We initialize the population by creating random chromosomes
    void initializePopulation(int populationSize)
    {
        this.population = new ArrayList<>();
        for(int i = 0; i < populationSize; i++)
        {

            this.population.add(new queens(populationSize));
        }
        this.updateOccurrences();
    }

    //Updates the list that contains indexes of the chromosomes in the population ArrayList
    void updateOccurrences()
    {
        this.x = new ArrayList<>();
        for(int i = 0; i < this.population.size(); i++)
        {
            int f=this.population.get(i).getScore();

            for(int j = 0; j < f; j++)
            {
                //Each chromosome index exists in the list as many times as its fitness score
                //By creating this list this way, and choosing a random index from it,
                //the greater the fitness score of a chromosome, the greater chance it will be chosen.
                this.x.add(i);
            }
        }
    }

    //Reproduces two chromosomes and generates their children
    queens[] reproduce(queens x, queens y, int n)
    {
        Random r = new Random();

        //Randomly choose the intersection point
        int intersectionPoint = r.nextInt(population.size());
        int[] firstChild = new int[population.size()];
        int[] secondChild = new int[population.size()];

        //The first child has the left side of the x chromosome up to the intersection point...
        //The second child has the left side of the y chromosome up to the intersection point...
        for(int i = 0; i < intersectionPoint; i++)
        {
            firstChild[i] = x.getboard()[i];
            secondChild[i] = y.getboard()[i];
        }
        //the right side of the y chromosome after the intersection point (for the first)
        //the right side of the x chromosome after the intersection point (for the second)
        for(int i = intersectionPoint; i < firstChild.length; i++)
        {
            firstChild[i] = y.getboard()[i];
            secondChild[i] = x.getboard()[i];
        }
        return new queens[]{new queens(firstChild,n), new queens(secondChild,n)};
    }
}
