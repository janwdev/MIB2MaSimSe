package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.Constants;

public class InfoDialog extends JDialog {

	public InfoDialog() {
		createInfoDialog();
		this.pack();
		this.setVisible(true);
	}

	private void createInfoDialog() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0;
		gbc.weighty = 0.1;
		
		JLabel lbProgName = new JLabel("Programm Name:");
		JLabel lbVersion = new JLabel("Version:");
		JLabel lbDescription = new JLabel("Beschreibung:");

		JTextField tfProgName = new JTextField(Constants.PROGNAME);
		JTextField tfVersion = new JTextField(Constants.VERSION);
		JTextArea taDescription = new JTextArea(Constants.DESCRIPTION+"\n");
		

		tfProgName.setEditable(false);
		tfVersion.setEditable(false);
		taDescription.setEditable(false);

		this.add(lbProgName, gbc);
		gbc.gridy = 1;
		this.add(lbVersion, gbc);
		gbc.gridy = 2;
		this.add(lbDescription, gbc);
		
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		this.add(tfProgName, gbc);
		gbc.gridy = 1;
		this.add(tfVersion, gbc);
		gbc.gridy = 2;
		this.add(new JScrollPane(taDescription), gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weighty = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(createLicencesSection(), gbc);
	}

	private JPanel createLicencesSection() {
		JPanel licences = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		licences.add(new JLabel("Lizenzen:"), gbc);
		gbc.gridy = 1;
		gbc.weighty = 1;
		
		JTextArea taLicences = new JTextArea();
		taLicences.setEditable(false);
		taLicences.append(Constants.LICENCETEXT);
		
		licences.add(new JScrollPane(taLicences), gbc);
		return licences;
	}

}
