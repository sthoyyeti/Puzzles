package edu.srini.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShortPathFinder {
	private static Logger logger = LoggerFactory.getLogger(ShortPathFinder.class);
	private static final String R = "R";
	private static final String E = "E";
	private static final String S = "S";
	String[] board;
	int sIndex = -1, eIndex = -1;
	Set<Integer> minList = new HashSet<>();

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		// read board length
		int length = scan.nextInt();

		ShortPathFinder spf = new ShortPathFinder();
		// read board content
		spf.board = new String[length];
		for (int i = 1; i <= length; i++) {
			spf.board[i - 1] = scan.next();
		}
		scan.close();
		spf.findShortPathS2E(spf.board);

	}

	public String findShortPathS2E(String[] board) {
		this.board = board;

		for (int i = 1; i <= board.length; i++) {

			if (S.equalsIgnoreCase(board[i - 1]))
				sIndex = i - 1;
			if (E.equalsIgnoreCase(board[i - 1]))
				eIndex = i - 1;
		}

		logger.debug("Board given:" + Arrays.asList(board) + " sIndex = " + sIndex + " eIndex = " + eIndex);
		if (sIndex == -1 || eIndex == -1) {
			return "Missing S or E";
		}
		rollDice(sIndex, 0);

		logger.info("All possibility count List:" + minList);
		String result = (minList.size() > 0) ? String.valueOf(Collections.min(minList))
				: "Not possible to reach E from S";
		logger.info("Min turns from S -> E:" + result);

		return result;
	}

	private void rollDice(int currIndex, int count) {
		int nextIndex;
		for (int i = 1; i <= 6; i++) {
			int internalCount = count;
			internalCount++;
			nextIndex = currIndex + i;
			while (nextIndex < board.length) {
				String nextChar = board[nextIndex];
				logger.debug("Next char:" + nextChar);
				if (E.equalsIgnoreCase(nextChar)) {
					minList.add(internalCount);
					break;
				}

				if (nextChar.equalsIgnoreCase(R)) {
					rollDice(nextIndex, internalCount);
					break;
				}

				int tempPrevIndex = nextIndex;
				nextIndex = nextIndex + Integer.valueOf(nextChar);
				if (nextIndex < tempPrevIndex && isInfiniteLoop(tempPrevIndex, nextIndex)) {
					logger.info(
							"Infinite loop found...tempPrevIndex: " + tempPrevIndex + " nextIndex: " + nextIndex);
					break;
				}
				internalCount++;
			}
		}
	}

	private boolean isInfiniteLoop(int tempPrevIndex, int nextIndex) {
		String nextChar = board[nextIndex];

		while (nextIndex < tempPrevIndex) {
			if (E.equalsIgnoreCase(nextChar)) {
				return false;
			}

			if (R.equalsIgnoreCase(nextChar) && (nextIndex + 6 >= tempPrevIndex)) {
				return true;
			}
			if (S.equalsIgnoreCase(nextChar)) {
				return true;
			}

			// try to reach prev Index if possible
			if (nextIndex + Integer.valueOf(nextChar) == tempPrevIndex) {
				return true;
			}
			int tempInnerPrevIndex = nextIndex;
			nextIndex = nextIndex + Integer.valueOf(nextChar);
			if (nextIndex < tempInnerPrevIndex && isInfiniteLoop(tempInnerPrevIndex, nextIndex)) {
				return true;
			}
			nextChar = board[nextIndex];

		}

		return false;
	}

}
