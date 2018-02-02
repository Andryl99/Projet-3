package com.gg.main;

import com.gg.joueurs.*;

// ne sert pas
public class Launcher {

	public Launcher(int choixJeu, int choixMode, int nbCases, int nbCoups, int nbCouleurs) {

		Player joueur1 = new HumanPlayer();
		Player joueur2 = new AIPlayer(nbCases);
		if (choixJeu == 1) {
			
		}

	}
}
