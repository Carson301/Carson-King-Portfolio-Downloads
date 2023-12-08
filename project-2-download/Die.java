//CS110A
//Carson J. King
//Class that simulates a die, that die is created and then rolled as much as requested.

//Imports Random.
import java.util.Random;

public class Die
{
   //Declaration of value variable, value on die.
   private int value;
   //Declaration of SIDES variable, number of sides on die.
   private final int SIDES = 6;
   //Declaration of class r variable, random generator for the side/value of our die.
   private static Random r = new Random();
   
   /**
      The Die() Constructor sets the value rolled by the die initially, to
      a random int between 1 and the maximum sides (SIDES) 6.
   */

   public Die()
   {
      //Uses r to come up with a random int to represent a value rolled.
      value = r.nextInt(SIDES) + 1;
   }
   
   /**
      The roll() Method rolls the die, setting value to a new random value generated
      by r.
   */
   public void roll()
   {
      //Uses r to come up with a random int to represent a value rolled.
      value = r.nextInt(SIDES) + 1;
   }
   
   /**
      The getValue() Method returns the value of the die.
      @return The value in the value field.
   */
   public int getValue()
   {
      //Returns value.
      return value;
   }
   
   /**
      The toString method creates a string value of the data
      important to the class for user ease.
      @return The string of data important to the class.
   */

   public String toString()
   {
      //Creates a string object str, that is the value on the die.
      String str = String.format("%d", value);
      //Returns str.
      return str;
   }
}