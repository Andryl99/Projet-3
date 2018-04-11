package com.gg.proj.games;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gg.proj.ConfigurationClass;
import com.gg.proj.players.APairOfPlayer;
import com.gg.proj.players.PlayerFactory;

public class GameFactory {

	static final Logger logger = LogManager.getLogger();
	private ConfigurationClass config;
	private PlayerFactory playerFactory;
	private APairOfPlayer aPairOfPlayer;

	public GameFactory(ConfigurationClass config) {
		this.config = config;
		this.playerFactory = new PlayerFactory(config);
	}

	// Je me sert d'un booleen pour donner une valeur au reserveFlag et modifier la
	// création des joueurs.
	public List<Game> getAListOfGames(int gameChoice, int modChoice) {
		List<Game> listOfGames = new ArrayList<Game>();
		try {
			listOfGames.add(getGame(gameChoice, modChoice, false));
			if (modChoice == 3)
				listOfGames.add(getGame(gameChoice, modChoice, true));
		} catch (IllegalArgumentException e) {
			logger.fatal(e.toString());
		}
		return listOfGames;
	}

	private Game getGame(int gameChoice, int modChoice, boolean reverserFlag) throws IllegalArgumentException {

		// On leve une possible exception a la creation des joueurs
		try {
			aPairOfPlayer = playerFactory.getAPairOfPlayer(gameChoice, modChoice);
		} catch (IllegalArgumentException e) {
			logger.fatal(e.toString());
		}

		if (reverserFlag == true) {
			aPairOfPlayer = aPairOfPlayer.getReversedPlayers();
		}

		switch (gameChoice) {
		case 1:
			return new MoreLessGame(config.getNbTurns(), config.getSolutionLength(), aPairOfPlayer.getPlayer1(),
					aPairOfPlayer.getPlayer2());
		case 2:
			return new Mastermind(config.getNbTurns(), config.getSolutionLength(), config.getNbColors(),
					aPairOfPlayer.getPlayer1(), aPairOfPlayer.getPlayer2());
		default:
			// Utilisation d'une exception qui est levé dans createAListOfGames, on evite le
			// null pointer
			throw new IllegalArgumentException("Programming error, gameChoice is " + gameChoice
					+ " and should be 1 or 2. Game received a null pointer");
		}
	}

}
