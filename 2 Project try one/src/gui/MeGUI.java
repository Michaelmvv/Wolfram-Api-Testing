package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import saveing.Save;
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

	Color background;
	Color buttonColor;

	JButton delfiles = new JButton("Clear Files");

	// color choosing obj.
	JFrame colorFrame = new JFrame("Color Choser");
	JColorChooser cc = new JColorChooser();
	JButton goToColorFrame = new JButton("Open color choser");
	JPanel colorPanel = new JPanel();
	JButton CDone = new JButton("Done");

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

		settingsPanel.setLayout(new GridLayout(5, 1, 10, 10));
		JLabel apikey = new JLabel("Api Key:");
		apikey.setPreferredSize(new Dimension(100, 10));
		settingsPanel.add(apikey);
		newApiKey.setPreferredSize(new Dimension(100, 50));
		settingsPanel.add(newApiKey);
		settingsPanel.add(new JLabel("Background color:"));
		settingsPanel.add(goToColorFrame);
		settingsPanel.add(delfiles);
		delfiles.addActionListener(this);

		panel.add(quaryField);
		panel.add(SerchButton);
		SerchButton.addActionListener(this);
		newApiKey.addActionListener(this);

		// color chozer
		goToColorFrame.addActionListener(this);
		CDone.addActionListener(this);
		colorFrame.add(colorPanel);
		colorPanel.add(cc);
		colorPanel.add(CDone);

		colorFrame.pack();

		quaryField.addKeyListener(this);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() instanceof JButton) {
			JButton buttonPressed = (JButton) arg0.getSource();
			if (buttonPressed == SerchButton) {
				tabbedPane.setSelectedIndex(1);
				try {
					text.setText(new Save().Search(quaryField.getText()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				frame.pack();
			}
			if (buttonPressed == newApiKey) {
				try {
					new KeyFile().ResetAPI();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			if (buttonPressed == goToColorFrame) {
				colorFrame.setVisible(true);
			}
			if (buttonPressed == CDone) {

				colorFrame.setVisible(false);
				background = cc.getColor();

				frame.setBackground(background);
				panel.setBackground(background);
				quaryField.setBackground(background);
				SerchButton.setBackground(background);
				text.setBackground(background);
				tabbedPane.setBackground(background);
				settingsPanel.setBackground(background);
				newApiKey.setBackground(background);
				goToColorFrame.setBackground(background);
				frame.pack();

			}
			if (buttonPressed == delfiles) {
				new Save().DelFiles();
				
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

		if (arg0.getKeyCode() == KeyEvent.VK_ENTER
				|| arg0.getKeyCode() == KeyEvent.VK_TAB) {
			tabbedPane.setSelectedIndex(1);
			try {
				text.setText(new Save().Search(quaryField.getText()));
			} catch (Exception e) {
				e.printStackTrace();
			}
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
