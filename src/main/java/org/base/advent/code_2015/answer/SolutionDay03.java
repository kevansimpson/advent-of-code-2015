package org.base.advent.code_2015.answer;

import java.util.HashMap;
import java.util.Map;

import org.base.advent.code_2015.AdventDay;
import org.base.advent.code_2015.AdventOfCode2015;

/**
 *
 */
public class SolutionDay03 implements AdventDay {

	private static final Integer DELIVERED_PRESENT = Integer.valueOf(1);

	private Map<Coord, Integer> presentCount = new HashMap<Coord, Integer>();

	public void solvePuzzle1() throws Exception {
		String directions = AdventOfCode2015.readInput("/day03input.txt");
		followDirections(directions, 0, 1);

		// Wrong answer #1: 8193 (should be lower, caused by Coord not having proper key implementation)
		System.out.println("The number of houses which receive at least one present is "+ presentCount.size());
	}

	public void solvePuzzle2() throws Exception {
		this.presentCount.clear();
		String directions = AdventOfCode2015.readInput("/day03input.txt");
		followDirections(directions, 0, 2);
		followDirections(directions, 1, 2);

		// Wrong answer #1: 3742 (should be lower, caused by not clearing presentCount map b/w puzzles)
		System.out.println("The number of houses which receive at least one present is "+ presentCount.size());
	}

	protected void followDirections(String directions, int startIndex, int increment) {
		// begins by delivering a present to the house at his starting location
		Coord position = new Coord(0, 0);
		deliverPresentAt(position);

		char[] steps = directions.toCharArray();
		for (int index = startIndex, max = steps.length; index < max; index += increment) {
			switch (steps[index]) {
				case '^':
					position = deliverPresentAt(new Coord(position.x, 1 + position.y));
					break;
				case 'v':
					position = deliverPresentAt(new Coord(position.x, position.y - 1));
					break;
				case '<':
					position = deliverPresentAt(new Coord(position.x - 1, position.y));
					break;
				case '>':
					position = deliverPresentAt(new Coord(1 + position.x, position.y));
					break;
			}
		}

	}
	protected Coord deliverPresentAt(Coord coord) {
		presentCount.put(coord, DELIVERED_PRESENT);
		return coord;
	}

	public static class Coord {
		public final int x, y;

		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Coord) {
				Coord c = (Coord) obj;
				return this.x == c.x && this.y == c.y;
			}
			
			return false;
		}

		@Override
		public int hashCode() {
			return 19 + (7 * this.toString().hashCode());
		}

		@Override
		public String toString() {
			return "["+ this.x +","+ this.y +"]";
		}
	}
}
