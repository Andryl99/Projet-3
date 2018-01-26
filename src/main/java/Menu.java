import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	// Consultation du fichier config, PEUT ETRE mieux dans main
	private Configuration config;

	public Menu() {

		System.out.println("Menu créé");
		this.config = new Configuration(6, 4, 5, false);
		System.out.println("Configuration chargée");
	}

	public void lancerMenu() {

		int choixJeu;
		int choixMode;
		int choixFinal;
		boolean exit = false;

		while (!exit) {

			choixJeu = -1;
			choixMode = -1;
			choixFinal = -1;
			
			System.out.println("multi-jeux");
			System.out.println("--------------------");

			//
			// Choix du jeu
			do {

				System.out.println("Choisissez un jeu");
				System.out.println("1 - RecherchePM");
				System.out.println("2 - Mastermind");
				
				choixJeu = this.saisieInt();
				
			} while (choixJeu < 1 || choixJeu > 2);

			System.out.println("--------------------");

			//
			// Choix du mode
			do {

				System.out.println("Choisissez un mode");
				System.out.println("1 - Duel");
				System.out.println("2 - Defenseur");
				System.out.println("3 - Challenger");

				choixMode = this.saisieInt();

			} while (choixMode != 1 && choixMode != 2 && choixMode != 3);

			System.out.println("--------------------");

			// TODO ces info seront utile au mode dev et/ou mode verbose
			// System.out.println("Le joueur a choisi jeu n°" + choixJeu + " et mode n°" +
			// choixMode + "\n");
			// System.out.println("Le jeu demarrera avec les parametres suivant");
			// System.out.println("nbCoups = " + config.getNbCoups());
			// System.out.println("nbCases = " + config.getNbCases());
			// System.out.println("nbCouleurs = " + config.getNbCouleurs());

			this.lancerJeu(choixJeu, choixMode);

			//
			// Menu apres jeu
			do {

				System.out.println("--------------------");
				System.out.println("Voulez vous :");
				System.out.println("1 - Rejouer");
				System.out.println("2 - Retourner au menu");
				System.out.println("3 - Quitter l'application ?");

				choixFinal = this.saisieInt();

				if (choixFinal == 1)
					this.lancerJeu(choixJeu, choixMode);
				else if (choixFinal == 2) {
				}

				else if (choixFinal == 3) {
					exit = true;
				}

			} while (choixFinal != 2 && choixFinal != 3); 

		}

	}

	private void lancerJeu(int choixJeu, int choixMode) {
		if (choixJeu == 1) {
			RecherchePM recherchePM = new RecherchePM(config.getNbCoups(), config.getNbCases());
			recherchePM.lancerPartie();
		} else if (choixJeu == 2) {
			System.out.println("Mastermind est toujours en développement");
		}
	}
	
	public int saisieInt() {

		Scanner scanner = new Scanner(System.in);
		
		int choix = -1;
		System.out.print("Choix : ");
		try {
			choix = scanner.nextInt();

		} catch (InputMismatchException e) {
			System.out.println("Erreur de saisie, recommencer");	
		} finally { scanner.nextLine();}

		return choix;
	}

}
