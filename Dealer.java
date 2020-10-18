import java.util.Stack;

public class Dealer extends Player{

    private String name;

    public Dealer(){
        super();
        name = "Dealer";
    }

    //update game - dealer rules
    public void updateDealer(ManageGame game){

        if(this.getScore() > this.getScore()) {
            this.setEndGame(true);
            return;
        }
        if(this.getScore()>=17 && this.getScore() > game.getGambler().getScore() || game.getGambler().getScore() > 21 ) {
            this.setEndGame(true);
            return;
        }
        while(this.getEndGame() == false && this.getScore() < game.getGambler().getScore()){
            Card pulled = game.getDeckOfCards().dealCard();
            if(pulled!=null && pulled.getFace() == "Ace")
                this.setNumberOfAces(this.getNumberOfAces()+1);
            this.getHand().add(pulled);
            this.calcScore();

            if(this.getScore() > 21) {
                this.aceToOne();
                if(this.getScore() > 21) {
                    this.setEndGame(true);
                    return;
                }
            }
            if(this.getScore() > game.getGambler().getScore()){
                this.setEndGame(true);
                return;
            }
            if(this.getScore() > 21) {
                this.setEndGame(true);
                return;
            }
            if(this.getScore() < 22 && this.getScore() > game.getGambler().getScore()) {
                this.setEndGame(true);
                return;
            }
        }
        this.setEndGame(true);
    }

    public String getName(){
        return name;
    }
}
