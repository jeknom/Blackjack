package Game;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;

public class Gui {
	
	private JMenuItem fileNewGame;
	private JMenuItem fileHelp;
	private JMenuItem fileExit;
	private JMenu fileM;
	private JMenuBar mainMB;
	
	private JLabel logoLabel;
	private JLabel playerTotal;
	private JLabel playerBet;
	private JLabel playerMoney;
	private JLabel dealerTotal;
	private JTextArea nameArea;
	private JTextArea searchArea;
	private JComboBox<String> deckArea;
	private JSlider betSlider;
	private JButton goBtn;
	private JButton hitButton;
	private JButton callButton;
	private JButton nrButton;
	private JButton lbButton;
	private JButton search;
	
	private JPanel playerP;
	private JPanel dealerP;
	private JPanel betP;
	private JPanel gameP;
	private JPanel sideBP;
	private JPanel sideP;
	private JPanel mainP;
	private JFrame mainF;
		
		public Gui() {
			
			//Dropdown menu
			this.fileNewGame = new JMenuItem("New game", new ImageIcon("src/img/green.png"));
			this.fileHelp = new JMenuItem("Help", new ImageIcon("src/img/yellow.png"));
			this.fileExit = new JMenuItem("Exit", new ImageIcon("src/img/red.png"));
			
			this.fileM = new JMenu("File");
			this.fileM.add(this.fileNewGame);
			this.fileM.add(this.fileHelp);
			this.fileM.add(this.fileExit);
			
			this.mainMB = new JMenuBar();
			this.mainMB.add(this.fileM);
			
			//Playerpanel
			this.playerP = new JPanel();
			this.playerP.setBorder(BorderFactory.createTitledBorder("Player"));
			
			//Dealerpanel
			this.dealerP = new JPanel();
			this.dealerP.setBorder(BorderFactory.createTitledBorder("Dealer"));
			
			//Bet slider
			this.betSlider = new JSlider(JSlider.HORIZONTAL,0,100,1);
			this.betSlider.setMajorTickSpacing(50);
			this.betSlider.setMinorTickSpacing(10);
			this.betSlider.setPaintTicks(true);
			this.betSlider.setPaintLabels(true);
			this.betSlider.setSnapToTicks(true);	
			this.betSlider.setBorder(BorderFactory.createTitledBorder("Bet"));
			this.betP = new JPanel();
			this.betP.add(betSlider);
			
			
			//Gamepanel
			this.gameP = new JPanel(new GridLayout(2,0));
			this.gameP.setPreferredSize(new Dimension(600,600));
			this.gameP.add(this.playerP);
			this.gameP.add(this.dealerP);
			this.gameP.add(this.betP);
			
			//Side button panel
			this.hitButton = new JButton("Hit");
			this.hitButton.setToolTipText("Draw a card by clicking");
			this.hitButton.setContentAreaFilled(false);
			this.hitButton.setFocusPainted(false);
			
			this.callButton = new JButton("Stand");
			this.callButton.setToolTipText("When you wish to stop");
			this.callButton.setContentAreaFilled(false);
			this.callButton.setFocusPainted(false);
			
			this.sideBP = new JPanel();
			this.sideBP.setPreferredSize(new Dimension(100,100));
			this.sideBP.add(hitButton);
			this.sideBP.add(callButton);
			
			//Sidepanel
			this.logoLabel = new JLabel(new ImageIcon("src/img/bjlogo.png"));
			this.playerTotal = new JLabel(new ImageIcon("src/img/user.png"));
			this.playerTotal.setText("Player total: ");
			this.playerTotal.setHorizontalTextPosition(JLabel.CENTER);
			this.playerTotal.setVerticalTextPosition(JLabel.BOTTOM);
			this.playerBet = new JLabel(new ImageIcon("src/img/coinStack.png"));
			this.playerBet.setText("Player bet: ");
			this.playerBet.setHorizontalTextPosition(JLabel.CENTER);
			this.playerBet.setVerticalTextPosition(JLabel.BOTTOM);
			this.playerMoney = new JLabel(new ImageIcon("src/img/coin.png"));
			this.playerMoney.setText("Player money: ");
			this.playerMoney.setHorizontalTextPosition(JLabel.CENTER);
			this.playerMoney.setVerticalTextPosition(JLabel.BOTTOM);
			this.dealerTotal = new JLabel(new ImageIcon("src/img/house.png"));
			this.dealerTotal.setText("Dealer total: ");
			this.dealerTotal.setHorizontalTextPosition(JLabel.CENTER);
			this.dealerTotal.setVerticalTextPosition(JLabel.BOTTOM);
			this.sideP = new JPanel(new GridLayout(7,0));
			this.sideP.setPreferredSize(new Dimension(250,600));
			this.sideP.setBorder(BorderFactory.createEtchedBorder());
			this.sideP.add(this.logoLabel);
			this.sideP.add(this.sideBP);
			this.sideP.add(this.playerTotal);
			this.sideP.add(this.playerBet);
			this.sideP.add(this.playerMoney);
			this.sideP.add(this.dealerTotal);
			
			//Mainpanel
			this.mainP = new JPanel();
			this.mainP.add(this.sideP);
			this.mainP.add(this.gameP);
			
			//Frame
			this.mainF = new JFrame("Blackjack");
			this.mainF.getContentPane().add(this.mainP);
			this.mainF.setJMenuBar(this.mainMB);
			ImageIcon icon = new ImageIcon("src/img/bjicon.png");
			this.mainF.setIconImage(icon.getImage());
			this.mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.mainF.pack();
			this.mainF.setResizable(false);
			this.mainF.setVisible(true);
		}
		
