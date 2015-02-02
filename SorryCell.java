/**
 * CS 230 Final Project
 * SorryCell.java
 * Purpose: To create cells that will be used as vertices
 * in the graph
 * 
 * @author: Alice Wong
 * @author: Jazlyn Akaka
 * @version: 12/10/13
 */

public class SorryCell {
  private String cellColor; 
  private int cellNum, row, col;
  private SorryPiece currentPiece;
  
  
  /**
   * CONSTRUCTOR: Creates the cell that will be used as a vertex
   * in the graph(SorryBoard class). Every cell created contains
   * information on what int it is with respect to the graph,
   * integers for the row and column it is in with respect to the 
   * grahical user interface, the color associated with it
   * and the piece that is in it (if there is no piece then it
   * will just be null)
   * 
   * @param An int that will be arbitrarily decided by the
   * program. Int - = 59 are outer board cells, 60-83 are
   * the dorm cells and 83-91 are the class cells.
   */
  public SorryCell(int cellNumber) {
    cellNum = cellNumber;
    
    //These are the cells that are dorm, class, and safezone cells, and therefore correspond to a specific color
    if(cellNum > 59 && cellNum < 66 || cellNum == 84 || cellNum == 85 || cellNum == 3) cellColor = "yellow";
    else if(cellNum > 65 && cellNum < 72 || cellNum == 86 || cellNum == 87 || cellNum == 18) cellColor = "green";
    else if(cellNum > 71 && cellNum < 78 || cellNum == 88 || cellNum == 89 || cellNum == 33) cellColor = "red";
    else if(cellNum > 77 && cellNum < 84 || cellNum == 90 || cellNum == 91 || cellNum == 52) cellColor = "purple";
    //The rest of the cells make up the outer board and are therefore grey
    else cellColor = "grey";
    
    /**
     * Each cell corresonds to a specific row and column in the graphical user interface (GUI).
     * In order to be able to reference specific cells and change the icons in the GUI accordingly,
     * the cell columns (col) and cell rows (row) must be properly initialized. There is no real 
     * pattern to the way vertices (cellNum) match up to the rows and columns, and thus we have initialized
     * the rows and columns in the most organized why possible.
     */
    
    //Initializing col (columns)
    if(cellNum > 44 && cellNum < 60 || cellNum == 0) col = 0;
    if(cellNum == 1 || cellNum == 90 || cellNum == 78 || cellNum == 44) col = 1;
    if(cellNum > 59 && cellNum < 66 || cellNum == 79 || cellNum == 43 || cellNum == 91 || cellNum == 2) col = 2; 
    if(cellNum == 3 || cellNum == 80 || cellNum == 42) col = 3;
    if(cellNum == 4 || cellNum == 84 || cellNum == 85 || cellNum == 81 || cellNum == 41) col = 4;
    if(cellNum == 5 || cellNum == 82 || cellNum == 40) col = 5;
    if(cellNum == 6 || cellNum == 83 || cellNum == 39) col = 6;
    if(cellNum == 7 || cellNum == 38) col = 7;
    if(cellNum == 8 || cellNum == 37) col = 8;
    if(cellNum == 9 || cellNum == 71 || cellNum == 36) col = 9;
    if(cellNum == 10 || cellNum == 70 || cellNum == 35) col = 10;
    if(cellNum == 11 || cellNum == 69 || cellNum == 89 || cellNum == 88 || cellNum == 34) col = 11;
    if(cellNum == 12 || cellNum == 68 || cellNum == 33) col = 12;
    if(cellNum > 71 && cellNum < 78 || cellNum == 13 || cellNum == 67 || cellNum == 32 || cellNum == 87) col = 13;
    if(cellNum == 14 || cellNum == 66 || cellNum == 86 || cellNum == 31) col = 14;
    if(cellNum > 14 && cellNum < 31) col = 15;
    
    //Initializing rows
    if(cellNum < 16) row = 0;
    if(cellNum == 59 || cellNum == 60 || cellNum == 84 || cellNum == 16) row = 1;
    if(cellNum > 65 && cellNum < 72 || cellNum == 58 || cellNum == 61 || cellNum == 17 || cellNum == 85) row = 2;
    if(cellNum == 57 || cellNum == 62 || cellNum == 18) row = 3;
    if(cellNum == 56 || cellNum == 63 || cellNum == 87 || cellNum == 86 || cellNum == 19) row = 4;
    if(cellNum == 55 || cellNum == 64 || cellNum == 20) row = 5;
    if(cellNum == 54 || cellNum == 65 || cellNum == 21) row = 6;
    if(cellNum == 53 || cellNum == 22) row = 7;
    if(cellNum == 52 || cellNum == 23) row = 8;
    if(cellNum == 51 || cellNum == 77 || cellNum == 24) row = 9;
    if(cellNum == 50 || cellNum == 76 || cellNum == 25) row = 10;
    if(cellNum == 49 || cellNum == 90 || cellNum == 91 || cellNum == 75 || cellNum == 26) row = 11;
    if(cellNum == 48 || cellNum == 74 || cellNum == 27) row = 12;
    if(cellNum > 77 && cellNum < 84 || cellNum == 47 || cellNum == 73 || cellNum == 28 || cellNum == 89) row = 13;
    if(cellNum == 46 || cellNum == 88 || cellNum == 72 || cellNum == 29) row = 14;
    if(cellNum > 29 && cellNum < 46) row = 15;
  }
  
