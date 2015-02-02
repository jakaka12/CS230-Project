/**
 * CS 230 Final Project
 * SorryGrid.java
 * Purpose: To create the grid Panel that will be 
 * used by the SorryGamePanel class to visually represent
 * the gameboard and the pieces moving on the gameboard. The gameboard
 * is a square board with 16 rows and 16 columns, along with 8 
 * icons that represent student pieces (2 for each player).
 * 
 * @author: Jazlyn Akaka
 * @version: 1.0 12/18/13
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Image;
import javax.swing.JLabel;

public class SorryGrid extends JPanel{
  private int ROWS = 16;
  private int COLUMNS = 16;
  private int RESIZE = 30; //30x30 is the pixel dimension that each grid square student piece will be resized to 
  private JLabel cell;
  private JButton movePiece;
  private ImageIcon y1, y2, g1, g2, r1, r2, p1, p2;
  private JLabel[][] rowCol; 
  private SorryGame game;
  
  
  /**
   * CONSTRUCTOR: Creates the grid that will be used in the SorryGamePanel class.
   * When a SorryGrid is created, there is the main grid, which is made up of
   * JLabel squares of different colors (Grey, Yellow, Green, Red, and Purple)
   * and JLabel squares with different colored pieces (two of each of Yellow,
   * Green, Red, and Purple). In the constructor the fully made grid is added
   * to the panel. The movePiece button is also added, which when clicked will 
   * move a student piece to its appropriate location (if applicable). 
   * 
   * @param A SorryGame so that the movements of the pieces on the backend
   * can correspond to movements of the pieces on the frontend. A game must be
   * provided so that we can find the various locations associated with each piece. 
   */
  public SorryGrid(SorryGame currentGame){
    game = currentGame;
    rowCol = new JLabel[ROWS][COLUMNS];
    y1 = resizeImg("Yellow1.png");
    y2 = resizeImg("Yellow2.png");
    g1 = resizeImg("Green1.png");
    g2 = resizeImg("Green2.png");
    r1 = resizeImg("Red1.png");
    r2 = resizeImg("Red2.png");
    p1 = resizeImg("Purple1.png");
    p2 = resizeImg("Purple2.png");
    movePiece = new JButton("Move Piece");
    ButtonListener listener = new ButtonListener();
    movePiece.addActionListener(listener);
    setBackground(new Color(109,207,246));
    add(makeGrid());
    add(movePiece);
  }
  
  /**
   * This method creates the actual boardgame grid that will be
   * the main part of the GUI. For each row and column there is
   * a specified cell that will be created using the makeCell() 
   * method and these cells are added left to right, top to bottom.
   * The GridBagLayout is used to make the grid as it gives us the 
   * desired board/grid look. 
   * 
   * @return A JPanel that visually represents the gameboard.
   */
  private JPanel makeGrid() {
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    for (int r = 0; r < ROWS; r ++){
      for (int c = 0; c < COLUMNS; c++){
        rowCol[r][c] = new JLabel(makeCell(r,c));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.gridx = c;
        gbc.gridy = r;
        centerPanel.add(rowCol[r][c],gbc);
      }  
    }
    
    /*
     * The above only set the cell colors of the board,
     * so below, the two for loops work to add the pieces
     * onto the board by resetting the icon to a player's
     * piece instead of a solid square of color. It uses a 
     * SorryPiece[] to go through each player and find where
     * each piece is located at the beginning of the game and
     * changes the icon using changeCellPic().
     */
    SorryPiece[] piecesArray= new SorryPiece[8];
    int j = 0;
    for (int i = 0; i < game.players.length; i++){
      piecesArray[j] = game.players[i].getPiece(1);
      j++;
      piecesArray[j] = game.players[i].getPiece(2);
      j++;
    }
    for (int i = 0; i < piecesArray.length; i++){
      changeCellPic(piecesArray[i]);
    }
    
    return centerPanel;
  }
  
  /**
   * This helper method visually creates the cell to be displayed
   * on the gameboard. It takes in the row and column of the desired
   * cell you would like to create, and uses this information to 
   * find out which picture (color) to use for the desired cell.
   * The default color is white, because many of
   * the inner squares of the gameboard are not used. 
   * 
   * @param An integer that represents what row the cell you are making is in
   * @param An integer that represents what column the cell you are making is in
   * 
   * @return A JPanel that visually represents the gameboard.
   */
  private ImageIcon makeCell(int row, int column){
    ImageIcon cell = resizeImg("WhiteCell.png");
    
    //make outer board grey
    if (row==0||row==15||column==0||column==15){
      cell = resizeImg("GreyCell.png");
    }
    
    //make yellow's safezone, dorm, and class 
    if ((column==2&&row>0&&row<7)||(column==4&&row==1)||(column==4&&row==2))
      cell = resizeImg("YellowCell.png");
    
    //make green's safezone, dorm, and class 
    if ((row==2&&column>8&&column<15)||(row==4&&column==14)||(row==4&&column==13))
      cell = resizeImg("GreenCell.png");
    
    //make red's safezone, dorm, and class 
    if ((column==13&&row>8&&row<15)||(column==11&&row==14)||(column==11&&row==13))
      cell = resizeImg("RedCell.png");
    
    //make purple's safezone, dorm, and class 
    if ((row==13&&column>0&&column<7)||(row==11&&column==1)||(row==11&&column==2))
      cell = resizeImg("PurpleCell.png");
    return cell;
  }
  
  
  /**
   * This method is used to remove a student piece's icon once it has made a move on the 
   * gameboard. We don't want the piece to be added to a new cell, but also still have it
   * visually exist in it's previous cell, so this method prevents the piece from staying
   * in the old cell. It uses the piece's oldLocation to get the row and column, and resets 
   * the cell using makeCell(), which will return the square to it's original image (color). 
   * 
   * @param A SorryPiece that you wish to reset the icon in its oldLocation
   * 
   * @return A JLabel that visually represents the specific square that was once occupied by the given piece.
   */
  public JLabel removeOldPic(SorryPiece piece){
    int oldC = piece.getOldLocation().cellCol();
    int oldR = piece.getOldLocation().cellRow();
    rowCol[oldR][oldC].setIcon(makeCell(oldR,oldC));
    return rowCol[oldR][oldC];
  }
  
  /**
   * This method is used to change the cell's image to a different or new student piece. 
   * It takes in a SorryPiece as a parameter, finds the current location of the cell,
   * pinpoints the row and column, and resets the image in that specific cell. Each
   * SorryPiece has a distinct pieceNumber, which we are able to use here in a switch statement
   * to decided which icon to use for the cell. 
   * 
   * @param A SorryPiece that you wish to reset the icon in its current location
   * 
   * @return A JLabel that visually represents the specific square is now occupied by the given piece.
   */  
  public JLabel changeCellPic(SorryPiece piece){
    int c = piece.getLocation().cellCol();
    int r = piece.getLocation().cellRow();
    
    switch (piece.getPieceNumber()){
      case 0: //reset yellow 1 piece
        rowCol[r][c].setIcon(y1);
        break;
      case 1: //reset yellow 2 piece
        rowCol[r][c].setIcon(y2);
        break;
      case 2: //reset green 1 piece
        rowCol[r][c].setIcon(g1);
        break;
      case 3: //reset green 2 piece
        rowCol[r][c].setIcon(g2);
        break;
      case 4: //reset red 1 piece
        rowCol[r][c].setIcon(r1);
        break;
      case 5: //reset red 2 piece
        rowCol[r][c].setIcon(r2);
        break;
      case 6: //reset purple 1 piece
        rowCol[r][c].setIcon(p1);
        break;
      case 7: //reset purple 2 piece
        rowCol[r][c].setIcon(p2);
        break;
    }
    return rowCol[r][c];
  }
  
  /**
   * This helper method is used to resize the of each image that will be 
   * a part of the gameboard. This includes the cells of the board (solid colors) and 
   * the student pieces as well. 
   * 
   * @param A String that is the filename of the desired image.
   * 
   * @return An ImageIcon that is later used as a parameter to create the JLabels of each cell.
   */  
  private ImageIcon resizeImg(String filename){
    ImageIcon i = new ImageIcon(filename);
    Image img = i.getImage();
    Image imgIcon = img.getScaledInstance(RESIZE,RESIZE,Image.SCALE_SMOOTH);
    i = new ImageIcon(imgIcon);
    return i;
  }
  
  /**
   * This class represents a listener for the movePiece button.
   * When the movePiece button is pressed, if any locations of the 
   * SorryPieces have moved, it will adjust the JLabels so that they 
   * are visually in the correct cell. A for loop is used to check the 
   * positions of all the players' pieces. When a "Sorry!" card is used
   * or a player lands on another players piece, those other pieces locations 
   * change, and their visual representation must also be updated.
   */  
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      if (event.getSource()==movePiece) {     
        for (int i = 0; i < game.players.length; i++){
          SorryPiece piece1 = game.players[i].getPiece(1);
          SorryPiece piece2 = game.players[i].getPiece(2);
          
          if (piece1.equals(game.getPieceSelected())){ 
            //if this is the current pieceSelected, it removes it from its current location
            //and moves it to its new location on the gameboard
            removeOldPic(piece1);
            changeCellPic(piece1);
          }else{
            //if a piece lands on another piece, the oldPic of the piece that was landed on
            //will already be removed, so all that needs to happen is: 
            //the piece that was landed on moves back to class
            changeCellPic(piece1);
          }
          if (piece2.equals(game.getPieceSelected())){
            //if this is the current pieceSelected, it removes it from its current location
            //and moves it to its new location on the gameboard
            removeOldPic(piece2);
            changeCellPic(piece2);
          }else{
            //if a piece lands on another piece, the oldPic of the piece that was landed on
            //will already be removed, so all that needs to happen is: 
            //the piece that was landed on moves back to class
            changeCellPic(piece2);
          }          
        }
      }
    }
  }
  
}


