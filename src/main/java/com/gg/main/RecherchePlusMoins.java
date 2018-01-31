package com.gg.main;
import com.gg.comportement.*;
public class RecherchePlusMoins extends ModeDeJeu {


	private String solution; // Solution du jeu
	private String reponse; // Donn�e par le defenseur +-=+
	private String proposition; // Faite par l'attaquant 4549
	private int compteurCoups = 0;

	public RecherchePlusMoins(int nbCoups, int nbCases, Player attaquant, Player defenseur) {
		
		super(nbCoups, nbCases, attaquant, defenseur);
		this.solution = "";
		this.reponse = "";
		this.proposition = "";
	}
		
	@Override
	public void defenseurChoisiSolution() {
		this.solution = defenseur.choisitSolution(nbCases);
	}

	@Override
	public void attaquantJoue() {
		this.proposition = attaquant.joueUnCoup(reponse, proposition, nbCases);
		compteurCoups++;
	}

	@Override
	public void defenseurRepond() {
		this.reponse = defenseur.donneReponse(proposition, solution, nbCases);
	}

	@Override
	public EtatFinDeManche tourSuivant() {
		if (proposition.equals(solution))
			return EtatFinDeManche.ATTAQUANTGAGNE;
		else if (!(proposition.equals(solution)) && (this.compteurCoups == this.nbCoups))
			return EtatFinDeManche.DEFENSEURGAGNE;
		else return EtatFinDeManche.AUCUNGAGNANT;		
	}
}
