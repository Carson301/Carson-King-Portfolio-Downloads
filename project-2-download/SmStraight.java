//CS110A
//Carson J. King
//The SmStraight class simulates the category for small stright on a Yahtzee scoring sheet.

public class SmStraight extends Category
{
   /**
      The evaluate() Method evaluates the Dice object it is sent. In this case it returns a score of
      30 if there is a small stright and 0 if there is not.
      @param d The Dice object/Die objects rolled.
      @return The score for the category.
   */
   
   public int evaluate(Dice d)
   {
      //The score to be returned, initially set to 0 in case conditions are not met.
      int score = 0;
      //A counting variable used to count if there are four consecutive values. 
      int count = 0;
      //For loop to search through Dice object using each possibly value on a Die object.
      for (int index = 1; index < 7; index++)
      {
         //If the Dice object contains the die value.
         if (d.contains(index))
            //Count is incremented.
            count++;
         //Deals with a cases such as 1 2 4 and 5 being the straight.
         if ((d.contains(index) == false) && count < 4)
            //Count is set to 0.
            count = 0;
      }
      //If count is more than 3.
      if (count > 3)
         //Then there must be four consectutive values and score is set to 30.
         score = 30;
      //Returns score.
      return score;
            
   }
}