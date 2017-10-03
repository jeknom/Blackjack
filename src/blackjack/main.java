package blackjack;
import java.awt.event.*;

public class Main {


	public static void main(String[] args) {
		
		//Player panel and its contents
		Panel playerPanel = new Panel(200,600);
		playerPanel.setTitledBorder("Player");
		
		//Dealer panel and its contents
		Panel dealerPanel = new Panel(200,600);
		dealerPanel.setTitledBorder("Dealer");
		
		//Button panel and its contents
		Panel buttonPanel = new Panel(200,300);
		buttonPanel.setGridLayout(3,2);
		Button drawButton = new Button("Draw", false);
		Button callButton = new Button("Call", false);
		Button exitButton = new Button("Exit", true);
		Button newGameButton = new Button("New game", true);
		Button yesButton = new Button("Yes", false);
		Button noButton = new Button("No", false);
		buttonPanel.addButton(drawButton.getButton());
		buttonPanel.addButton(callButton.getButton());
		buttonPanel.addButton(yesButton.getButton());
		buttonPanel.addButton(noButton.getButton());
		buttonPanel.addButton(newGameButton.getButton());
		buttonPanel.addButton(exitButton.getButton());
		
		//Info panel and its contents
		Panel infoPanel = new Panel(200,300);
		infoPanel.setGridLayout(5,1);
		Label titleLabel = new Label("<html><center><h1>BLACKJACK</h1></center></html>");
		Label infoLabel1 = new Label("");
		Label infoLabel2 = new Label("");
		Label infoLabel3 = new Label("");
		infoLabel1.setCenter();
		infoLabel2.setCenter();
		infoLabel3.setCenter();
		infoPanel.addLabel(titleLabel.getLabel());
		infoPanel.addLabel(infoLabel1.getLabel());
		infoPanel.addLabel(infoLabel2.getLabel());
		infoPanel.addLabel(infoLabel3.getLabel());
		titleLabel.setCenter();
		
		//Bottom panel and its contents
		Panel bottomPanel = new Panel(200,600);
		bottomPanel.setGridLayout(1, 2);
		bottomPanel.addPanel(buttonPanel.getPanel());
		bottomPanel.addPanel(infoPanel.getPanel());
		
		//Add all panels to masterpanel
		Panel masterPanel = new Panel(600,600);
		masterPanel.setGridLayout(3,0);
		masterPanel.addPanel(playerPanel.getPanel());
		masterPanel.addPanel(dealerPanel.getPanel());
		masterPanel.addPanel(bottomPanel.getPanel());
		
		//Finally add this masterpanel to the frame
		Frame frame = new Frame("Blackjack",600,600);
		frame.addPanel(masterPanel.getPanel());
		
		//Game variables
		Scoreboard score = new Scoreboard(0,0);
		Deck deck = new Deck();
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();
		StateChecker state = new StateChecker();
		
		//Action listeners for the buttons
		newGameButton.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawButton.getButton().setEnabled(true);
				callButton.getButton().setEnabled(true);
				newGameButton.getButton().setEnabled(false);
				score.resetAll();
				playerHand.clearHand();
				dealerHand.clearHand();
				playerPanel.removeAndRefresh();
				dealerPanel.removeAndRefresh();
				deck.buildDeck();
				infoLabel1.setText("Player Score " + String.valueOf(score.getPlayerScore()));
				infoLabel2.setText("");
				infoLabel3.setText("");
			}
		});
		
		yesButton.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Case yes: Adds the card with value of 11 and updates labels and frame.
				deck.setCardRank(0, 11);
				playerPanel.addLabel(new Label(String.valueOf(deck.getDeck().get(0))).getLabel());
				playerHand.addCard(deck.getCard());
				drawButton.getButton().setEnabled(true);
				callButton.getButton().setEnabled(true);
				yesButton.getButton().setEnabled(false);
				noButton.getButton().setEnabled(false);
				score.setPlayerScore(playerHand.getTotal());
				infoLabel1.setText("Player Score " + String.valueOf(score.getPlayerScore()));
				infoLabel3.setText("");
				
				//In case of an overdraw
				if(score.getPlayerScore() > 21) {
					infoLabel3.setText("You overdrew, and lost!");
					drawButton.getButton().setEnabled(false);
					callButton.getButton().setEnabled(false);
					newGameButton.getButton().setEnabled(true);
				}
				frame.pack();
			}
		});
		
		noButton.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Case no: Just adds the card as is and updates labels and frame.
				playerPanel.addLabel(new Label(String.valueOf(deck.getDeck().get(0))).getLabel());
				playerHand.addCard(deck.getCard());
				drawButton.getButton().setEnabled(true);
				callButton.getButton().setEnabled(true);
				yesButton.getButton().setEnabled(false);
				noButton.getButton().setEnabled(false);
				score.setPlayerScore(playerHand.getTotal());
				infoLabel1.setText("Player Score " + String.valueOf(score.getPlayerScore()));
				infoLabel3.setText("");
				
				//In case of an overdraw
				if(score.getPlayerScore() > 21) {
					infoLabel3.setText("You overdrew, and lost!");
					drawButton.getButton().setEnabled(false);
					callButton.getButton().setEnabled(false);
					newGameButton.getButton().setEnabled(true);
				}
				frame.pack();
			}
		});
		
		drawButton.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Adds all but aces
				if(score.getPlayerScore() < 22 && deck.getCardRank(0) != 1) {
					playerPanel.addLabel(new Label(String.valueOf(deck.getDeck().get(0))).getLabel());	
					playerHand.addCard(deck.getCard());
					score.setPlayerScore(playerHand.getTotal());
					infoLabel1.setText("Player Score " + String.valueOf(score.getPlayerScore()));
				}
				
				//Adds aces
				if(score.getPlayerScore() < 22 && deck.getCardRank(0) == 1) {
					drawButton.getButton().setEnabled(false);
					callButton.getButton().setEnabled(true);
					yesButton.getButton().setEnabled(true);
					noButton.getButton().setEnabled(true);
					infoLabel3.setText("Next card is an ACE! Change its value to 11?");
				}
				
				//In case of an overdraw
				if(score.getPlayerScore() > 21) {
					infoLabel3.setText("You overdrew, and lost!");
					drawButton.getButton().setEnabled(false);
					callButton.getButton().setEnabled(false);
					newGameButton.getButton().setEnabled(true);
				}
				frame.pack();
			}
		});
		
		callButton.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawButton.getButton().setEnabled(false);
				callButton.getButton().setEnabled(false);
				yesButton.getButton().setEnabled(false);
				noButton.getButton().setEnabled(false);
				while(score.getDealerScore() < 16) {
					dealerPanel.addLabel(new Label(String.valueOf(deck.getDeck().get(0))).getLabel());
					dealerHand.addCard(deck.getCard());
					score.setDealerScore(dealerHand.getTotal());
					infoLabel2.setText("Dealer Score " + String.valueOf(score.getDealerScore()));
					frame.pack();
				}
				if(state.getWinner(score.getPlayerScore(), score.getDealerScore(), playerHand.getHand().size(), dealerHand.getHand().size()) == "player") {
					infoLabel3.setText("You win!");
					newGameButton.getButton().setEnabled(true);
				}
				if(state.getWinner(score.getPlayerScore(), score.getDealerScore(), playerHand.getHand().size(), dealerHand.getHand().size()) == "dealer") {
					infoLabel3.setText("Dealer wins!");
					newGameButton.getButton().setEnabled(true);
				}
				if(state.getWinner(score.getPlayerScore(), score.getDealerScore(), playerHand.getHand().size(), dealerHand.getHand().size()) == "draw") {
					infoLabel3.setText("House always wins.");
					newGameButton.getButton().setEnabled(true);
				}
			}
		});
		
		exitButton.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}