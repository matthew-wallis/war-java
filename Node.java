/**
 * Node.java
 *
 * COMP 1020 SECTION A01
 * INSTRUCTOR       Lauren Himbeault (A01)
 * ASSIGNMENT       Assignment #4
 * @author          Matthew Wallis, 7613913
 * @version         2021-08-01
 *
 *
 * PURPOSE: A linked list node that stores Card objects for inclusion in a Deck linked list.
 */

public class Node {

    private Card thisCard;
    private Node nextNode;

    public Node(Card thisCard){
        this.thisCard = thisCard;
        nextNode = null;
    }
    public Node getLink(){
        return nextNode;
    }

    public void setLink(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Card cardInNode(){
        return thisCard;
    }
}
