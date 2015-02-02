/**
 * CS 230 Final Project
 * SorryPiece.java
 * Purpose: To create pieces that will be used by 
 * the players of the game
 * 
 * @author: Alice Wong
 * @version: 1.0 12/03/13
 */

public class SorryPiece {
  private String playerColor;
  private SorryCell location, oldLocation;
  
  //this variable stores the piece's start and finish locations
  private SorryCell classLocation, dormLocation; 
  
  private int pieceNumber;
  
  
  /**
   * CONSTRUCTOR: Creates the SorryPiece objects that will
   * contain instance variables about its color and location
   * 
   * @param A string that will be input by the player's instance
   * variable because each player is associated with a color
   */
  
  public SorryPiece(String color) {
    playerColor = color.toLowerCase();
    location = null; //not on the board yet. set later initially in SorryGame
    classLocation = null; //this is set later in SorryGame
    dormLocation = null; //changed later by SorryGame
    oldLocation = null; //this changes as players' pieces move around the board
    pieceNumber = -1; //defaulted to -1 and changed in SorryGame later
  }
  
  /**
   * Gets the color of the piece and returns it as a string
   * 
   * @return A string that states the color of the piece
   */
  public String getColor() {
    return playerColor;
  }
  
  /**
   * Assigns the piece's SorryCell variable to the location
   * in which it will be in (i.e. which vertex it will be in)
   * 
   * @param A SorryCell that is input by the methods from the 
   * various classes that it will be used in
   */
  public void setLocation(SorryCell c){
    location = c;
  }
  
  /**
   * A getter to give the vertex in which the SorryPiece is
   * located.
   * 
   * @return A SorryCell that is the vertex in which the piece
   * is located on the graph.
   */
  public SorryCell getLocation(){
    return location;
  }
  
  /**
   * Assigns the piece's SorryCell variable, oldLocation, to the location
   * in which it was previously in(i.e. which vertex it just moved from)
   * 
   * @param A SorryCell that is input by the methods from the 
   * various classes that it will be used in
   */
  public void setOldLocation(SorryCell c){
    oldLocation = c;
  }
  
  /**
   * A getter to give the vertex in which the SorryPiece was
   * just located.
   * 
   * @return A SorryCell that is the vertex in which the piece
   * was located on the graph.
   */
  public SorryCell getOldLocation(){
    return oldLocation;
  }
  
  /**
   * A getter that returns the class cell of the SorryPiece
   * (i.e. the start vertex of the piece)
   * 
   * @return A SorryCell that is the vertex where the piece
   * originally started from
   */
  public SorryCell getClassLocation(){
    return classLocation;
  }
  
  /**
   * A setter to set the class cell of the SorryPiece
   * (i.e. the start vertex of the piece)
   * 
   * @param A SorryCell that is the vertex where the piece
   * originally starts
   */
  public void setClassLocation(SorryCell classCell){
    classLocation = classCell;
  }
  
  /**
   * A getter that returns the dorm cell of the SorryPiece
   * (i.e. the end vertex of the piece)
   * 
   * @return A SorryCell that is the vertex where the piece
   * finishes
   */
  public SorryCell getDormLocation(){
    return dormLocation;
  }
  
  /**
   * A setter to set the dorm cell of the SorryPiece
   * (i.e. the end vertex of the piece)
   * 
   * @param A SorryCell that is the vertex where the piece
   * finishes
   */
  public void setDormLocation(SorryCell dormCell){
    dormLocation = dormCell;
  }
  
  /**
   * A setter to set the piece's number
   * 
   * @param An integer that is distinct from all other pieceNumbers initialized thus far
   */
  public void setPieceNumber(int pieceNum){
    pieceNumber = pieceNum;
  }
  
  /**
   * A getter to get the piece's number
   * 
   * @return An integer that is the piece's number (pieceNumber)
   */
  public int getPieceNumber(){
    return pieceNumber;
  }
  
  /**
   * String representation of the piece
   * 
   * @return A string representation which states the 
   * color of the piece
   */
  public String toString() {
    //The commented out part is previously used for testing
    return playerColor;// + "\t Location: " + location;//"The color of this piece is " + playerColor + ".";
    
  }
  
  /**
   * The main method for the SorryPiece program.
   * Tests the methods in the class.
   * 
   * @param args Not used
   */
  public static void main(String[] args){
    SorryPiece firstPiece = new SorryPiece ("Red");
    System.out.println("Should say red: "+ firstPiece);
    SorryPiece secondPiece = new SorryPiece ("Purple");
    System.out.println("Should say purple: " + secondPiece);
    
    System.out.println("Should say red: "+ firstPiece.getColor());
    System.out.println("Should say purple: " + secondPiece.getColor());
    
    firstPiece.setLocation(new SorryCell(1));
    System.out.println("Should say 1: "+ firstPiece.getLocation());
    
    firstPiece.setOldLocation(new SorryCell(56));
    System.out.println("Should say 56: "+ firstPiece.getOldLocation());
    
    secondPiece.setClassLocation(new SorryCell(102));
    System.out.println("Should say 102: "+ secondPiece.getClassLocation());
    
    secondPiece.setDormLocation(new SorryCell(58));
    System.out.println("Should say 58: " + secondPiece.getDormLocation());
    
    firstPiece.setPieceNumber(4);
    System.out.println("Should say 4:" + firstPiece.getPieceNumber());
  }
  
  
}

