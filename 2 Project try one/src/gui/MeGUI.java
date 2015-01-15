package gui;

import getInfoFromApi.QueryIt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MeGUI implements ActionListener {
	JFrame frame = new JFrame("Michaels Wolframalfa Project");
	JPanel panel = new JPanel();
	JTextField quaryField = new JTextField("Pi");
	JButton SerchButton = new JButton("Go");

	public void makeGui() {
		frame.add(panel);
		panel.add(quaryField);
		panel.add(SerchButton);
		SerchButton.addActionListener(this);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton buttonPressed = (JButton) arg0.getSource();

		if (buttonPressed == SerchButton) {
			System.out
					.println(QueryIt.query(quaryField.getText(), "plaintext"));
		}

	}

}
