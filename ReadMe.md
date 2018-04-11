# Projet-3 : Mastermind/Jeu du plus et du moins.  

	
## A propos  

Cette application propose d'affronter une IA sur les deux jeux que sont "le plus et le moins", et le mastermind : https://fr.wikipedia.org/wiki/Mastermind  
3 modes de jeux sont disponibles :  
	- le challenger : l'IA cache une combinaison, � vous de la d�couvrir,  
	- le d�fenseur : Proposez un code secret, l'IA tentera de trouver la solution,  
	- le duel : deux matchs simultan�s, le gagnant est celui qui casse le code en premier.  

	
## Compilation et lancement  

Le code source se trouve ici : https://github.com/xxjokerx/Projet-3
Cette application � �t� d�velopp� sous Java 8, �quipez vous de Java 8 ou ult�rieur.

Sous Eclipse JavaEE IDE :  
Avant la compilation rendez-vous dans 
	Project -> Properties -> Java compiler ->  
									Cochez Enabled project specific settings  
									Dans Compiler compliance level, selectionnez '1.8'  

									
## Probl�mes connus  

La m�thode inspir�e de l'algorythme de Knuth (IA du mastermind) prend trop de temps a l'execution, son comportement est donc modifi� (moins efficace) d�s lors qu'un nombre important de couleurs/digits est configur�.

## Historique
Version 1(Avril 2018)
- Version compl�te de l'application.