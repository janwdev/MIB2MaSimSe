package app;

public class Constants {
	public static final String PROGNAME = "3DFlightMaSim - Luca Jannik";
	public static final String VERSION = "0.001";
	public static final String DESCRIPTION = "Programm um die Flugrouten zwischen zwei Positionen auf der Erde zu visualisieren";
	public static String LICENCETEXT;
	
	public static int drawSizeXPixels = 800;
	public static int drawSizeYPixels = 800;
	public static int r = 1;
	
	public static int FPS = 60;
	public static int TPF = 1000/FPS;
	public static double TIMESCALE = 1.0;
	public static double earthRadius = 1.0; //später
	
	public static int yOffsetDraw = 350;
	public static int xOffsetDraw = 350;
	public static double drawScaleFactor = 2;
}
