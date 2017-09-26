package blackjack;
import java.util.ArrayList;
import java.util.Collections;

public class getDeck {
	
	public ArrayList<Integer> getRanks() {
		ArrayList<Integer> getRanks = new ArrayList<Integer>();
		for (int i = 1; i < 5; i++) {	
			for(int i2 = 1; i2 < 11; i2++) {
				getRanks.add(i2);
			}
			for(int i2 = 1; i2 < 5; i2++) {
				getRanks.add(10);
			}
		}
	Collections.shuffle(getRanks);
	return getRanks;
	}
	
	public ArrayList<String> getSuits(){
		ArrayList<String> getSuits = new ArrayList<String>();
		
		for (int i = 1; i < 14; i++) {
			getSuits.add("hearts");
		}
		for (int i = 1; i < 14; i++) {
			getSuits.add("diamonds");
		}
		for (int i = 1; i < 14; i++) {
			getSuits.add("clubs");
		}
		for (int i = 1; i < 14; i++) {
			getSuits.add("spades");
		}
	Collections.shuffle(getSuits);
	return getSuits;
	}
}
