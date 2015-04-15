package help;

import gui.MeGUI;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import starter.Start;

public class Help implements WindowListener {
	JFrame frame = new JFrame("Help");
	JPanel panel = new JPanel();
	Dimension size = new Dimension(800, 280);

	public Help() {
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.add(panel);
		frame.addWindowListener(this);
		try {
			BufferedImage inputimg = ImageIO.read(new File(Start.class
					.getClassLoader().getResource("img/input.png").toURI()));
			BufferedImage outputimg = ImageIO.read(new File(Start.class
					.getClassLoader().getResource("img/output.png").toURI()));
			BufferedImage settingsimg = ImageIO.read(new File(Start.class
					.getClassLoader().getResource("img/settings.png").toURI()));
			JLabel input = new JLabel(new ImageIcon(inputimg));
			JLabel output = new JLabel(new ImageIcon(outputimg));
			JLabel settings = new JLabel(new ImageIcon(settingsimg));
			input.setPreferredSize(size);
			output.setPreferredSize(size);
			settings.setPreferredSize(size);
			panel.add(input);
			panel.add(output);
			panel.add(settings);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		frame.setSize(800, 880);

		frame.setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {

	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		if (MeGUI.text.getText().equals("Opening Help.....")
				|| MeGUI.text.getText().equals("Help Window Opend!")) {
			MeGUI.text.setText("Help was opend, But now its closed");
		}

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		MeGUI.text.setText("Help Window Opend!");
	}
}
