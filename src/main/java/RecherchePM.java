
public class RecherchePM {

	//  config
	private int nbCoups = 0;
	private int nbCases = 0;

	//  constructeur
	public RecherchePM(int nbCoups, int nbCases) {
		this.nbCoups = nbCoups;
		this.nbCases = nbCases;

	}


	public void lancerPartie() {


		Player attaquant = new JoueurHumain();
		Player defenseur = new JoueurAI();

		int compteurCoups = 0;
		boolean continuer = true;

		String solution;	 // Solution du jeu
		String reponse;		 // Donnée par le defenseur +-=+
		String proposition;	 // Faite par l'attaquant 4549


		solution = defenseur.choisitSolution(this.nbCases);
		reponse = "";
		proposition = "";



		// BOUCLE du jeu PM
		while (continuer && compteurCoups < this.nbCoups) {

			proposition = attaquant.joueUnCoup(reponse, proposition, this.nbCases);			
			compteurCoups++;
			reponse = defenseur.donneReponse(proposition, solution, this.nbCases);

			if (proposition.equals(solution)) {
				continuer = false;
				this.AfficheVictoire();
			} else if (compteurCoups == this.nbCoups) {
				this.AfficheDefaite();
			}
		}

	}

	private void AfficheVictoire() {
		System.out.println("\n--------------------");
		System.out.println("Felictation vous avez trouvé la bonne combinaison !!!");
	}

	private void AfficheDefaite() {
		System.out.println("\n--------------------");
		System.out.println("Dommage vous avez perdu !");
	}
}
