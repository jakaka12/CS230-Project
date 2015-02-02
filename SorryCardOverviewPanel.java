/**
 * CS 230 Final Project
 * SorryCardOverviewPanel.java
 * Purpose: To create a panel that lays out the cards
 * and the action associated with the given value
 * 
 * @author: Jazlyn Akaka
 * @version: 1.0 12/19/13
 */

import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;

public class SorryCardOverviewPanel extends JPanel{
  
  /**
   * CONSTRUCTOR: Creates the rules panel which has all the instructions.
   * This adds a JLabel that fills up the entire area of the window
   * with an image of the rules
   */
  public SorryCardOverviewPanel() { 
    ImageIcon i = new ImageIcon("CardOverview.png");
    Image img = i.getImage();
    Image imgIcon = img.getScaledInstance(900,700,Image.SCALE_SMOOTH);
    i = new ImageIcon(imgIcon);
    add(new JLabel(i));
  }
  
}