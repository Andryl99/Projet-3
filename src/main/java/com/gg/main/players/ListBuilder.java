package com.gg.main.players;

import java.util.ArrayList;

import com.gg.main.ConfigurationClass;

public class ListBuilder {

		private ArrayList<String> candidateList;

		public ListBuilder(ConfigurationClass config) {

			String combination = "";
			int numberOfCombination = (int) Math.pow(config.getNbColors(), config.getSolutionLength());
			String blank = "";

			candidateList = new ArrayList<String>();

			for (int i = 0; i < config.getSolutionLength(); i++)
				blank += '0';

			for (int i = 0; i < numberOfCombination; i++) {
				// Utilisation de radix = config.getNumberColor();
				combination = Integer.toString(i, config.getNbColors());
				if (combination.length() < config.getSolutionLength())
					combination = blank.substring(0, config.getSolutionLength() - combination.length()) + combination;
				candidateList.add(combination);
			}
		}

		public void display() {
			for (String string : candidateList) {
				System.out.println(string);
			}
		}

		public ArrayList<String> getList() {
			return candidateList;
		}


}
