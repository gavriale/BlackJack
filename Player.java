import java.util.ArrayList;
import java.util.Iterator;

public class Player {

    private String name;
    private ArrayList<Card> hand;
    private int score;
    private boolean endGame;
    private int numberOfAces;
    private int acesChangedToOne;

    /*
     * Implementing player
     * */
    public Player(){
        numberOfAces = 0;
        acesChangedToOne = 0;
        score = 0;
        hand = new ArrayList<>();
        endGame = false;
        name = "Player";
    }

    public void pullCard(Card c){
        if(c.getFace()=="Ace"){
            this.numberOfAces++;
        }
        hand.add(c);
    }

    public void calcScore() {

        Iterator iter = hand.iterator();
        score = 0;
        while(iter.hasNext()) {
            Card c = (Card) iter.next();
            if(c!=null)
                score = score + c.cardValue();
        }
        score = score - acesChangedToOne*10;
        if (score>21 && numberOfAces>acesChangedToOne){
            this.aceToOne();
        }
        if(score>21){
            endGame = true;
        }
    }

    //Method that change the value of the ace from 11 to 1 if needed
    public void aceToOne() {
        if( score > 21 && numberOfAces > 0 && numberOfAces!=acesChangedToOne) {
            while(score > 21 && numberOfAces!=acesChangedToOne) {
                this.acesChangedToOne++;
                score = score-10;
            }
        }

     }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getName(){
        return name;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean getEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public int getNumberOfAces() {
        return numberOfAces;
    }

    public void setNumberOfAces(int numberOfAces) {
        this.numberOfAces = numberOfAces;
    }

    public int getAcesChangedToOne() {
        return acesChangedToOne;
    }
}
