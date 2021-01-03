package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import app.Animation;
import app.Constants;
import app.Control;
import objectClasses.Vector;

public class MainGUI extends JFrame {
	private Control control;
	private CenterPanel centerPanel = new CenterPanel();
	private Animation animation = new Animation(this, centerPanel);

	private JComboBox<String> comboBAbflugK;
	private JComboBox<String> comboBAnkunftK;

	private JLabel lbAnkunftK = new JLabel("Koordinaten:");
	private JLabel lbAbflugK = new JLabel("Koordinaten:");

	public MainGUI(Control control) {
		this.control = control;

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

	private void setStartVector() {
		int selIndex = comboBAbflugK.getSelectedIndex();
		Vector v = new Vector(control.abflugFlughafenPhi[selIndex], control.abflugFlughafenThetha[selIndex]);
		centerPanel.startVector = new VectorToDraw(v, Constants.COLORSTARTEND, Constants.STARTENDVECWIDTH,
				Constants.STARTENDVECHEIGHT);
		lbAnkunftK.setText("Koordinaten: Phi: " + (Math.round(v.getPWinkel() * 1000) / 1000.0) + "Thetha: "
				+ (Math.round(v.getOWinkel() * 1000) / 1000.0));

	}

	private void setEndVector() {
		int selIndex = comboBAnkunftK.getSelectedIndex();
		Vector v = new Vector(control.ankunftFlughafenPhi[selIndex], control.ankunftFlughafenThetha[selIndex]);
		centerPanel.endVector = new VectorToDraw(v, Constants.COLORSTARTEND, Constants.STARTENDVECWIDTH,
				Constants.STARTENDVECHEIGHT);
		lbAbflugK.setText("Koordinaten: Phi: " + (Math.round(v.getPWinkel() * 1000) / 1000.0) + "Thetha: "
				+ (Math.round(v.getOWinkel() * 1000) / 1000.0));
	}

	public void drawVector(Vector v) {
		centerPanel.vectorDrawList.add(new VectorToDraw(v, Constants.COLORVECWAY, 4, 4));
		centerPanel.repaint();
	}

	public void clearDrawedVectors() {
		centerPanel.vectorDrawList.clear();
		centerPanel.repaint();
	}

	private void startAnim() {
		clearDrawedVectors();
		setStartVector();
		setEndVector();
		animation.startAnimation();
	}

	private JPanel createEastPanel() {
		JPanel eastPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.LINE_START;

		JLabel lbAbflug = new JLabel("Abflug:");
		comboBAbflugK = new JComboBox<String>(control.abflugFlughafenStr); // TODO actionlistener
		JButton btEditAbflugK = new JButton("Koordinaten bearbeiten"); // TODO actionlistener

		JLabel lbAnkunft = new JLabel("Ankunft:");
		comboBAnkunftK = new JComboBox<String>(control.ankunftFlughafenStr); // TODO actionlistener
		JButton btEditAnkunftK = new JButton("Koordinaten bearbeiten"); // TODO actionlistener

		JButton btStartAnim = new JButton("Animation starten");
		btStartAnim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startAnim();
			}
		});

		eastPanel.add(lbAbflug, gbc);
		gbc.gridy = 1;
		eastPanel.add(comboBAbflugK, gbc);
		gbc.gridy = 2;
		// eastPanel.add(btEditAbflugK, gbc);
		gbc.gridy = 3;
		eastPanel.add(lbAbflugK, gbc);

		gbc.gridy = 4;
		eastPanel.add(new JLabel("-------------------"), gbc);

		gbc.gridy = 5;
		eastPanel.add(lbAnkunft, gbc);
		gbc.gridy = 6;
		eastPanel.add(comboBAnkunftK, gbc);
		gbc.gridy = 7;
		// eastPanel.add(btEditAnkunftK, gbc);
		gbc.gridy = 8;
		eastPanel.add(lbAnkunftK, gbc);

		gbc.gridy = 9;
		eastPanel.add(btStartAnim, gbc);

		return eastPanel;
	}

	private JPanel createSouthPanel() {
		JPanel southPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = gbc.weighty = 1;

		JSlider meinSlider = new JSlider();
		meinSlider.setMinimum(0);
		meinSlider.setMaximum(360);
		meinSlider.setMajorTickSpacing(10);
		meinSlider.setMinorTickSpacing(5);
		meinSlider.setPaintTicks(true);
		meinSlider.setPaintLabels(true);
		meinSlider.setValue(0);

		meinSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					int deg = (int) source.getValue();
					double val = 2 * Math.PI / 360 * deg;
					centerPanel.zRot = val;
					centerPanel.repaint();
				}
			}
		});

		southPanel.add(meinSlider, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0;
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
