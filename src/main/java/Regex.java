
public class Regex {
	
	// Vérifie que le joueur donne une correction de la bonne forme
	public static boolean estCorrectionValide(String correction, int nbCases)	{
		String regExp = "";
		for (int i = 0; i < nbCases; i++) {
			regExp += "[-=+]";
		}
		return correction.matches(regExp);
	}
	
	// Vérifie que le joueur donne une combinaison valide
	public static boolean estCombinaisonValide(String combinaison, int nbCases)	{
		String regExp = "";
		for (int i = 0; i < nbCases; i++) {
			regExp += "[1-9]";
		}
		return combinaison.matches(regExp);
	}
}
