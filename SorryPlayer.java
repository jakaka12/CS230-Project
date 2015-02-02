/**
 * CS 230 Final Project
 * SorryPlayer.java
 * Purpose: To create players that will be used 
 * in the SorryGame.java 
 * 
 * @author: Alice Wong
 * @version: 12/03/13
 */

public class SorryPlayer {
  private String playerName;
  private String playerColor;
  private SorryPiece firstPiece;
  private SorryPiece secondPiece;
  
  /**
   * CONSTRUCTOR: Creates the SorryPlayer objects that will
   * contain instance variables about its color, name and 
   * pieces associated with it. The the program
   * will arbitrarily give the player a number/name.
   * 
   * @param An int that will be arbitrarily decided by the
   * program and a string representation of a color that will
   * also be input arbitrarily by the program
   */
  public SorryPlayer(int playerNumber, String color){
    playerName = "Player "+playerNumber;
    playerColor = color.toLowerCase();
    firstPiece = new SorryPiece(playerColor);
    secondPiece = new SorryPiece(playerColor);
  }
  
  /**
   *Gets the player's name and returns it
   * as a string
   * 
   * @return A string of the player's name
   */
  public String getPlayerName() {
    return playerName; 
  }
  
  /**
   *Gets the color of the piece and returns it
   * as a string
   * 
   * @return A string of the player's color.
   */
  public String getPlayerColor() {
    return playerColor;
  }
  
  /**
   * Gets the SorryPiece that is associated
   * with the player and that the player selects
   * 
   * @param A int that will is arbitrarily assigned to
   * each piece.
   * @return A SorryPiece that the player has selected
   */
  public SorryPiece getPiece(int piece) {
    if(piece == 1) 
      return firstPiece; 
    else
      return secondPiece;
  }
  
  /**
   * String representation of the player
   * 
   * @return A string representation of the player
   */
  public String toString() {
    //We used this for testing earlier.
//    String s = "The player's name is " + playerName;
//    s += " and their color is " + playerColor + ".\n";
//    s += "The first piece is in cell " + getPiece(1).getLocation(); 
//    s += ", and the second piece is in cell " + getPiece(2).getLocation() + ".\n";
//    
//    return s;
    return playerName;
  }
  
  /**
   * The main method for the SorryPlayer program.
   * Tests the methods in the class.
   * 
   * @param args Not used
   */
  public static void main(String[] args) {
    SorryPlayer player1 = new SorryPlayer(1 , "Yellow");
    System.out.println("The first player's name is "+ player1.getPlayerName()+".");
    System.out.println("The first player's color is "+ player1.getPlayerColor()+".");
    System.out.println(player1);
    
    System.out.println("Should say yellow: " + player1.getPiece(1));
    System.out.println("Should say yellow: " + player1.getPiece(2));
    
    System.out.println("Should say Player 1: " + player1.toString());
    
    
  }
}