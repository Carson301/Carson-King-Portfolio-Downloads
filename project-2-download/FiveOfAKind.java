//CS110A
//Carson J. King
//The FiveOfAKind class simulates the category for yahtzee or five of a kind on a Yahtzee scoring sheet.

public class FiveOfAKind extends Category
{
   /**
      The evaluate() Method evaluates the Dice object it is sent. In this case it returns a score of
      50 if there is a yahtzee and 0 if there is not.
      @param d The Dice object/Die objects rolled.
      @return The score for the category.
   */

   public int evaluate(Dice d)
   {
      //The score to be returned, initially set to 0 in case conditions are not met.
      int score = 0;
      //For loop to search through Dice object using each possibly value on a Die object.
      for (int index = 1; index < 7; index++)
         //If the Dice object contains 4 or more of the same value on Die objects.
         if (d.count(index) > 4)
            //A yahtzee is found and score is set to 50.
            score = 50;  
      //Returns score.
      return score; 
   }
}