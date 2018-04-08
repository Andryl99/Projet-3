***************************************************************

	Projet-3 : Mastermind/Jeu du plus et du moins.

	
************************  A propos ****************************
Cette application propose d'affronter une IA sur les deux jeux que sont "le plus et le moins", et le mastermind : https://fr.wikipedia.org/wiki/Mastermind 
3 modes de jeux sont disponibles :
	- le challenger : l'IA cache une combinaison, � vous de la d�couvrir,
	- le d�fenseur : Proposez un code secret, l'IA tentera de trouver la solution,
	- le duel : deux matchs simultan�s, qui parviendra a casser le code en premier?

	
********** Compilation et lancement  **************************
Le code source se trouve ici : 
https://github.com/xxjokerx/Projet-3
Ce application � �t� d�velopp� sous Java 1.8, �quipez vous de Java 8 ou ult�rieur

Sous Eclipse JavaEE IDE :
Avant la compilation rendez-vous dans 
	Project -> Properties -> Java compiler -> 
									Cochez Enabled project specific settings
									Dans Compiler compliance level, selectionnez '1.8'

									
******************** Probl�mes connus   ***********************
La m�thode inspir�e de l'algorythme de knuth (IA du mastermind) prend trop de temps a l'execution, son comportement est donc modifi� (moins efficace) d�s lors qu'un nombre important de couleurs/digits est configur�.


*********************** Historique ****************************
Version 0.1.0 (Avril 2018)
	- Version compl�te du logiciel
	- ajout d'un logger et d'un fichier configuration

	
***************************************************************