		//Get
		public JMenuItem fileNewGame() {
			return this.fileNewGame;
		}
		
		public JMenuItem fileHelp() {
			return this.fileHelp;
		}
		
		public JMenuItem fileExit() {
			return this.fileExit;
		}
		
		public JButton hitButton() {
			return this.hitButton;
		}
		
		public JButton callButton() {
			return this.callButton;
		}
		
		public JPanel playerP() {
			return this.playerP;
		}
		
		public JPanel dealerP() {
			return this.dealerP;
		}
		
		public JSlider betSlider() {
			return this.betSlider;
		}
		
		//Add panels
		public void playerPaddPanel(JPanel panel) {
			this.playerP.add(panel);
			this.playerP.invalidate();
			this.playerP.validate();
			this.playerP.repaint();
		}
		
		public void dealerPaddPanel(JPanel panel) {
			this.dealerP.add(panel);
			this.dealerP.invalidate();
			this.dealerP.validate();
			this.dealerP.repaint();
		}
		
		//Score text setter
		public void setScoreText(int playerTotal, int playerBet, int playerMoney, int dealerTotal) {
			this.playerTotal.setText("Player total: " + String.valueOf(playerTotal));
			this.playerBet.setText("Player bet: " + String.valueOf(playerBet));
			this.playerMoney.setText("Player money: " + String.valueOf(playerMoney));
			this.dealerTotal.setText("Dealer total: " + String.valueOf(dealerTotal));
		}
		
		//Board wipe
		public void clearBoard() {
			this.playerP.removeAll();
			this.dealerP.removeAll();
			this.gameP.removeAll();
			this.gameP.setLayout(new GridLayout(2,0));
			this.gameP.add(this.playerP);
			this.gameP.add(this.dealerP);
			this.mainF.invalidate();
			this.mainF.validate();
			this.mainF.repaint();
		}
		
		//Button enabler
		public void setButtons(boolean hit, boolean surrender) {
			this.hitButton.setEnabled(hit);
			this.callButton.setEnabled(surrender);
		}
		
