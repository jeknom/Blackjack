package blackjack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainClass {
	public static void main(String[] args) {
		Deck newDeck = new Deck();
		newDeck.buildDeck();
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();
		
		JPanel yesNo = new JPanel(new GridLayout(2,0));
		JButton yes = new JButton("Yes");
		JButton no = new JButton("No");
		yesNo.add(yes);
		yesNo.add(no);
		yesNo.setVisible(false);
		
		JPanel gamePanel = new JPanel(new GridLayout(5,0));
		JLabel gameState = new JLabel("Welcome to blackjack");
		JLabel playerPoints = new JLabel("Player points: "+String.valueOf(playerHand.getTotal()));
		JLabel dealerPoints = new JLabel("Dealer points: "+String.valueOf(playerHand.getTotal()));
		JLabel playerHandLabel = new JLabel("Player hand: "+String.valueOf(playerHand));
		playerHandLabel.setBorder(BorderFactory.createTitledBorder("PlayerHand"));
		JLabel dealerHandLabel = new JLabel("Dealer hand: "+String.valueOf(dealerHand));
		dealerHandLabel.setBorder(BorderFactory.createTitledBorder("DealerHand"));
		gamePanel.add(gameState);
		gamePanel.add(playerPoints);
		gamePanel.add(playerHandLabel);
		gamePanel.add(dealerPoints);
		gamePanel.add(dealerHandLabel);
		
		JButton newGameButton = new JButton("New game");
		newGameButton.setVisible(false);
		JButton drawButton = new JButton("New game");
		JButton stopButton = new JButton("Stop");
		JButton exitButton = new JButton("Exit");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(drawButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(exitButton);
		buttonPanel.add(newGameButton);
		
		JPanel masterPanel = new JPanel(new GridLayout(3,0));
		masterPanel.add(gamePanel);
		masterPanel.add(buttonPanel);
		masterPanel.add(yesNo);
		
		JFrame frame = new JFrame("Blackjack");
		frame.getContentPane().add(masterPanel);
		frame.setVisible(true);
		frame.pack();
		
		drawButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(playerHand.getTotal() < 22 && newDeck.getCurrent() != 1) {
					gameState.setText("Game is on!");
					drawButton.setText("Draw a card");
					playerHand.addCard(newDeck.getCard());
					playerPoints.setText("Player points : "+String.valueOf(playerHand.getTotal()));
					dealerPoints.setText("Dealer points : "+String.valueOf(dealerHand.getTotal()));
					playerHandLabel.setText("Player hand: "+String.valueOf(playerHand));
					dealerHandLabel.setText("Dealer hand: "+String.valueOf(dealerHand));
					frame.pack();
				}
				if(playerHand.getTotal() < 22 && newDeck.getCurrent() == 1) {
					drawButton.setVisible(false);
					stopButton.setVisible(false);
					gameState.setText("Next card is an ace. Do you wish to set its value to 11?");
					frame.pack();
					yesNo.setVisible(true);
				}
				if(playerHand.getTotal() > 21) {
					drawButton.setVisible(false);
					stopButton.setVisible(false);
					gameState.setText("You lose...");
					newGameButton.setVisible(true);
				}
				
			}
		});
		stopButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				drawButton.setVisible(false);
				stopButton.setVisible(false);
				
				while(dealerHand.getTotal() < 16) {
					dealerHand.addCard(newDeck.getCard());
				}
				dealerPoints.setText("Dealer points : "+String.valueOf(dealerHand.getTotal()));
				dealerHandLabel.setText("Dealer hand: "+String.valueOf(dealerHand));
				if(dealerHand.getTotal() > 21) {
					gameState.setText("Dealer loses!");
				}
				else if(dealerHand.getTotal() > playerHand.getTotal() && dealerHand.getTotal() < 22){
					gameState.setText("Dealer wins!");
				}
				else if(dealerHand.getTotal() < playerHand.getTotal() && playerHand.getTotal() < 22){
					gameState.setText("You win!");
				}else {
					gameState.setText("You lose maby?");
				}
				
				newGameButton.setVisible(true);
			}
		});
		
		exitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		yes.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			newDeck.setCurrent(11);
			playerHand.addCard(newDeck.getCard());
			playerPoints.setText("Player points : "+String.valueOf(playerHand.getTotal()));
			dealerPoints.setText("Dealer points : "+String.valueOf(dealerHand.getTotal()));
			playerHandLabel.setText("Player hand: "+String.valueOf(playerHand));
			dealerHandLabel.setText("Dealer hand: "+String.valueOf(dealerHand));
			yesNo.setVisible(false);
			drawButton.setVisible(true);
			stopButton.setVisible(true);
			frame.pack();
			}
		});
		
		no.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newDeck.setCurrent(1);
				playerHand.addCard(newDeck.getCard());
				playerPoints.setText("Player points : "+String.valueOf(playerHand.getTotal()));
				dealerPoints.setText("Dealer points : "+String.valueOf(dealerHand.getTotal()));
				playerHandLabel.setText("Player hand: "+String.valueOf(playerHand));
				dealerHandLabel.setText("Dealer hand: "+String.valueOf(dealerHand));
				yesNo.setVisible(false);
				drawButton.setVisible(true);
				stopButton.setVisible(true);
				frame.pack();
			}
		});
		
		newGameButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				playerHand.clearHand();
				dealerHand.clearHand();
				playerPoints.setText("Player points : "+String.valueOf(playerHand.getTotal()));
				dealerPoints.setText("Dealer points : "+String.valueOf(dealerHand.getTotal()));
				playerHandLabel.setText("Player hand: "+String.valueOf(playerHand));
				dealerHandLabel.setText("Dealer hand: "+String.valueOf(dealerHand));
				gameState.setText("Game is on!");
				newGameButton.setVisible(false);
				drawButton.setVisible(true);
				stopButton.setVisible(true);
				frame.pack();
			}
		});
	}
}