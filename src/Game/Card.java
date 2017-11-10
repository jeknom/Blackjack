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
private JLabel label2;
private JPanel panel;
	
	//Card constructor
	public Card(int rank, String suit, ImageIcon suitImg) {
		System.out.println("Card: "+rank+" "+suit);
		this.rank = rank;
		this.suit = suit;
		this.suitImg = suitImg;
		//
		//Visual look of a card
		this.label = new JLabel(suit);
		this.label.setIcon(this.suitImg);
		this.label.setHorizontalTextPosition(SwingConstants.RIGHT);
		
		this.label2 = new JLabel(suit, SwingConstants.RIGHT);
		this.label2.setIcon(this.suitImg);
		this.label2.setHorizontalTextPosition(SwingConstants.LEFT);
		
		this.panel = new JPanel(new BorderLayout());
		this.panel.setPreferredSize(new Dimension(86,112));
		this.panel.setBorder(BorderFactory.createEtchedBorder());
		this.panel.setBackground(Color.white);
		this.panel.add(this.label, BorderLayout.NORTH);
		switch(suit) {
		case "A":
			this.panel.add(new JLabel(this.suitImg));
			break;
		case "K":
			this.panel.add(new JLabel(new ImageIcon("img/k.png")));
			break;
		case "Q":
			this.panel.add(new JLabel(new ImageIcon("img/q.png")));
			break;
		case "J":
			this.panel.add(new JLabel(new ImageIcon("img/j.png")));
			break;
		}
		this.panel.add(this.label2, BorderLayout.SOUTH);
		
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
	public JPanel getPanel(File hit) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(hit));
			clip.start();
		}
		catch(Exception a) {
			System.out.println("Sound could not be played");
		}
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
