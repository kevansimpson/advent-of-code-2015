package org.base.advent.util;

/**
 * Represents an x,y coordinate pair.
 */
public class Coord {
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

	public static Coord coord(String commaDelimitedValues) {
		String[] values = commaDelimitedValues.split(",");
		return new Coord(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
	}
}
