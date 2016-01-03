package org.base.advent.code_2015.answer;

import org.base.advent.code_2015.AdventDay;
import org.base.advent.code_2015.AdventOfCode2015;

/**
 *
 */
public class SolutionDay17 implements AdventDay {

	int[] cans;
	int numberOfCans = 0;
	int totalPermutations = 0;
	int fewestCans = Integer.MAX_VALUE;
	int totalPermutationsWithFewest = 0;

	public void solvePuzzle1() throws Exception {
		cans = AdventOfCode2015.readLines("/day17input.txt")
				.stream().map(Integer::parseInt).mapToInt(i->i).toArray();
		this.numberOfCans = cans.length;
		boolean[] permutation = new boolean[this.numberOfCans];
		
		sumCans(permutation, 0);
		System.out.println("Total permutations of cans is "+ this.totalPermutations);
	}

	public void solvePuzzle2() throws Exception {
		System.out.println("Total permutations using fewest cans is "+ this.totalPermutationsWithFewest);
	}

	void sumCans(boolean[] permutation, int index) {
		if (index >= numberOfCans) {
			if (sum(permutation) == 150) {
//				System.out.println(ArrayUtils.toString(debug(permutation)));
				totalPermutations += 1;

				int used = used(permutation);
				if (used < fewestCans) {
					fewestCans = used;
					totalPermutationsWithFewest = 1;
				}
				else if (used == fewestCans) {
					totalPermutationsWithFewest += 1;
				}
			}
			return;
		}

		boolean[] off = new boolean[numberOfCans];
		boolean[] on = new boolean[numberOfCans];
		
		System.arraycopy(permutation, 0, off, 0, numberOfCans);
		System.arraycopy(permutation, 0, on, 0, numberOfCans);

		sumCans(off, 1 + index);
		on[index] = true;
		sumCans(on, 1 + index);
	}

	int sum(boolean[] permutation) {
		int sum = 0;
		for (int i = 0; i < numberOfCans; i++)
			sum += (permutation[i]) ? cans[i] : 0;

		return sum;
	}

	int used(boolean[] permutation) {
		int used = 0;
		for (int i = 0; i < numberOfCans; i++)
			used += (permutation[i]) ? 1 : 0;

		return used;
	}

	int[] debug(boolean[] permutation) {
		int[] debug = new int[permutation.length];
		for (int i = 0; i < numberOfCans; i++)
			debug[i] = permutation[i] ? cans[i] : 0;

		return debug;
	}
}
