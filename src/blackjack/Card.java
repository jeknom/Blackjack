package blackjack;

public class Card {
	private int rank;
	private String suit;
	
	public Card(int rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public void setRank(int currentRank) {
		this.rank = currentRank;
	}
	
	public String getSuit() {
		return this.suit;
	}
}