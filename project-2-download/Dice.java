//CS110A
//Carson J. King
//Simulates a group of dice, that can be added to, removed from, and evaluated (Contains, Sum, etc...)

//Imports ArrayList.
import java.util.ArrayList;

public class Dice
{
   //Declaration of dice arrayList, to hold die objects.
   private ArrayList<Die> dice;
   //Declaration of DEF_CAP variable, maximum number of die that can be rolled.
   private final int DEF_CAP = 5;
   
   /**
      The Dice() Constructor creates an ArrayList with a capacity of (DEF_CAP) 5.
   */
   
   public Dice()
   {
      //Initializes an empty Die ArrayList to the dice variable.
      dice = new ArrayList<Die>(DEF_CAP);
   }
   
   /**
      The alternate Dice() Constructor creates an ArrayList with a capacity of num (Capacity sent to 
      it).
      @param num The capacity wanted for the dice ArrayList.
   */
   
   public Dice(int num)
   {
      //Initializes an empty Die ArrayList to the dice variable.
      dice = new ArrayList<Die>(num);
   }
   
   /**
      The addDie() Method adds a Die object to the dice ArrayList.
      @param d The Die object to be added.
   */
   
   public void addDie(Die d)
   {
      //Adds the Die object d to the dice ArrayList.
      dice.add(d);
   }
   
   /**
      The getNumDice() Method returns the size of the dice ArrayList (Which is the number of
      Die objects in the ArrayList).
      @return The number of die in the dice ArrayList.
   */
   
   public int getNumDice()
   {
      //Returns the number of Die objects in the dice ArrayList.
      return dice.size();
   }
   
   /**
      The getDie() Method returns the die at the given index i.
      @param i The index of the wanted die.
      @return The Die Object at the given index.
   */
   
   public Die getDie(int i)
   {
      //Returns the die at the given index.
      return dice.get(i);
   }
   
   /**
      The removeDie() Method removes the Die object at the given index i.
      @param i The index of the Die object to be removed.
   */
   
   public void removeDie(int i)
   {
      //Removes the Die object at the given index.
      dice.remove(i);
   }
  
  /**
   The count() method returns the number of Die objects with the value that it is called with.
   @param val The die value to be counted.
   @return The number of die with the value.
  */
  
   public int count(int val)
   {
      //Counting variable numDie, to count how many Die objects with that value there are.
      int numDie = 0;
      //Advanced for loop to iterate over each Die object.
      for (Die die : dice)
         //If statement to check if Die object has the value.
         if (die.getValue() == val)
            //If it does numDie if incremented.
            numDie++;
      //Returns numDie.
      return numDie;
   }
   
   /**
      The sum() Method adds up the values of the Die objects within the dice ArrayList.
      @return The sum of all the Die objects values in the dice ArrayList.
   */
   
   public int sum()
   {
      //The sum of all Die objects, intitially set to 0.
      int sum = 0;
      //Advanced for loop to iterate over each Die object.
      for (Die die : dice)
         //Adds each Die objects value to sum.
         sum += die.getValue();
      //Returns sum.
      return sum;
   }
   
   /**
      The contains() Method uses a given value, to return true if the value is in the dice ArrayList
      and false otherwise.
      @param val The value to be checked for in the dice ArrayList.
      @return A boolean value indicating if the value is or is not in the dice ArrayList.
   */
   
   public boolean contains(int val)
   {
      //Boolean initially set to false that represents if the value is present in the dice ArrayList.
      boolean isInDice = false;
      //Advanced for loop to iterate over each Die object in the dice ArrayList.
      for (Die die : dice)
         //Evaluates if a Die objects value is equal to the val variable.
         if (die.getValue() == val)
            //If so isInDice is set to true.
            isInDice = true;
      //Returns isInDice.
      return isInDice;
   }
   
   /**
      The toString method creates a string value of the data
      important to the class for user ease.
      @return The string of data important to the class.
   */
   
   public String toString()
   {
      //Creates empty string str.
      String str = "";
      //Uses a for loop and the number of Die objects in the dice ArrayList to iterate for each Die object.
      for (int index = 0; index < dice.size(); index++)
         //Adds to the string str the Die objects number (1-5) and it's value.
         str += (index + 1) + ": value " + getDie(index) + "\n";
      //Returns str.
      return str;
   }
}
