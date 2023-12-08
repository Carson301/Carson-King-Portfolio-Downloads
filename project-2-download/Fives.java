//CS110A
//Carson J. King
//The Fives class simulates the category for Fives on a Yahtzee scoring sheet.

public class Fives extends Category
{
   /**
      The evaluate() Method evaluates the Dice object it is sent. In this case it sums up the score
      for Die objects with value 5 rolled.
      @param d The Dice object/Die objects rolled.
      @return The score for the category.
   */

   public int evaluate(Dice d)
   {
      //The variable FIVE represents the points given per Die object with 5 as it's value rolled.
      final int FIVE = 5;
      //Returns the total value earned in the category using FIVE and the count of Die objects with value 5.
      return FIVE * d.count(5);
   }
}