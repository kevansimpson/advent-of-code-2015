package org.base.advent.code_2015.answer;

import java.util.List;

import org.base.advent.code_2015.AdventDay;
import org.base.advent.code_2015.AdventOfCode2015;

/**
 *
 */
public class SolutionDay18 implements AdventDay {

	private boolean[][] currentGrid;

	public void solvePuzzle1() throws Exception {
		List<String> gridLines = AdventOfCode2015.readLines("/day18input.txt");

		loadGrid(gridLines);
		displayGrid(currentGrid);
		for (int i = 0; i < 100; i++) {
			click(false);
//			displayGrid(currentGrid);
		}
		System.out.println("Total # of lights on is "+ countLigths(currentGrid));
	}

	public void solvePuzzle2() throws Exception {
		List<String> gridLines = AdventOfCode2015.readLines("/day18input.txt");

		loadGrid(gridLines);
		// turn on corners
		breakCornerLights();
		displayGrid(currentGrid);
		
		for (int i = 0; i < 100; i++) {
			click(true);
//			displayGrid(currentGrid);
		}
		System.out.println("Total # of lights on when corners are stuck on is "+ countLigths(currentGrid));
	}

	void click(boolean hasBrokenCornerLights) {
		int size = currentGrid.length;
		boolean[][] next = new boolean[size][size];

		for (int i = 1; i <= size - 2; i++) {
			for (int j = 1; j <= size - 2; j++) {
				boolean isOn = currentGrid[i][j];
				int neighborsOn = countNeighbors(i, j);
				next[i][j] = (isOn) ? neighborsOn ==2 || neighborsOn ==3 : neighborsOn ==3;
			}
		}

		synchronized (this) {
			currentGrid = next;
			if (hasBrokenCornerLights)
				breakCornerLights();
		}
	}

	void breakCornerLights() {
		int size = currentGrid.length;
		currentGrid[1][1] = true;
		currentGrid[1][size - 2] = true;
		currentGrid[size - 2][1] = true;
		currentGrid[size - 2][size - 2] = true;
	}

	int countNeighbors(int i, int j) {
		int on = 0;
		on += this.currentGrid[i - 1][j - 1] ? 1 : 0;
		on += this.currentGrid[i][j - 1] ? 1 : 0;
		on += this.currentGrid[i + 1][j - 1] ? 1 : 0;
		on += this.currentGrid[i - 1][j] ? 1 : 0;
		on += this.currentGrid[i + 1][j] ? 1 : 0;
		on += this.currentGrid[i - 1][j + 1] ? 1 : 0;
		on += this.currentGrid[i][j + 1] ? 1 : 0;
		on += this.currentGrid[i + 1][j + 1] ? 1 : 0;
		return on;
	}

	void displayGrid(boolean[][] grid) {
		for (int i = 1; i <= grid.length - 2; i++) {
			for (int j = 1; j <= grid.length - 2; j++) {
				System.out.print((grid[i][j]) ? "#" : ".");
			}
			System.out.println("");
		}

		System.out.println("\n-----\n");
	}

	void loadGrid(List<String> lines) {
		int size = lines.size();
		this.currentGrid = new boolean[size + 2][size + 2];
		for (int i = 0; i < size; i++) {
			char[] bits = lines.get(i).toCharArray();
			for (int j = 0; j < size; j++) {
				this.currentGrid[i + 1][j + 1] = (bits[j] == '#');
			}
		}
	}

	int countLigths(boolean[][] grid) {
		int on = 0;
		for (int i = 1; i <= grid.length - 2; i++) {
			for (int j = 1; j <= grid.length - 2; j++) {
				on += (grid[i][j]) ? 1 : 0;
			}
		}
		
		return on;
	}

}
