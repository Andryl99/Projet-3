package com.gg.comportement;

public class ReponseAuto implements Answer {

	@Override
	public String DonnerReponse(String proposition, String solution, int nbCases) {

		int digitProp;
		int digitSol;

		String reponse = "";

		// On compare les combinaison et determine le résultat
		// Ici les paramètre de l'application change la borne sup;
		for (int i = 0; i < nbCases; i++) {

			digitProp = Integer.parseInt(String.valueOf(proposition.charAt(i)));
			digitSol = Integer.parseInt(String.valueOf(solution.charAt(i)));

			if (digitProp == digitSol)
				reponse += "=";
			else if (digitProp < digitSol)
				reponse += "+";
			else if (digitProp > digitSol)
				reponse += "-";
		}

		System.out.println("Proposition : " + proposition + "\tSolution : " + solution + "\t\t==> " + reponse);

		return reponse;
	}

}
