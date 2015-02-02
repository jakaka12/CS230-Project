/**
 * CS 230 Final Project
 * SorryCard.java
 * Purpose: To create cards that will be used to
 * make up the deck of the game
 * 
 * @author: Jazlyn Akaka
 * @version: 1.0 12/10/13
 */

public class SorryCard{
  private String value;
  private String meaning;
  
  /**
   * CONSTRUCTOR: Creates the card that will be used in the SorryDeck
   * class to make up a deck of 45 cards. Every card created contains
   * information on what value it is (numerical unless the "Sorry!" 
   * card, stored as a string), the meaning that is associated with the value
   * (meanings will become scenarios associated with the Wellesley campus such as
   * "There's steak at the Lulu. Run if you can." when the value is 12, 
   * and an integer numMoves which is the numerical value stored as an int
   * instead of a String. numMoves is only associated with cards other than the "Sorry!" card. 
   * The "Sorry!" card allows a player to replace an opponent's piece with one of their own, 
   * and send their opponent's piece back to class. 
   * 
   * @param The first string parameter is the value the user would like the card to have.
   * Integers must be used, but in string form, so that they can later be used as 
   * integers. (User inputs "2" and not "Two" or "two").
   * 
   * @param The second string parameter is the meaning the user would like the card to have. 
   */
  public SorryCard(String cardValue, String cardMeaning){
    value = cardValue;
    meaning = cardMeaning;
  }
  
  /**
   * A getter that returns what value the card has.
   * These values should range between 1 and 12, 
   * exclude 6 and 9, and include "Sorry!"
   * 
   * @return A String that represents the value of the card.
   */
  public String getValue(){
    return value;
  }
  
  /**
   * A getter that returns the meaning of the card.
   * 
   * @return A String that represents the meaning of the card.
   */
  public String getMeaning(){
    return meaning;
  }
  
  /**
   * String representation of the cell
   * 
   * @return A string representation which states the 
   * the value and meaning associated with the card.
   */
  public String toString(){
    String s = "";
    s += value + ": " + meaning + "\n";
    return s;
  }
  
  /**
   * The main method for the SorryCard program.
   * Tests the methods in the class.
   * 
   * @param args Not used
   */
  public static void main(String[] args){
    SorryCard card1 = new SorryCard("1", "You can leave Class or move forward 1 space");
    System.out.println(card1);
    System.out.println("get card1's value and meaning: " + card1.getValue() + ", " + card1.getMeaning());
  }
}