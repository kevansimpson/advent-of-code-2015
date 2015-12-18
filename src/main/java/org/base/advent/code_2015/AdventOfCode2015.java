package org.base.advent.code_2015;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.reflections.Reflections;

/**
 * 2015 Advent of Code
 *
 */
public class AdventOfCode2015 {
	public static void main(String[] args) throws Exception {
		List<AdventDay> solutions = loadSolutions();
		
		for (AdventDay answer : solutions) {
			System.out.println(answer.getClass().getSimpleName());
			answer.solvePuzzle1();
			answer.solvePuzzle2();
			System.out.println("=====");
		}
	}

	public static String readInput(String resource) throws Exception {
		return FileUtils.readFileToString(new File(
				AdventOfCode2015.class.getResource(resource).toURI()));
	}

	public static List<String> readLines(String resource) throws Exception {
		return FileUtils.readLines(new File(
				AdventOfCode2015.class.getResource(resource).toURI()));
	}

	private static List<AdventDay> loadSolutions() throws Exception {
		Reflections reflections = new Reflections("org.base.advent.code_2015.answer");
		Set<Class<? extends AdventDay>> solutionClasses = reflections.getSubTypesOf(AdventDay.class);
		
		List<AdventDay> solutions = new ArrayList<AdventDay>();
		
		for (Class<? extends AdventDay> type : solutionClasses) {
			solutions.add(type.newInstance());
		}
		
		return solutions;
	}
}
