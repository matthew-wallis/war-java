/**
 * Card.java
 *
 * COMP 1020 SECTION A01
 * INSTRUCTOR       Lauren Himbeault (A01)
 * ASSIGNMENT       Assignment #4
 * @author          Matthew Wallis, 7613913
 * @version         2021-08-01
 *
 *
 * PURPOSE: A class representing a card from a deck, to be stored in a Node object to be linked via a Deck linked list.
 */

public class Card {
    private int value;
    private String suit;

    public Card(int value, String suit){
        this.value = value;
        this.suit = suit;
    }


    public int getValue(){
        return value;
    }

    public String getSuit(){
        return suit;
    }


    public String toString(){
        String cardVal;
        switch (value) {
            case (11):
                cardVal = "Jack";
                break;
            case (12):
                cardVal = "Queen";
                break;
            case (13):
                cardVal = "King";
                break;
            case (14):
                cardVal = "Ace";
                break;
            case (1)://Assignment suggested Ace could be programmed as either 14 or 1, but the test program
                //provided uses both, so this accommodates that.
                cardVal = "Ace";
                break;
            default:
                cardVal = ""+ value;
        }
        return (cardVal + " " + suit);
    }
}
