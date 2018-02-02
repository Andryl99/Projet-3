package com.gg.main;
import com.gg.joueurs.*;

public abstract class ModeDeJeu {

	protected int nbCases;
	protected int nbCoups;

	protected Player attaquant;
	protected Player defenseur;


	public ModeDeJeu(int nbCoups, int nbCases, Player attaquant, Player defenseur) {
		
		this.nbCoups = nbCoups;
		this.nbCases = nbCases;
		this.attaquant = attaquant;
		this.defenseur = defenseur;
	}

	public abstract void defenseurChoisiSolution();

	public abstract void attaquantJoue();

	public abstract void defenseurRepond();

	public abstract EtatFinDeManche tourSuivant();

}
