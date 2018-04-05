package com.gg.main.players;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gg.main.ConfigurationClass;
import com.gg.main.Regex;

public abstract class Player {
	protected ConfigurationClass config;
	protected Scanner scanner = new Scanner(System.in);
	static final Logger logger = LogManager.getLogger();
	
	public Player (ConfigurationClass config) {
		this.config = config;
		this.scanner = new Scanner(System.in);
	}
	public abstract String selectSolution();
	public abstract String giveAnswer(String proposition, String solution);
	public abstract String play(String reponse);
	public abstract String toString();

	public String inputErrorCheck(int solutionLength, int nbColors ,SequenceType sequence) {
		String str = "";
		while (true) {
			try {
				str = scanner.nextLine();
			} catch (InputMismatchException e) {
				logger.warn("Erreur de saisie, recommencez : ");
			}

			if (Regex.isValidCombination(str, solutionLength, nbColors, sequence)) {
				break;
			} else {
				logger.warn("Erreur de saisie, recommencez : ");
			}
		}
		return str;
	}
}
