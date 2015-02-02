/**
 * CS 230 Final Project
 * SorryDeck.java
 * Purpose: To create a collection of SorryCard objects
 * that can be shuffled, and used within the SorryGame class.
 * This class uses a LinkedList to store the full collection 
 * of cards, and LinkedStacks to store the used and unused cards.
 * 
 * @author: Jazlyn Akaka
 * @version: 12/10/13
 */

import javafoundations.*;
import java.util.*;

public class SorryDeck{
  private LinkedList<SorryCard> fullDeck; //full collection of cards used to shuffle the used cards
  private LinkedStack<SorryCard> unusedCards; //Starts as a Collection of 45 cards
  private LinkedStack<SorryCard> usedCards; //Each time a card is used, this Stack pushes that card
  private int NUM_CARDS = 45;
  
  /**
   * CONSTRUCTOR: Creates a collection of 45 SorryCard objects that
   * will be used in SorryGame. Each deck contains Every card created contains
   * five 1s, and four of each of 2, 3, 4, 5, 7, 8, 10, 11, 12, and Sorry!
   */
  public SorryDeck(){
    int cardCount;
    fullDeck = new LinkedList<SorryCard>();
    unusedCards = new LinkedStack<SorryCard>();
    usedCards = new LinkedStack<SorryCard>(); //starts out empty
    String[] cardValue = {"1","2","3","4","5","7","8","10","11","12","Sorry!"};
    String[] cardMeaning = {"Good news: Your class has ended. Bad news: "
      +" There's snow. If you've already left class, you can only move 1 cell forward.",
      "Good news: Your class was let out early. Move 2 cells forward",
      "You have a meeting with your RA. Move 3 cells forward reluctantly.",
      "You miss the exchange bus, and have to wait for the next one. Go back 4 cells as you wait for the next one to arrive",
      "You want to catch the Grey's Anatomy episode. Move 5 cells forward to save the Sev TV room from being occupied.",
      "You've studied enough in Clapp. Move 7 cells towards your dorm for a snack.",
      "You made it onto the exchange bus. Move 8 cells forward.",
      "You see a friend walking ahead of you. Move 10 cells forward to catch up with them.",
      "There's Lemon Thai in the common room. Move 11 cells forward to get there before it runs out.",
      "There is steak in Tower. Move 12 cells forward.",
      "You realize you forgot to hand in your pset. Send a friend back to drop it off for you."};
    
    unusedCards.push(new SorryCard(cardValue[0],cardMeaning[0]));//add an extra 1 so that there are five total in the deck
    fullDeck.add(new SorryCard(cardValue[0],cardMeaning[0]));
    cardCount = 1;
    
    while (cardCount<NUM_CARDS){
      for (int i = 0; i < 11; i++){
        SorryCard addCard = new SorryCard(cardValue[i],cardMeaning[i]);
        unusedCards.push(addCard);
        fullDeck.add(addCard);
        cardCount++;
      }
    }
  }
  
  
  /**
   * Rearranges the order of cards in a deck by using a random
   * number generator to grab specific cards from the full deck
   * LinkedList, and push them onto the unused card Stack. 
   * This method is to be used when all cards in the unusedCards
   * collection have been used. This method is protected so that
   * SorryGame may call shuffle on the deck in setting up the game.
   */  
  protected void shuffle(){
    Random rand = new Random();
    LinkedList<SorryCard> temp = new LinkedList<SorryCard>();
    //rearranges the order in fullDeck, 
    //and adds this new order of the deck to the unusedCards stack
    for (int i = 45; i > 0; i--){
      int n = rand.nextInt(i);
      temp.add(fullDeck.get(n));
      fullDeck.remove(n);
    }
    fullDeck = temp;
    for (int i = 0; i < 45; i++){
      unusedCards.push(fullDeck.get(i));
    }
    while (!usedCards.isEmpty())
      usedCards.pop();
  }
  
