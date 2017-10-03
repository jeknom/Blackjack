package blackjack;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
private ArrayList<Card> deck;
	
	//Constructor
	public Deck() {
		this.deck = new ArrayList<Card>();
	}
	//Build
	public void buildDeck() {
		this.deck.clear();
		
		for (int i=2; i < 11; i++) {
			this.deck.add(new Card(i, "Hearts"));
			this.deck.add(new Card(i, "Diamonds"));
			this.deck.add(new Card(i, "Clubs"));
			this.deck.add(new Card(i, "Spades"));
		}
		
		this.deck.add(new Card(10, "Jack of Hearts"));
		this.deck.add(new Card(10, "Queen of Hearts"));
		this.deck.add(new Card(10, "King of Hearts"));
		this.deck.add(new Card(1, "Ace of Hearts"));
		
		this.deck.add(new Card(10, "Jack of Diamonds"));
		this.deck.add(new Card(10, "Queen of Diamonds"));
		this.deck.add(new Card(10, "King of Diamonds"));
		this.deck.add(new Card(1, "Ace of Diamonds"));
		
		this.deck.add(new Card(10, "Jack of Clubs"));
		this.deck.add(new Card(10, "Queen of Clubs"));
		this.deck.add(new Card(10, "King of Clubs"));
		this.deck.add(new Card(1, "Ace of Clubs"));
		
		this.deck.add(new Card(10, "Jack of Spades"));
		this.deck.add(new Card(10, "Queen of Spades"));
		this.deck.add(new Card(10, "King of Spades"));
		this.deck.add(new Card(1, "Ace of Spades"));
		
		Collections.shuffle(this.deck);
	}
	
	//Get methods
	public ArrayList<Card> getDeck() {
		return this.deck;
	}
	
	public int getCardRank(int column) {
		return deck.get(column).getRank();
	}
	
	public String getCardSuit(int column) {
		return deck.get(column).getSuit();
	}
	
	public String getCardString(int column) {
		return String.valueOf(deck.get(column));
	}
	
	public Card getCard(){
		Card holder;
		holder = deck.get(0);
		deck.remove(0);
		return holder;
	}
	
	//Set methods
	public void setCardRank(int column, int rank) {
		deck.get(column).setRank(rank);
	}
	
	public void setCardSuit(int column, String suit) {
		deck.get(column).setSuit(suit);
	}
}
