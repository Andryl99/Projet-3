package com.gg.main;

public class Configuration {
	private int nbCoups;
	private int nbCases;
	private int nbCouleurs;
	private boolean devMod;
	
	public Configuration() {
		setNbCoups(4);
		setNbCases(4);
		setNbCouleurs(4);
		this.devMod = false;
	}
	
	//
	// Au final on remplacera ce constructeur ainsi que les setters par une lecture du fichier de configuration;
	public Configuration(int nbCoups, int nbCases, int nbCouleurs, boolean devMod) {
		setNbCoups(nbCoups);
		setNbCases(nbCases);
		setNbCouleurs(nbCouleurs);
		this.devMod = devMod;
	}
	
	protected void setNbCoups(int nbCoups) {
		this.nbCoups = nbCoups;
	}
	protected void setNbCases(int nbCases) {
		this.nbCases = nbCases;
	}
	protected void setNbCouleurs(int nbCouleurs) {
		this.nbCouleurs = nbCouleurs;
	}
	
	public int getNbCoups() {
		return this.nbCoups;
	}
	public int getNbCases() {
		return this.nbCases;
	}
	public int getNbCouleurs() {
		return this.nbCouleurs;
	}
}
