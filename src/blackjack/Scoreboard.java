package blackjack;

public class Scoreboard {
private String newline = System.getProperty("line.separator");
private int playerScore, dealerScore;
	
	//Constructor
	public Scoreboard(int playerScore, int dealerScore) {
		this.playerScore = playerScore;
		this.dealerScore = dealerScore;
	}
	
	//Add methods
	public void addToPlayer(int points) {
		this.playerScore += points;
	}
	
	public void addToDealer(int points) {
		this.dealerScore += points;
	}
	
	//Get methods
	public int getPlayerScore() {
		return this.playerScore;
	}
	
	public int getDealerScore() {
		return this.dealerScore;
	}
	
	//Set methods
	public void setPlayerScore(int score) {
		this.playerScore = score;
	}
	
	public void setDealerScore(int score) {
		this.dealerScore = score;
	}
	
	//Other
	public void resetAll() {
		this.playerScore = 0;
		this.dealerScore = 0;
	}
	
	//toString
	@Override
	public String toString() {
		return "Player score is "+this.playerScore + newline+ "Dealer score is "+this.dealerScore+" ";
	}
}
