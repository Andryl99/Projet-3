package com.gg.main;

public class Main {

	public static void main(String[] args) {
		boolean devMod = false;
		String firstArg;

		
		if (args.length > 0)
		try {
			//
			// On recupere la valeur du premier arguments
			firstArg = args[0];
			
			// 
			// Si cet argument est "dev" devMod est vrai
			if (firstArg.equals("dev"))
				devMod = true;
			
		} catch (Exception e) { }
		//
		// TODO pour l'instant le devmod n'est pas implement�
		Menu menu = new Menu();
		menu.runMenu();
	}
}

// TODO factorisation des scanner dans une classe, le scanner sera une m�thode statique
