package app;

import java.awt.Color;

public class Constants {
	public static final String PROGNAME = "3DFlightMaSim - Luca Jannik";
	public static final String VERSION = "0.001";
	public static final String DESCRIPTION = "Program to visualize the flight routes between two positions on the earth";
	public static String LICENCETEXT;

	public static int drawSizeXPixels = 800;
	public static int drawSizeYPixels = 800;
	public static int r = 1;

	public static int FPS = 60;
	public static int TPF = 1000 / FPS;
	public static double TIMESCALE = 1.0;
	public static double earthRadius = 1.0; // später
	
	public static double animSpeedNormal = 10;
	public static double animSpeedSlow = 5;
	public static double animSpeedFast = 30;

	public static int yOffsetDraw = 350;
	public static int xOffsetDraw = 350;
	public static double drawScaleFactor = 2;
	
	public static int STARTENDVECWIDTH = 8;
	public static int STARTENDVECHEIGHT = 8;
	public static int FLIGHTVECWIDTH = 6;
	public static int FLIGHTVECHEIGHT = 6;

	public static Color EARTHCOLORFRONT = new Color(0, 0, 0, 255);
	public static Color EARTHCOLORBACKFACE = new Color(35, 35, 35, 255); // or new Color(42, 40, 39, 255);
	public static Color EARTHOUTLINECOLOR = new Color(0, 0, 0, 255);
	
	public static Color COLORSTARTEND = new Color(255, 0, 0, 255);
	public static Color COLORVECWAY = new Color(12, 92, 169, 255);
	
	public static Color COLORFLIGHT = new Color(0, 255, 0, 255);
}
