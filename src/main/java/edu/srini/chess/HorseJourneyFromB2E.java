package edu.srini.chess;

import java.util.LinkedList;
import java.util.Scanner;


public class HorseJourneyFromB2E {
	static final char DOT = '.';
	static final char TAKEN = '#';
	static char[][] m;
	static int W;
	static int H;
	static int moveCount = 0;
	static boolean moveMade = false;
	static Position bp = null;
	static MovementPossibility openSteps;
	static Position ePos = null;
	static LinkedList<Position> trackB = new LinkedList<Position>();
	private static Scanner scanner;;

	enum DIR {
		NORTH_EAST, NORTH_WEST, EAST_NORTH, EAST_SOUTH, SOUTH_EAST, SOUTH_WEST, WEST_NORTH, WEST_SOUTH, EAST, WEST, NORTH, SOUTH
	};

	public static void main(String args[]) {

		scanner = new Scanner(System.in);
		W = scanner.nextInt();
		H = scanner.nextInt();
		m = new char[H][W];
		for (int i = 0; i < H; i++) {
			String ROW = scanner.next();

			for (int j = 0; j < W; j++) {
				m[i][j] = ROW.charAt(j);
			}

		}
		printMatrix();
		System.out.println();

		bp = getPosition('B');
		trackB.add(new Position(bp));
		ePos = getPosition('E');

		System.out.println("'B' Position --> " + bp);
		System.out.println("'E' Position --> " + ePos);

		executeB2EMovement(ePos);

	}

	private static void executeB2EMovement(Position ep) {

		while (true) {
			moveMade = false;
			openSteps = new MovementPossibility();

			fillNextMoves();

			System.out.println(openSteps);

			if (openSteps.isMovementPossible()) {

				// To North
				if (bp.getX() > ep.getX()) {

					if (bp.getY() > ep.getY()) {
						moveTowards(DIR.NORTH_WEST);

					} else if (bp.getY() < ep.getY()) {
						moveTowards(DIR.NORTH_EAST);
					} else {
						moveTowards(DIR.NORTH);
					}

					// To South
				} else if (ep.getX() > bp.getX()) {

					if (bp.getY() > ep.getY()) {
						moveTowards(DIR.SOUTH_WEST);
					} else if (bp.getY() < ep.getY()) {
						moveTowards(DIR.SOUTH_EAST);
					} else {
						moveTowards(DIR.SOUTH);
					}

					// on same row
				} else if (bp.getX() == ep.getX()) {

					if (bp.getY() > ep.getY()) {
						moveTowards(DIR.WEST);

					} else if (bp.getY() < ep.getY()) {
						moveTowards(DIR.EAST);
					}
				}
			} // if any movement possible end

			if (!moveMade) // this can also be B blocked or can not proceed
			// towards E
			{
				System.out.println("No movement possible...quitting!!!");
				System.exit(1);
			} else {

				if (trackB.size() >= 2) {
					if (bp.equals(trackB.get(trackB.size() - 2))) {
						moveCount--;
						m[bp.getX()][bp.getY()] = TAKEN;
						bp = new Position(trackB.get(trackB.size() - 1));
						m[bp.getX()][bp.getY()] = 'B';
						continue;
					}

				}

				if (Math.abs(bp.getX() - ePos.getX()) <= 1 && Math.abs(bp.getY() - ePos.getY()) <= 1) {
					moveCount--;
					m[bp.getX()][bp.getY()] = TAKEN;
					bp = new Position(trackB.get(trackB.size() - 1));
					m[bp.getX()][bp.getY()] = 'B';
					continue;
				}

			}
			trackB.add(new Position(bp));
			printMatrix();
		} // while end
	}

