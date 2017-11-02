package Game;

public class Player {
	private String name, date;
	private int score, decks;
	
	public Player(String name, int score, int decks, String date) {
		this.name = name;
		this.score = score;
		this.decks = decks;
		this.date = date;
	}
	
	public void setName (String name){
		this.name = name;
	}
	
	public void setScore (int score) {
		this.score = score;
	}
	
	public void setDecks (int decks) {
		this.decks = decks;
	}
	
	public void setDate (String date) {
		this.date = date;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getDecks() {
		return this.decks;
	}
	
	public String getDate() {
		return this.date;
	}
}
