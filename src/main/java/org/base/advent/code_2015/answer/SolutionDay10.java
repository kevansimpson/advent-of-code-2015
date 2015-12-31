package org.base.advent.code_2015.answer;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.base.advent.code_2015.AdventDay;
import org.base.advent.code_2015.AdventOfCode2015;

/**
 *
 */
public class SolutionDay10 implements AdventDay {

	public void solvePuzzle1() throws Exception {//492982
		String input = lookAndSay("1321131112", 40);
		System.out.println("The length after 40 iterations is "+ input.length());
	}

	public void solvePuzzle2() throws Exception {
		String input = lookAndSay("1321131112", 50);
		System.out.println("The length after 50 iterations is "+ input.length());
	}

	String lookAndSay(String input, int count) {
		for (int i = 0; i < count; i++) {
			input = lookAndSay(input);
		}
		return input;
	}
	String lookAndSay(String input) {
		int count = 0;
		char digit = input.charAt(0);
		StringBuilder bldr = new StringBuilder();

		for (char ch : input.toCharArray()) {
			if (digit == ch) {
				count +=1;
			}
			else {
				bldr.append(count).append(digit);
				digit = ch;
				count = 1;
//				System.out.println(bldr.toString());
			}
		}

		bldr.append(count).append(digit);
		return bldr.toString();
	}
}