  /**
   * A getter that returns what int the cell is.
   * The outer board are cells 0 - 59.
   * Dorm cells are 60 - 83
   * Class cells are 83 - 91
   * 
   * @return An int that the cell is represented with.
   */
  public int cellNumber() {
    return cellNum; 
  }
  
  /**
   * A getter that returns what column the cell is.
   * There are 16 columns total (numbered 0 - 15).
   * 
   * @return An int that represents the column the cell is in.
   */
  public int cellCol() {
    return col; 
  }
  
  /**
   * A getter that returns what row the cell is.
   * There are 16 rows total (numbered 0 - 15).
   * 
   * @return An int that represents the row the cell is in.
   */
  public int cellRow() {
    return row; 
  }
  
  /**
   * A getter that returns what color the square is
   * 
   * @return A string representation of the color
   * of the cell.
   */
  public String cellColor() {
    return cellColor; 
  }
  
  /**
   * A method to update the current piece in the cell
   * 
   * @param: A SorryPiece that is the piece that will
   * be moving into the cell.
   */
  public void setCurrentPiece(SorryPiece newCurrentPiece) {
    currentPiece = newCurrentPiece;
  }
  
  /**
   * A getter to get the current piece in the cell
   * 
   * @return A SorryPiece that is currently in the cell.
   */
  public SorryPiece getCurrentPiece() {
    return currentPiece;
  }
  
  /**
   * A method to remove the piece as the piece moves
   * out of the cell during the game.
   */
  public void removePiece(){
    currentPiece = null;
  }
  
  /**
   * String representation of the cell
   * 
   * @return A string representation which states the 
   * the number associated with the cell
   */
  public String toString() {
    String s = Integer.toString(cellNum);
// The commented out strings are part of our original testing in this class
//    s+= "The Sorry Piece in this cell is " + currentPiece;
//    s+= "The cell color of this cell is " + cellColor;
//    s+= "The row and col associated with this cell is row:" + row + " and col: " + col;
//    s+= "The cellNumber is this cell is Integer.toString(cellNum);
    return s;
  }
  
  /**
   * The main method for the SorryCell program.
   * Tests the methods in the class.
   * 
   * @param args Not used
   */
  public static void main (String[] args) {
    
    SorryCell cell = new SorryCell (2);
    SorryCell cell2 = new SorryCell (74);
    SorryCell cell3 = new SorryCell (18);
    SorryCell cell4 = new SorryCell (84);
    
    System.out.println(cell);
    
    System.out.println("cellNumber() (2) : "+ cell.cellNumber());
    
    System.out.println();
    
    System.out.println(cell2);
    System.out.println("cellCol() (13): "+ cell2.cellCol());
    System.out.println("cellRow() (12): "+ cell2.cellRow());
    
    System.out.println();
    
    System.out.println(cell3);
    System.out.println("cellColor() (green): " + cell3.cellColor());
    System.out.println("setting current piece in 18 to be yellow 1");
    SorryPlayer player1 = new SorryPlayer(1, "yellow");
    System.out.println("Setting current piece to yellow");
    cell3.setCurrentPiece(player1.getPiece(1));
    System.out.println("getCurrentPiece: (yellow) " + cell3.getCurrentPiece());
    System.out.println("Removing current piece");
    cell3.removePiece();
    System.out.println("getCurrentPiece: (null) " + cell3.getCurrentPiece());
    
    System.out.println();
    
    System.out.println(cell4);
    System.out.println("cellColor() (yellow): " + cell4.cellColor());
  }
  
  
  
  
}