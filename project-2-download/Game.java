//CS110A
//Carson J. King
//The Game class simulates a game of yahtzee.

//Imports Scanner.
import java.util.Scanner;
//Imports ArrayList.
import java.util.ArrayList;

public class Game
{  
   //Declares variable rollDice, which is the set of Die objects we will be rolling.
   private DiceRoll rollDice;
   //Declares variable saveDice, which is the set of Die objects we will be saving.
   private Dice saveDice;
   //Declares two Player objects player1 and player2, which are our two players.
   private Player player1, player2;
   //Declares a Scanner object keyboard for user input later on.
   private Scanner keyboard = new Scanner(System.in);

   /**
      The Game() Constructor intitializes our Player objects with thier constructor, setting their 
      names to Player 1 and Player 2 respectively.
      
   */
   
   public Game()
   {
      //Initializes player1 with a new Player object, which has the name Player 1.
      player1 = new Player("Player 1");
      //Initializes player2 with a new Player object, which has the name Player 2.
      player2 = new Player("Player 2");
      //Initializes rollDice with a new Diceroll object.
      rollDice = new DiceRoll();
      //Initializes saveDice with a new Dice object.
      saveDice = new Dice();
   }
   
   /**
      The playGame() Method plays the game of yahtzee, begins and ends the game with each turn
      being done in another method.
   */
   
   public void playGame()
   {
      //Declares varaible gameOver, represents whether the game is over or not with a boolean value.
      boolean gameOver = false;
      //Declares variable turns, represents the number of turns in the game.
      int turns = 0;
      //A while loop that keeps the game going as long as gameOver is set to false.
      while (gameOver == false)
      {
         //Plays a turn for player1 by calling the turn method.
         turn(player1);
         //Plays a turn for player2 by calling the turn method.
         turn(player2);
         //A for loop that iterates over each category to determine if the game is done.
         for (CategoryValue cv : CategoryValue.values())
         {
            //If both player's category for the given category are scored.
            if ((player1.getScorecard().checkScored(cv)) && (player2.getScorecard().checkScored(cv)))
            {
               //Then turns is incremented.
               turns++;
            }
         }
         //If turns equals 13 it means all the categories have been scored and the game is over.
         if (turns == 13)
         {
            //So it sets gameOver to true.
            gameOver = true;
         }
         //If turns is anything else.
         else
         {
            //Then turns is set back to 0.
            turns = 0;
         }

      }
      //If player 1 scored more points then they won.
      if (player1.getScorecard().score() > player2.getScorecard().score())
      {
         //Output message for player 1 victory.
         System.out.printf("\n\nGood Game Everybody!\nPlayer 1 earned %s points\nPlayer 2 earned %s points\nPlayer 1 has won by %s points!", player1.getScorecard().score(), player2.getScorecard().score(), (player1.getScorecard().score() - player2.getScorecard().score()));
      }
      //If player 2 scored more points then they won.
      else if (player1.getScorecard().score() < player2.getScorecard().score())
      {   
         //Output message for player 2 victory.
         System.out.printf("Good Game Everybody!\nPlayer 1 earned %s points\nPlayer 2 earned %s points\nPlayer 2 has won by %s points!",player1.getScorecard().score(), player2.getScorecard().score(), (player2.getScorecard().score() - player1.getScorecard().score()));
      }
      //Otherwise the players must have tied.
      else
      {
         //Putput message for a tie.
         System.out.printf("Good Game Everybody!\nPlayer 1 earned %s points\nPlayer 2 earned %s points\nBoth player's tied!",player1.getScorecard().score(), player2.getScorecard().score());
      }
   }
   
   /**
      The turn() Method plays the turn for each Player object sent to it.
      @param player The Player object representing the player's turn.
   */
   
