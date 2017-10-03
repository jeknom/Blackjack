package blackjack;
import javax.swing.*;

public class Label {
private JLabel label;
private String text;
private ImageIcon img;
	
	//Constructor
	public Label(String text) {
		this.text = text;
		this.label = new JLabel(this.text);
		
		//Setting a card image to this label is its text contains a suit.
		if(this.text.contains("Hearts")) {
			this.label.setIcon(new ImageIcon("src/img/hearts.jpg"));
		}
		if(this.text.contains("Diamonds")) {
			this.label.setIcon(new ImageIcon("src/img/diamonds.jpg"));
		}
		if(this.text.contains("Clubs")) {
			this.label.setIcon(new ImageIcon("src/img/clubs.jpg"));
		}
		if(this.text.contains("Spades")) {
			this.label.setIcon(new ImageIcon("src/img/spades.jpg"));
		}
		
		//Setting text position, in this case under the card image.
		this.label.setHorizontalTextPosition(JLabel.CENTER); //<- Using only this will draw text in center of the card image.
		//this.label.setVerticalTextPosition(JLabel.BOTTOM);
	}
	
	//Set methods
	public void setText(String text) {
		this.text = text;
		this.label.setText(this.text);
	}
	
	public void setImg(ImageIcon img) {
		this.img = img;
		this.label.setIcon(this.img);
	}
	
	public void setCenter() {
		this.label.setHorizontalAlignment(SwingConstants.CENTER);
		this.label.setVerticalAlignment(SwingConstants.CENTER);
	}
	
	//Add methods
	public void addText(String text) {
		this.text += text;
		this.label.setText(this.text);
	}
	
	//Get methods
	public String getText() {
		return this.text;
	}
	
	public ImageIcon getImg() {
		return this.img;
	}
	
	public JLabel getLabel() {
		return this.label;
	}
	
}
