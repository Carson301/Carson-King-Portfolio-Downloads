//CS110A
//Carson J. King
//The Category class simulates a category from a game of Yahtzee.

public abstract class Category
{
   //Declaration of score variable, score of category chosen.
   private int score = 0;
   //Declaration of used variable, indicates if category has been used or not.
   private boolean used = false;
   
   /**
      An abstract method that is filled in within category subclasses.
      @param d A dice object.
      @return The score possible in the category.
   */
   
   public abstract int evaluate(Dice d);
   
   /**
      The addValue() Method adds the score from evaluating the catgeory and marks
      the category as used.
      @param d A dice object.
   */
   
   public void addValue(Dice d)
   {
      //Adds the evaluated score to the actual score.
      score += evaluate(d);
      //Sets used to true.
      used = true;
   }
   
   /**
      The getScore() Method returns the score of the category object.
      @return The score achieved in the category.
   */
   
   public int getScore()
   {
      //Returns score.
      return score;
   }
   
   /**
      The getUsed() Method returns a boolean indicating whether the category has been used or not.
      @return A boolean indicating whether the category was used or not.
   */
   
   public boolean getUsed()
   {
      //Returns used.
      return used;
   }
}