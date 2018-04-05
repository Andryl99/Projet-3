package com.gg.main;

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

	private void writeProperties(String nbTurns, String solutionLength, String nbColors) {
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("src/main/resources/config.properties");

			// set the properties value
			prop.setProperty("nbTurns", nbTurns);
			prop.setProperty("solutionLength", solutionLength);
			prop.setProperty("nbColors", nbColors);

			// save properties
			prop.store(output, null);

		} catch (IOException io) {
			logger.fatal(io.toString());
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					logger.fatal(e.toString());
				}
			}
		}
	}

	private void loadProperties() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("src/main/resources/config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			nbTurns = prop.getProperty("nbTurns");
			solutionLength = prop.getProperty("solutionLength");
			nbColors = prop.getProperty("nbColors");

		} catch (IOException ex) {
			logger.fatal(ex.toString());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.fatal(e.toString());
				}
			}
		}
	}

	public ConfigurationClass getConfigurationClass() {
		loadProperties();
		try{
			nbColors.isEmpty();
			solutionLength.isEmpty(); 
			nbTurns.isEmpty();
		} catch (NullPointerException e) {
			// Ecriture des valeur par défaut
			logger.error("config.properties looks empty, default values will be writen. e : " + e.toString() + "\n");
			writeProperties("10", "4", "6");
			loadProperties();
		}
		config = new ConfigurationClass(Integer.parseInt(nbTurns),Integer.parseInt(solutionLength),Integer.parseInt(nbColors));
		return config;
	}
}
