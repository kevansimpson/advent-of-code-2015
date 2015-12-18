package org.base.advent.code_2015.answer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.base.advent.code_2015.AdventDay;
import org.base.advent.code_2015.AdventOfCode2015;

/**
 *
 */
public class SolutionDay02 implements AdventDay {

	private int neededRibbon = 0;
	
	public void solvePuzzle1() throws Exception {
		List<String> dimensions = AdventOfCode2015.readLines("/day02input.txt");
		int total = 0;
		
		for (String dimStr : dimensions) {
			Present p = new Present();
			p.add(Integer.parseInt(StringUtils.substringBefore(dimStr, "x")));
			p.add(Integer.parseInt(StringUtils.substringBetween(dimStr, "x")));
			p.add(Integer.parseInt(StringUtils.substringAfterLast(dimStr, "x")));
			Collections.sort(p);
			total += p.needsWrappingPaper();
			this.neededRibbon += p.needsRibbon();
		}
		System.out.println("Elves should order "+ total +" sq.ft. of wrapping paper");
	}

	public void solvePuzzle2() throws Exception {
		System.out.println("Elves should order "+ this.neededRibbon +" of ribbon");
	}
	
	public static class Present extends ArrayList<Integer> {
		private static final long serialVersionUID = -6669661914404297874L;

		public int needsWrappingPaper() {
			// adding an extra of first side for slack
			return (3 * get(0) * get(1)) + (2 * get(1) * get(2)) + (2 * get(2) * get(0));
		}
		
		public int needsRibbon() {
			return (2 * get(0)) + (2 * get(1)) + /*bow*/ (get(0) * get(1) * get(2));
		}
	}
}
