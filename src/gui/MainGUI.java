package gui;

import javax.swing.JFrame;

import app.Control;

public class MainGUI  extends JFrame{
	private Control control;
	
	public MainGUI(Control control) {
		this.control = control;
		this.setTitle("3DFlight Simulator MaSiSe Luca Jannik");
		this.setSize(300,300); //TODO aendern
		this.setVisible(true);
	}
}