   public void turn(Player player)
   {
      //Constant MAX_ROLLS, represents the maximum rolls a player can make, set to 2.
      final int MAX_ROLLS = 2;
      //Variable rollCounter, represents the number of rolls made by the player, set to 0.
      int rollCounter = 0;
      //Variable inputValidation, used to test input, set to nothing "".
      String inputValidation = "";
      //Variable newUserInput, used to set user input to, set to nothing "".
      String newUserInput = "";
      //Variable error, represents when an error has occured.
      boolean error = false;
      //Variable rollDice, resets roll dice each new turn.
      rollDice = new DiceRoll();
      //A while loop that continues as long as the player has not reacher their max rolls.
      while (rollCounter < MAX_ROLLS)
      {
         //If an error occured the scorecard is not shown, but if there was not error it is shown.
         if (error == false)
         {
            //Outputs the players scorecard at the start of each turn.
            System.out.printf("%s\n%s", player, player.getScorecard());
         }
         //Resets error to false.
         error = false;
         //Outputs the dice the player can reroll.
         System.out.println("Dice to reroll:\n" + rollDice);
         //As long as there are saveDice to show.
         if (saveDice.getNumDice() > 0)
         {
            //Outputs the dice that have been saved.
            System.out.println("\nDice to save:\n" + saveDice);
         }
         //Outputs instructions for the user to follow, with their next input.
         System.out.println("\nSave dice with [ ] filled with index values separated by spaces\n'roll' to reroll, 'score' to score");
         //Sets the next inputted value by the user to userInput.
         String userInput = keyboard.nextLine();  
            //If userInput doesn't equal any of the wanted values.  
            if (((userInput.equalsIgnoreCase("roll") == false) && (userInput.equalsIgnoreCase("score") == false) && (userInput.startsWith("[") && userInput.endsWith("]")) == false))
            {
               //Outputs that the user didn't input a valid value.
               System.out.println("Please enter a valid die number, roll, or score");
               //Error is set to true which changes the next iteration output. 
               error = true;
            }
            //If the user inputs the command to roll the dice.
            else if (userInput.equalsIgnoreCase("roll"))
            {
               //The dice are rolled using the roll() method.
               roll();
               //And the rollCounter is incremented.
               rollCounter++;    
            }
            //If the user inputed the command to score.
            else if (userInput.equalsIgnoreCase("score"))
            {
               //RollCounter is set to the max value ending the while loop.
               rollCounter = MAX_ROLLS;
            }
            //Otherwise, the user inputted a value between to [].
            else 
            {
               //Try statement.
               try
               {
                  //Removes [] from either end and assigns that value to newUserInput.
                  newUserInput = userInput.substring(1, userInput.length() - 1);
                  //If userInput doesn't start and end with [].
                  if ((userInput.startsWith("[") && userInput.endsWith("]")) == false)
                  {
                     //An exception is thrown.
                     throw (new Exception());
                  }
                  //If the newUserInput is greater than 10, the maximum length the string could be.
                  if (newUserInput.length() > 9)
                  {
                     //An exception is thrown.
                     throw (new Exception());   
                  }
                  //A for loop that iterates over the indexes of only the numbers in the string, not the spaces.
                  for (int index = 0; index < newUserInput.length() + 1; index += 2)
                  {
                     //Checks to see if the sub string contains one of the valid values 1-5.
                     if ((newUserInput.substring(index, index + 1).contains("1") || newUserInput.substring(index, index + 1).contains("2") || newUserInput.substring(index, index + 1).contains("3") || newUserInput.substring(index, index + 1).contains("4") || newUserInput.substring(index, index + 1).contains("5")) == false)
                     {
                        //If it does not then an exception is thrown.         
                        throw (new Exception());
                     }
                     //This if statement prevents the use of 2 of the same value [5 5] for example.
                     if (inputValidation.contains(newUserInput.substring(index, index + 1)))
                     {
                        //If the sub string is within the inputValidation, then an exception is thrown.
                        throw (new Exception());
                     }
                     //Adds the sub string to inputValidation, so it can be checked on the next iteration.
                     inputValidation += newUserInput.substring(index, index + 1);
                  }
                  //If no exception was thrown the userInput is sent to the save() method.
                  save(userInput);
                  //This if statement prevents the question of more user input, the input within [] from
                  //continuing.
                  if (rollDice.getNumDice() == 0)
                  {
                     //Sets rollCounter to the max rolls.
                     rollCounter = MAX_ROLLS;
                  }
               }
               //Catch statement.
               catch(Exception e)
               {
                  //Informs user that their input was not valid.
                  System.out.printf("%s is not valid\n", userInput);
               }
            //Resets inputValidation string to "".
            inputValidation = "";
            }
      }
      //Sends Player object to the score() method as it is time to score after the rolls.
      score(player);
   }
   
   /**
      The save() Method saves the wanted dice from user input to saveDice.
      @param str The user input of dice to save.
   */
   
