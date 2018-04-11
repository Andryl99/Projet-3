# Projet-3 : Mastermind/Jeu du plus et du moins.  

	
## A propos  

Cette application propose d'affronter une IA sur les deux jeux que sont "le plus et le moins", et le mastermind : https://fr.wikipedia.org/wiki/Mastermind  
3 modes de jeux sont disponibles :  
	- le challenger : l'IA cache une combinaison, à vous de la découvrir,  
	- le défenseur : Proposez un code secret, l'IA tentera de trouver la solution,  
	- le duel : deux matchs simultanés, le gagnant est celui qui casse le code en premier.  

	
## Compilation et lancement  

Le code source se trouve ici : https://github.com/xxjokerx/Projet-3
Cette application à été développé sous Java 8, équipez vous de Java 8 ou ultérieur.

Sous Eclipse JavaEE IDE :  
Avant la compilation rendez-vous dans 
	Project -> Properties -> Java compiler ->  
									Cochez Enabled project specific settings  
									Dans Compiler compliance level, selectionnez '1.8'  

									
## Problèmes connus  

La méthode inspirée de l'algorythme de Knuth (IA du mastermind) prend trop de temps a l'execution, son comportement est donc modifié (moins efficace) dès lors qu'un nombre important de couleurs/digits est configuré.

## Historique
Version 1(Avril 2018)
- Version complète de l'application.