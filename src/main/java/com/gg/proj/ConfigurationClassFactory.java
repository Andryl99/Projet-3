package com.gg.proj;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationClassFactory {

	static final Logger logger = LogManager.getLogger();
	private ConfigurationClass config;
	private String nbTurns;
	private String solutionLength;
	private String nbColors;

	public ConfigurationClassFactory() {
		this.nbTurns = "";
		this.solutionLength = "";
		this.nbColors = "";
	}

	public ConfigurationClass getConfigurationClass() {
		loadProperties();
		config = new ConfigurationClass(Integer.parseInt(nbTurns), Integer.parseInt(solutionLength),
				Integer.parseInt(nbColors));
		writeProperties(nbTurns, solutionLength, nbColors);
		return config;
	}

	private void writeProperties(String nbTurns, String solutionLength, String nbColors) {
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("src/main/resources/config.properties");

			// set properties
			prop.setProperty("nbTurns", nbTurns);
			prop.setProperty("solutionLength", solutionLength);
			prop.setProperty("nbColors", nbColors);

			// save properties
			prop.store(output, null);

		} catch (IOException io) {
			logger.fatal(io.getMessage());
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					logger.fatal(e.getMessage());
				}
			}
		}
	}

	private void loadProperties() {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("src/main/resources/config.properties");

			// chargement du fichier properties
			prop.load(input);

			// récupération des properties
			nbTurns = prop.getProperty("nbTurns", "10");
			solutionLength = prop.getProperty("solutionLength", "4");
			nbColors = prop.getProperty("nbColors", "6");

		} catch (IOException ex) {
			logger.fatal(ex.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.fatal(e.getMessage());
				}
			}
		}
	}
}
