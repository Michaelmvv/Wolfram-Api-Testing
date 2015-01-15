package starter;

import javax.swing.JOptionPane;

import com.wolfram.alpha.WAQueryResult;

import getInfoFromApi.WolfApi;

public class Start {
	public static boolean LogResult = true;

	public static void main(String[] args) {
		WolfApi api = new WolfApi("plaintext");
		api.startEngine();
		WAQueryResult r = api.quary(JOptionPane.showInputDialog("Quary?"));
		

	}
}
