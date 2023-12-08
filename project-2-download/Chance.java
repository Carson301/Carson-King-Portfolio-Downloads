//CS110A
//Carson J. King
//The Chance class simulates the category for chance on a Yahtzee scoring sheet.

public class Chance extends Category
{
   /**
      The evaluate() Method evaluates the Dice object it is sent. In this case it returns a score of
      the sum of the Die objects.
      @param d The Dice object/Die objects rolled.
      @return The score for the category.
   */

   public int evaluate(Dice d)
   {
      //The score of the Die objects, them all summed up.
      int score = d.sum();
      //Returns Score.
      return score;      
   }
}