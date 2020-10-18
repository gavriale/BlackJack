/**
 * DeckOfCards object has getStack method that returns a stack of cards
 * and dealCard method that returns a card from the top of the stack
 */

import java.util.*;

public class DeckOfCards {

    private ArrayList<Card> deck;
    private Stack<Card> stack;
    private int currentCard;
    private static final int NUMBER_OF_CARDS = 52;


    public DeckOfCards() {
        String [] faces = {"Ace","Deuce","Three","Four","Five","Six","Seven","Eight","Nine",
                "Ten","Jack","Queen","King"};

        String [] suits = {"Hearts","Diamonds","Clubs","Spades"};
        this.currentCard = 0;
        this.deck = new ArrayList<Card>();
        this.stack = new Stack<Card>();
        //filling the deck with the 52 cards - we have deck of 52 Cards unshuffled
        for(int count = 0; count < NUMBER_OF_CARDS; count++) {
            deck.add(new Card(faces[count % 13], suits[count / 13]));
        }
        this.shuffle();//shuffle the deck
    }

    /**
     * shuffle shuffling the cards deck in O(n) runtime, after that it fills the Stack with cards
     * ready for the game
     */
    private void shuffle() {
        Collections.shuffle(this.deck);//O(n)
        Card [] pack =  this.deck.toArray(new Card[NUMBER_OF_CARDS]);
        Collections.addAll(this.stack,pack);

    }

    /**
     *
     * @return a card from the top of the stack of cards
     */
    public Card dealCard() {
        Card deal;
        currentCard = 0;
        if(!stack.isEmpty()) {
            deal = new Card(stack.pop());
            return deal;
        }
        else {
            return null;
        }
    }

    public Stack<Card> getStack(){
        return this.stack;
    }
}