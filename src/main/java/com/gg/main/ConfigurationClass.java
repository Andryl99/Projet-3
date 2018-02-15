package com.gg.main;

public class ConfigurationClass {
	private int nbTurns;
	private int solutionLength;
	private int nbColors;
	private boolean devMod;
	
	public ConfigurationClass() {
		setNbTurns(4);
		setSolutionLength(4);
		setNbColors(4);
		this.devMod = false;
	}
	
	//
	// Au final on remplacera ce constructeur ainsi que les setters par une lecture du fichier de configuration;
	public ConfigurationClass(int nbTurns, int solutionLength, int nbColors, boolean devMod) {
		setNbTurns(nbTurns);
		setSolutionLength(solutionLength);
		setNbColors(nbColors);
		this.devMod = devMod;
	}
	
	private void setNbTurns(int nbTurns) {
		this.nbTurns = nbTurns;
	}
	private void setSolutionLength(int nbCases) {
		this.solutionLength = nbCases;
	}
	private void setNbColors(int nbColors) {
		this.nbColors = nbColors;
	}
	
	public int getNbTurns() {
		return this.nbTurns;
	}
	public int getSolutionLength() {
		return this.solutionLength;
	}
	public int getNbColors() {
		return this.nbColors;
	}
}
