//CS110A
//Carson J. King
//The Ones class simulates the category for Ones on a Yahtzee scoring sheet.

public class Ones extends Category
{
   
   /**
      The evaluate() Method evaluates the Dice object it is sent. In this case it sums up the score
      for Die objects with value 1 rolled.
      @param d The Dice object/Die objects rolled.
      @return The score for the category.
   */
   
   public int evaluate(Dice d)
   {
      //The variable ONE represents the points given per Die object with 1 as it's value rolled.
      final int ONE = 1;
      //Returns the total value earned in the category using ONE and the count of Die objects with value 1.
      return ONE * d.count(1);
   }
}