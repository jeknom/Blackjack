package blackjack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		getDeck deck;
		deck = new getDeck();
		
		ArrayList<Integer> ranks = deck.getRanks();
		ArrayList<String> suits = deck.getSuits();
		
		ArrayList<Integer> playerHandRanks = new ArrayList<Integer>();
		ArrayList<String> playerHandSuits = new ArrayList<String>();
		
		ArrayList<Integer> dealerHandRanks = new ArrayList<Integer>();
		ArrayList<String> dealerHandSuits = new ArrayList<String>();
		
		int playerTotal=0, dealerTotal=0;
		
		Scanner scn = new Scanner(System.in);

		String userInput = "y";
		while (userInput.equals("y")) {	
			
			playerHandRanks.add(ranks.get(0));
			playerHandSuits.add(suits.get(0));
			playerTotal += ranks.get(0);
			ranks.remove(0);
			suits.remove(0);
			System.out.println("Your total is "+ playerTotal);
			System.out.println("Your hand: ");
			for(int i=0; i<playerHandRanks.size(); i++) {
				System.out.println(playerHandRanks.get(i)+ " " + playerHandSuits.get(i));
			}
			
			if (playerTotal > 21) {
				System.out.println("You lose!");
				System.exit(0);
			}
			System.out.println("Draw a card?");
			userInput = scn.next();
		}
		
		while (dealerTotal < 16 || dealerTotal < playerTotal) {
				dealerHandRanks.add(ranks.get(0));
				dealerHandSuits.add(suits.get(0));
				dealerTotal += ranks.get(0);
				ranks.remove(0);
				suits.remove(0);
		}
		
		System.out.println("Dealer draws:");	
			for(int i=0; i<dealerHandRanks.size(); i++) {
				System.out.println(dealerHandRanks.get(i)+ " " + dealerHandSuits.get(i));
			}
			System.out.println("Dealer total is: "+ dealerTotal);
			
			if (dealerTotal > playerTotal && dealerTotal < 22) {
				System.out.println("Dealer wins...");
			}
			
			if (dealerTotal < playerTotal && playerTotal < 22) {
				System.out.println("You win!");
			}
			
			if (dealerTotal == playerTotal) {
				System.out.println("It's a draw but the house always wins!");
			}
			if (dealerTotal > 21) {
				System.out.print("Dealer lost! ");
			}
			
			if (playerTotal == 21 && playerHandRanks.size() < 3) {
				System.out.println("You got blackjack!");
			}
			
			if (dealerTotal == 21 && dealerHandRanks.size() < 3) {
				System.out.println("Dealers got blackjack! House always wins.");
				System.out.println("Thanks for playing.");
				System.exit(0);
			}
		
	System.out.println("Thanks for playing.");
	scn.close();
	}
}
