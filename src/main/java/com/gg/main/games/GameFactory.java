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
	
	public Game getGame(int gameChoice, int modChoice) {
		return createGame(gameChoice, modChoice);
	}

	private Game createGame(int gameChoice, int modChoice) {
		config = new ConfigurationClass();
		playerFactory = new PlayerFactory();
		aPairOfPlayer = playerFactory.getAPairOfPlayer(gameChoice, modChoice);
		
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
	
	private List<Game> createAListOfGames(int gameChoice, int modChoice){
		List<Game> listOfGames = new ArrayList<Game>();
		listOfGames.add(createGame(gameChoice, modChoice));
		if (modChoice == 3) 
			listOfGames.add(createGame(gameChoice, modChoice));
		return listOfGames;
	}
}
