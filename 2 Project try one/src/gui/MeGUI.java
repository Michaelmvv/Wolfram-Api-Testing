package gui;

import getInfoFromApi.QueryIt;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import apiKey.KeyFile;

public class MeGUI implements ActionListener, KeyListener {
	JFrame frame = new JFrame("Michaels Wolframalfa Project");
	JPanel panel = new JPanel();
	JTextField quaryField = new JTextField("Pi");
	JButton SerchButton = new JButton("Go");
	JTextPane text = new JTextPane();
	JTabbedPane tabbedPane = new JTabbedPane();
	JPanel settingsPanel = new JPanel();
	JButton newApiKey = new JButton("New Api Key");

	public void makeGui() {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		quaryField.setPreferredSize(new Dimension(300, 40));
		SerchButton.setPreferredSize(new Dimension(100, 40));
		frame.setSize(800, 300);

		text.setBackground(panel.getBackground());

		frame.add(tabbedPane);

		tabbedPane.add(panel, 0);
		tabbedPane.add(text, 1);
		tabbedPane.add(settingsPanel, 2);

		tabbedPane.setTitleAt(0, "Input");
		tabbedPane.setTitleAt(1, "Output");
		tabbedPane.setTitleAt(2, "Settings");

		settingsPanel.add(newApiKey);
		panel.add(quaryField);
		panel.add(SerchButton);
		SerchButton.addActionListener(this);
		newApiKey.addActionListener(this);

		quaryField.addKeyListener(this);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton buttonPressed = (JButton) arg0.getSource();

		if (buttonPressed == SerchButton) {
			tabbedPane.setSelectedIndex(1);
			text.setText(QueryIt.qs(quaryField.getText(), "plaintext"));
			frame.pack();
		}
		if (buttonPressed == newApiKey) {
			System.out.println("HI");
			try {
				new KeyFile().ResetAPI();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

		if (arg0.getKeyCode() == KeyEvent.VK_ENTER||arg0.getKeyCode() == KeyEvent.VK_TAB) {
			tabbedPane.setSelectedIndex(1);
			text.setText(QueryIt.qs(quaryField.getText(), "plaintext"));
			frame.pack();

		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
