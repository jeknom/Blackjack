package blackjack;

public class Card {
private int rank;
private String suit;

	//Constructor
	public Card(int rank, String suit) {
		this.rank = rank; this.suit = suit;
	}
	
	//Get methods
	public int getRank() {
		return this.rank;
	}
	
	public String getSuit() {
		return this.suit;
	}
	
	//Set methods
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	//Other
	@Override
	public String toString() {
		return String.valueOf(this.rank)+" "+this.suit;
	}
}
