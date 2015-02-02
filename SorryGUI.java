/**
 * CS 230 Final Project
 * SorryGUI.java
 * Purpose: To create the graphical user interface for our Sorry! Wellesley Edition
 * game. This class has a main method which creates a frame and adds three tabbed panes
 * to it. It uses the SorryRulesPanel, SorryCardOverviewPanel, and SorryGamePanel classes.
 * 
 * @author: Jazlyn Akaka
 * @version: 1.0 12/18/13
 */
import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;


public class SorryGUI {
  
  /**
   * The main method for the SorryGUI program.
   * When run, the frame with 3 tabbed panes will appear. The three
   * tabbed panes are derived from the SorryRulesPanel, SorryCardOverviewPanel,
   * and SorryGamePanel. 
   * 
   * @param args Not used
   */
  public static void main (String[] args) {
    // creates and shows a Frame 
    JFrame frame = new JFrame("Sorry! Wellesley Edition");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JTabbedPane tp = new JTabbedPane();
    tp.addTab ("Instructions", new SorryRulesPanel());
    tp.addTab ("Card Overview", new SorryCardOverviewPanel());
    tp.addTab ("The Game", new SorryGamePanel());
    frame.setResizable(false);
    frame.getContentPane().add(tp);
    frame.pack();
    frame.setVisible(true);
  }
}
