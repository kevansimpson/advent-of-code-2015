package org.base.advent.code_2015.answer;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.base.advent.code_2015.AdventDay;
import org.base.advent.code_2015.AdventOfCode2015;
import org.base.advent.util.Coord;

/**
 *
 */
public class SolutionDay06 implements AdventDay {

	private enum Cmd { on, off, toggle; }

	private static Pattern parser = Pattern.compile(
			"(toggle|turn on|turn off)\\s([\\d,]+)\\sthrough\\s([\\d,]+)", Pattern.DOTALL);

	public void solvePuzzle1() throws Exception {
		List<String> directions = AdventOfCode2015.readLines("/day06input.txt");
		boolean[][] lightGrid = new boolean[1000][1000];

		for (String directive : directions) {
			Matcher matcher = parser.matcher(directive);
			if (matcher.matches()) {
				Cmd cmd = parseCmd(matcher.group(1));
				Coord start = Coord.coord(matcher.group(2));
				Coord end = Coord.coord(matcher.group(3));
				
				for (int x = start.x; x <= end.x; x++) {
					for (int y = start.y; y <= end.y; y++) {
						switch (cmd) {
							case on:
								lightGrid[x][y] = true;
								break;
							case off:
								lightGrid[x][y] = false;
								break;
							case toggle:
								lightGrid[x][y] = !lightGrid[x][y];
								break;
						}
					}
				}
			}
			else {
				System.err.println("No match: "+ directive);
			}
		}
		
		int lightsOn = 0;
		for (int x = 0; x < 1000; x++) {
			for (int y = 0; y < 1000; y++) {
				if (lightGrid[x][y]) ++lightsOn;
			}
		}
		
		System.out.println("There are "+ lightsOn +" lights on");
	}

	public void solvePuzzle2() throws Exception {
		List<String> directions = AdventOfCode2015.readLines("/day06input.txt");
		int[][] lightGrid = new int[1000][1000];

		for (String directive : directions) {
			Matcher matcher = parser.matcher(directive);
			if (matcher.matches()) {
				Cmd cmd = parseCmd(matcher.group(1));
				Coord start = Coord.coord(matcher.group(2));
				Coord end = Coord.coord(matcher.group(3));
				
				for (int x = start.x; x <= end.x; x++) {
					for (int y = start.y; y <= end.y; y++) {
						int value = lightGrid[x][y];
						switch (cmd) {
							case on:
								lightGrid[x][y] = value + 1;
								break;
							case off:
								lightGrid[x][y] = Math.max(value - 1, 0);
								break;
							case toggle:
								lightGrid[x][y] = value + 2;
								break;
						}
					}
				}
			}
			else {
				System.err.println("No match: "+ directive);
			}
		}
		
		int lightsOn = 0;
		for (int x = 0; x < 1000; x++) {
			for (int y = 0; y < 1000; y++) {
				lightsOn += lightGrid[x][y];
			}
		}
		
		// Wrong answer #1: 14190930 (too low, caused by not accounting for minimum brightness of 0)
		System.out.println("The total brightness is "+ lightsOn);
	}

	protected Cmd parseCmd(String text) {
		return Cmd.valueOf(text.replace("turn ", "").trim());
	}
}