   public void save(String str)
   {
      //Creates an ArrayList, rollList to store the indexes of dice for the rollDice/saveDice.
      ArrayList<Integer> rollList = new ArrayList<Integer>();
      //Iterates through the user input only using the substring of wanted numbers, not spaces or [].
      for (int index = 1; index < str.length(); index += 2)
      {
         //Uses index of rollDice to take that die and add it to saveDice.
         saveDice.addDie(rollDice.getDie(((Integer.parseInt(str.substring(index, index + 1))) - 1)));
      }
      //Iterates over each Die object in rollDice.
      for (int index = 1; index < rollDice.getNumDice() + 1; index++)
      {
         //Variable inSaveDice, represents whether the value is in saveDice or not.
         boolean inSaveDice = false;
         //Iterates over the index of the string to obtain the vaild sub string each time.
         for (int i = 1; i < str.length(); i += 2)
         {
            //Checks to see if the index is in the string.
            if (index == (Integer.parseInt(str.substring(i, i + 1))))
            {
               //If it is the index for that certain Die object must be in saveDice, and thus
               //is set to true.
               inSaveDice = true;
            }
         }
         //If the index is not in saveDice.
         if (inSaveDice == false)
         {
            //It is added to rollList.
            rollList.add(index);
         }
      }
      //Iterates throught rollList, adding each Die object with that index to the end of rollDice.
      for (int value : rollList)
      {
         //Adds each rollList index Die object to rollDice again.
         rollDice.addDie(rollDice.getDie(value - 1));
      }
      //While rollList doesn't equal the sieze of rollDice.
      while (rollList.size() != rollDice.getNumDice())
      {
         //The first item in rollDice is removed.
         rollDice.removeDie(0);
      }
      //Clears rollList just in case.
      rollList.removeAll(rollList);
   //NOTE: The reason this is done, the roll list adding and removing of rollDice. Is so I am able to 
   //remove values at certain indexes from rollDice without them shifting.
   }
   
   /**
      The roll() Method rolls the rollDice object.
   */
   
   public void roll()
   {
      //Rolls the Die objects in rollDice.
      rollDice.toss();
   }
   /**
      The score() Method helps the user score thier scorecard with the dice they rolled.
      @param player The player who's turn it is.
   */
   
   public void score(Player player)
   {
      //In case not all Die objects were saved, all the remaining Die objects in rollDice are saved.
      for (int index = 0; index < rollDice.getNumDice(); index++)
      {
         //Adds all leftover rollDice Die objects to saveDice.
         saveDice.addDie(rollDice.getDie(index));
      }
      //Outputs the final dice of the user.
      System.out.printf("Final roll:\n%s", saveDice);
      //Declares CategoryValue, set to ONES initially.
      CategoryValue cv = CategoryValue.ONES;
      //Declares variable goodInput, represents whether input is good or not, set to false.
      boolean goodInput = false;
      //Declares scoreCategory, represents which category is to be scored, intitially set to 1.
      int scoredCategory = 1;      
      //Outputs start of user intructions for input.   
      System.out.println("\nSelect a category that you have not scored in yet:");
      //Outputs each possible point value that can be obtained in each category with savedice.
      for (CategoryValue catVal : CategoryValue.values()) 
         System.out.printf("%2s: %s, %s points\n",(catVal.getValue() + 1), catVal, player.getScorecard().getEvaluation(catVal, saveDice));
      //While input is valid.
      while (goodInput == false)
      {   
            //Try statement.
            try 
            {
               //Outputs for user to enter input.
               System.out.print("");
               //Assigns user input to scoreCategory, turning it into an int.
               scoredCategory = Integer.parseInt(keyboard.nextLine());
               //If the scoredCategory is not a valid category 1-13.
               if (scoredCategory < 1 || scoredCategory > 13)  
                  //An exception is thrown.
                  throw (new Exception());
               //Otherwise the input must be good.
               else
                  //So goodInput is set to true.
                  goodInput = true;
                  //Iterates over possible CategoryValues.
                  for (CategoryValue catVal : CategoryValue.values())
                     //If it matches the value inputted by the user.
                     if ((catVal.getValue() + 1) == scoredCategory)
                        //cv is set to that CategoryValue.
                        cv = catVal;
                  //In the case of the category already being scored before.
                  if (player.getScorecard().checkScored(cv) == true)
                  {
                     //goodInput is set to false.
                     goodInput = false;
                     //An excpetion is thrown.
                     throw (new Exception());
                  }

            }
            //Catch statement.
            catch (Exception e)
            {
               //Outputs to the user that thier input was invalid.
               System.out.println("Please enter a Category between 1 and 13 that has not been scored");
            }
      }
      //Chooses the chosen category as the one to be scored.
      player.getScorecard().choose(cv ,saveDice);
      //Informs the user of the points they made.
      System.out.printf("You scored %s points in %s\n", player.getScorecard().getCategoryScore(cv), cv);
      //A wile loop to remove all Die objects from saveDice.
      while (saveDice.getNumDice() != 0)
      {
         //Removes first Die object in saveDice.
         saveDice.removeDie(0);
      }
   }
}