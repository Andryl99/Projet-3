package com.gg.main.games;

import java.util.ArrayList;
import java.util.List;

import com.gg.main.ConfigurationClass;
import com.gg.main.players.APairOfPlayer;
import com.gg.main.players.PlayerFactory;

public class GameFactory {
	
	private ConfigurationClass config;
	private PlayerFactory playerFactory;
	private APairOfPlayer aPairOfPlayer;
	
	public GameFactory(ConfigurationClass config) {
		this.config = config;
		playerFactory = new PlayerFactory(config);
	}
	
	public Game getGame(int gameChoice, int modChoice, boolean reverserFlag) {

		return createGame(gameChoice, modChoice, reverserFlag);
	}

	private Game createGame(int gameChoice, int modChoice, boolean reverserFlag) {
		aPairOfPlayer = playerFactory.getAPairOfPlayer(gameChoice, modChoice);
		
		// Desormais j'utlise getReversedPlayers qui ne détériore pas l'objet original
		if (reverserFlag == true) {
			aPairOfPlayer = aPairOfPlayer.getReversedPlayers();
		}
		
		switch (gameChoice) {
		case 1 :
			return new MoreLessGame(config.getNbTurns(), config.getSolutionLength(),aPairOfPlayer.getPlayer1(), aPairOfPlayer.getPlayer2());
		case 2 :
			return new Mastermind(config.getNbTurns(), config.getSolutionLength(), config.getNbColors(), aPairOfPlayer.getPlayer1(), aPairOfPlayer.getPlayer2());
		default :
			return null;
		}
	}
	
	public List<Game> getAListOfGames(int gameChoice, int modChoice){
		return createAListOfGames(gameChoice, modChoice);
	}
	
	// Je me sert d'un booleen pour 
	private List<Game> createAListOfGames(int gameChoice, int modChoice){
		List<Game> listOfGames = new ArrayList<Game>();
		listOfGames.add(getGame(gameChoice, modChoice, false));
		if (modChoice == 3) 
			listOfGames.add(getGame(gameChoice, modChoice, true));
		return listOfGames;
	}
}
