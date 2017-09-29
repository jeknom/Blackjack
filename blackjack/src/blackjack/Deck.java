package blackjack;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
private ArrayList<Card> deck = new ArrayList<Card>();
	
	//Deck cleaner and builder
	public void buildDeck() {
		//Clean up
		for (int i=0; i < deck.size(); i++) {
			deck.remove(0);
		}
		
		//Add cards
		
		//Hearts
		for (int i=1; i < 11; i++) {
			deck.add(new Card(i,"Hearts"));
		}
		for (int i=0; i < 4; i++) {
			deck.add(new Card(10,"Hearts"));
		}
		//Diamonds
		for (int i=1; i < 11; i++) {
			deck.add(new Card(i,"Diamonds"));
		}
		for (int i=0; i < 4; i++) {
			deck.add(new Card(10,"Diamonds"));
		}
		//Clubs
		for (int i=1; i < 11; i++) {
			deck.add(new Card(i,"Clubs"));
		}
		for (int i=0; i < 4; i++) {
			deck.add(new Card(10,"Clubs"));
		}
		//Spades
		for (int i=1; i < 11; i++) {
			deck.add(new Card(i,"Spades"));
		}
		for (int i=0; i < 4; i++) {
			deck.add(new Card(10,"Spades"));
		}
	Collections.shuffle(deck);
	}
	
	//Card draw method
	public Card getCard() {
		Card newCard = this.deck.get(0);
		this.deck.remove(0);
		return newCard;
	}
	
	public int getCurrent() {
		return deck.get(0).getRank();
	}
	
	public void setCurrent(int cR) {
		this.deck.get(0).setRank(cR);
	}
	
	
	public String toString() {
		String printDeck="";
		for (int i=0; i<deck.size();i++) {
			printDeck += deck.get(i).getRank() + " " + deck.get(i).getSuit() + " ";
		}
	return printDeck;
	}
}