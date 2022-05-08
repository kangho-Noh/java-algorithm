package pgsummer2022.p2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
}

class Solution {
	int target;

	public String[] solution(String[] rooms, int target) {
		this.target = target;
		List<Staff> staffList = new ArrayList<>();
		HashMap<String, Integer> hashMap = new HashMap<>();
		Set<String> alreadyHas = new HashSet<>();

		for (String room : rooms) {
			String r = room.replace("]", ",");
			r = r.substring(1);
			String[] split = r.split(",");
			int roomNumber = Integer.parseInt(split[0]);

			for (int i = 1; i < split.length; i++) {
				String name = split[i];
				if (roomNumber == target) {
					//그 방에 자리 있으면 제외
					alreadyHas.add(name);
					continue;
				}
				if (hashMap.containsKey(name)) {
					int ind = hashMap.get(name);
					Staff staff = staffList.get(ind);
					staff.seatNumber++;
					staff.distanceToTarget = Math.min(staff.distanceToTarget, Math.abs(roomNumber-target));
				} else {
					staffList.add(new Staff(name, roomNumber));
					hashMap.put(name, staffList.size() - 1);
				}
			}
		}

		Collections.sort(staffList);
		int cnt = 0;
		for (Staff staff : staffList) {
			if (alreadyHas.contains(staff.name)) {
				cnt++;
			}
		}

		String[] answer = new String[staffList.size() - cnt];
		int i = 0;
		for (Staff staff : staffList) {
			String name = staff.name;
			if(!alreadyHas.contains(name)){
				answer[i++] = name;
			}
		}
		return answer;
	}

	class Staff implements Comparable<Staff> {
		String name;
		int roomNumber;
		int distanceToTarget;
		int seatNumber;

		public Staff(String name, int roomNumber) {
			this.name = name;
			this.roomNumber = roomNumber;
			this.distanceToTarget = Math.abs(roomNumber - target);
			this.seatNumber = 1;
		}

		@Override
		public int compareTo(Staff o) {
			int ret = Integer.compare(seatNumber, o.seatNumber);
			if (ret == 0) {
				ret = Integer.compare(distanceToTarget, o.distanceToTarget);
				if (ret == 0) {
					ret = name.compareTo(o.name);
				}
			}
			return ret;
		}
	}
}