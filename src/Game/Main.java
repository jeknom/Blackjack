package Game;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.SQLException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Main {
	
	//Create gui
	static Gui gui = new Gui();
	
	//Create deck
	static Deck deck = new Deck();
	private static boolean deckCheck = true;
	
	//Create hands
	static Hand playerHand = new Hand();
	static Hand dealerHand = new Hand();
	
	//State checker
	static State gameState = new State();
	
	//Database
	static Database db = new Database();
	
	public static void main(String[] args) throws Exception {
		//Player name and decks
		startQ();
		
		gui.setButtons(false, false);
		//ActionListeners
		gui.fileNewGame().addActionListener(new ActionListener(){
		    @Override
		    public void actionPerformed(ActionEvent e){
		    	newGame();
		    }
		});
		
		gui.fileHelp().addActionListener(new ActionListener(){
		    @Override
		    public void actionPerformed(ActionEvent e){
		        System.out.println("Opening help screen!");
		    }
		});
		
		gui.fileExit().addActionListener(new ActionListener(){
		    @Override
		    public void actionPerformed(ActionEvent e){
		    	System.out.println("Shutting down...");
		        System.exit(0);
		    }
		});
		
		gui.hitButton().addActionListener(new ActionListener(){
		    @Override
		    public void actionPerformed(ActionEvent e){
		    	hit();
		    }
		});
		
		gui.callButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				call();
			}
		});
		

		
		System.out.println("Action listeners online");
	}
	
	//New game method
	private static void newGame() {
		gui.fileNewGame().setEnabled(false);
		if (deckCheck == true) {
			gameState.wipeALL();
			for (int i=0; i<gameState.getDecks(); i++) {
				deck.addDeck();
				System.out.println("Deck added.");
			}
		deckCheck = false;
		}
		
		if (gameState.playerMoney() > 0) {
	        System.out.println("Starting a new game!");
	        gameState.scoreWipe();
	        playerHand.getHand().clear();
	        dealerHand.getHand().clear();
	        gui.clearBoard();
		        int bet = gui.betSlider().getValue();
		        gameState.setBet(bet);
		        if (gameState.playerMoney() > bet) {
		        	gameState.subtractPlayerMoney(bet);
		        	System.out.println("Bet set to " + bet);
		        }else {
		        	gameState.setBet(gameState.playerMoney());
		        	System.out.println("Not enough money. Bet set to: " + gameState.playerMoney());
		        	gameState.subtractPlayerMoney(gameState.playerMoney());
		        }
	        gui.setButtons(true, true);
	        gui.clearBoard();
	        gameState.addDealerTotal(deck.getDeck().get(0).getRank());
	        dealerHand.addCard(deck.getCard());
	        gui.dealerPaddPanel(dealerHand.getHand().get(0).getPanel());
	        gui.setScoreText(gameState.playerTotal(), gameState.playerBet(),gameState.playerMoney(), gameState.dealerTotal());
    	}else {
    		System.out.println("You ain't got money, honey.");
    		gui.announcer("src/img/nomoney.png", "New game",true,true);
    		gameState.wipeALL();
    		nrBtnActionListener();
    		lbBtnActionListener();
    	}
	}
	
	//Card draw
	private static void hit() {
		if (deck.getDeck().size() > 0) {
			File hit = new File("sound/hit.wav");
			if (gameState.getStatus().equals("go")) {
		    	try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(hit));
					clip.start();
				}
				catch(Exception a) {
					System.out.println("Sound could not be played");
				}
		    	
		    	if (deck.getDeck().get(0).getRank() == 1) {
		    		if (gui.convertAce() == true) {
		    			deck.getDeck().get(0).setRank(11);
		    			System.out.println("Converted ace value to 11..");
		    		}else {
		    			System.out.println("Keeping the same value");
		    		}
		    	}
		    	
		    	gameState.addPlayerTotal(deck.getDeck().get(0).getRank());
		    	playerHand.addCard(deck.getCard());
		    	gui.playerP().removeAll();
		    	for (int i=0; i < playerHand.getHand().size(); i++) {
		    		gui.playerPaddPanel(playerHand.getHand().get(i).getPanel());
		    	}
		    	if (!gameState.getStatus().equals("go")) {
		    		gui.setButtons(false, false);
		    	}
	    	}
	    	gui.setScoreText(gameState.playerTotal(), gameState.playerBet(),gameState.playerMoney(), gameState.dealerTotal());
	    	if (gameState.getStatus().equals("overdrew")) {
	    		gui.announcer("src/img/overdraw.png", "New round", true, true);
	    		nrBtnActionListener();
	    		lbBtnActionListener();
	    		gui.fileNewGame().setEnabled(true);
	    	}
		} else {
			System.out.println("Out of cards.. moving to dealer.");
			call();
		}
	}
	
	//Stop draw
	private static void call() {
		gui.setButtons(false, false);
		if (deck.getDeck().size() > 0) {
			if (gameState.getStatus().equals("go")) {
				gui.setButtons(false, false);
				while(gameState.dealerTotal() < 17 && deck.getDeck().size() > 0) {
					gameState.addDealerTotal(deck.getDeck().get(0).getRank());
					dealerHand.addCard(deck.getCard());
					gui.setScoreText(gameState.playerTotal(), gameState.playerBet(),gameState.playerMoney(),gameState.dealerTotal());
					gui.dealerP().removeAll();
						for (int i=0; i < dealerHand.getHand().size(); i++) {
							gui.dealerPaddPanel(dealerHand.getHand().get(i).getPanel());
				    	}
				}
				if (deck.getDeck().size() > 0) {
					String lbimg="";
					switch(gameState.getWinner(playerHand.size(), dealerHand.size())){
					case 0:
						lbimg = "src/img/victory.png";
						gameState.addPlayerMoney(gameState.playerBet() * 2);
						gui.setScoreText(gameState.playerTotal(), gameState.playerBet(),gameState.playerMoney(),gameState.dealerTotal());
						break;
					case 1:
						lbimg = "src/img/bj.png";
						gameState.addPlayerMoney(gameState.playerBet() * 4);
						gui.setScoreText(gameState.playerTotal(), gameState.playerBet(),gameState.playerMoney(),gameState.dealerTotal());
						break;
					case 2:
						lbimg = "src/img/defeat.png";
						break;
					case 3:
						lbimg = "src/img/defeat.png";
						break;
					}
					gui.announcer(lbimg, "New round",true,true);
					nrBtnActionListener();
					lbBtnActionListener();
					gui.fileNewGame().setEnabled(true);
				} else {
					gui.announcer("src/img/end.png", "New game", true, true);
					nrBtnActionListener();
					lbBtnActionListener();
				}
			}
		} else {
			try {
			db.insert(gameState.getName(), gameState.playerMoney(), gameState.getDecks());
			} catch(SQLException ex){
				System.out.println(ex.getMessage());
			}
			gui.announcer("src/img/end.png", "New game", true, true);
			nrBtnActionListener();
			lbBtnActionListener();
		}
	}
	
	//Announcer action listener
	private static void nrBtnActionListener() {
		gui.nrButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (deck.getDeck().size() != 0) {
					newGame();
				} else {
					startQ();
				}
			}
		});
	}
	
	//Start listeners
	private static void startQ() {
		gui.fileNewGame().setEnabled(false);
		gui.startQ();
		gui.goBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameState.setName(gui.getName());
				System.out.println("Game will be played with " + gui.getDecks() + " decks");
				gameState.setDecks(gui.getDecks());
				deckCheck = true;
				newGame();
			}
		});
	}
	
	//Leaderboard action listener
	private static void lbBtnActionListener(){
		gui.lbButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				gui.leaderboards(db.getPlayers());
				searchBtn();
				} catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
	}
	
	//Highscore search
	private static void searchBtn() {
		gui.search().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Searching for player " + gui.searchArea());
					gui.leaderboards(db.searchName(gui.searchArea()));
					System.out.println("Search complete");
					searchBtn();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
		});
	}
}