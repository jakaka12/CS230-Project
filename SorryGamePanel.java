/**
 * CS 230 Final Project
 * SorryGamePanel.java
 * Purpose: To create the game Panel that will be one of the tabs of our GUI.
 * This class uses the SorryGrid class to add the gameboard to the center
 * of this panel. This Panel includes two buttons total, one to draw a SorryCard,
 * and the other is the move a piece, which is in the SorryGrid class. The top
 * of this panel includes the draw button, a discard pile label, and another heading
 * that alerts the players as to whose turn it is.
 * 
 * @author: Jazlyn Akaka
 * @version: 1.0 12/18/13
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.MatteBorder;

public class SorryGamePanel extends JPanel{
  private SorryGame game;
  private String whoseTurn;
  private JButton drawButton, resetButton;
  private JButton drawSorryButton;
  private JLabel discardLabel, playerTurn;
  private int RESIZE_X = 100;
  private int RESIZE_Y = 170;
  private SorryGrid grid;
  private boolean winOrLose;
  
  /**
   * CONSTRUCTOR: Creates the panel that will be used in the game tab of the GUI.
   * When a SorryGamePanel is created, there is the main grid (made from SorryGrid), 
   * a drawButton, discard pile label, and label with whose turn it is. This class
   * also makes a new SorryGame.
   */
  public SorryGamePanel(){
    game = new SorryGame();
    whoseTurn = game.getCurrentPlayer().getPlayerColor();
    setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    setBackground (new Color(109,207,246)); //sets background to light blue color 
    grid = new SorryGrid(game);
    add(makeNorthPanel());
    add(grid);
  }
  
  /**
   * This method makes the north most panel of the SorryGamePanel. This includes
   * the making of the drawButton button, the discardLabel which serves to make the 
   * players feel more like they are playing the actual board game, and a label, 
   * playerTurn, which updates everytime it is the next player's turn. 
   */
  private JPanel makeNorthPanel() {
    //setLayout(new GridLayout());
    Listener listener = new Listener();
    JPanel northPanel = new JPanel();
    northPanel.setBackground (new Color(109,207,246));
    drawButton = new JButton(resizeImg("DrawPile.png"));
    drawButton.addActionListener(listener);
    discardLabel = new JLabel(resizeImg("DiscardPile.png")); 
    playerTurn = new JLabel("It's " + whoseTurn + "'s turn");
    playerTurn.setFont(new Font("Arial",Font.BOLD,40));    
    northPanel.add(drawButton); 
    northPanel.add(new JLabel("      ")); //space between two piles of cards
    northPanel.add(discardLabel); 
    northPanel.add(new JLabel("      ")); //space between discard pile and label with whose turn it is
    northPanel.add(playerTurn);
    return northPanel; 
  }
  
  /**
   * This helper method is used to resize the of each images for the drawButton
   * and the discardLabel.
   * 
   * @param A String that is the filename of the desired image.
   * 
   * @return An ImageIcon that is later used to make the drawButton and discardLabel.
   */  
  private ImageIcon resizeImg(String filename){
    ImageIcon i = new ImageIcon(filename);
    Image img = i.getImage();
    Image imgIcon = img.getScaledInstance(RESIZE_X,RESIZE_Y,Image.SCALE_SMOOTH);
    i = new ImageIcon(imgIcon);
    return i;
  }
  
  /**
   * This class represents a listener for drawButton.
   * When the drawButton is clicked, if there is not yet a winner, it
   * will call the method, playOneTurn on the SorryGame. If a player makes
   * a move to make them the winner, the playerTurn label updates to announce the winner.
   */  
  private class Listener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      //when the draw button is pressed
      if (event.getSource()==drawButton&&!winOrLose) {
        if (!winOrLose){
          winOrLose = game.playOneTurn(game.getCurrentPlayer());
          if(winOrLose) {
            playerTurn.setText(whoseTurn.toUpperCase() + " IS THE WINNER!!!");
          }else{
            whoseTurn = game.getCurrentPlayer().getPlayerColor();
            playerTurn.setText("It's " + whoseTurn + "'s turn");
          } 
        }
      }
    }
  } 
  
}