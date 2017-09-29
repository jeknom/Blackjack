package blackjack;
import java.util.ArrayList;

public class Hand {
private ArrayList<Card> Cards = new ArrayList<Card>();

	public void addCard(Card newCard) {
		Cards.add(newCard);
	}
	
	public int getTotal() {
		int total=0;
		for (int i=0; i<Cards.size(); i++) {
			total += Cards.get(i).getRank();
		}
		return total;
	}
	
	public void clearHand() {
			Cards.clear();
	}
	
	public int getCurrent() {
		int current=0;
		current = Cards.get(0).getRank();
		return current;
	}
	
	public String toString() {
		String printHand="";
		for (int i=0; i<Cards.size();i++) {
			printHand += Cards.get(i).getRank() + " " + Cards.get(i).getSuit() + " ";
		}
	return printHand;
	}
}