	private static void moveTowards(DIR direction) {

		switch (direction) {
		case NORTH_WEST:
			executeSequence(DIR.NORTH_WEST, DIR.WEST_NORTH, DIR.NORTH_EAST, DIR.WEST_SOUTH, DIR.EAST_NORTH,
					DIR.SOUTH_WEST, DIR.EAST_SOUTH, DIR.SOUTH_EAST);

			break;
		case NORTH_EAST:

			executeSequence(DIR.NORTH_EAST, DIR.EAST_NORTH, DIR.NORTH_WEST, DIR.EAST_SOUTH, DIR.WEST_NORTH,
					DIR.SOUTH_EAST, DIR.WEST_SOUTH, DIR.SOUTH_WEST);
			break;

		case EAST_NORTH:
			executeSequence(DIR.EAST_NORTH, DIR.NORTH_EAST, DIR.EAST_SOUTH, DIR.NORTH_WEST, DIR.SOUTH_EAST,
					DIR.WEST_NORTH, DIR.SOUTH_WEST, DIR.WEST_SOUTH);

			break;
		case EAST_SOUTH:
			executeSequence(DIR.EAST_SOUTH, DIR.SOUTH_EAST, DIR.EAST_NORTH, DIR.SOUTH_WEST, DIR.NORTH_EAST,
					DIR.WEST_SOUTH, DIR.NORTH_WEST, DIR.WEST_NORTH);

			break;

		case SOUTH_WEST:
			executeSequence(DIR.SOUTH_WEST, DIR.WEST_SOUTH, DIR.SOUTH_EAST, DIR.WEST_NORTH, DIR.EAST_SOUTH,
					DIR.NORTH_WEST, DIR.EAST_NORTH, DIR.NORTH_EAST);
			break;

		case SOUTH_EAST:
			executeSequence(DIR.SOUTH_EAST, DIR.EAST_SOUTH, DIR.SOUTH_WEST, DIR.EAST_NORTH, DIR.NORTH_EAST,
					DIR.WEST_SOUTH, DIR.NORTH_WEST, DIR.WEST_NORTH);
			break;

		case WEST_NORTH:
			executeSequence(DIR.WEST_NORTH, DIR.NORTH_WEST, DIR.WEST_SOUTH, DIR.NORTH_EAST, DIR.SOUTH_WEST,
					DIR.EAST_NORTH, DIR.SOUTH_EAST, DIR.EAST_SOUTH);
			break;

		case WEST_SOUTH:
			executeSequence(DIR.WEST_SOUTH, DIR.SOUTH_WEST, DIR.WEST_NORTH, DIR.SOUTH_EAST, DIR.NORTH_WEST,
					DIR.EAST_SOUTH, DIR.NORTH_EAST, DIR.EAST_NORTH);
			break;
		case NORTH:
			executeSequence(DIR.NORTH_EAST, DIR.NORTH_WEST, DIR.EAST_NORTH, DIR.WEST_NORTH, DIR.EAST_SOUTH,
					DIR.WEST_SOUTH, DIR.SOUTH_EAST, DIR.SOUTH_WEST);
			break;
		case SOUTH:
			executeSequence(DIR.SOUTH_EAST, DIR.SOUTH_WEST, DIR.EAST_SOUTH, DIR.WEST_SOUTH, DIR.EAST_NORTH,
					DIR.WEST_NORTH, DIR.NORTH_EAST, DIR.NORTH_WEST);
			break;
		case EAST:
			executeSequence(DIR.EAST_NORTH, DIR.EAST_SOUTH, DIR.NORTH_EAST, DIR.SOUTH_EAST, DIR.NORTH_WEST,
					DIR.SOUTH_WEST, DIR.WEST_NORTH, DIR.WEST_SOUTH);
			break;
		case WEST:
			executeSequence(DIR.WEST_NORTH, DIR.WEST_SOUTH, DIR.NORTH_WEST, DIR.SOUTH_WEST, DIR.NORTH_EAST,
					DIR.SOUTH_EAST, DIR.EAST_NORTH, DIR.EAST_SOUTH);
			break;

		}
	}

	private static void executeSequence(DIR d1, DIR d2, DIR d3, DIR d4, DIR d5, DIR d6, DIR d7, DIR d8) {
		tryMove(d1);
		if (!moveMade) {
			tryMove(d2);
			if (!moveMade) {
				tryMove(d3);
				if (!moveMade) {
					tryMove(d4);
					if (!moveMade) {
						tryMove(d5);
						if (!moveMade) {
							tryMove(d6);
							if (!moveMade) {
								tryMove(d7);
								if (!moveMade) {
									tryMove(d8);
								}
							}
						}
					}
				}
			}
		}
	}

