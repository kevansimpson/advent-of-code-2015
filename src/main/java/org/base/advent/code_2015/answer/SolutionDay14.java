package org.base.advent.code_2015.answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.base.advent.code_2015.AdventDay;
import org.base.advent.code_2015.AdventOfCode2015;

/**
 *
 */
public class SolutionDay14 implements AdventDay {

	
	public void solvePuzzle1() throws Exception {
		List<String> input = AdventOfCode2015.readLines("/day14input.txt");
		Map<String, ReindeerSpeed> speedMap = buildSpeedMap(input);
		TreeMap<Integer, List<String>> distanceMap = buildDistanceMap(speedMap, 2503);

		System.out.println(String.format("The winning reindeer has traveled %d miles", distanceMap.lastKey()));
	}

	public void solvePuzzle2() throws Exception {
		List<String> input = AdventOfCode2015.readLines("/day14input.txt");
		Map<String, ReindeerSpeed> speedMap = buildSpeedMap(input);
		Map<String, String> pointMap = new HashMap<>();

		for (String reindeer : speedMap.keySet()) {
			pointMap.put(reindeer, "");
		}

		for (int i = 1; i < 2504; i++) {
			List<String> winners = identifyWinner(speedMap, i);
//			System.out.println("winner @ "+ i +" = "+ winnerAt);
			for (String winnerAt : winners)
				pointMap.put(winnerAt, pointMap.get(winnerAt).concat("."));
		}

		int highest = 0;
		String winner = null;
		for (String reindeer : pointMap.keySet()) {
			int points = pointMap.get(reindeer).length();
			if (points > highest) {
				winner = reindeer;
				highest = points;
			}
		}

		// > 1075 - didn't account for ties
		System.out.println(String.format("The winning reindeer %s has %d points", winner, highest));
	}

	List<String> identifyWinner(Map<String, ReindeerSpeed> speedMap, int seconds) {
		TreeMap<Integer, List<String>> distanceMap = buildDistanceMap(speedMap, seconds);
		return distanceMap.get(distanceMap.lastKey());
	}

	/* Snapshot */
	TreeMap<Integer, List<String>> buildDistanceMap(Map<String, ReindeerSpeed> speedMap, int seconds) {
		TreeMap<Integer, List<String>> distanceMap = new TreeMap<>();
		for (String reindeer : speedMap.keySet()) {
			int dist = calculateDistance(speedMap.get(reindeer), seconds);
			if (!distanceMap.containsKey(dist)) {
				distanceMap.put(dist, new ArrayList<>());
			}
			distanceMap.get(dist).add(reindeer);
		}

		return distanceMap;
	}

	int calculateDistance(ReindeerSpeed speed, int seconds) {
		int totalTime = speed.goTime + speed.restTime;
		return ((seconds / totalTime) * speed.goTime * speed.kmPerSec +
				(Math.min((seconds % totalTime), speed.goTime) * speed.kmPerSec));
	}

	
	private static Pattern parser = Pattern.compile(
			"(.+)\\scan.+\\s(\\d+)\\skm.+\\s(\\d+)\\sseconds.*\\s(\\d+)\\s.+", Pattern.DOTALL);

	Map<String, ReindeerSpeed> buildSpeedMap(List<String> speeds) {
		Map<String, ReindeerSpeed> speedMap = new HashMap<>();
		for (String directive : speeds) {
			Matcher matcher = parser.matcher(directive);
			if (matcher.matches()) {
				int kmPerSec = Integer.parseInt(matcher.group(2));
				int goTime = Integer.parseInt(matcher.group(3));
				int restTime = Integer.parseInt(matcher.group(4));
				speedMap.put(matcher.group(1), new ReindeerSpeed(kmPerSec, goTime, restTime));
			}
			else {
				System.err.println("No match: "+ directive);
			}
		}

		return speedMap;
	}

	void debug(Map<String, ReindeerSpeed> speedMap) {
		for (String reindeer : speedMap.keySet())
			System.out.println(reindeer +" ==> "+ speedMap.get(reindeer));
	}

	private static class ReindeerSpeed {
		public final int kmPerSec, goTime, restTime;
		public ReindeerSpeed(int velocity, int go, int rest) {
			this.kmPerSec = velocity;
			this.goTime = go;
			this.restTime = rest;
		}
		
		@Override
		public String toString() {
			return Arrays.asList(kmPerSec, goTime, restTime).toString();
		}
	}
}
