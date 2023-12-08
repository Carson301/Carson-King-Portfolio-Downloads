//CS110A
//Carson J. King
//The DiceRoll class simulates the intitial roll of 5 dice, as well as all dice that are chosen to
//be rolled later.
public class DiceRoll extends Dice
{
   //Declaration of NUM_DIE variable, default number of dice to be rolled.
   private final int NUM_DIE = 5;
   
   /**
      The DiceRoll() Constructor uses the super constructer to create an empty dice ArrayList,
      then the DiceRoll() adds 5 random Die objects to the dice ArrayList.
   */
   
   public DiceRoll()
   {
      //Calls Dice super constructor.
      super();
      //Uses for loop to iterate 5 times.
      for (int index = 1; index <= NUM_DIE; index++)
         //Adds a random Die object to the dice ArrayList.
         addDie(new Die());
   }
   
   /**
      The toss() Method, rolls each of the Die objects in a dice ArrayList.
   */
   
   public void toss()
   {
      //Iterates for as many Die objects are in the dice ArrayList.
      for (int index = 0; index < getNumDice(); index++)
         //Retrieves the Die object from the dice ArrayList and rolls it.
         getDie(index).roll();
   }
}