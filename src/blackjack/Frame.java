package blackjack;
import javax.swing.*;
import java.awt.*;

public class Frame {
private JFrame frame;
private String frameText;
private int frameHeight, frameWidth;

	public Frame(String frameText, int frameWidth, int frameHeight) {
		this.frameText = frameText; this.frameHeight = frameHeight; this.frameWidth = frameWidth;
		this.frame = new JFrame(this.frameText);
		this.frame.setPreferredSize(new Dimension(this.frameWidth, this.frameHeight));
		this.frame.pack();
		this.frame.setVisible(true);
	}
	//Set methods
	public void setTitle(String newTitle) {
		this.frameText = newTitle;
		this.frame.setTitle(this.frameText);
		this.frame.pack();
	}
	
	public void setSize(int frameHeight, int frameWidth) {
		this.frameHeight = frameHeight; this.frameWidth = frameWidth;
		this.frame.setPreferredSize(new Dimension (this.frameWidth, this.frameHeight));
		this.frame.pack();
	}
	
	public void setVisible(boolean yesNo) {
		this.frame.setVisible(yesNo);
	}
	
	public void setResizeable(boolean yesNo) {
		this.frame.setResizable(yesNo);
	}
	//Add methods
	public void addPanel(JPanel newPanel) {
		this.frame.getContentPane().add(newPanel);
		this.frame.pack();
	}
	//Do methods
	public void pack() {
		this.frame.pack();
	}
	
	//Get methods
	public JFrame getFrame() {
		return this.frame;
	}
}