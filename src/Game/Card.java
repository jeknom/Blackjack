package Game;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Card {

private int rank;
private String suit;
private ImageIcon suitImg;
private JLabel label;
private JPanel panel;
	
	//Card constructor
	public Card(int rank, String suit, ImageIcon suitImg) {
		System.out.println("Card: "+rank+" "+suit);
		this.rank = rank;
		this.suit = suit;
		this.suitImg = suitImg;
		
		//Visual look of a card
		this.panel = new JPanel(new BorderLayout());
		this.panel.setPreferredSize(new Dimension(64,64));
		this.panel.setBorder(BorderFactory.createEtchedBorder());
		this.panel.setBackground(Color.white);
		
		this.label = new JLabel(suit, SwingConstants.CENTER);
		this.label.setIcon(this.suitImg);
		this.label.setHorizontalTextPosition(SwingConstants.RIGHT);
		this.panel.add(this.label);
		
		//Creating sound files
		File draw = new File("sound/draw.wav");
		
		//Mouse listener for mouse hover
		this.panel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				panel.setBackground(Color.green);
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(draw));
					clip.start();
				}
				catch(Exception a) {
					System.out.println("Error: Sound could not be played");
				}
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				panel.setBackground(Color.white);
			}
		});
	}
	
	//Get methods
	public JPanel getPanel() {
		return this.panel;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public String getSuit() {
		return this.suit;
	}
	
	//Set methods
	public void setRank(int rank) {
		this.rank = rank;
	}
}
