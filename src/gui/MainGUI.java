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
	
	private JFrame frame;

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
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		frame = this;
	}

	private void setStartVector() {
		int selIndex = comboBAbflugK.getSelectedIndex();
		Vector v = new Vector(control.flughafenPhi[selIndex], control.flughafenThetha[selIndex]);
		centerPanel.startVector = new VectorToDraw(v, Constants.COLORSTARTEND, Constants.STARTENDVECWIDTH,
				Constants.STARTENDVECHEIGHT);
		lbAnkunftK.setText("Koordinaten: Phi: " + (Math.round(v.getPWinkel() * 1000) / 1000.0) + "Thetha: "
				+ (Math.round(v.getOWinkel() * 1000) / 1000.0));

	}

	private void setEndVector() {
		int selIndex = comboBAnkunftK.getSelectedIndex();
		Vector v = new Vector(control.flughafenPhi[selIndex], control.flughafenThetha[selIndex]);
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
		centerPanel.startVector = null;
		centerPanel.endVector = null;
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

		eastPanel.add(createCoordinatesPanel(), gbc);
		gbc.gridy = 1;
		eastPanel.add(new JLabel("###################"), gbc);
		gbc.gridy = 2;
		eastPanel.add(createAnimControlPanel(), gbc);

		return eastPanel;
	}

	private JPanel createCoordinatesPanel() {
		JPanel coordinPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.LINE_START;

		JLabel lbAbflug = new JLabel("Abflug:");
		comboBAbflugK = new JComboBox<String>(control.flughafenAbModel); // TODO actionlistener
		JButton btEditAbflugK = new JButton("Koordinaten bearbeiten"); // TODO actionlistener

		JLabel lbAnkunft = new JLabel("Ankunft:");
		comboBAnkunftK = new JComboBox<String>(control.flughafenAnModel); // TODO actionlistener
		JButton btEditAnkunftK = new JButton("Koordinaten bearbeiten"); // TODO actionlistener
		
		JButton btAddKoordinates = new JButton("Add Koordinaten");
		btAddKoordinates.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddCoordinatesDialog(frame, control);
			}
		});
		
		coordinPanel.add(btAddKoordinates, gbc);
		gbc.gridy = 1;
		coordinPanel.add(lbAbflug, gbc);
		gbc.gridy = 2;
		coordinPanel.add(comboBAbflugK, gbc);
		gbc.gridy = 3;
		// eastPanel.add(btEditAbflugK, gbc);
		gbc.gridy = 4;
		coordinPanel.add(lbAbflugK, gbc);

		gbc.gridy = 5;
		coordinPanel.add(new JLabel("-------------------"), gbc);

		gbc.gridy = 6;
		coordinPanel.add(lbAnkunft, gbc);
		gbc.gridy = 7;
		coordinPanel.add(comboBAnkunftK, gbc);
		gbc.gridy = 8;
		// eastPanel.add(btEditAnkunftK, gbc);
		gbc.gridy = 9;
		coordinPanel.add(lbAnkunftK, gbc);

		return coordinPanel;
	}

	private JPanel createAnimControlPanel() {
		JPanel animControlPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.LINE_START;

		JButton btStartAnim = new JButton("Animation starten");
		btStartAnim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startAnim();
			}
		});
		JButton btPlayPause = new JButton("Pause/Weiter");
		btPlayPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				animation.pauseContinue();
			}
		});
		JButton btCancel = new JButton("Abbrechen");
		btCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				animation.cancel();
				clearDrawedVectors();
			}
		});

		gbc.gridy = 0;
		animControlPanel.add(btStartAnim, gbc);
		gbc.gridy = 1;
		animControlPanel.add(btPlayPause, gbc);
		gbc.gridy = 2;
		animControlPanel.add(btCancel, gbc);

		return animControlPanel;
	}

	private JPanel createSouthPanel() {
		JPanel southPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = gbc.weighty = 1;

		JSlider rotationSlider = new JSlider();
		rotationSlider.setMinimum(0);
		rotationSlider.setMaximum(360);
		rotationSlider.setMajorTickSpacing(10);
		rotationSlider.setMinorTickSpacing(5);
		rotationSlider.setPaintTicks(true);
		rotationSlider.setPaintLabels(true);
		rotationSlider.setValue(0);

		rotationSlider.addChangeListener(new ChangeListener() {
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

		//southPanel.add(rotationSlider, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0;
		JButton btShowInfo = new JButton("Info");
		btShowInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new InfoDialog(frame);
			}
		});
		southPanel.add(btShowInfo, gbc);

		return southPanel;
	}
}
