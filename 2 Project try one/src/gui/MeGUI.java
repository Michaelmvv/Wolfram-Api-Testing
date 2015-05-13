package gui;

import help.Help;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import saveing.Save;
import apiKey.KeyFile;

public class MeGUI implements ActionListener, KeyListener {
	String name = "Michaels Wolframalfa Project";
	JFrame frame = new JFrame(name);
	JPanel panel = new JPanel();
	JTextField quaryField = new JTextField("Type your quarry here!");
	JButton SerchButton = new JButton("Go");
	public static JTextPane text = new JTextPane();
	JScrollPane ScrollText = new JScrollPane(text);

	JCheckBox caps = new JCheckBox("Case Sensitive");

	JButton help = new JButton();

	JTabbedPane tabbedPane = new JTabbedPane();
	JPanel settingsPanel = new JPanel();
	JButton newApiKey = new JButton("Reset api key");

	Color background;
	Color buttonColor;

	JButton delfiles = new JButton("Clear Application Files");

	// color choosing obj.
	JFrame colorFrame = new JFrame("Change background color");
	JColorChooser cc = new JColorChooser();
	JButton goToColorFrame = new JButton("Open color choser");
	JPanel colorPanel = new JPanel();
	JButton CDone = new JButton("Done");
	Save saver = new Save();

	// loading screen
	JFrame loading = new JFrame("Loading");
//	JPanel loadingPanel = new JPanel();
//	JLabel loadingtxt = new JLabel("LOADING......");

	public MeGUI() {
		makeGui();
	}

	public void makeGui() {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		quaryField.setPreferredSize(new Dimension(300, 40));
		SerchButton.setPreferredSize(new Dimension(100, 40));
		frame.setSize(800, 300);

		text.setBackground(panel.getBackground());
		text.setEditable(false);

		frame.add(tabbedPane);

		ScrollText.setPreferredSize(new Dimension(1000, 800));

		tabbedPane.add(panel, 0);
		tabbedPane.add(ScrollText, 1);
		tabbedPane.add(settingsPanel, 2);

		tabbedPane.setTitleAt(0, "Input");
		tabbedPane.setTitleAt(1, "Output");
		tabbedPane.setTitleAt(2, "Settings");

		settingsPanel.setLayout(new GridLayout(5, 1, 10, 10));
		newApiKey.setPreferredSize(new Dimension(100, 50));
		settingsPanel.add(newApiKey);
		settingsPanel.add(goToColorFrame);
		settingsPanel.add(delfiles);
		delfiles.addActionListener(this);
		panel.add(help);
		help.addActionListener(this);
		help.setText("Help");
		help.setPreferredSize(new Dimension(100, 40));
		panel.add(quaryField);
		panel.add(SerchButton);
		panel.add(caps);

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
		tabbedPane.setSelectedIndex(0);
		frame.setVisible(true);

		// loading
//		loading.add(loadingPanel);
//		loadingPanel.add(loadingtxt);
//		loadingtxt.setFont(new Font(loadingtxt.getFont().getName(), Font.PLAIN,
//				100));
		loading.pack();
		loading.setVisible(false);
		loading.setAlwaysOnTop(true);

	}

	public void go() {
		loading.setSize(frame.getSize());
		loading.setLocationRelativeTo(frame);
		loading.setVisible(true);
		tabbedPane.setSelectedIndex(1);
		frame.pack();
		try {
			text.setText(saver.Search(quaryField.getText(), caps.isSelected()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		loading.setVisible(false);

		frame.pack();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() instanceof JButton) {
			JButton buttonPressed = (JButton) arg0.getSource();
			if (buttonPressed == SerchButton) {
				go();
			}
			if (buttonPressed == newApiKey) {
				if (JOptionPane
						.showConfirmDialog(
								frame,
								"Bad Api keys will render the application unusable!!! You Have been warned. Continure?") == JOptionPane.YES_OPTION) {
					try {

						new KeyFile().ResetAPI();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
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
				delfiles.setBackground(background);
				caps.setBackground(background);
				ScrollText.setBackground(background);
				help.setBackground(background);
				frame.pack();

			}
			if (buttonPressed.equals(delfiles)) {
				if (JOptionPane
						.showConfirmDialog(null, "Get rid of the files?") == JOptionPane.YES_OPTION) {
					saver.DelFiles();
				}

			}
			if (buttonPressed == help) {
				new Help();
				tabbedPane.setSelectedIndex(1);
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER
				|| arg0.getKeyCode() == KeyEvent.VK_TAB) {
			go();

		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
