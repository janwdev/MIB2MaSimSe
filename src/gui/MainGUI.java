package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import app.Animation;
import app.Constants;
import app.Control;
import objectClasses.Vector;

/**
 * GUI
 * 
 * @author jweisser
 *
 */
public class MainGUI extends JFrame {
	private Control control;
	private CenterPanel centerPanel = new CenterPanel();
	private Animation animation = new Animation(this, centerPanel);

	private MainGUI gui; // Only for hand-over parameters

	// Choose Airports
	private JComboBox<String> combDeparture;
	private JComboBox<String> combDestination;

	// coordinates from choosen Airports
	private JLabel lbDestinationC = new JLabel("Coordinates:");
	private JLabel lbDepartureC = new JLabel("Coordinates:");

	// Animation control
	private JButton btStartAnim;
	private JButton btPlayPause;
	private JButton btCancel;
	private JComboBox<String> combSpeed;

	/**
	 * Constructor
	 * 
	 * @param control
	 */
	public MainGUI(Control control) {
		this.control = control;

		this.setTitle(Constants.PROGNAME);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		ImageIcon img = new ImageIcon(control.getPathFromRessourceInProject("assets/icon.png"));
		this.setIconImage(img.getImage());

		this.add(createSouthPanel(), BorderLayout.SOUTH);
		this.add(createEastPanel(), BorderLayout.EAST);
		this.add(centerPanel, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);

		this.setMinimumSize(this.getSize());
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		gui = this;
	}

	/**
	 * Shows an Error Dialog with a Message
	 * 
	 * @param message the Message to Display
	 */
	public static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Set Vector from Departure Airport
	 */
	private void setStartVector() {
		int selIndex = combDeparture.getSelectedIndex();
		Vector v = new Vector(control.airportPhi[selIndex], control.airportThetha[selIndex]);
		centerPanel.startVector = new VectorToDraw(v, Constants.COLORSTARTEND, Constants.STARTENDVECWIDTH,
				Constants.STARTENDVECHEIGHT);
		lbDepartureC.setText("Coordinates: Phi: " + (Math.round(v.getPAngel() * 1000) / 1000.0) + "Theta: "
				+ (Math.round((-v.getOAngel() + Math.PI / 2) * 1000) / 1000.0));

	}

	/**
	 * Set Vector from Destination Airport
	 */
	private void setEndVector() {
		int selIndex = combDestination.getSelectedIndex();
		Vector v = new Vector(control.airportPhi[selIndex], control.airportThetha[selIndex]);
		centerPanel.endVector = new VectorToDraw(v, Constants.COLORSTARTEND, Constants.STARTENDVECWIDTH,
				Constants.STARTENDVECHEIGHT);
		lbDestinationC.setText("Coordinates: Phi: " + (Math.round(v.getPAngel() * 1000) / 1000.0) + "Theta: "
				+ (Math.round((-v.getOAngel() + Math.PI / 2) * 1000) / 1000.0));
	}

	/**
	 * Draw Vector
	 * 
	 * @param v Vector
	 */
	public void drawVector(Vector v) {
		centerPanel.vectorDrawList.add(new VectorToDraw(v, Constants.COLORVECWAY, 4, 4));
		centerPanel.repaint();
	}

	/**
	 * Remove all drawed Vectors from Center Panel
	 */
	public void clearDrawedVectors() {
		centerPanel.vectorDrawList.clear();
		centerPanel.startVector = null;
		centerPanel.endVector = null;
		centerPanel.repaint();
	}

	/**
	 * Start Animation
	 */
	private void startAnim() {
		if (combDestination.getSelectedIndex() != combDeparture.getSelectedIndex()) {
			// Btn Control
			btStartAnim.setEnabled(false);
			btPlayPause.setEnabled(true);
			btCancel.setEnabled(true);
			combSpeed.setEnabled(false);
			// Start Animation
			clearDrawedVectors();
			setStartVector();
			setEndVector();
			animation.startAnimation();
		} else {
			showErrorMessage("Destination and Departure Airport cant be the same");
		}

	}

	/**
	 * Animation finished (for Btn Control)
	 */
	public void animEnded() {
		btStartAnim.setEnabled(true);
		combSpeed.setEnabled(true);
		btPlayPause.setEnabled(false);
		btCancel.setEnabled(false);
	}

