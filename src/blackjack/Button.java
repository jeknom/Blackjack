package blackjack;
import javax.swing.*;

public class Button {
private JButton button;
private String buttonText;
	
	public Button(String text, boolean yesNo) {
		this.buttonText = text;
		this.button = new JButton(text);
		this.button.setBorderPainted(true);
		this.button.setFocusPainted(false);
		this.button.setContentAreaFilled(false);
		this.button.setEnabled(yesNo);
	}
	
	//Set methods
	public void setText(String text) {
		this.buttonText = text;
		this.button.setText(this.buttonText);
	}
	
	//Get methods
	public JButton getButton() {
		return this.button;
	}
}
