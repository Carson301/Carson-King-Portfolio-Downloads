//CS110A
//Carson J. King
//The Player class simulates a player in a yahtzee game.

public class Player
{
   //Declares player variable, the name of the player.
   private String player;
   //Declares scorecard variable, the scorecard of the player.
   private Scorecard scorecard;
   
   /**
      The Player() Constructor sets the given player string to our private player variable.
      Also creates a new scorecard for the player and assigns it to the scorecard variable.
      @param player The name of the player.
   */

   public Player(String player)
   {
      //Sets given player to private player.
      this.player = player;
      //Creates new scorecard and sets it to scorecard.
      this.scorecard = new Scorecard();
   } 
   
   /**
      The getPlayer() method returns the value in the player field.
      @return The name of the player.
   */
   
   public String getPlayer()
   {
      //Returns player.
      return player;
   }
   
   /**
      The getScorecard() method returns the value in the scorecard field.
      @return The scorecard for the player.
   */
   
   public Scorecard getScorecard()
   {
      //Returns scorecard.
      return scorecard;
   }
   
   /**
      The toString method creates a string value of the data
      important to the class for user ease.
      @return The string of data important to the class.
   */

   public String toString()
   {
      //Creates a string str that has the player name followed by a :.
      String str = player + ":";
      //Returns str.
      return str;
   }
}