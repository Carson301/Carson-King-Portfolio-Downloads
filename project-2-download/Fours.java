//CS110A
//Carson J. King
//The Fours class simulates the category for Fours on a Yahtzee scoring sheet.

public class Fours extends Category
{
   /**
      The evaluate() Method evaluates the Dice object it is sent. In this case it sums up the score
      for Die objects with value 4 rolled.
      @param d The Dice object/Die objects rolled.
      @return The score for the category.
   */

   public int evaluate(Dice d)
   {
      //The variable FOUR represents the points given per Die object with 4 as it's value rolled.
      final int FOUR = 4;
      //Returns the total value earned in the category using FOUR and the count of Die objects with value 4.
      return FOUR * d.count(4);

   }
}