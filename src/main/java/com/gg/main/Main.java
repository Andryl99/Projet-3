package com.gg.main;

public class Main {

	public static void main(String[] args) {
		boolean devMod = false;
		String premierArg;

		
		if (args.length > 0)
		try {
			//
			// On recupere la valeur du premier arguments
			premierArg = args[0];
			
			// 
			// Si cet argument est "dev" devMod est vrai
			if (premierArg.equals("dev"))
				devMod = true;
			
		} catch ( Exception e) { }
		//
		// TODO pour l'instant le devmod n'est pas implement�
		Menu menu = new Menu();
		menu.lancerMenu();
	}
}

// TODO factorisation des scanner dans une classe, le scanner sera une m�thode statique
