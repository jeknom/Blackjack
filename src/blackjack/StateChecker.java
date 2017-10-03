package blackjack;

public class StateChecker {
	
	public String getWinner(int playerScore, int dealerScore, int playerDrawTimes, int dealerDrawTimes) {
		String winner="";
		
		//Dealer win states
		if(playerScore < dealerScore && dealerScore < 22) {
			winner = "dealer";
		}
		
		if(dealerScore == playerScore && dealerDrawTimes < playerDrawTimes) {
			winner = "dealer";
		}
		
		if(playerScore > 21) {
			winner = "dealer";
		}
		
		//Player win states
		if(dealerScore < playerScore && playerScore < 22) {
			winner = "player";
		}
		
		if(dealerScore == playerScore && playerDrawTimes < dealerDrawTimes) {
			winner = "player";
		}
		
		if(dealerScore > 21) {
			winner = "player";
		}
		
		//Draw states
		if (dealerScore == playerScore && playerDrawTimes > 2 && dealerDrawTimes > 2) {
			winner = "draw";
		}
		
		return winner;
	}
}
