/**
 * @author Alexander Gavrilov
 *
 * Class ManageGame implements BlackJack between two players : dealer and player.
 * the class generates a shuffled stack of cards for the game from the DeckOfCards and Card classes, and give two cards for each player.
 *
 * the player sees the cards in his hand and the number of points he has,and the player sees only one card of the dealer,after the
 * player choose not to take more cards the dealer take cards, and after every player took his cards they reveal the cards,the player that
 * closer to 21 points win the round. if a player points greater the 21 he automatically lose the round.
 *
 */

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Iterator;

public class ManageGame {

    private Dealer dealer;
    private Gambler gambler;
    private DeckOfCards stack;

    public ManageGame() {
        dealer = new Dealer();
        gambler = new Gambler();
        stack = new DeckOfCards();//stack of cards

        gambler.pullCard(stack.dealCard());
        gambler.pullCard(stack.dealCard());
        dealer.pullCard(stack.dealCard());
        dealer.pullCard(stack.dealCard());

        gambler.calcScore();
        dealer.calcScore();
    }

    public DeckOfCards getDeckOfCards(){
        return stack;
    }
    public Dealer getDealer() {
        return dealer;
    }
    public Gambler getGambler() {
        return gambler;
    }

    //after every card the player or the dealer take update all the data related to each one of them and display it
    public void updateGameStatus(Player player) {

        if(player.getName().equals("Gambler")) {
            gambler.updateGambler(this);
        }
        else if(player.getName().equals("Dealer")) {
            dealer.updateDealer(this);
        }
    }

    //display the outcome of the round
    public void endGame() {

        if(dealer.getScore() < 22 && gambler.getScore() > 21)
            JOptionPane.showMessageDialog(null,resultToString() + "\n" + "Casino wins!");

        if(gambler.getScore() < dealer.getScore() && gambler.getScore()<22 && dealer.getScore()<22)
            JOptionPane.showMessageDialog(null,resultToString() + "\n" + "Casino wins!");

        if(gambler.getScore() < 22 && dealer.getScore() > 21)
            JOptionPane.showMessageDialog(null,resultToString() + "\n" + "Player wins!");

        if((gambler.getScore() > dealer.getScore()) && gambler.getScore()<22 && dealer.getScore()<22 )
            JOptionPane.showMessageDialog(null,resultToString() + "\n" + "Player wins!");

        if(gambler.getScore() == dealer.getScore())
            JOptionPane.showMessageDialog(null,resultToString() + "\n" + "Its a Draw!");

        return;
    }

    private String resultToString() {

        return "Player score :" + gambler.getScore() + "\n" + gambler.getHand() + "\n" +
                "Dealer Score :" + dealer.getScore() + "\n" + dealer.getHand() + "\n";
    }

    //initialize the game
    public static void init() {

        ManageGame game = new ManageGame();
        while( game.gambler.getScore() < 22 && game.dealer.getScore() < 22
                && (game.gambler.getEndGame() == false || game.dealer.getEndGame() == false)) {

            if (game.gambler.getEndGame()==false) {
                game.updateGameStatus(game.gambler);
            }else{
                game.updateGameStatus(game.dealer);
            }
        }
        game.endGame();
    }

    //if the player wants to play another game return true else false
    public static boolean newGame() {

        int startNewGame = JOptionPane.showConfirmDialog(null, "New Game?");

        if(startNewGame == 0)
            return true;
        return false;
    }


    public static void main(String[] args) {

        while(newGame()) {
            init();
        }
    }
}
