package codetest.kakaointern2022.p1;

import java.util.HashMap;

public class Main {

}

class Solution {
	final String[] Types = {"RT", "TR", "CF", "FC", "JM", "MJ", "AN", "NA"};// 0 0 1 1 2 2 3 3
	final String[] TypeDetail = {"R", "T", "C", "F", "J", "M", "A", "N"};    // 0 1 0 1 0 1 0 1
	final int[] point = {0, -3, -2, -1, 0, 1, 2, 3}; //survey[0][0] += point[choice]

	public String solution(String[] survey, int[] choices) {
		StringBuilder answer = new StringBuilder();
		HashMap<String, Integer> hashMap = new HashMap<>();
		int[][] finalScore = new int[4][2]; //RT, CF, JM, AN
		for (int i = 0; i < 4; i++) {
			hashMap.put(Types[i * 2], i);
			hashMap.put(Types[i * 2 + 1], i);
		}
		for (int i = 0; i < 8; i++) {
			hashMap.put(TypeDetail[i], i % 2);
		}
		for (int i = 0; i < survey.length; i++) {
			String surv = survey[i];
			int choice = choices[i];
			String positiveType = surv.substring(1);
			int y = hashMap.get(surv);
			int x = hashMap.get(positiveType);
			finalScore[y][x] += point[choice];
		}

		//점수 계산 및 산출
		char[] left = {'R','C','J','A'};
		char[] right = {'T','F','M','N'};
		for (int i = 0; i < 4; i++) {
			if(finalScore[i][0] < finalScore[i][1]){
				answer.append(right[i]);
			}else if(finalScore[i][0] > finalScore[i][1]){
				answer.append(left[i]);
			}else{
				if(left[i] < right[i]){
					answer.append(left[i]);
				}else{
					answer.append(right[i]);
				}
			}
		}

		return answer.toString();
	}
}
