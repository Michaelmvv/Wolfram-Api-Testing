package starter;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import gui.MeGUI;

public class Start {
	public static boolean LogResult = false;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			System.setProperty(
					"com.apple.mrj.application.apple.menu.about.name",
					"Michaels Caching Wolfram App");
			try {
				Class util = Class.forName("com.apple.eawt.Application");
				Method getApplication = util.getMethod("getApplication",
						new Class[0]);
				Object application = getApplication.invoke(util);
				Class params[] = new Class[1];
				params[0] = Image.class;
				Method setDockIconImage = util.getMethod("setDockIconImage",
						params);
				URL url = Start.class.getClassLoader().getResource(
						"img/Light.png");
				Image image = Toolkit.getDefaultToolkit().getImage(url);
				setDockIconImage.invoke(application, image);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			File location = new File(Start.class.getProtectionDomain()
					.getCodeSource().getLocation().getPath());
			if (location.getParentFile().list().length > 1) {
				String[] options = { "Yes It's Fine",
						"Whoops. I'll Change That Now" };
				int ret = JOptionPane
						.showOptionDialog(
								null,
								"I've detected that you may "
										+ "not have installed this in the right location.The exe or jar file should "
										+ "be placed in it's own folder with nothing else in it. Are you 100% sure "
										+ "that's what you've done?",
								"Warning", JOptionPane.DEFAULT_OPTION,
								JOptionPane.ERROR_MESSAGE, null, options,
								options[0]);
				if (ret != 0) {
					System.exit(0);
				}
			}
		} catch (Exception e) {
			System.out.println("Happens when running from IDE\n");
			System.out.println("Ignore Next Stack trace if in IDE\n");
			e.printStackTrace();
		}

		new MeGUI();
	}

}
