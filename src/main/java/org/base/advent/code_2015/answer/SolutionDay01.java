package org.base.advent.code_2015.answer;

import org.base.advent.code_2015.AdventDay;
import org.base.advent.code_2015.AdventOfCode2015;

/**
 *
 */
public class SolutionDay01 implements AdventDay {

	public void solvePuzzle1() throws Exception {
		String instructions = AdventOfCode2015.readInput("/day01input.txt");
		int totalLength = instructions.length();
		instructions = instructions.replaceAll("\\(", "");
		int down = instructions.length();
		int up = totalLength - down;
		instructions = instructions.replaceAll("\\)", "");
		int remaining = instructions.length();
		System.out.println("Santa is on Floor "+ (up - down - remaining));
	}

	public void solvePuzzle2() throws Exception {
		String instructions = AdventOfCode2015.readInput("/day01input.txt");
		int floor = 0;
		int position = 0;
		for (char ch : instructions.toCharArray()) {
			++position;
			switch (ch) {
				case ')':
					--floor;
					break;
				case '(':
					++floor;
					break;
			}
			if (floor == -1) break;
		}
		
		System.out.println("Santa first reaches basement @ "+ position);
		
	}
}