  /**
   * Draw method that returns the top SorryCard from the unusedCards stack. 
   * If the unusedCards stack becomes empty, the shuffle method is called
   * to rearrange the cards, and unusedCards will then have 45 cards once again.
   * The card is popped off the top of the stack so that the next time draw() is called,
   * a different card is drawn. 
   * 
   * @return A SorryCard that is the top of the unusedCards stack.
   */
  public SorryCard draw(){
    if (unusedCards.isEmpty())
      shuffle();
    usedCards.push(unusedCards.pop());
    return usedCards.peek();
  }
  
  /**
   * String representation of the deck
   * 
   * @return A string representation which states all
   * the SorryCards in the unusedCards stack and all 
   * the SorryCards in the usedCards stack. 
   */
  public String toString(){
    String s = "";
    //print unusedCards
    
    LinkedStack<SorryCard> tempUnused = new LinkedStack<SorryCard>();
    LinkedStack<SorryCard> tempUsed = new LinkedStack<SorryCard>();
    
    if (unusedCards.isEmpty()) 
      s+= "There are no cards in the draw pile.";
    else{
      s += "The cards that are in the draw pile are: \n<Top>\n";
      while (!unusedCards.isEmpty()){
        tempUnused.push(unusedCards.peek());
        s += unusedCards.pop();
      }
      s += "<Bottom>";
    }
    if (usedCards.isEmpty())
      s+= "\nThere are no cards in the discard pile.";
    else{
      s += "\nThe cards that are in the discard pile are: \n<Top>\n";
      while (!usedCards.isEmpty()){
        tempUsed.push(usedCards.peek());
        s += usedCards.pop();
      }
      s += "<Bottom>";
    }
    while(!tempUnused.isEmpty()){
      unusedCards.push(tempUnused.pop());
    }
    while(!tempUsed.isEmpty()){
      usedCards.push(tempUsed.pop());
    }
// The following for loop that is commented out is to print the fullDeck
//    for (int i = 0; i < NUM_CARDS; i++){
//    s += fullDeck.get(i);
//    }
    return s;
  }
  
  
  
  /**
   * The main method for the SorryDeck program.
   * Tests the methods in the class.
   * 
   * @param args Not used
   */
  public static void main(String[] args){
    SorryDeck deck1 = new SorryDeck();
    System.out.println("BEFORE DRAWING ANY CARDS\n " + deck1);
    System.out.println("Drawn Card: " + deck1.draw() + "\n" + deck1);//1
    deck1.draw();//2
    deck1.draw();//3
    deck1.draw();//4
    deck1.draw();//5
    deck1.draw();//6
    deck1.draw();//7
    deck1.draw();//8
    deck1.draw();//9
    deck1.draw();//10
    deck1.draw();//11
    deck1.draw();//12
    deck1.draw();//13
    deck1.draw();//14
    deck1.draw();//15
    deck1.draw();//16
    System.out.println("AFTER 16 DRAWS\n" + deck1);
    deck1.draw();//17
    deck1.draw();//18
    deck1.draw();//19
    deck1.draw();//20
    deck1.draw();//21
    deck1.draw();//22
    deck1.draw();//23
    deck1.draw();//24
    deck1.draw();//25
    deck1.draw();//26
    deck1.draw();//27
    deck1.draw();//28
    deck1.draw();//29
    deck1.draw();//30
    deck1.draw();//31
    deck1.draw();//32
    deck1.draw();//33
    deck1.draw();//34
    deck1.draw();//35
    deck1.draw();//36
    deck1.draw();//37
    deck1.draw();//38
    deck1.draw();//39
    deck1.draw();//40
    deck1.draw();//41
    deck1.draw();//42
    deck1.draw();//43
    deck1.draw();//44
    System.out.println("AFTER 44 DRAWS\n"+deck1);
    deck1.draw();//45
    System.out.println("AFTER 45 DRAWS\n"+deck1);
    deck1.draw();//46 -- This should now be a shuffled deck of SorryCards
    System.out.println("AFTER 46 DRAWS\n"+deck1);
  }
}