/**
 * WarGame.java
 *
 * COMP 1020 SECTION A01
 * INSTRUCTOR       Lauren Himbeault (A01)
 * ASSIGNMENT       Assignment #4
 * @author          Matthew Wallis, 7613913
 * @version         2021-08-01
 *
 *
 * PURPOSE: A class for executing a game of War when passed two player names. Uses decks for each user, and
 * one "pool" which is initially the entire deck distributed to players, and acts as the winnings pool each round.
 * Game will execute in full after it's constructor is called.
 */


public class WarGame {
    private Deck p1Deck;
    private Deck p2Deck;
    private Deck pool;
    private String player1;
    private String player2;

    public WarGame(String player1, String player2){
        this.player1 = player1;
        this.player2 = player2;
        populateDeck();
        distributeCards();
        roundStart();

    }

    //loops through each card value and suit number, and adds the associated card to the pool Deck in order.
    private void populateDeck(){
        this.pool = new Deck();
        for (int i = 2; i < 15; i++){
            for (int j = 1; j < 5; j++){
                String suit = "";
                switch(j){
                    case (1):
                        suit += "Hearts";
                        break;
                    case (2):
                        suit += "Diamonds";
                        break;
                    case (3):
                        suit += "Spades";
                        break;
                    case (4):
                        suit += "Clubs";
                        break;
                }
                pool.addCard(new Card(i, suit));
            }
        }
    }

    //After the pool has been populated, this simulates shuffling by randomly selecting cards one by one and providing
    //them to each of the two players until the pool Deck has been fully divided up.
    private void distributeCards(){
        this.p1Deck = new Deck();
        this.p2Deck = new Deck();
        while(!pool.isEmpty()){
            p1Deck.addCard(pool.getRandom());
            p2Deck.addCard(pool.getRandom());
        }
    }

    //Recursive method for initiating a round after calling winner() and confirming a win condition hasn't been met.
    private void roundStart(){
        if(!winner()){
            commenceWar(1);
            roundStart();
        }
    }

    //method for flipping cards and comparing values to see which player wins the pool. Uses recursion in instances
    //where the flipped cards are of the same value.
    private void commenceWar(int flipCount){
        Card p1Card = flip(player1, p1Deck, flipCount); //flips more cards if "it's war".
        Card p2Card = flip(player2, p2Deck, flipCount);
        System.out.println(player1 + " plays " + p1Card.toString() + ", " + player2 +" plays " + p2Card.toString());
        if (p1Card.getValue() > p2Card.getValue()){
            System.out.println(player1 + " wins the hand");
            p1Deck.addDeck(pool);

        }
        else if(p2Card.getValue() > p1Card.getValue()){
            System.out.println(player2 + " wins the hand");
            p2Deck.addDeck(pool);

        }
        else{
            System.out.println("It's war!");
            commenceWar(3);
        }
    }

    //helper for commenceWar, flips cards from the individual player's deck, adds them to the pool and communicates
    //to System.out what the value was.
    private Card flip(String player, Deck playerDeck, int flips){
        Card flipCard = null;
        for (int i = flips; i > 0; i--) {
            pool.addCard(flipCard = playerDeck.getCard());
            if (i > 1) {
                System.out.println(player + " adds " + flipCard.toString());
            }
        }
        return flipCard;
    }

    private boolean winner(){
        boolean isWinner;
        if (p1Deck.isEmpty()){
            System.out.println(player2 + " is victorious.");
            isWinner = true;
        }
        else if(p2Deck.isEmpty()){
            System.out.println(player1 + " is victorious.");
            isWinner = true;
        }
        else{isWinner = false;}
        return isWinner;
    }
}
