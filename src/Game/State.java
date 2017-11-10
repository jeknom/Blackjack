package Game;

public class State {
	private int playerTotal, playerBet, playerMoney=100, dealerTotal, decks;
	private String state = "go", playerName = "Anonymous";
	
	//Player name
	public String getName() {
		return this.playerName;
	}
	
	public void setName(String name) {
		this.playerName = name;
	}
	
	//Decks
	public int getDecks() {
		return this.decks;
	}
	
	public void setDecks(int decks) {
		this.decks = decks;
	}
	
	//Player total
	public int playerTotal() {
		return this.playerTotal;
	}
	public void addPlayerTotal(int amount) {
		this.playerTotal += amount;
	}
	
	//Player bet
	public int playerBet() {
		return this.playerBet;
	}
	public void setBet(int bet) {
		this.playerBet = bet;
	}
	
	//Player money
	public int playerMoney() {
		return this.playerMoney;
	}
	public void addPlayerMoney(int amount) {
		this.playerMoney += amount;
	}
	public void subtractPlayerMoney(int amount) {
		this.playerMoney -= amount;
	}
	
	//Dealer total
	public int dealerTotal() {
		return this.dealerTotal;
	}
	
	public void addDealerTotal(int amount) {
		this.dealerTotal += amount;
	}
	
	//New round
	public void scoreWipe() {
		this.playerTotal = 0;
		this.dealerTotal = 0;
		this.state = "go";
	}
	
	//New game
	public void wipeALL() {
		this.playerTotal = 0;
		this.dealerTotal = 0;
		this.playerBet = 0;
		this.playerMoney = 100;
		this.state = "go";
	}
	
	//Get winner
	public int getWinner(int psize, int dsize) {
		int wincase = 0;
		
		//Wincase 0 = player win
		//Wincase 1 = player blackjack
		//Wincase 2 = dealer win
		//Wincase 3 = dealer blackjack
		//Wincase 4 = draw
		
		//Player
		if (playerTotal > dealerTotal && playerTotal < 22) {
			wincase = 0;
		}
		if (playerTotal == 21 && playerTotal > dealerTotal && psize < 3) {
			wincase = 1;
		}
		
		//Dealer
		if (dealerTotal > playerTotal && dealerTotal < 22) {
			wincase = 2;
		}
		if(dealerTotal == 21 && dealerTotal > playerTotal && dsize < 3) {
			wincase = 3;
		}
		if (dealerTotal > 21) {
			wincase = 0;
		}
		if (dealerTotal == playerTotal) {
			wincase = 4;
		}
		return wincase;
	}
	
	//Get state
	public String getStatus() {
		if (this.playerTotal > 21) {
			System.out.println("Player overdrew!");
			this.state = "overdrew";
		}
		return this.state;
	}
}