		//Name and amount of decks
		public void startQ() {
			this.gameP.removeAll();
			JLabel nameL = new JLabel(new ImageIcon("src/img/user.png"));
			
			this.nameArea = new JTextArea("Anonymous",1,10);
			this.nameArea.setBorder(BorderFactory.createTitledBorder("Name"));
			JPanel nameP = new JPanel();
			nameP.add(nameL);
			nameP.add(this.nameArea);
			
			JLabel deckL = new JLabel(new ImageIcon("src/img/deck.png"));
			
			String[] di = {"1","2","3","4","5","6","7","8","9"};
			this.deckArea = new JComboBox<String>(di);
			this.deckArea.setPreferredSize(new Dimension(120,50));
			this.deckArea.setBorder(BorderFactory.createTitledBorder("Decks"));
			
			JPanel deckP = new JPanel();
			deckP.add(deckL);
			deckP.add(deckArea);
			
			this.goBtn = new JButton("Go!");
			this.goBtn.setContentAreaFilled(false);
			this.goBtn.setFocusPainted(false);
			
			JPanel startBtnP = new JPanel();
			startBtnP.add(this.goBtn);
			
			JPanel ngp = new JPanel(new GridLayout(5,0));
			
			ngp.setPreferredSize(new Dimension(200,300));
			ngp.add(new JLabel());
			ngp.add(nameP);
			ngp.add(deckP);
			ngp.add(this.betP);
			ngp.add(startBtnP);
			
			this.gameP.setLayout(new BorderLayout());
			this.gameP.add(ngp);
			this.gameP.invalidate();
			this.gameP.validate();
			this.gameP.repaint();
		}
		
		public String getName() {
			return this.nameArea.getText();
		}
		
		public int getDecks() {
			return Integer.parseInt(String.valueOf(this.deckArea.getSelectedItem()));
		}
		
		public JButton goBtn() {
			return this.goBtn;
		}
		
		//Announcer
		public void announcer(String img, String text, boolean nrEnable, boolean lbEnable) {
			this.fileNewGame().setEnabled(false);
			JPanel announcerP = new JPanel(new BorderLayout());
			this.nrButton = new JButton(text);
			this.nrButton.setContentAreaFilled(false);
			this.nrButton.setFocusPainted(false);
			this.nrButton.setEnabled(nrEnable);
			this.lbButton = new JButton("Leaderboards");
			this.lbButton.setContentAreaFilled(false);
			this.lbButton.setFocusPainted(false);
			this.lbButton.setEnabled(lbEnable);
			JPanel aBtns = new JPanel();
			announcerP.add(new JLabel(new ImageIcon(img)));
			aBtns.add(nrButton);
			aBtns.add(lbButton);
			announcerP.add(aBtns,BorderLayout.PAGE_END);
			this.gameP.removeAll();
			this.gameP.setLayout(new GridLayout(3,0));
			this.gameP.add(announcerP);
			this.gameP.add(this.betP);
			this.gameP.add(this.dealerP);
			this.gameP.invalidate();
			this.gameP.validate();
			this.gameP.repaint();
		}
		
		//Leaderboards
		public void leaderboards(ArrayList<Player> player) {
			this.gameP.removeAll();
			
			JPanel hsPanel = new JPanel(new GridLayout(10,0));
			hsPanel.removeAll();
			for (int i=0; i < player.size(); i++) {
				hsPanel.add(new JLabel(String.valueOf(player.get(i).getName() + " | " +player.get(i).getScore()), JLabel.CENTER));
			}
			hsPanel.setPreferredSize(new Dimension(600,400));
			hsPanel.setBorder(BorderFactory.createEtchedBorder());
			
			JPanel searchP = new JPanel();
			this.search = new JButton("Search");
			this.search.setContentAreaFilled(false);
			this.search.setFocusPainted(false);
			this.searchArea = new JTextArea("Name here",1,20);
			this.searchArea.setBorder(BorderFactory.createEtchedBorder());
			searchP.setPreferredSize(new Dimension(500,50));
			searchP.add(this.searchArea);
			searchP.add(this.search);
			
			this.gameP.setLayout(new FlowLayout());
			this.gameP.add(new JLabel(new ImageIcon("src/img/leaderboards.png")));
			this.gameP.add(searchP);
			this.gameP.add(hsPanel);
			this.gameP.invalidate();
			this.gameP.validate();
			this.gameP.repaint();
		}
		
		public JButton search() {
			return this.search;
		}
		
		public String searchArea() {
			return searchArea.getText();
		}
		
		public JButton nrButton() {
			return this.nrButton;
		}
		
		public JButton lbButton() {
			return this.lbButton;
		}
		
		//Ace convert
		public boolean convertAce() {
			boolean yn = false;
			int reply = JOptionPane.showConfirmDialog(mainF, "Next card is an ace, change it's value to 11?","Blackjack",JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION) {
					yn = true;
				}else {
					yn = false;
				}
		return yn;
		}
		
}
