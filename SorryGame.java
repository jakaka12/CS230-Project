/**
 * CS 230 Final Project
 * SorryGame.java
 * Purpose: To create the game in which the user plays
 * in the SorryGame.java 
 * 
 * @authors: Alice Wong and Jazlyn Akaka
 * @version: 12/18/2013
 */


import javafoundations.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class SorryGame extends AdjMatGraphPlus{
  protected SorryBoard board;
  private SorryDeck deck;
  private SorryPlayer player1;
  private SorryPlayer player2;
  private SorryPlayer player3;
  private SorryPlayer player4;
  protected SorryPlayer[] players;
  private SorryPiece pieceSelected;
  private SorryPiece oppPiece;
  
  private SorryCard drawn;
  private SorryPlayer previousPlayer, currentPlayer;
  private String currentCardValue;
  
  private SorryCell newCell, oldCell;
  
  /**
   * CONSTRUCTOR: Creates a board, a deck, 4 players.
   * Creates instance variables to keep track of 
   * cards, piece selected, opponent's piece selcted.
   */  
  public SorryGame() { 
    board = new SorryBoard();
    deck = new SorryDeck();
    pieceSelected = null;
    oppPiece = null;
    currentCardValue = null;
    
    deck.shuffle();
    
    player1 = new SorryPlayer(1, "yellow");
    player2 = new SorryPlayer(2, "green");
    player3 = new SorryPlayer(3, "red");
    player4 = new SorryPlayer(4, "purple");
    
    previousPlayer = null; 
    currentPlayer = player1; //1st player is defaulted to yellow
    
    //players array for other methods in the class
    players = new SorryPlayer[4];
    players[0] = player1;
    players[1] = player2;
    players[2] = player3;
    players[3] = player4;
    
    //Array of dorm and class locations that we use
    //to set the dorm and class location in each piece
    SorryCell[] dormLocations = {board.collection[65],board.collection[71],board.collection[77],board.collection[83]};
    SorryCell[] classLocations = {board.collection[84],board.collection[85],board.collection[86],board.collection[87],
      board.collection[88],board.collection[89],board.collection[90],board.collection[91]};
    
    //Each piece is set to remember its dorm location. Then it is
    //set to remember its class location as well as set to start
    //in the class cells.
    int j = 0;
    for(int i =0; i < players.length; i++) {
      
      players[i].getPiece(1).setDormLocation(dormLocations[i]);
      players[i].getPiece(2).setDormLocation(dormLocations[i]);
      
      players[i].getPiece(1).setOldLocation(dormLocations[i]);
      players[i].getPiece(2).setOldLocation(dormLocations[i]);
      
      players[i].getPiece(1).setPieceNumber(j);
      setPieceInClass(classLocations[j].cellNumber(), players[i], 1);
      j++;
      
      players[i].getPiece(2).setPieceNumber(j);
      setPieceInClass(classLocations[j].cellNumber(), players[i], 2);
      j++;
    }
  }
  
  /**
   * Draws a card and stores the card and its value
   * in instance variables.
   * 
   * @return A string that tells the user what card is drawn
   */
  private String draw() {
    drawn = deck.draw();
    currentCardValue = drawn.getValue();
    return "Card Value Drawn: " + currentCardValue;
  }
  
  /**
   * A method that checks if the card drawn is a Sorry! card
   * 
   * @return A boolean that indicates whether the card
   * drawn is a Sorry! card.
   */
  private boolean isSorryCard() {
    return currentCardValue.equals("Sorry!"); 
  }
  
  /**
   * A method that gets the card value drawn
   * 
   * @return A string representation of the card drawn
   */
  public String getCurrentCardValue(){
    return currentCardValue;
  }
  
  /**
   * A method that gets the player that just finished
   * their turn
   * 
   * @return A SorryPlayer that just completed their
   * turn
   */
  public SorryPlayer getPreviousPlayer(){
    return previousPlayer;
  }
  
  /**
   * A method that gets the current player whose
   * turn it is
   * 
   * @return A SorryPlayer whose turn it is
   */
  public SorryPlayer getCurrentPlayer(){
    return currentPlayer;
  }
  
  /**
   * Private method to change the current player.
   * Players go in a rotation from 1 to 4.
   * 
   */
  private void changeCurrentPlayer() {
    previousPlayer = getCurrentPlayer();
    if (currentPlayer.equals(player1)) currentPlayer = player2;
    else if (currentPlayer.equals(player2)) currentPlayer = player3;
    else if (currentPlayer.equals(player3)) currentPlayer = player4;
    else currentPlayer = player1;
  }
  
  /**
   * Helper method to set the piece in class and to
   * remember where its class location is for other
   * method later (i.e. When the Sorry Card is drawn)
   * 
   * @param An int for the cell number where CLASS is
   * located, a SorryPlayer, and an int for the piece
   * number that indicates which piece we are referring
   * to.
   */ 
  private void setPieceInClass(int cellNumber, SorryPlayer player, int pieceNumber) {
    SorryPiece piece = player.getPiece(pieceNumber);
    SorryCell cell = board.collection[cellNumber];
    piece.setLocation(cell);
    piece.setClassLocation(cell);
    
  }
  
  /**
   * A method to get the pieceSelected in
   * the game
   * 
   * @return A SorryPiece that is chosen by
   * the user to either move forward or
   * backward
   */
  public SorryPiece getPieceSelected(){
    return pieceSelected;
  }
  
  /**
   * A method to set the pieceSelected in the game
   * 
   * @param A SorryPiece that the user chooses through
   * a drop down box.
   */
  public void changePieceSelected(SorryPiece useThisPiece){
    pieceSelected = useThisPiece;
  }
  
  /**
   * A method to get the oppPiece(selected 
   * by the user) in the game
   * 
   * @return A SorryPiece that is the opponent's
   * piece that is selected by the user in which
   * the user wants to send that piece back to its
   * CLASS.
   */
  public SorryPiece getOppPiece(){
    return oppPiece;
  }
  
  /**
   * A method to set the oppPiece in the game
   * 
   * @param A SorryPiece that is selected by the user
   * to replace the old oppPiece that was previously
   * selected. The oppPiece that is newly chosen is the
   * one that will be sent back to CLASS later on.
   */
  public void changeOppPiece(SorryPiece diffOppPiece){
    oppPiece = diffOppPiece;
  }
  
  /**
   * A method that checks to see if you bumped
   * an opponent's piece back to CLASS.
   * 
   * Every time a numerical card is drawn, at the
   * end of moving the piece we want to see if you
   * have bumped another player's piece back 
   * to CLASS. We loop through all the players'
   * and their pieces to check to see if their 
   * piece is in the same location, since each piece
   * keeps track of its location. If the opponent's
   * piece is in the same location as the current
   * player's piece, then the opponent's piece will be
   * sent back to CLASS and the loop breaks.
   */
  private void didYouLandOnOppPiece() {
    SorryCell checkThisLocation = pieceSelected.getLocation();
    System.out.println("Piece Selected's Location: " + pieceSelected.getLocation());
    for(int i = 0; i < players.length; i++) {
      if(!currentPlayer.equals(players[i])){
        SorryPiece p1 = players[i].getPiece(1);
        SorryPiece p2 = players[i].getPiece(2);
        
        if(p1.getLocation().equals(checkThisLocation)){
          p1.setLocation(p1.getClassLocation());
          JOptionPane.showMessageDialog(null, "SORRY! " + p1.toString() + " 1 was just sent back to CLASS");
          break;
        }
        if(p2.getLocation().equals(checkThisLocation)){
          p2.setLocation(p2.getClassLocation());
          JOptionPane.showMessageDialog(null, "SORRY! " + p2.toString() + " 2 was just sent back to CLASS");
          break;
        }
      }
    }
  }
  
  
  /**
   * A method that moves a piece forward
   * one cell.
   * 
   * The method looks at the successor of each
   * vertex(the cells) and gets them in a list
   * from highest to lowest. The new cell is 
   * defaulted to be at the highest cell number
   * of the list. However, there are two cases
   * in which that will not be the case:
   * 1. If the cell is 0 or 59, we want it to get the
   * first cell of the LinkedList
   * 2. If the cell is on the outer board and on
   * one of the special cells where you can enter
   * into dorm, but the piece that is being moved
   * is not the same color as the special cell.
   */
  private void forward() {
    LinkedList<SorryCell> successors = board.getSuccessors(pieceSelected.getLocation());
    int successSize = successors.size();
    if (successSize>0){
      try {
        newCell =  successors.get(successSize-1);
        oldCell = pieceSelected.getLocation();
        //1st special case
        if (successSize>0&&successSize<3 && oldCell.cellNumber()==0||oldCell.cellNumber()==59){ 
          newCell = successors.get(0);
          //2nd special case
        }else if (successSize==3 && !newCell.cellColor().equals(pieceSelected.getColor())){
          newCell = successors.get(successSize-2);
        }
        //Setting the location of the piece that is moving across the board
        pieceSelected.setLocation(newCell);
        //Setting the cell to remember which piece is in it
        newCell.setCurrentPiece(pieceSelected);
        //Sets the old cell to remove the piece that just left
        oldCell.removePiece();
      } catch (IndexOutOfBoundsException e) {
        System.out.println("Arrived in your Dorm earlier than expected");     
      }
    }
  }
  
  
  /**
   * A method that moves a piece backward
   * one cell.
   * 
   * The method looks at the predecessor of
   * the cell to see where to move backwards.
   * The list of predecessors are in order of
   * cell number, and so in most cases we want
   * the lower cell number, except in the case
   * or 0 and 59. 
   */
  private void backward(){
    LinkedList<SorryCell> predecessors = board.getPredecessors(pieceSelected.getLocation());
    int predSize = predecessors.size();
    oldCell = pieceSelected.getLocation();
    if (predSize>0){
      int numberOfOldCell = oldCell.cellNumber();
      //If the cell number is anything but 0 or 59 we want the lowest
      //cell number possible as the place where we want our piece to
      //move next.
      if ((numberOfOldCell>0&&numberOfOldCell<59)||(numberOfOldCell>59)){
        newCell = predecessors.get(0);
        
        //If the cell number is 0 or 59, we want the cell to be
        //59 or 58 respectively, so we want to get the last cell
        //of the linkedlist.
      }else if(numberOfOldCell==0 || numberOfOldCell == 59){
        newCell = predecessors.get(predSize-1);
      }
    }
    //sets the piece to remember its new cell location
    //then sets the new cell to remember the piece
    //and removes the piece from the old cell
    pieceSelected.setLocation(newCell);
    newCell.setCurrentPiece(pieceSelected);
    oldCell.removePiece();  
  }
  
  
  /**
   * A method that creates the Sorry! move,
   * in which if you're in CLASS, you will
   * bump another player's piece on the outer
   * board back to CLASS.
   * 
   * The method automatically picks a piece
   * for the current player to move out if
   * there is any available pieces to move
   * out of CLASS. After it has picked a piece
   * it changes the locations of the piece
   * selected and the opponent's piece.
   * 
   */
  private void sorryMove(SorryPiece opponentPiece) {
    SorryPiece piece1 = currentPlayer.getPiece(1);
    SorryPiece piece2 = currentPlayer.getPiece(2);
    
    //Choosing a piece to be moved out of CLASS if possible
    if (piece1.getLocation().equals(piece1.getClassLocation())) pieceSelected = piece1;
    if (piece2.getLocation().equals(piece2.getClassLocation())) pieceSelected = piece2;
    pieceSelected.setOldLocation(pieceSelected.getLocation());
    
    //for the piece that is atuomatically chosen for the user,
    // we change the location variable in both the current 
    //player's piece and the opponent's piece, as well as the cell's
    //instance variables about the pieces in them.
    if (piece1.equals(pieceSelected)||piece2.equals(pieceSelected)){
      pieceSelected.setLocation(opponentPiece.getLocation()); // changing location variable in SorryPiece object
      pieceSelected.getLocation().setCurrentPiece(pieceSelected); //changing currentPiece variable in the SorryCell object
      opponentPiece.setLocation(opponentPiece.getClassLocation()); 
      opponentPiece.getLocation().setCurrentPiece(opponentPiece);
    }
  }
  
  /**
   * A method to move the pieces across the board.
   * There are three situations possible:
   * 1. If value of the card drawn is Sorry!
   * 2. If the value of the card drawn is a numerical
   *    card, which sends the piece forward, except in 
   *    the case of the 4 card.
   * 3. If the value of the card drawn is a 4, which
   *    sends the piece backwards 4 spaces.
   * 
   * Each situation will call a different method in
   * order to move the piece. At the end of any numerical
   * card drawn, we will check if the piece selected by
   * the user has landed on someone else's piece, which in
   * that case, will send the opponent's piece back to home.
   */
  public void movePiece() {
    
    //Situation 1: If a Sorry! card is drawn
    if(isSorryCard()){   
      sorryMove(oppPiece);    
    }else{ //If a numerical card is drawn
      pieceSelected.setOldLocation(pieceSelected.getLocation());
      int value = Integer.parseInt(currentCardValue);
      //If the numerical card is anything but a 4, go forward
      if(value != 4) {
        for (int i = 0; i < value; i++) {  
          forward(); 
          System.out.println("Is now in cell: " + pieceSelected.getLocation());
        }
      } else { //If the numerical card is a 4, go backwards
        if (board.getPredecessors(pieceSelected.getLocation()).size()!=0){
          for (int i = 0; i < value; i ++) {
            backward();
            System.out.println("Is now in cell: " + pieceSelected.getLocation());
          }
        }
      }
      //Check to see if you landed on your own piece. If so, jump over it by moving
      //forward one.
      if (currentPlayer.getPiece(1).getLocation().equals(currentPlayer.getPiece(2).getLocation()))
        forward();
    }
    //Check to see if you landed on an opponent's piece.
    didYouLandOnOppPiece();
  }
  
  
  
  
  
  
  /**
   * A method that returns a boolean to see if
   * there is a winner at the end of every turn.
   * This method checks if there is a winner for
   * all players so that the label with whose turn 
   * or the winner does not change back to whose turn 
   * if there is already a winner (this is all in 
   * accordance with the GUI).
   * 
   * @return A boolean that indicates whether
   * there is a winner, which is used in the GUI
   * later on.
   */
  public boolean isThereWinner() {
    for (int i = 0; i < players.length; i++){
      SorryPiece p1 = players[i].getPiece(1);
      SorryPiece p2 = players[i].getPiece(2);
      if (p1.getLocation().equals(p1.getDormLocation()) && p2.getLocation().equals(p2.getDormLocation())){
        return true;
      }
    }
    return false;
  }
  
  
  
  
  /**
   * A method to play one turn that returns a
   * boolean at the end of the method that
   * indicates whether the player has won or
   * not.
   * 
   * @param A SorryPlayer who is the current player
   * @return A boolean that inidicates if the current
   * player has won or not.
   */
  public boolean playOneTurn(SorryPlayer player){
    draw();
    
    //Arrays for the dialog box to select a piece to move
    String[] pieceOptionsInternal = {"Select a Piece!","Yellow 1", "Yellow 2", "Green 1", "Green 2", 
      "Red 1", "Red 2", "Purple 1", "Purple 2"};
    SorryPiece[] pieces;
    String[] displayPiece;
    
    //Get the two SorryPieces from the current player
    SorryPiece piece1 = currentPlayer.getPiece(1);
    SorryPiece piece2 = currentPlayer.getPiece(2);
    
    //LinkedList to keep track of which pieces are options
    //for the player or move or select for a Sorry! move
    LinkedList<String> linkedPieceOptions = new LinkedList<String>();
    LinkedList<SorryPiece> linkedPieces = new LinkedList<SorryPiece>();
    linkedPieceOptions.add(pieceOptionsInternal[0]);
    linkedPieces.add(player.getPiece(1));
    
    //If we draw a SorryCard
    if (isSorryCard()){
      //We only want this to occur if there are pieces
      //within CLASS for the current player. If there
      //isn't we just skip through this process.
      if (piece1.getLocation().equals(piece1.getClassLocation())||piece2.getLocation().equals(piece2.getClassLocation())){
        //For each player we check both their pieces
        //to see if they are on the board by looking at their
        //cell number
        for (int i = 0; i < players.length; i++){
          if(!player.equals(players[i])){
            
            int pieceOneCellNumber = players[i].getPiece(1).getLocation().cellNumber();
            int pieceTwoCellNumber = players[i].getPiece(2).getLocation().cellNumber();
            
            if (pieceOneCellNumber >= 0 && pieceOneCellNumber < 60)  { 
              linkedPieceOptions.add(players[i].getPiece(1).toString() + " 1");
              linkedPieces.add(players[i].getPiece(1)); 
            }
            if (pieceTwoCellNumber >= 0 && pieceTwoCellNumber < 60){
              linkedPieceOptions.add(players[i].getPiece(2).toString() + " 2");
              linkedPieces.add(players[i].getPiece(2));
            }
          }
        }
      } 
      //If the player drew a 1 or 2, we can show both pieces as options
      //as long as the piece is not in dorm.
    } else if (Integer.parseInt(currentCardValue)==1||Integer.parseInt(currentCardValue)==2){
      if (!piece1.getLocation().equals(piece1.getDormLocation())){
        linkedPieceOptions.add(piece1.toString() + " 1");
        linkedPieces.add(piece1);
      }
      if (!piece2.getLocation().equals(piece2.getDormLocation())){
        linkedPieceOptions.add(piece2.toString() + " 2");
        linkedPieces.add(piece2);
      }
      
    } else {
      //When not a 1 or a 2, only display pieces that can move
      //i.e. pieces not in class or in dorm.
      if (!piece1.getLocation().equals(piece1.getClassLocation())&&!piece1.getLocation().equals(piece1.getDormLocation())){
        linkedPieceOptions.add(piece1.toString() + " 1");
        linkedPieces.add(piece1);
      }
      if (!piece2.getLocation().equals(piece2.getClassLocation())&&!piece2.getLocation().equals(piece2.getDormLocation())){
        linkedPieceOptions.add(piece2.toString() + " 2");
        linkedPieces.add(piece2);
      }
      
    }
    //We put the information from the LL into the
    //array because the dialog box takes in an array only
    pieces = new SorryPiece[linkedPieces.size()];
    displayPiece = new String[linkedPieceOptions.size()];
    for (int i = 0; i < linkedPieceOptions.size(); i++){
      pieces[i] = linkedPieces.get(i);
      displayPiece[i] = linkedPieceOptions.get(i);
    }
    
    //Dialog box
    String s = (String)JOptionPane.showInputDialog(null, drawn.getMeaning(),"Choose a piece to move!", 
                                                   JOptionPane.PLAIN_MESSAGE, null, displayPiece, pieces[0]);
    //If s is not null, we will choose pieces to move
    //whether your own or an opponent's. We will search
    //through our displayPiece array to match the string
    //and then when we find a match, we will put in the
    //respective SorryPiece from the piece[].
    if(s == null||s.equals(displayPiece[0])){
    }else{
      //Choosing opponent's piece for Sorry! card
      if(isSorryCard()) {
        for (int i = 1; i < displayPiece.length; i++){
          if (s.equals(displayPiece[i].toLowerCase())) {
            oppPiece = pieces[i];
          }
        }  
      } else { //Choosing your own piece for a numerical card
        for (int i = 1; i < displayPiece.length; i++){
          if (s.equals(displayPiece[i].toLowerCase())) 
            pieceSelected = pieces[i];
        }    
      }
      movePiece(); //moves the piece
    }
    //Change players and check if there is a winner
    changeCurrentPlayer();    
    return isThereWinner(); 
  }
  
  
  /**
   * String Representation of the Game
   * 
   * @return A String representation of the game
   */
  public String toString(){
    String s = "";
    s += "Player 1: " + player1 + "\n";
    s += "Player 2: " + player2 + "\n";
    s += "Player 3: " + player3 + "\n";
    s += "Player 4: " + player4 + "\n";
    return s;
  }
  
  
  /**
   * The main method for the SorryGame program.
   * Tests the methods in the class.
   * 
   * @param args Not used
   */
  public static void main(String[] args) { 
    SorryGame first = new SorryGame();
    System.out.println("We are now drawing.");
    first.draw();
    //We tested this while shuffle was not being invoked so that
    //the top card would be a Sorry! card and the next one after
    //that would be a 12. Comment out shuffle at the top and the
    //following lines to see the methods tested.
    //Sorry! card:
    //System.out.println("Is it a Sorry! card? (true)" + first.isSorryCard());
    //System.out.println("What is the current card value? (Sorry!)"+first.getCurrentCardValue());
    //12 card:
    //first.draw();
    //System.out.println("Is it a Sorry! card? (false)" + first.isSorryCard());
    //System.out.println("What is the current card value? (12) "+first.getCurrentCardValue());
    
    System.out.println("Is it a Sorry! card? (Varies)" + first.isSorryCard());
    System.out.println("What is the current card value? "+first.getCurrentCardValue());
    //No previous player yet.
    System.out.println("Who was the previous player? (null)" + first.getPreviousPlayer());
    System.out.println("Who is the current player? (Player 1): " +first.getCurrentPlayer());
    first.changeCurrentPlayer();
    System.out.println("Who is the current player? (Player 2): " +first.getCurrentPlayer());
    System.out.println("getPieceSelected(): (null)" + first.getPieceSelected());
    System.out.println("Changing pieceSelected to yellow");
    first.changePieceSelected(first.player1.getPiece(1));
    System.out.println("getPieceSelected(): (yellow)" + first.getPieceSelected());
    System.out.println("getOppPiece(): (null) " + first.getOppPiece());
    System.out.println("Changing oppPiece to green");
    first.changeOppPiece(first.player2.getPiece(2));
    System.out.println("getOppPiece(): (green) " + first.getOppPiece());
    System.out.println("isThereWinner()? (false): " + first.isThereWinner());
    
    SorryGame second = new SorryGame();
    second.playOneTurn(second.player1);
  }
  
}
