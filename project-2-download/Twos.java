//CS110A
//Carson J. King
//The Twos class simulates the category for Twos on a Yahtzee scoring sheet.

public class Twos extends Category
{
   
   /**
      The evaluate() Method evaluates the Dice object it is sent. In this case it sums up the score
      for Die objects with value 2 rolled.
      @param d The Dice object/Die objects rolled.
      @return The score for the category.
   */
   
   public int evaluate(Dice d)
   {
      //The variable TWO represents the points given per Die object with 2 as it's value rolled.
      final int TWO = 2;
      //Returns the total value earned in the category using TWO and the count of Die objects with value 2.
      return TWO * d.count(2);

   }
}