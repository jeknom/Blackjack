package Game;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.SQLException;
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
	
	//Sounds
	private static File hit = new File("sound/hit.wav");
	
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
		
		gui.fileOptions().addActionListener(new ActionListener(){
		    @Override
		    public void actionPerformed(ActionEvent e){
		    	gui.options();
		    }
		});
		
		gui.optApply().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				db.setDb(gui.optDbArea());
				db.setUn(gui.optUnArea());
				db.setPw(gui.optPwArea());
				
				gui.optSetInfo("Changes applied!");
			}
		});
		
		gui.optBack().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.options();
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
		if (deckCheck == true) {
			deck.clear();
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
		        if (gameState.playerMoney() > gui.betSlider().getValue()) {
		        	gameState.setBet(gui.betSlider().getValue());
		        	gameState.subtractPlayerMoney(gui.betSlider().getValue());
		        	System.out.println("Bet set to: " + gameState.playerBet());
		        } else {
		        	System.out.println("Not enough money. Going all in...");
		        	gameState.setBet(gameState.playerMoney());
		        	gameState.subtractPlayerMoney(gameState.playerMoney());
		        	System.out.println("Bet set to " + gameState.playerBet());
		        }
	        gui.setButtons(true, true);
	        gui.clearBoard();
	        if (deck.getDeck().get(0).getRank() == 1) {
	        	deck.getDeck().get(0).setRank(11);
	        }
	        gameState.addDealerTotal(deck.getDeck().get(0).getRank());
	        dealerHand.addCard(deck.getCard());
	        gui.dealerPaddPanel(dealerHand.getHand().get(0).getPanel(hit));
	        gui.setScoreText(gameState.playerTotal(), gameState.playerBet(),gameState.playerMoney(), gameState.dealerTotal());
    	}
	}
	
	//Card draw
	private static void hit() {
		if (deck.getDeck().size() > 0) {
			if (gameState.getStatus().equals("go")) {
		    	if (deck.getDeck().get(0).getRank() == 1 && gameState.playerTotal() < 11) {
		    		deck.getDeck().get(0).setRank(11);
		    	}
		    	
		    	gameState.addPlayerTotal(deck.getDeck().get(0).getRank());
		    	playerHand.addCard(deck.getCard());
		    	gui.playerP().removeAll();
		    	for (int i=0; i < playerHand.getHand().size(); i++) {
		    		gui.playerPaddPanel(playerHand.getHand().get(i).getPanel(hit));
		    	}
		    	if (!gameState.getStatus().equals("go")) {
		    		gui.setButtons(false, false);
		    	}
	    	}
	    	gui.setScoreText(gameState.playerTotal(), gameState.playerBet(),gameState.playerMoney(), gameState.dealerTotal());
	    	if (gameState.getStatus().equals("overdrew")) {
	    		gui.announcer("img/overdraw.png", "New round", true, true, true, false);
	    		nr();
	    		lb();
	    		gui.fileNewGame().setEnabled(true);
	    		if (gameState.playerMoney() < 1) {
	    			gui.announcer("img/nomoney.png", "New game",true,true,false,true);
					nr();
					lb();
	    		}
	    	} else if (gameState.playerTotal() == 21 && playerHand.size() < 3) {
	    		gui.setButtons(false, false);
	    		gui.announcer("img/bj.png", "New round", true, true, true,false);
				gameState.addPlayerMoney(gameState.playerBet() * 3);
				gui.setScoreText(gameState.playerTotal(), gameState.playerBet(),gameState.playerMoney(),gameState.dealerTotal());
				nr();
	    		lb();
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
		Thread thread = new Thread(() -> {
		boolean drawing = true;
		if (deck.getDeck().size() > 0) {
			if (gameState.getStatus().equals("go")) {
				gui.setButtons(false, false);
				while(drawing == true && deck.getDeck().size() > 0) {
					if (deck.getDeck().get(0).getRank() == 1 && gameState.dealerTotal() < 11) {
						deck.getDeck().get(0).setRank(11);
					}
					if (gameState.dealerTotal() < 17) {
						gameState.addDealerTotal(deck.getDeck().get(0).getRank());
						dealerHand.addCard(deck.getCard());
					}
					else if (gameState.dealerTotal() > 16) {
						for (int i=1; i < dealerHand.getHand().size(); i++) {
					        try {
					            Thread.sleep(300);
					            gui.dealerPaddPanel(dealerHand.getHand().get(i).getPanel(hit));
					            Thread.sleep(300);
					        } catch (InterruptedException e) {
					            e.printStackTrace();
					        }
						}
						try {
				            Thread.sleep(800);
				        } catch (InterruptedException e) {
				            e.printStackTrace();
				        }
						gui.setScoreText(gameState.playerTotal(), gameState.playerBet(),gameState.playerMoney(),gameState.dealerTotal());
						drawing = false;
					}
				}
				if (deck.getDeck().size() > 0) {
					String lbimg="";
					switch(gameState.getWinner(playerHand.size(), dealerHand.size())){
					case 0:
						lbimg = "img/victory.png";
						gameState.addPlayerMoney(gameState.playerBet() * 2);
						gui.setScoreText(gameState.playerTotal(), gameState.playerBet(),gameState.playerMoney(),gameState.dealerTotal());
						break;
					case 1:
						lbimg = "img/bj.png";
						gameState.addPlayerMoney(gameState.playerBet() * 4);
						gui.setScoreText(gameState.playerTotal(), gameState.playerBet(),gameState.playerMoney(),gameState.dealerTotal());
						break;
					case 2:
						lbimg = "img/defeat.png";
						break;
					case 3:
						lbimg = "img/defeat.png";
						break;
					case 4:
						lbimg = "img/draw.png";
						gameState.addPlayerMoney(gameState.playerBet());
						gui.setScoreText(gameState.playerTotal(), gameState.playerBet(),gameState.playerMoney(),gameState.dealerTotal());
						break;
					}
					if (gameState.playerMoney() < 1) {
						gui.announcer("img/nomoney.png", "New game",true,true,false,true);
						nr();
						lb();
					} else if (gameState.playerMoney() > 0) {
					gui.announcer(lbimg, "New round",true,true,true,false);
					nr();
					lb();
					gui.fileNewGame().setEnabled(true);
					}
				} else {
					gui.announcer("img/end.png", "New game",true,true,false,true);
					try {
						db.insert(gameState.getName(), gameState.playerMoney(), gameState.getDecks());
					} catch(SQLException ex){
						System.out.println(ex.getMessage());
					}
					gui.announcer("img/end.png", "New game",true,true,false,true);
					nr();
					lb();
				}
				}
		} else {
			try {
			db.insert(gameState.getName(), gameState.playerMoney(), gameState.getDecks());
			} catch(SQLException ex){
				System.out.println(ex.getMessage());
			}
			gui.announcer("img/end.png", "New game",true,true,false,true);
			nr();
			lb();
		}
		});
		thread.start();
	}
	
	//Announcer action listener
	private static void nr() {
		gui.nrButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (deck.getDeck().size() > 0 && gameState.playerMoney() > 0) {
					newGame();
				} else {
					startQ();
				}
			}
		});
	}
	
	
	//Leaderboard action listener
	private static void lb(){
		gui.lbButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				gui.leaderboards(db.getPlayers());
				search();
				nr();
				} catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
	}
	
	//Start listeners
	private static void startQ() {
		gui.fileNewGame().setEnabled(false);
		gui.startQ();
		gameState.wipeALL();
		gui.setScoreText(gameState.playerTotal(), gameState.playerBet(),gameState.playerMoney(),gameState.dealerTotal());
		gui.goBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameState.setName(gui.getName());
				gameState.setDecks(gui.getDecks());
				System.out.println("Game will be played with " + gui.getDecks() + " decks");
				deckCheck = true;
				newGame();
			}
		});
	}
	
	//Highscore search
	private static void search() {
		gui.search().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Searching for player " + gui.searchArea());
					gui.leaderboards(db.searchName(gui.searchArea()));
					System.out.println("Search complete");
					search();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
		});
	}
}