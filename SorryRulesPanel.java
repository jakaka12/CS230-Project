/**
 * CS 230 Final Project
 * SorryRulesPanel.java
 * Purpose: To create the panel of the GUI that has instructions
 * 
 * @author: Jazlyn Akaka
 * @version: 1.0 12/19/13
 */

import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;


public class SorryRulesPanel extends JPanel{

  /**
   * CONSTRUCTOR: Creates the rules panel which has all the instructions.
   * This adds a JLabel that fills up the entire area of the window
   * with an image of the rules
   */
  public SorryRulesPanel(){
    ImageIcon i = new ImageIcon("RulesPanel.png");
    Image img = i.getImage();
    Image imgIcon = img.getScaledInstance(900,700,Image.SCALE_SMOOTH);
    i = new ImageIcon(imgIcon);
    add(new JLabel(i));
  }

}