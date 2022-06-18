package codetest.kakaointern2022.p3;

import java.util.ArrayList;
import java.util.List;

public class Main {
}

class Solution {
	public int solution(int alp, int cop, int[][] problems) {
		int answer = 0;
		User user = new User(alp, cop);
		List<Problem> problemList = new ArrayList<>(); //모든 문제들
		int solveCount = 0;
		boolean[] solved = new boolean[problems.length];
		//List<Problem> solvable = new ArrayList<>(); //풀 수 있는 문제들
		int n = problems.length;
		for (int i = 0; i < n; i++) {
			int[] problem = problems[i];
			problemList.add(new Problem(problem[0], problem[1], problem[2], problem[3], problem[4]));
			if (alp >= problem[0] && cop >= problem[1]) {
				solveCount++;
				solved[i] = true;
			}
		}

		// while (solveCount != problems.length) {
		//
		// }

		return answer;
	}

	private class User {
		int alp;
		int cop;

		public User(int alp, int cop) {
			this.alp = alp;
			this.cop = cop;
		}
	}

	private class Problem {
		int alp_req;
		int cop_req;
		int alp_rwd;
		int cop_rwd;
		int cost;
		float alpPerCost;
		float copPerCost;

		public Problem(int alp_req, int cop_req, int alp_rwd, int cop_rwd, int cost) {
			this.alp_req = alp_req;
			this.cop_req = cop_req;
			this.alp_rwd = alp_rwd;
			this.cop_rwd = cop_rwd;
			this.cost = cost;
			alpPerCost = (float)alp_rwd / cost;
			copPerCost = (float)cop_rwd / cost;
		}
	}
}