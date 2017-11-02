package Game;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;

public class Deck {
private ArrayList<Card> deck;
	
	public Deck() {
		this.deck = new ArrayList<Card>();
	}
	
	public void clear() {
		this.deck.clear();
	}
	
	public void addDeck() {
		//Small suits
		for (int i=2; i<11; i++) {
			this.deck.add(new Card(i, String.valueOf(i), new ImageIcon("src/img/hearts.png")));
			this.deck.add(new Card(i, String.valueOf(i), new ImageIcon("src/img/diamonds.png")));
			this.deck.add(new Card(i, String.valueOf(i), new ImageIcon("src/img/clubs.png")));
			this.deck.add(new Card(i, String.valueOf(i), new ImageIcon("src/img/spades.png")));
		}
		
		//Big hearts
		this.deck.add(new Card(10, "J", new ImageIcon("src/img/hearts.png")));
		this.deck.add(new Card(10, "Q", new ImageIcon("src/img/hearts.png")));
		this.deck.add(new Card(10, "K", new ImageIcon("src/img/hearts.png")));
		this.deck.add(new Card(1, "A", new ImageIcon("src/img/hearts.png")));
		//Big diamonds
		this.deck.add(new Card(10, "J", new ImageIcon("src/img/diamonds.png")));
		this.deck.add(new Card(10, "Q", new ImageIcon("src/img/diamonds.png")));
		this.deck.add(new Card(10, "K", new ImageIcon("src/img/diamonds.png")));
		this.deck.add(new Card(1, "A", new ImageIcon("src/img/diamonds.png")));
		//Big clubs
		this.deck.add(new Card(10, "J", new ImageIcon("src/img/clubs.png")));
		this.deck.add(new Card(10, "Q", new ImageIcon("src/img/clubs.png")));
		this.deck.add(new Card(10, "K", new ImageIcon("src/img/clubs.png")));
		this.deck.add(new Card(1, "A", new ImageIcon("src/img/clubs.png")));
		//Big spades
		this.deck.add(new Card(10, "J", new ImageIcon("src/img/spades.png")));
		this.deck.add(new Card(10, "Q", new ImageIcon("src/img/spades.png")));
		this.deck.add(new Card(10, "K", new ImageIcon("src/img/spades.png")));
		this.deck.add(new Card(1, "A", new ImageIcon("src/img/spades.png")));
		
		//Deck shuffler
		Collections.shuffle(this.deck);
	}
	
	public Card getCard() {
		Card holder = this.deck.get(0);
		this.deck.remove(0);
		return holder;
	}
	
	public ArrayList<Card> getDeck(){
		return this.deck;
	}
}
