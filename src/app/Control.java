package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import gui.MainGUI;

public class Control {
	private MainGUI gui;

	public String[] abflugFlughafenStr = new String[] { "Z�rch", "Bangkok", "Helsinki" };
	//TODO in Konstruktor aus Datei einlesen
	// TODO ueberpruefen und ersetzen
	public int[] abflugFlughafenX = new int[] { 5, 7, 9 };
	public int[] abflugFlughafenY = new int[] { 5, 7, 9 };
	public int[] abflugFlughafenZ = new int[] { 5, 7, 9 };
	public String[] ankunftFlughafenStr = new String[] { "Z�rch", "Bangkok", "Helsinki" };
	// TODO ueberpruefen und ersetzen
	public int[] ankunftFlughafenX = new int[] { 5, 7, 9 };
	public int[] ankunftFlughafenY = new int[] { 5, 7, 9 };
	public int[] ankunftFlughafenZ = new int[] { 5, 7, 9 };

	public Control() {
		Constants.LICENCETEXT = createLicencesText();
	}

	protected void setGUI(MainGUI gui) {
		this.gui = gui;
	}

	private String createLicencesText() {
		StringBuilder apacheBuilder = new StringBuilder();
		apacheBuilder.append("Uses Apache 2 Licence\n");
		apacheBuilder.append("FlatLaf - https://www.formdev.com/flatlaf/\n" + "--------------------" + "\n");
		try (BufferedReader br = new BufferedReader(
				new FileReader(getPathFromRessourceInProject("assets/ApacheLicence2.txt")))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				apacheBuilder.append(sCurrentLine).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String apacheLicence = apacheBuilder.toString();

		// evtl mehr Lizenzen einfuegen

		String licences = apacheLicence + "\n";

		return licences;
	}

	private String getPathFromRessourceInProject(String resource) {
		File file = null;
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream(resource);
			file = File.createTempFile(new Date().getTime() + "", ".tmp");
			OutputStream out = new FileOutputStream(file);
			int read;
			byte[] bytes = new byte[1024];
			while ((read = input.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			input.close();
			file.deleteOnExit();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return file.getAbsolutePath();
	}
}
