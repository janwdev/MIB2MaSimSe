package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
		gbc.weightx = gbc.weighty = 1;

		JLabel lbProgName = new JLabel("Programm Name");
		JLabel lbVersion = new JLabel("Version");
		JLabel lbDescription = new JLabel("Beschreibung");

		JTextField tfProgName = new JTextField(Constants.PROGNAME);
		JTextField tfVersion = new JTextField(Constants.VERSION);
		JTextField tfDescription = new JTextField(Constants.DESCRIPTION);

		tfProgName.setEditable(false);
		tfVersion.setEditable(false);
		tfDescription.setEditable(false);

		this.add(lbProgName, gbc);
		gbc.gridx = 1;
		this.add(tfProgName, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(lbVersion, gbc);
		gbc.gridx = 1;
		this.add(tfVersion, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(lbDescription, gbc);
		gbc.gridx = 1;
		this.add(tfDescription, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(createLicencesSection(), gbc);
	}

	private JPanel createLicencesSection() {
		JPanel licences = new JPanel();
		// TODO einfuegen
		//licences.add(createLicences());
		return licences;
	}

	private JScrollPane createLicences() {

		StringBuilder apacheBuilder = new StringBuilder();
		apacheBuilder.append("Uses Apache 2 Licence\n");
		apacheBuilder.append("FlatLaf - https://www.formdev.com/flatlaf/\n");
		//TODO aus Datei einlesen, Path zur Datei stimmt noch nicht
		try (BufferedReader br = new BufferedReader(new FileReader(getClass().getResource("assets/Apache.txt").getFile()))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				apacheBuilder.append(sCurrentLine).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String apacheLicence = apacheBuilder.toString();

		JTextArea taLicences = new JTextArea();
		taLicences.setEditable(false);

		taLicences.append(apacheLicence + "\n");
		taLicences.append("\n" + "--------------------" + "\n\n");

		JScrollPane scrollPane = new JScrollPane(taLicences);

		return scrollPane;
	}

}
