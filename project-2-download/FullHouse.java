//CS110A
//Carson J. King
//The FullHouse class simulates the category for fullhouse on a Yahtzee scoring sheet.

public class FullHouse extends Category
{
   /**
      The evaluate() Method evaluates the Dice object it is sent. In this case it returns a score of
      25 if there is a fullhouse and 0 if there is not.
      @param d The Dice object/Die objects rolled.
      @return The score for the category.
   */

   public int evaluate(Dice d)
   {
     //The score to be returned, initially set to 0 in case conditions are not met.
     int score = 0;
     //For loop to search through Dice object using each possibly value on a Die object.
     for (int index = 1; index < 7; index++)
     {
         //If there is a value with a count of 3.
         if (d.count(index) == 3)
         {
            //A for loop begins, searching through another set of possible values on a Die object.
            for (int i = 1; i < 7; i++)
            {
               //If there is a value with a count of 2.
               if (d.count(i) == 2)
               {
                  //A fullhouse has been found and the score is set to 25.
                  score = 25;
               }
            }
         }
      }
      //Returns score.
      return score; 
         
   }
}