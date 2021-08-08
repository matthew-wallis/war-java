/**
 * Deck.java
 *
 * COMP 1020 SECTION A01
 * INSTRUCTOR       Lauren Himbeault (A01)
 * ASSIGNMENT       Assignment #4
 * @author          Matthew Wallis, 7613913
 * @version         2021-08-01
 *
 *
 * PURPOSE: A class for organizing and retrieving a list of cards. Provides instance methods for
 * removing, adding and counting cards in various ways to facilitate a game of War.
 */
public class Deck {

    private Node top;


    public void addCard(Card newCard) {
        if (top == null) {
            top = new Node(newCard);
        }
        else {
            Node currentCard = top;
            while (currentCard.getLink() != null) {  //loops through linked list until reaching bottom of the deck.
                currentCard = currentCard.getLink();
            }
            currentCard.setLink(new Node(newCard));
        }
    }

    public void emptyDeck(){
        top = null;
    }

    public Card getCard(){
        if(!isEmpty()) {
            Card pickedCard = top.cardInNode();
            removeTopCard(); //it was only clear to me through the test file provided that removal is required.
            return pickedCard;
        }
        else{return null;}
    }

    private void removeTopCard(){
        if(top.getLink()!=null) { //if the top card is not the only card, this reassigns top to the next node.
            top = top.getLink();
        }
        else{top = null;} //if only card, simply removes top reference to the Node.
    }

    public void addDeck(Deck other) {
        if (!other.isEmpty()) {
            addCard(other.getCard());
            other.emptyDeck();
        }
    }

    public boolean isEmpty(){
        return top == null;
    }

    public Card getRandom() {
        Card returnedCard = null;
        if (!isEmpty()) { //loop that first determines the random location of the card to be picked
            int pickNum = (int) (Math.random() * count()) + 1;
            Node pick = top;

            Node prevCard = null;
            if (pickNum == 1) {returnedCard = getCard();}  //calls top card pulling method if the top card is randomly chosen.
            else { //for all other cards, loops through linked list until picked card is found.
                while (pickNum > 1) {
                    prevCard = pick;
                    pick = pick.getLink();
                    pickNum--;
                }
                if (pick.getLink() == null) {prevCard.setLink(null);} //if the pick is at the end, the previous node is reassigned as last.
                else {prevCard.setLink(pick.getLink());} //otherwise, the previous card's node is relinked to the card below the picked card.
                returnedCard = pick.cardInNode();
            }
        }
        return returnedCard;
    }


    public void orderedInsert(Card in) {
        Node newNode = new Node(in);
        if (top == null) {top = newNode;} //empty deck condition
        else if(top.cardInNode().getValue() > in.getValue())  { //card to insert is ordered first
            newNode.setLink(top);
            top = newNode;
        }
        else {
            Node currentNode = top;
            Node prevNode = null;

            //while end of deck not reached and inserted card is greater in value than current one, find next card in list.
            while (currentNode.getLink() != null && currentNode.cardInNode().getValue() <= in.getValue()) {
                prevNode = currentNode;
                currentNode = currentNode.getLink();
            }

            //inserts cards by reassigning the previous node's link, and copying the old link to the new node.
            if (currentNode.cardInNode().getValue() > in.getValue()) {
                newNode.setLink(currentNode);
                prevNode.setLink(newNode);
            }
            else {currentNode.setLink(newNode);}
        }
    }

    public String toString(){
        String returnString = "[";
        returnString += printCards(top);
        returnString += "]";
        return returnString;
    }

    //toString helper for iterating through the linked list and returning the card toString info of each node.
    public String printCards(Node index){
        String returnString = "";
        if (index == null){returnString += " ";}
        else if (index.getLink() == null){returnString += index.cardInNode().toString();}
        else{returnString += index.cardInNode().toString() + ", " + printCards(index.getLink());}
        return returnString;
    }

    //interface for recursive summation so user need not enter initial parameters, returns result of sumR.
    public int sum(){
        return sumR(0, top);
    }

    //recursive summation, base case is the end of the deck.
    private int sumR(int subTotal, Node current){
        if (current.getLink()==null){subTotal += current.cardInNode().getValue();}
        else{subTotal += current.cardInNode().getValue() + sumR(subTotal, current.getLink());}
        return subTotal;
    }
    //interface for recursive count so user need not enter initial parameters, returns result of countR.
    public int count(){
        int c = 0;
        if (!isEmpty()) {c = countR(c, top);}
        else {c = 0;}
        return c;
    }
    //recursive count of cards in deck, base case is end of the deck.
    private int countR(int c, Node current){
        if (current.getLink()==null){c++;}
        else{c = 1 + countR(c, current.getLink());}
        return c;
    }

}
