//CS110A
//Carson J. King
//The LgStraight class simulates the category for large straight on a Yahtzee scoring sheet.

public class LgStraight extends Category
{
   /**
      The evaluate() Method evaluates the Dice object it is sent. In this case it returns a score of
      40 if there is a large stright and 0 if there is not.
      @param d The Dice object/Die objects rolled.
      @return The score for the category.
   */

   public int evaluate(Dice d)
   {
      //The score to be returned, initially set to 0 in case conditions are not met.
      int score = 0;
      //A counting variable used to count if there are five consecutive values. 
      int count = 0;
      //For loop to search through Dice object using each possibly value on a Die object.
      for (int index = 1; index < 7; index++)
      {
         //If the Dice object contains the die value.
         if (d.contains(index))
            //Count is incremented.
            count++;
         //Deals with a cases such as 1 2 4 5 and 6 being the straight.
         if ((d.contains(index) == false) && count < 5)
            //Count is set to 0.
            count = 0;
      }
      //If count is more than 4.
      if (count > 4)
         //Then there must be five consectutive values and score is set to 40.
         score = 40;
      //Returns score.
      return score;


   }
}