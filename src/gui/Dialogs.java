package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.Constants;
import app.Control;

class AddCoordinatesDialog extends JDialog {

	private Control control;
	private MainGUI gui;

	public AddCoordinatesDialog(MainGUI gui, Control control) {
		super(gui, "Add Coordinates", true);
		this.control = control;
		this.gui = gui;
		createDialog();
		this.pack();
		this.setLocationRelativeTo(gui);
		this.setVisible(true);
	}

	private void createDialog() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = gbc.weighty = 0;

		JComboBox<String> cbNS = new JComboBox<String>(new String[] { "N", "S" });
		JComboBox<String> cbEW = new JComboBox<String>(new String[] { "E", "W" });

		JTextField tfGradNS = new JTextField(3);
		JLabel lbGradNS = new JLabel("°");
		JTextField tfMinNS = new JTextField(5);
		JLabel lbMinNS = new JLabel("'");
		JTextField tfSekNS = new JTextField(5);
		JLabel lbSekNS = new JLabel("''");

		JTextField tfGradEW = new JTextField(3);
		JLabel lbGradEW = new JLabel("°");
		JTextField tfMinEW = new JTextField(5);
		JLabel lbMinEW = new JLabel("'");
		JTextField tfSekEW = new JTextField(5);
		JLabel lbSekEW = new JLabel("''");

		JLabel lbName = new JLabel("Name:");
		JTextField tfName = new JTextField(15);

		JButton btApply = new JButton("Apply");
		btApply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					if (!tfGradNS.getText().isBlank() && !tfMinNS.getText().isBlank() && !tfSekNS.getText().isBlank()
							&& !tfGradEW.getText().isBlank() && !tfMinEW.getText().isBlank()
							&& !tfSekEW.getText().isBlank() && !tfName.getText().isBlank()) {
						double gradNS = Double.parseDouble(tfGradNS.getText());
						double minNS = Double.parseDouble(tfMinNS.getText());
						double sekNS = Double.parseDouble(tfSekNS.getText());
						double dezGradNS = (((sekNS / 60) + minNS) / 60) + gradNS;

						double gradEW = Double.parseDouble(tfGradEW.getText());
						double minEW = Double.parseDouble(tfMinEW.getText());
						double sekEW = Double.parseDouble(tfSekEW.getText());
						double dezGradEW = (((sekEW / 60) + minEW) / 60) + gradEW;

						double phi;
						double theta;

						if (cbNS.getSelectedIndex() == 0) {
							// N
							theta = Math.PI / 2 - (dezGradNS * (Math.PI / 180));
							// } else if (cbNS.getSelectedIndex() == 1) {
						} else {
							// S
							theta = Math.PI / 2 + (dezGradNS * (Math.PI / 180));
						}

						if (cbEW.getSelectedIndex() == 0) {
							// E
							phi = (dezGradEW * (Math.PI / 180));
							// } else if (cbEW.getSelectedIndex() == 1) {
						} else {
							// W
							phi = 2 * Math.PI - (dezGradEW * (Math.PI / 180));
						}

						control.airportStr = Arrays.copyOf(control.airportStr, control.airportStr.length + 1);
						control.airportStr[control.airportStr.length - 1] = tfName.getText().trim();
						control.airportPhi = Arrays.copyOf(control.airportPhi, control.airportPhi.length + 1);
						control.airportPhi[control.airportPhi.length - 1] = phi;
						control.airportThetha = Arrays.copyOf(control.airportThetha,
								control.airportThetha.length + 1);
						control.airportThetha[control.airportThetha.length - 1] = theta;

						control.writeAirportToFile();
						control.reloadAirportCombobox();

						close();
					} else {
						throw new Exception("You got empty Fields!");
					}

				} catch (NumberFormatException ex) {
					ex.printStackTrace();
					gui.showErrorMessage("Wrong Values (Number?)");
					// TODO
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
					gui.showErrorMessage(e1.getMessage());
				}
			}
		});

		JLabel lbHint = new JLabel("To edit coordinates just enter the new coordinates with the same name");

		this.add(tfGradNS, gbc);
		gbc.gridx++;
		this.add(lbGradNS, gbc);
		gbc.gridx++;
		this.add(tfMinNS, gbc);
		gbc.gridx++;
		this.add(lbMinNS, gbc);
		gbc.gridx++;
		this.add(tfSekNS, gbc);
		gbc.gridx++;
		this.add(lbSekNS, gbc);
		gbc.gridx++;
		this.add(cbNS, gbc);

		gbc.gridy++;
		gbc.gridx = 0;

		this.add(tfGradEW, gbc);
		gbc.gridx++;
		this.add(lbGradEW, gbc);
		gbc.gridx++;
		this.add(tfMinEW, gbc);
		gbc.gridx++;
		this.add(lbMinEW, gbc);
		gbc.gridx++;
		this.add(tfSekEW, gbc);
		gbc.gridx++;
		this.add(lbSekEW, gbc);
		gbc.gridx++;
		this.add(cbEW, gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		this.add(lbName, gbc);
		gbc.gridx++;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(tfName, gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(btApply, gbc);
		gbc.gridy++;
		this.add(lbHint, gbc);
	}

	private void close() {
		this.dispose();
		gui.repaint();
	}

}

class InfoDialog extends JDialog {

	public InfoDialog(JFrame parent) {
		super(parent, "Info", true);
		createInfoDialog();
		this.pack();
		this.setLocationRelativeTo(parent);
		this.setVisible(true);
	}

	private void createInfoDialog() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0;
		gbc.weighty = 0.1;

		JLabel lbProgName = new JLabel("Programmname:");
		JLabel lbVersion = new JLabel("Version:");
		JLabel lbDescription = new JLabel("Description:");

		JTextField tfProgName = new JTextField(Constants.PROGNAME);
		JTextField tfVersion = new JTextField(Constants.VERSION);
		JTextArea taDescription = new JTextArea(Constants.DESCRIPTION + "\n");

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
		licences.add(new JLabel("Licences:"), gbc);
		gbc.gridy = 1;
		gbc.weighty = 1;

		JTextArea taLicences = new JTextArea();
		taLicences.setEditable(false);
		taLicences.append(Constants.LICENCETEXT);

		licences.add(new JScrollPane(taLicences), gbc);
		return licences;
	}

}
