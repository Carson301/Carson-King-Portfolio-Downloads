//CS110A
//Carson J. King
//The Sixes class simulates the category for Sixes on a Yahtzee scoring sheet.

public class Sixes extends Category
{
   /**
      The evaluate() Method evaluates the Dice object it is sent. In this case it sums up the score
      for Die objects with value 6 rolled.
      @param d The Dice object/Die objects rolled.
      @return The score for the category.
   */
   
   public int evaluate(Dice d)
   {
      //The variable SIX represents the points given per Die object with 6 as it's value rolled.
      final int SIX = 6;
      //Returns the total value earned in the category using SIX and the count of Die objects with value 6.
      return SIX * d.count(6);
   }
}