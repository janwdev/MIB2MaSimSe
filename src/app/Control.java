package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;

import gui.MainGUI;

public class Control {
	private MainGUI gui;

	// TODO in Konstruktor aus Datei einlesen
	// TODO ueberpruefen und ersetzen
	public DefaultComboBoxModel<String> flughafenAnModel = new DefaultComboBoxModel<String>();
	public DefaultComboBoxModel<String> flughafenAbModel = new DefaultComboBoxModel<String>();
	public String[] flughafenStr = new String[] { "Bangkok", "Helsinki" };
	public double[] flughafenPhi = new double[] { (2 * Math.PI * 3 / 4), (2 * Math.PI * 3 / 4) };
	public double[] flughafenThetha = new double[] { 0, (2 * Math.PI * 3 / 4) };

	public Control() {
		Constants.LICENCETEXT = createLicencesText();
		reloadFlughafenCombobox();
	}

	protected void setGUI(MainGUI gui) {
		this.gui = gui;
	}
	
	public void reloadFlughafenCombobox() {
		flughafenAbModel.removeAllElements();
		for (String s : flughafenStr) {
			flughafenAbModel.addElement(s);
		}
		flughafenAnModel.removeAllElements();
		for (String s : flughafenStr) {
			flughafenAnModel.addElement(s);
		}
		
		for (int i = 0; i < flughafenStr.length; i++) {
			System.out.println("Flughafen: " + flughafenStr[i] + ": Phi: " + flughafenPhi[i] + ", Theta: " + flughafenThetha[i]);
		}
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
