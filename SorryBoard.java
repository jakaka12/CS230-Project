/**
 * CS 230 Final Project
 * SorryBoard.java
 * Purpose: To create the board that will be
 * used by the game.
 * 
 * @author: Alice Wong
 * @version: 12/03/13
 */

import javafoundations.*;
import java.util.*;
import java.io.*;


public class SorryBoard extends AdjMatGraphPlus{
  protected AdjMatGraphPlus board;
  protected final int NUM_CELLS = 92;
  protected final int NUM_OUTER_BOARD_CELL = 60;
  protected SorryCell[] collection;
  
  /**
   * CONSTRUCTOR: Creates the SorryBoard with
   * SorryCells as vertices and edges betwee them. 
   * Every board will have 92 cells, where 60 of them
   * will be the outer board squares. Each player/color
   * has 6 dorm cells and 2 class cells. There are
   * two class cells because there are two pieces
   * per player and each cell is made so that it can only
   * hold one piece at a time.
   */
  public SorryBoard() {
    board = new AdjMatGraphPlus();
    
    //A collection of cells so that we don't have to manually create each individual cell
    collection = new SorryCell[NUM_CELLS];
    for(int i = 0; i < NUM_CELLS; i++) {
      collection[i] = new SorryCell(i); 
      board.addVertex(collection[i]);
    }
    
    //Creates the edges between all the vertices on the
    //outer board except for the edge between vertex 0
    // 59, which is added after the for loop because
    //we can't add an edge in the for loop without continuously
    //looping forever.
    //It adds an edge between the previous cell an0 the current cell
    //as well as the current cell and its successive cell 
    for(int j = 0; j < NUM_OUTER_BOARD_CELL-1; j++) {
      board.addEdge(collection[j], collection[j+1]); 
    }
    board.addEdge(collection[0], collection[59]); 
    
    //Yellow portion of the dorm, class attachment to the rest of the board
    //Adds the edge between the outer board and the dorm cells. Then adds
    //edges between the 
    board.addEdge(collection[2], collection[60]);
    for(int k = 60; k < 64; k++) {
      board.addEdge(collection[k], collection[k+1]);
    }
    board.addArc(collection[64], collection[65]);
    
    //CLASS cells attachtment to the board
    board.addArc(collection[84], collection[4]); 
    board.addArc(collection[85], collection[4]);
    
    
    //green portion of the dorm, class attachment to the rest of the board
    board.addEdge(collection[17], collection[66]);
    for(int l = 66; l < 70; l++) {
      board.addEdge(collection[l], collection[l+1]);
    }
    board.addArc(collection[70], collection[71]);
    //CLASS cells attachtment to the board
    board.addArc(collection[86], collection[19]);
    board.addArc(collection[87], collection[19]);
    
    
    //red portion of the dorm, class attachment to the rest of the board
    board.addEdge(collection[32], collection[72]);
    for(int m = 72; m < 76; m++) {
      board.addEdge(collection[m], collection[m+1]);
    }
    board.addArc(collection[76], collection[77]);
    //CLASS cells attachtment to the board
    board.addArc(collection[88], collection[34]); //class sq to outerboard
    board.addArc(collection[89], collection[34]);
    
    //purple portion of the dorm, class attachment to the rest of the board
    board.addEdge(collection[47], collection[78]);
    for(int n = 78; n < 82; n++) {
      board.addEdge(collection[n], collection[n+1]);
    }
    board.addArc(collection[82], collection[83]);
    board.addArc(collection[90], collection[49]); //class sq to outerboard
    board.addArc(collection[91], collection[49]);
  }
  
  
  /**
   * A wrapper method that gets the LinkedList of
   * successors of the cell(vertex).
   * 
   * @param A SorryCell that is chosen by where the
   * piece currently is.
   * @return A list of successors of the cell
   */
  public LinkedList<SorryCell> getSuccessors(SorryCell c) {
    return board.getSuccessors(c);
  }
  
  /**
   * A wrapper method that gets the LinkedList of
   * predecessors of the cell(vertex).
   * 
   * @param A SorryCell that is chosen by where the
   * piece currently is.
   * @return A list of predecessors of the cell
   */
  public LinkedList<SorryCell> getPredecessors(SorryCell c){
    return board.getPredecessors(c);
  }
  
  /**
   * String representation of the board
   * 
   * @return A string representation which states the 
   */  
  public String toString() {
    String s = "The number of cells in the board is " + NUM_CELLS;
//To see which cells were added in, uncomment this out.
//    for (int i = 0; i<NUM_CELLS; i++){
//      s += "\t"+collection[i];
//    }
    return s;
  }
  
  /** The main method for the SorryBoard program.
    * Tests the methods in the class.
    * 
    * @param args Not used
    */
  public static void main(String[] args){
    SorryBoard first = new SorryBoard();
    System.out.println(first);
    
    System.out.println("Is there an edge between 0 and 4? (false) "
                         +first.board.isEdge(first.collection[0], first.collection[4]));
    System.out.println("Is there an edge between 51 and 50? (true) "
                         +first.board.isEdge(first.collection[51], first.collection[50]));
    System.out.println("Is there an edge between 58 and 59? (true) "
                         +first.board.isEdge(first.collection[58], first.collection[59]));
    System.out.println("Is there an edge between 0 and 59? (true) "
                         +first.board.isEdge(first.collection[0], first.collection[59]));
    
    System.out.println("Successors of 47 (46, 48, 78): "+first.board.getSuccessors(first.collection[47]));
    System.out.println("Successors of 32 (31, 33, 72): "+first.board.getSuccessors(first.collection[32]));
    System.out.println("Successors of 0 (1, 59): "+first.board.getSuccessors(first.collection[0]));
    System.out.println("Successors of 59 (0, 58): "+first.board.getSuccessors(first.collection[59]));
    System.out.println("Successors of 56 (55, 57): "+first.board.getSuccessors(first.collection[56]));
    
    first.board.saveTGF("Testing.tgf");
    System.out.println("Predecessors of 85 (Should be empty): " + first.board.getPredecessors(first.collection[85]));
    System.out.println("Predecessors of 4 [3,5,84,85]: " + first.board.getPredecessors(first.collection[4]));
    System.out.println("Predecessors of 59 [0,58]: " + first.board.getPredecessors(first.collection[59]));
    System.out.println("Predecessors of 20 [19,21]: " + first.board.getPredecessors(first.collection[20]));
  }
}

