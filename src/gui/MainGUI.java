package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.Constants;
import app.Control;
import objectClasses.Vector;

public class MainGUI extends JFrame {
	private Control control;
	private CenterPanel centerPanel;

	public MainGUI(Control control) {
		this.control = control;
		centerPanel = new CenterPanel();
		
		this.setTitle(Constants.PROGNAME);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		this.add(createSouthPanel(), BorderLayout.SOUTH);
		this.add(createEastPanel(), BorderLayout.EAST);
		this.add(centerPanel, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
		
		this.setMinimumSize(this.getSize());
	}
	
	public void drawVector(Vector v) {
		centerPanel.vectorDrawList.add(new VectorToDraw(v, Constants.COLORVECWAY, 4, 4));
		centerPanel.repaint();
	}
	
	public void clearDrawedVectors() {
		centerPanel.vectorDrawList.clear();
		centerPanel.repaint();
	}

	private JPanel createEastPanel() {
		JPanel eastPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.LINE_START;

		JLabel lbAbflug = new JLabel("Abflug:");
		JComboBox<String> comboBAbflugK = new JComboBox<String>(control.abflugFlughafenStr); // TODO actionlistener
		JButton btEditAbflugK = new JButton("Koordinaten bearbeiten"); // TODO actionlistener
		JLabel lbAbflugK = new JLabel("Koordinaten:");
		
		JLabel lbAnkunft = new JLabel("Ankunft:");
		JComboBox<String> comboBAnkunftK = new JComboBox<String>(control.ankunftFlughafenStr); // TODO actionlistener
		JButton btEditAnkunftK = new JButton("Koordinaten bearbeiten"); // TODO actionlistener
		JLabel lbAnkunftK = new JLabel("Koordinaten:");

		eastPanel.add(lbAbflug, gbc);
		gbc.gridy = 1;
		eastPanel.add(comboBAbflugK, gbc);
		gbc.gridy = 2;
		eastPanel.add(btEditAbflugK, gbc);
		gbc.gridy = 3;
		eastPanel.add(lbAbflugK, gbc);
		
		gbc.gridy = 4;
		eastPanel.add(new JLabel("-------------------"), gbc);
		
		gbc.gridy = 5;
		eastPanel.add(lbAnkunft, gbc);
		gbc.gridy = 6;
		eastPanel.add(comboBAnkunftK, gbc);
		gbc.gridy = 7;
		eastPanel.add(btEditAnkunftK, gbc);
		gbc.gridy = 8;
		eastPanel.add(lbAnkunftK, gbc);
		
		return eastPanel;
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
