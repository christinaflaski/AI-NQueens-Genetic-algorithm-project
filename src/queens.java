import java.util.Random;
import java.util.Scanner;

public class queens implements Comparable<queens> {
    //the board of the game
    private int[] board;


    //Integer that holds the non attacks score of a queen
    private int score;


    //Constructs a randomly created board
    queens( int N)
    {

        this.board = new int[N];
        Random r = new Random();
        for(int i = 0; i < this.board.length; i++)
        {
            this.board[i] = r.nextInt(N);
        }
        this.calculateScore();
    }

    //Constructs a copy of a chromosome
    queens(int[] board_copy, int N)
    {
        //System.out.println("hi"+N);
        this.board = new int[N];
        for(int i = 0; i <N; i++)
        {
            //System.out.println(board_copy[i]);
            this.board[i] = board_copy[i];
        }
        this.calculateScore();
        //this.print();
    }


    //Calculates the fitness score of the chromosome as the number queen pairs that are NOT threatened
    void calculateScore()
    {
        int nonThreats = 0;

        for(int i = 0; i < this.board.length; i++) {

            for(int j = i+1; j < this.board.length; j++) {
                if((this.board[i] != this.board[j]) && (Math.abs(i - j) != Math.abs(this.board[i] - this.board[j]))) {
                    nonThreats++;
                }
            }
        }

        this.score=nonThreats;
        setScore(score);

    }

    //randomize new position of a queen that i want to move
    void newPos(int N)
    {
        Random r = new Random();
        this.board[r.nextInt(N)] = r.nextInt(N);
        calculateScore();
    }


    // print tou pinaka
    void print()
    {
        System.out.print("Chromosome : |");
        for(int i = 0; i < board.length; i++)
        {
            System.out.print(board[i]);
            System.out.print("|");
        }
        System.out.print(", Score : ");
        System.out.println(score);

        System.out.println("                        ");
        for(int i = 0; i < board.length; i++)
        {
            for(int j=0; j < board.length; j++)
            {
                if(board[j] == i)
                {
                    System.out.print("Q ");
                }
                else
                {
                    System.out.print("- ");
                }
            }
            System.out.println("");
        }
        System.out.println("                            ");

    }

    //compareTo function -> sorting can be done according to fitness scores
    @Override
    public int compareTo(queens x)
    {
        return score - x.score;
    }

    public void  setScore(int score) {
        this.score=score;
    }
    public int getScore() {
        return this.score;
    }

    public int[] getboard() {
        return this.board;
    }

    public void setboard(int[] board) {
        this.board = board;
    }



}
