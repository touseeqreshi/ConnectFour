package connect4;

import java.util.Scanner;  

public class connect4
{
  //First we need to create the basic visual pattern
 
  public static String[][] createPattern()
  {
    //the game is more like a table of 6 columns and 6 rows
	
	//we're going to have to make a 2D array of 7 rows 

     String[][] f = new String[7][15];

    //Time to loop over each row from up to down

    for (int i = 0; i < f.length; i++)
    {      
       for (int j = 0; j < f[i].length; j++)
      {
        if (j % 2 == 0) f[i][j] ="|";

        else f[i][j] = " ";
 
        if (i==6) f[i][j]= "-";
      }      
    }
    return f;
  }

  public static void printPattern(String[][] f)
  {

    for (int i =0;i<f.length;i++)
    {

      for (int j=0;j<f[i].length;j++)
      {

        System.out.print(f[i][j]);
      }

      System.out.println();
    }
  }

  //making the lowest empty row of a specific column Red

  public static void dropRedPattern(String[][] f)
  {

    //We need to ask user which column he wants to drop a red into

    System.out.println("Drop a red disk at column (0–6): ");

    @SuppressWarnings("resource")
	Scanner scan = new Scanner (System.in);

    int c = 2*scan.nextInt()+1;

    //we have to loop over each row from the bottom to the top

    //till we find the first empty space, drop, and then finish

    for (int i =5;i>=0;i--)
    {

      if (f[i][c] == " ")
      {

        f[i][c] = "R";

        break;

      }

    }

  }
   
  //Now for Blue

  public static void dropbluePattern(String[][] f)
  {

    System.out.println("Drop a Blue disk at column (0–6): ");

    @SuppressWarnings("resource")
	Scanner scan = new Scanner (System.in);

    int c = 2*scan.nextInt()+1;

    for (int i =5;i>=0;i--)
    {

      if (f[i][c] == " ")
      {

        f[i][c] = "B";

        break;

      }
       
    }

  }

  //There are four patterns of Reds or Blue that can win the game

  public static String checkWinner(String[][] f)
  {
    //look for the first type of winning line, a horizontal line

    for (int i = 0; i < 6; i++)
    {

      for (int j = 0; j < 7; j += 2)
      {

        if ((f[i][j+1] != " ")

        && (f[i][j+3] != " ")
        
        && (f[i][j+5] != " ")

        && (f[i][j+7] != " ")

        && ((f[i][j+1] == f[i][j+3])

        && (f[i][j+3] == f[i][j+5])

        && (f[i][j+5] == f[i][j+7])))

        //If we found a same-colored pattern, we'll return the color so that we will know who won.

          return f[i][j+1]; 
      }

    }

    //Now, let's first loop over each odd-numbered column by incrementing with 2

    //and check for consecutive boxes in the same column that are the same color

    for (int i=1;i<15;i+=2)
    {

      for (int j =0;j<3;j++)
      {

            if((f[j][i] != " ")

            && (f[j+1][i] != " ")

            && (f[j+2][i] != " ")

            && (f[j+3][i] != " ")

            && ((f[j][i] == f[j+1][i])

            && (f[j+1][i] == f[j+2][i])

            && (f[j+2][i] == f[j+3][i])))

              return f[j][i]; 
      } 

    }

    //For the left-up to right-down diagonal line

    //We'll have to loop over the 3 uppermost

    //rows and then go from left to right column-wise

    for (int i = 0; i < 3; i++)
    {

      for (int j = 1; j < 9; j += 2)

      {

            if((f[i][j] != " ")

            && (f[i+1][j+2] != " ")

            && (f[i+2][j+4] != " ")

            && (f[i+3][j+6] != " ")

            && ((f[i][j] == f[i+1][j+2])

            && (f[i+1][j+2] == f[i+2][j+4])

            && (f[i+2][j+4] == f[i+3][j+6])))

              return f[i][j]; 
      } 

    }

    //Similar to the method above, but we're just reversing

    for (int i = 0; i < 3; i++)

    {
      for (int j = 7; j < 15; j += 2)

      {

            if((f[i][j] != " ")

            && (f[i+1][j-2] != " ")

            && (f[i+2][j-4] != " ")
            
            && (f[i+3][j-6] != " ")

            && ((f[i][j] == f[i+1][j-2])

            && (f[i+1][j-2] == f[i+2][j-4])

            && (f[i+2][j-4] == f[i+3][j-6])))

              return f[i][j]; 
      } 

    }

    //If after going over the table and we didn't find a winning color

    return null;
  }

  public static void main (String[] args)
  {

    String[][] f = createPattern();

    boolean loop = true;

    //We need something to keep track of whose turn it is

    int count = 0;

    printPattern(f);

    while(loop)
    {

       //Let's say that Red gets the first turn and thus every other turn then

       if (count % 2 == 0) dropRedPattern(f);

       else dropbluePattern(f);

       count++;//We need to keep track of the turns

       printPattern(f);

       //check for a winner during every turn 

       if (checkWinner(f) != null)
       {

          if (checkWinner(f) == "R")

             System.out.println("The red player won.");

          else if (checkWinner(f)== "B")

            System.out.println("The Blue player won.");

         loop = false;

    }

  }

}

}

