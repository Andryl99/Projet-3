package com.gg.main;

public class ConfigurationClass {
	private int nbTurns;
	private int solutionLength;
	private int nbColors;
	private boolean devMod;

	
	public ConfigurationClass(int nbTurns, int solutionLength, int nbColors) {
		setNbTurns(nbTurns);
		setSolutionLength(solutionLength);
		setNbColors(nbColors);
//		this.devMod = devMod;
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
