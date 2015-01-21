package gui;

import getInfoFromApi.QueryIt;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class MeGUI implements ActionListener {
	JFrame frame = new JFrame("Michaels Wolframalfa Project");
	JPanel panel = new JPanel();
	JTextField quaryField = new JTextField("Pi");
	JButton SerchButton = new JButton("Go");
	JTextPane text = new JTextPane();
	JTabbedPane tabbedPane = new JTabbedPane();

	public void makeGui() {
		quaryField.setPreferredSize(new Dimension(200, 40));
		SerchButton.setPreferredSize(new Dimension(100, 40));
		frame.setSize(800, 300);

		frame.add(tabbedPane);

		tabbedPane.add(panel, 0);
		tabbedPane.add(text, 1);
		tabbedPane.setTitleAt(0, "Input");
		tabbedPane.setTitleAt(1, "Output");

		panel.add(quaryField);
		panel.add(SerchButton);
		SerchButton.addActionListener(this);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton buttonPressed = (JButton) arg0.getSource();

		if (buttonPressed == SerchButton) {
			text.setText(QueryIt.qs(quaryField.getText(), "plaintext"));
			frame.pack();
		}

	}

}
