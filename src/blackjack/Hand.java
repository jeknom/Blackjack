package blackjack;
import java.util.ArrayList;

public class Hand {
private ArrayList<Card> hand;

	//Constructor
	public Hand() {
		this.hand = new ArrayList<Card>();
	}
	
	//Get methods
	public ArrayList<Card> getHand() {
		return this.hand;
	}
	
	public int getCardRank(int column) {
		return hand.get(column).getRank();
	}
	
	public String getCardSuit(int column) {
		return hand.get(column).getSuit();
	}
	
	public int getTotal() {
		int total=0;
		for(int i=0; i < hand.size(); i++) {
			total += hand.get(i).getRank();
		}
		return total;
	}
	
	//Set methods
	public void setCardRank(int column, int rank) {
		hand.get(column).setRank(rank);
	}
	
	public void setCardSuit(int column, String suit) {
		hand.get(column).setSuit(suit);
	}
	
	//Add methods
	public void addCard(Card card) {
		this.hand.add(card);
	}
	//Other
	public void clearHand() {
		this.hand.clear();
	}
	
	public void aceChecker() {
		
	}
}
