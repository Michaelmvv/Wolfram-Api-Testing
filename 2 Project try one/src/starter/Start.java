package starter;
import java.awt.Image;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

import javax.swing.UIManager;

import gui.MeGUI;

public class Start {
	public static boolean LogResult = false;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			System.setProperty(
					"com.apple.mrj.application.apple.menu.about.name",
					"Super Kool App");
			try {
				Class util = Class.forName("com.apple.eawt.Application");
				Method getApplication = util.getMethod("getApplication", new Class[0]);
			    Object application = getApplication.invoke(util);
			    Class params[] = new Class[1];
			    params[0] = Image.class;
			    Method setDockIconImage = util.getMethod("setDockIconImage", params);
			    URL url = Start.class.getClassLoader().getResource("Light.png");
			    Image image = Toolkit.getDefaultToolkit().getImage(url);
			    setDockIconImage.invoke(application, image);
			} catch (ClassNotFoundException e) {
			    // log exception
			} catch (NoSuchMethodException e) {
			    // log exception
			} catch (InvocationTargetException e) {
			    // log exception
			} catch (IllegalAccessException e) {
			    // log exception
			}
	   
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
