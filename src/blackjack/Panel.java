package blackjack;
import javax.swing.*;
import java.awt.*;

public class Panel {
private JPanel panel;
private int panelWidth, panelHeight;
	
	//Constructor
	public Panel(int panelWidth, int panelHeight) {
		this.panelWidth = panelWidth; this.panelHeight = panelHeight;
		this.panel = new JPanel();
		this.panel.setPreferredSize(new Dimension(this.panelWidth, this.panelHeight));
		this.panel.setVisible(true);
	}
	
	//Set methods
	public void setSize(int panelWidth, int panelHeight) {
		this.panelWidth = panelWidth; this.panelHeight = panelHeight;
		this.panel.setPreferredSize(new Dimension(this.panelWidth, this.panelHeight));
	}
	
	public void setTitledBorder(String title) {
		this.panel.setBorder(BorderFactory.createTitledBorder(title));
	}
	
	public void setVisible(boolean yesNo) {
		this.panel.setVisible(yesNo);
	}
	
	public void setGridLayout(int rows, int columns) {
		this.panel.setLayout(new GridLayout(rows,columns));
	}
	
	//Add methods
	public void addPanel(JPanel newPanel) {
		this.panel.add(newPanel);
	}
	
	public void addLabel(JLabel newLabel) {
		this.panel.add(newLabel);
	}
	
	public void addButton(JButton newButton) {
		this.panel.add(newButton);
	}
	
	//Get methods
	public JPanel getPanel() {
		return this.panel;
	}
	
	//Other
	public void removeAndRefresh() {
		this.panel.removeAll();
		this.panel.revalidate();
		this.panel.repaint();
	}
}
