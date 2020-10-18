import javax.swing.*;
import java.util.Stack;

public class Gambler extends Player {

    private String name;

    public Gambler(){
        super();
        name = "Gambler";
    }

    //update game - gambler rules/logic
    public void updateGambler(ManageGame game){
        switch(JOptionPane.showConfirmDialog(null,
                "this is Players hand:\n" + this.getHand() + "\n" + "Player score:" + this.getScore() + "\n"
                        +"this is Dealers visible Card:\n" + this.getHand().get(0) + "\n"
                        + "\nPull more Cards ?")) {

            //pull more cards
            case 0:
                Card pulled = game.getDeckOfCards().dealCard();
                if(pulled!=null && pulled.getFace() == "Ace")
                    this.setNumberOfAces(this.getNumberOfAces()+1);
                this.pullCard(pulled);
                this.calcScore();

                //if score is over 21 check if the player as aces to save him from losing the game init/main
                if(this.getScore() > 21) {
                    this.aceToOne();
                }
                break;
            case 1:
            case 2:
                this.setEndGame(true);
                break;
        }
    }

    public String getName() {
        return name;
    }
}