	/**
	 * Create Panel on the Right side
	 * 
	 * @return JPanel with anim and coordinates control
	 */
	private JPanel createEastPanel() {
		// Panel init
		JPanel eastPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.LINE_START;

		// Add Content to JPanel
		eastPanel.add(createCoordinatesPanel(), gbc);
		gbc.gridy = 1;
		eastPanel.add(new JLabel("###################"), gbc);
		gbc.gridy = 2;
		eastPanel.add(createAnimControlPanel(), gbc);

		return eastPanel;
	}

	/**
	 * Create Panel for Coordinates Control
	 * 
	 * @return JPanel
	 */
	private JPanel createCoordinatesPanel() {
		// Panel init
		JPanel coordinatesPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.LINE_START;

		// Create Panel content
		JLabel lbDeparture = new JLabel("Departure:");
		combDeparture = new JComboBox<String>(control.airportDepartModel);

		JLabel lbDestination = new JLabel("Destination:");
		combDestination = new JComboBox<String>(control.airportDestiModel);

		JButton btAddCoordinates = new JButton("Add Coordinates");
		btAddCoordinates.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Open Dialog
				new AddCoordinatesDialog(gui, control);
			}
		});

		// Add content to panel
		coordinatesPanel.add(btAddCoordinates, gbc);
		gbc.gridy = 1;
		coordinatesPanel.add(lbDeparture, gbc);
		gbc.gridy = 2;
		coordinatesPanel.add(combDeparture, gbc);
		gbc.gridy = 3;
		gbc.gridy = 4;
		coordinatesPanel.add(lbDepartureC, gbc);

		gbc.gridy = 5;
		coordinatesPanel.add(new JLabel("-------------------"), gbc);

		gbc.gridy = 6;
		coordinatesPanel.add(lbDestination, gbc);
		gbc.gridy = 7;
		coordinatesPanel.add(combDestination, gbc);
		gbc.gridy = 8;
		gbc.gridy = 9;
		coordinatesPanel.add(lbDestinationC, gbc);

		return coordinatesPanel;
	}

	/**
	 * Create Panel for Animation Control
	 * 
	 * @return JPanel
	 */
	private JPanel createAnimControlPanel() {
		// Panel init
		JPanel animControlPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.LINE_START;

		// Create Panel content
		btStartAnim = new JButton("Start Animation");
		btStartAnim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startAnim();
			}
		});
		btPlayPause = new JButton("Pause/Continue");
		btPlayPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				animation.pauseContinue();
			}
		});
		btPlayPause.setEnabled(false);
		btCancel = new JButton("Cancel");
		btCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				animation.cancel();
				clearDrawedVectors();
			}
		});
		btCancel.setEnabled(false);

		JLabel lbSpeed = new JLabel("Speed");
		combSpeed = new JComboBox<String>(new String[] { "Normal", "Slow", "Fast" });
		combSpeed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selIndex = combSpeed.getSelectedIndex();
				if (selIndex == 0) {
					animation.setSpeed(Constants.ANIMSPEEDNORMAL);
				} else if (selIndex == 1) {
					animation.setSpeed(Constants.ANIMSPEEDSLOW);
				} else if (selIndex == 2) {
					animation.setSpeed(Constants.ANIMSPEEDFAST);
				}
			}
		});

		// Add content to panel
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridy = 0;
		animControlPanel.add(btStartAnim, gbc);
		gbc.gridy++;
		animControlPanel.add(btPlayPause, gbc);
		gbc.gridy++;
		animControlPanel.add(btCancel, gbc);
		gbc.gridy++;
		gbc.gridwidth = 1;
		animControlPanel.add(lbSpeed, gbc);
		gbc.gridx++;
		animControlPanel.add(combSpeed);

		return animControlPanel;
	}

	/**
	 * Create Panel in the south with rotation slider and Info Button
	 * 
	 * @return JPanel
	 */
	private JPanel createSouthPanel() {
		// Panel init
		JPanel southPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = gbc.weighty = 1;

		// create Content
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

		JButton btShowInfo = new JButton("Info");
		btShowInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new InfoDialog(gui);
			}
		});

		// add content to Panel
		southPanel.add(rotationSlider, gbc);
		gbc.gridx = 1;
		gbc.weightx = 0;
		southPanel.add(btShowInfo, gbc);

		return southPanel;
	}
}