	private static void tryMove(final DIR direction) {

		switch (direction) {
		case NORTH_WEST:
			if (openSteps.isNWExists()) {
				moveB(openSteps.getPosNW());
			}
			break;
		case NORTH_EAST:
			if (openSteps.isNEExists()) {
				moveB(openSteps.getPosNE());
			}
			break;

		case EAST_NORTH:
			if (openSteps.isENExists()) {
				moveB(openSteps.getPosEN());
			}
			break;

		case EAST_SOUTH:
			if (openSteps.isESExists()) {
				moveB(openSteps.getPosES());
			}
			break;

		case SOUTH_WEST:
			if (openSteps.isSWExists()) {
				moveB(openSteps.getPosSW());
			}
			break;

		case SOUTH_EAST:
			if (openSteps.isSEExists()) {
				moveB(openSteps.getPosSE());
			}
			break;

		case WEST_NORTH:
			if (openSteps.isWNExists()) {
				moveB(openSteps.getPosWN());
			}
			break;
		case WEST_SOUTH:
			if (openSteps.isWSExists()) {
				moveB(openSteps.getPosWS());
			}
			break;
		default:
			break;

		}
	}

	private static void moveB(Position posNW) {
		moveCount++;
		moveMade = true;

		int x = posNW.getX();
		int y = posNW.getY();
		// Success case
		if (m[x][y] == 'E') {
			System.out.println("No of steps: " + moveCount);
			System.exit(0);
		}

		// Change B position
		m[bp.getX()][bp.getY()] = DOT;
		m[x][y] = 'B';

		bp.setX(x);
		bp.setY(y);

	}

	private static void fillNextMoves() {
		int curRow = bp.getX();
		int curCol = bp.getY();
		openSteps.reset();

		// North
		if (curRow - 2 >= 0) {
			// Left
			if ((curCol - 1 >= 0) && (m[curRow - 2][curCol - 1] != TAKEN)) {
				openSteps.setPosNW(new Position(curRow - 2, curCol - 1));
			}
			// Right
			if ((curCol + 1 < W) && (m[curRow - 2][curCol + 1] != TAKEN)) {
				openSteps.setPosNE(new Position(curRow - 2, curCol + 1));
			}
		}

		// South
		if (curRow + 2 < H) {
			// Left
			if ((curCol - 1 >= 0) && (m[curRow + 2][curCol - 1] != TAKEN)) {
				openSteps.setPosSW(new Position(curRow + 2, curCol - 1));
			}
			// Right
			if ((curCol + 1 < W) && (m[curRow + 2][curCol + 1] != TAKEN)) {
				openSteps.setPosSE(new Position(curRow + 2, curCol + 1));
			}
		}

		// East
		if (curCol + 2 < W) {
			// Left
			if ((curRow - 1 >= 0) && (m[curRow - 1][curCol + 2] != TAKEN)) {
				openSteps.setPosEN(new Position(curRow - 1, curCol + 2));
			}
			// Right
			if ((curRow + 1 < H) && (m[curRow + 1][curCol + 2] != TAKEN)) {
				openSteps.setPosES(new Position(curRow + 1, curCol + 2));
			}
		}

		// West
		if (curCol - 2 >= 0) {
			// Left
			if ((curRow - 1 >= 0) && (m[curRow - 1][curCol - 2] != TAKEN)) {
				openSteps.setPosWN(new Position(curRow - 1, curCol - 2));
			}
			// Right
			if ((curRow + 1 < H) && (m[curRow + 1][curCol - 2] != TAKEN)) {
				openSteps.setPosWS(new Position(curRow + 1, curCol - 2));
			}
		}

	}

	private static Position getPosition(char c) {

		Position p = null;
		for (int i = 0; i < H; i++) {

			for (int j = 0; j < W; j++) {
				if (m[i][j] == c) {
					p = new Position(i, j);
					break;
				}
			}
		}
		return p;
	}

	private static void printMatrix() {
		for (int i = 0; i < H; i++) {
			System.out.println();
			for (int j = 0; j < W; j++) {
				System.out.print(m[i][j] + " ");

			}
		}

	}
}