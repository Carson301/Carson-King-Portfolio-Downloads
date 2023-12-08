//CS110A
//Carson J. King
//The GameDriver class begins a yahtzee game and asks the user if they would like to play again.

//NOTE:I would like to be evauluated for extra credit for early submission and the Player class.
//My main method has more than was required, I thought it would be best to ask the user if they
//would like to play again. That is why it is longer than the few lines of code mentioned in the
//yahtzee instructions on BB.


//Imports Scanner.
import java.util.Scanner;

public class GameDriver
{
   public static void main(String[] args)
   {
      //Creates Scanner object keyboard.
      Scanner keyboard = new Scanner(System.in);
      //Declares variable goodinput, represents whether input is good or not.
      boolean goodInput = false;
      //Declares playAgain variable intitialized to y, to help validate user input.
      String playAgain = "y";
      //Creates a Game object g.
      Game g = new Game();
      //While playAgain equals y, the game is played, until user inputs n to stop.
      while (playAgain.equals("y"))
      {
         //Resets goodInput so infinite loop does not occur, after play again is chosen.
         goodInput = false;
         //Creates a Game object g.
         g = new Game();
         //Uses playGame() Method to begin the game of yahtzee.
         g.playGame();
         //After the game has been completed, the user is prompted to play again.
         System.out.println("\nWould you like to play again? (y/n) ");
         //While user input is not valid.
         while (goodInput == false)
         {  
            //Try statement. 
            try
            {
               //User Input is set to playAgain.
               playAgain = keyboard.nextLine();
               //If user input is equal to y or n, goodInput is set to true.
               if (playAgain.equals("y") || playAgain.equals("n"))
                  goodInput = true;
               //If it is anything else an exception is thrown.
               else
                  throw (new Exception());
            }
            catch (Exception e)
            {
               //Informs the user that their input was not valid.
               System.out.println("Please enter y to play again or n to stop" );
            }
         }
      }
      
  }   
}
