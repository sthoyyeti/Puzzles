package edu.srini.compress;

import java.util.Scanner;

/*
 * 1
 * 1 1
 * 2 1
 * 1 2 1 1
 * 1 1 1 2 2 1
 * 3 1 2 2 1 1
 * 1 3 1 1 2 2 2 1
 * 1 1 1 3 2 1 3 2 1 1
 * 3 1 1 3 1 2 1 1 1 3 1 2 2 1
 */
public class CompressionLogic {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int seed = scanner.nextInt();
		int line = scanner.nextInt();
		scanner.close();
		System.out.println(new CompressionLogic().getNthCompressedLine(seed, line));
	}

	public String getNthCompressedLine(int seed, int line) {
		return (isValidInput(seed, line)) ? fetchCompressedLine(seed, line) : "";
	}

	private boolean isValidInput(int seed, int line) {
		System.out.printf("seed = %d, line=%d\n", seed, line);
		if ((seed < 0 || seed >= 100) || (line < 0 || line > 25)) {
			System.out.println("Invalid input... 0 < seed < 100 and 0 < line <= 25");
			throw new IllegalArgumentException("Invalid input... Expecting: 0 < seed < 100 and 0 < line <= 25");
		}
		return true;
	}

	private String fetchCompressedLine(int seed, int line) {
		StringBuffer curr = new StringBuffer();
		StringBuffer next = new StringBuffer();

		curr.append(seed);
		Scanner scan;
		for (int i = 2; i <= line; i++) {
			scan = new Scanner(curr.toString());
			int count = 0;
			int curNum = -1, prevNum = -1;
			while (scan.hasNext()) {
				// read next int in current buffer
				curNum = scan.nextInt();
				// rise counter
				if (count == 0 || curNum == prevNum) {
					count++;
				} // prev num is different from current number then write
					// current (count,number)
				else if ((prevNum != -1 && curNum != prevNum)) {
					appendCountNumPair(next, count, prevNum);
					count = 1;
				}
				// end of line reached
				if (!scan.hasNext()) {
					appendCountNumPair(next, count, curNum);
					count = 0;
					prevNum = -1;

				} else {
					prevNum = curNum;
				}
			}
			// copy "next" to "current" and make "next" empty
			curr.delete(0, curr.length());
			curr.append(next.toString());

			next.delete(0, next.length());
		}
		return curr.toString();
	}

	private void appendCountNumPair(StringBuffer sb, int count, int number) {
		sb.append(count);
		sb.append(" ");
		sb.append(number);
		sb.append(" ");
	}
}