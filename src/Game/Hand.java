package Game;
import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> hand;
	
	public Hand() {
		this.hand = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	public int size() {
		return this.hand.size();
	}
	
	public ArrayList<Card> getHand(){
		return this.hand;
	}
}