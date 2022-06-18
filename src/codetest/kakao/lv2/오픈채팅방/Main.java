package codetest.kakao.lv2.오픈채팅방;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.solution(
			new String[] {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan"}));
	}

	class Solution {
		public String[] solution(String[] record) {
			String[] answer = {};
			ArrayList<String> ans = new ArrayList<>();
			HashMap<String, String> nickname = new HashMap<>();
			//마지막에 answer을 만들 때 필요한 것
			//record 하면서 userid만 answer에 일단 적음
			//마지막에 record 순회하면서 userid -> nickname으로 바꿔주고,
			//enter로 들어왔습니다, 나갔습니다 구분해주기
			ArrayList<Boolean> enter = new ArrayList<>(); //true -> 들어왔습니다, false -> 나갔습니다.

			for (String s : record) {
				String[] split = s.split(" ");
				String cmd = split[0];
				String userid = split[1];
				if (cmd.equals("Enter")) {
					//ans에 userid 추가하고, enter에 true 추가
					//userid에 대한 nickname 업데이트
					String name = split[2];
					nickname.put(userid, name);
					ans.add(userid);
					enter.add(true);
				} else if (cmd.equals("Leave")) {
					//ans에 userid 추가하고, enter에 false 추가
					ans.add(userid);
					enter.add(false);
				} else if (cmd.equals("Change")) {
					//nickname 업데이트
					String name = split[2];
					nickname.put(userid, name);
				}
			}
			answer = new String[ans.size()];
			for (int i = 0; i < answer.length; i++) {
				if (enter.get(i)) {
					answer[i] = nickname.get(ans.get(i)) + "님이 들어왔습니다.";
				} else {
					answer[i] = nickname.get(ans.get(i)) + "님이 나갔습니다.";
				}
			}

			return answer;
		}
	}
}
