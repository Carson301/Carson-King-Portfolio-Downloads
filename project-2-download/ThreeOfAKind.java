//CS110A
//Carson J. King
//The ThreeOfAKind class simulates the category for three of a kind on a Yahtzee scoring sheet.

public class ThreeOfAKind extends Category
{
   /**
      The evaluate() Method evaluates the Dice object it is sent. In this case it returns a score of
      the sum of the dice if there are three of a kind and 0 if there are not.
      @param d The Dice object/Die objects rolled.
      @return The score for the category.
   */

   public int evaluate(Dice d)
   {
      //The score to be returned, initially set to 0 in case conditions are not met.
      int score = 0;
      //For loop to search through Dice object using each possibly value on a Die object.
      for (int index = 1; index < 7; index++)
         //Checks if the possible Die object value appears 3 or more times in the Dice object.
         if (d.count(index) > 2)
            //If so the sum of the Dice object's Die objects is set to score.
            score = d.sum();  
      //Returns score.
      return score; 
   }
}