package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import app.Constants;
import app.Control;

public class MainGUI  extends JFrame{
	private Control control;
	
	public MainGUI(Control control) {
		this.control = control;
		this.setTitle(Constants.PROGNAME);
		this.setSize(300,300); //TODO aendern
		this.setLayout(new BorderLayout());
		
		this.add(createSouthPanel(), BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	private JPanel createSouthPanel() {
		JPanel southPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = gbc.weighty = 1;
		
		JButton btShowInfo = new JButton("Info");
		btShowInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new InfoDialog();
			}
		});
		southPanel.add(btShowInfo, gbc);
		
		return southPanel;
	}
}
