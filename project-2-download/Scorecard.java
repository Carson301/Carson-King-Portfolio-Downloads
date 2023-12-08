//CS110A
//Carson J. King
//The Scorecard class simulates the scorecard used in yahtzee.

//Imports ArrayList.
import java.util.ArrayList;

public class Scorecard
{
   //Creates a Category ArrayList to store categories, this represents our scoreboard.
   private ArrayList<Category> scorecard = new ArrayList<Category>();
   //Bonus if you roll a yatzee but already have scored one.
   private int yahtzeeBonus = 0;
   //The number of categories in the game/per scorecard.
   private final int NUM_CATS = 13;
   
    /**
      The Scorecard() Constructor declares and initializes all the categories for the card.
      It also adds each of them to the scorecard ArrayList.
   */

   public Scorecard()
   {
      Ones ones = new Ones();
      Twos twos = new Twos();
      Threes threes = new Threes();
      Fours fours = new Fours();
      Fives fives = new Fives();
      Sixes sixes = new Sixes();
      ThreeOfAKind threeOfAKind = new ThreeOfAKind();
      FourOfAKind fourOfAKind = new FourOfAKind();
      FullHouse fullHouse = new FullHouse();
      SmStraight smStraight = new SmStraight();
      LgStraight lgStraight = new LgStraight();
      FiveOfAKind fiveOfAKind = new FiveOfAKind();
      Chance chance = new Chance();
      scorecard.add(ones);
      scorecard.add(twos);
      scorecard.add(threes);
      scorecard.add(fours);
      scorecard.add(fives);
      scorecard.add(sixes);
      scorecard.add(threeOfAKind);
      scorecard.add(fourOfAKind);
      scorecard.add(fullHouse);
      scorecard.add(smStraight);
      scorecard.add(lgStraight);
      scorecard.add(fiveOfAKind);
      scorecard.add(chance);
   }
    /**
      The choose() Method gets the wanted category from scorecard and uses the addValue()
      Method of the category object to actually add set points to the category.
      @param cv The Category being chosen.
      @param d The set of dice chosen.
   */

   public void choose(CategoryValue cv, Dice d)
   {
      //Gets the category from the scorecard ArrayList and uses the addValue() Method to
      //actually add the points to the category.
      scorecard.get(cv.getValue()).addValue(d);
      //If your roll is a yahtzee, and you have already scored a yahtzee and it has been scored not a 0.
      //The yahtzee bonus is increased by 100. NOTE: This means you can score in another categroy not in
      //the yahtzee category again.
      if (getEvaluation(CategoryValue.YAHTZEE, d) == 50 && getCategoryScore(CategoryValue.YAHTZEE) == 50 && checkScored(CategoryValue.YAHTZEE) == true)
         //Increases yahtzeeBonus by 100.
         yahtzeeBonus += 100;
   }
   
    /**
      The getEvaluation() Method gets the wanted category from scorecard and uses the evaluate()
      function to see how many points are possible in the category.
      @param cv The Category being chosen.
      @param d The set of dice chosen.
      @return The points possible in the category.
    */

   public int getEvaluation(CategoryValue cv, Dice d)
   {
      //Gets the category from the scorecard ArrayList and uses the evaluate() Method to
      //get the possible points in the category, then returns the value.
      return scorecard.get(cv.getValue()).evaluate(d);
   }
   
   /**
      The checkScored Method checks to see whether the category has already been scored.
      Returning a boolean indicating this fact.
      @param cv The Category being chosen.
      @return A boolean representing whether the category has been scored or not.
    */

   public boolean checkScored(CategoryValue cv)
   {
      //Declares scored to represent whether category has or has not been scored.
      boolean scored;
      //If (Using the getUsed() method from a category) the category has been scored.
      if (scorecard.get(cv.getValue()).getUsed() == true)
         //Then scored is set to true.
         scored = true;
      //If not.
      else
         //Then scored is set to false.
         scored = false;
      //Returns scored.
      return scored;
   }
   
   /**
      The getCategoryScore() Method gets the wanted category from scorecard and uses the
      getScore() method from category to return the score of the category.
      @param cv The Category being chosen.
      @return The points scored in the category.
    */

   public int getCategoryScore(CategoryValue cv)
   {
      //Gets the category from the scorecard ArrayList and uses the getScore() Method to
      //get the points scored in the category, then returns the value.
      return scorecard.get(cv.getValue()).getScore();
   }
   
   /**
      The scoreTop() Method returns the sum of all the scores for the top 6 categories.
      @return The score for the top section of the scoreboard.
   */
   
   public int scoreTop()
   {
      //Variable sum declared and initialized to 0.
      int sum = 0;
      //A for loop that iterates through the first 6 Category objects in scoreboard.
      for (int index = 0; index < 6; index ++)
      {
         //Adds each individual score for a category to the sum.
         sum += scorecard.get(index).getScore();
      }
      //If sum and therefore the top section of the scoreboard is above 62 points, then sum 
      //is given another 35 points as a bonus.
      if (sum >= 63)
      {
         //Adds the bonus of 35 to sum.
         sum += 35;
      }
      //Returns sum.
      return sum;
   }
   
   /**
      The scoreBottom() Method returns the sum of all the scores for the bottom 7 categories.
      @return The score for the bottom section of the scoreboard.
   */
   
   public int scoreBottom()
   {
      //Variable sum declared and initialized to 0.
      int sum = 0;
      //A for loop that iterates through the first 6 Category objects in scoreboard.
      for (int index = 6; index < scorecard.size(); index ++)
      {
         //Adds each individual score for a category to the sum.
         sum += scorecard.get(index).getScore();
      }
      //Returns sum.
      return sum;
   }
   
   /**
      The score() Method returns the sum of all the scores for the categories.
      @return The score for the whole the scoreboard.
   */

   public int score()
   {
      //Variable sum declared and initialized to 0.
      int sum = 0;
      //Adds both the top and bottom section of the scoreboard to sum.
      sum += scoreTop() + scoreBottom();
      //Adds the yahtzeeBonus to sum.
      sum += yahtzeeBonus;
      //Returns sum.
      return sum;
   }
   
   /**
      The toString method creates a string value of the data
      important to the class for user ease.
      @return The string of data important to the class.
   */

   public String toString()
   {
      //Declares String and initializes it with the start of wanted string output.
      String str = "Current Scorecard:\n";
      //A for loop that iterates over each possible CategoryValue.
      for (CategoryValue cv : CategoryValue.values())
      {
         //Adds each Category to the string with it's points scored.
         str += String.format("%15s: %s\n", cv, getCategoryScore(cv));
      }
      //Adds the final bottom, top, and total score sections to the string.
      str += String.format("%15s: %s\n%15s: %s\n%15s: %s\n", "Upper Total", scoreTop(), "Lower Total", scoreBottom(), "Total", score());
      //Returns string.
      return str;
   }
}
