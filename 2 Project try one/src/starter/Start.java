package starter;

import javax.swing.UIManager;

import gui.MeGUI;

public class Start {
	public static boolean LogResult = true;

	public static void main(String[] args) {
		if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			System.setProperty(
					"com.apple.mrj.application.apple.menu.about.name",
					"WRA Project");
		}
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		new MeGUI().makeGui();
	}
}
