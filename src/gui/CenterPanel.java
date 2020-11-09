package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import app.Constants;

public class CenterPanel extends JPanel{
	
	private Graphics g;
	
	public CenterPanel() {
		Dimension size = new Dimension(Constants.drawSizeXPixels, Constants.drawSizeYPixels);
		this.setMinimumSize(size);
		this.setPreferredSize(size);
		this.setMaximumSize(size);
	}
	
	@Override protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g = g;
	}

}
