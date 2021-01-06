package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import gui.MainGUI;

/**
 * Control Class to read Files and manage stuff
 * 
 * @author jweisser
 *
 */
public class Control {
	private String tempFilePath = System.getProperty("java.io.tmpdir");
	private String fileName = "airportSim.cfg";
	private String fileDiv = ":";
	private File airportFile = new File(tempFilePath + "/" + fileName);

	public DefaultComboBoxModel<String> airportDestiModel = new DefaultComboBoxModel<String>();
	public DefaultComboBoxModel<String> airportDepartModel = new DefaultComboBoxModel<String>();
	public String[] airportStr = new String[] {};
	public Double[] airportPhi = new Double[] {};
	public Double[] airportThetha = new Double[] {};

	/**
	 * Constructor
	 */
	public Control() {
		Constants.LICENCETEXT = createLicencesText();
		if (!airportFile.exists()) {
			copyStdAirportFileToTemp();
		}
		reloadAirportCombobox();
	}

	/**
	 * Reload Combobox for Airports
	 */
	public void reloadAirportCombobox() {
		airportDepartModel.removeAllElements();
		airportStr = new String[] {};
		airportPhi = new Double[] {};
		airportThetha = new Double[] {};
		readContentFromAirPFile();
		for (String s : airportStr) {
			airportDepartModel.addElement(s);
		}
		airportDestiModel.removeAllElements();
		for (String s : airportStr) {
			airportDestiModel.addElement(s);
		}

		for (int i = 0; i < airportStr.length; i++) {
			System.out
					.println("Airport: " + airportStr[i] + ": Phi: " + airportPhi[i] + ", Theta: " + airportThetha[i]);
		}
	}

	/**
	 * Saves Airports to Files
	 */
	public void writeAirportToFile() {
		for (int i = 0; i < airportStr.length; i++) {
			writeContenToAirPFile(airportStr[i], airportPhi[i].toString(), airportThetha[i].toString());
		}
	}

	/**
	 * Copy Standard Airport File to Temp Directory
	 */
	private void copyStdAirportFileToTemp() {
		File f = new File(getPathFromRessourceInProject("assets/airportSim.cfg"));
		try {
			Files.copy(f.toPath(), airportFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read Airports from File
	 */
	private void readContentFromAirPFile() {
		if (airportFile.exists()) {
			try {
				List<String> fileContent = Files.readAllLines(airportFile.toPath());
				int index = fileContent.size() + 1;
				for (String string : fileContent) {
					String[] airport = string.split(fileDiv);
					if (airport.length == 3) {
						airportStr = Arrays.copyOf(airportStr, airportStr.length + 1);
						airportStr[airportStr.length - 1] = airport[0];
						airportPhi = Arrays.copyOf(airportPhi, airportPhi.length + 1);
						airportPhi[airportPhi.length - 1] = Double.parseDouble(airport[1]);
						airportThetha = Arrays.copyOf(airportThetha, airportThetha.length + 1);
						airportThetha[airportThetha.length - 1] = Math.PI / 2 - Double.parseDouble(airport[2]);
					} else {
						throw new Exception("File corrupted");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				copyStdAirportFileToTemp();
			}
		} else {
			copyStdAirportFileToTemp();
		}
	}

	/**
	 * Write Airport to File
	 * 
	 * @param AIRPORT
	 * @param PHI
	 * @param THETA
	 */
	protected void writeContenToAirPFile(String AIRPORT, String PHI, String THETA) {
		if (airportFile.exists()) {
			try {
				List<String> fileContent = Files.readAllLines(airportFile.toPath());
				int index = fileContent.size() + 1;
				for (String string : fileContent) {
					if (string.contains(AIRPORT)) {
						index = fileContent.indexOf(string);
						break;
					}
				}
				if (index <= fileContent.size())
					fileContent.set(index, AIRPORT + fileDiv + PHI + fileDiv + THETA);
				else
					fileContent.add(AIRPORT + fileDiv + PHI + fileDiv + THETA);
				Files.write(airportFile.toPath(), fileContent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			List<String> fileContent = new ArrayList<String>();
			fileContent.add(AIRPORT + fileDiv + PHI + fileDiv + THETA);
			try {
				Files.write(airportFile.toPath(), fileContent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create Licence Text from used Librarys
	 * 
	 * @return Licence Text
	 */
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

		String licences = apacheLicence + "\n";

		return licences;
	}

	/**
	 * Get Path from File in Program (needed for .jar)
	 * 
	 * @param resource Path to File in Project
	 * @return Path from File
	 */
	public String getPathFromRessourceInProject(String resource) {
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
