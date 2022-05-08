package pgsummer2022.p1;

public class Main {
}

class Solution {

	int[] bad = new int[] {81, 36};
	int[] veryBad = new int[] {151, 76};

	public int solution(int[][] atmos) {
		int mask = 0;
		int n = atmos.length;
		for (int i = 0; i < atmos.length; i++) {
			int dust = atmos[i][0];
			int smallDust = atmos[i][1];
			if(dust >= veryBad[0] && smallDust >= veryBad[1]){
				mask++;
			} else if (dust < bad[0] && smallDust < bad[1]) {
				continue;
			}else{
				mask++;
				int j = i+2;
				while(i<j){
					i++;
					if(i<n && atmos[i][0] >= veryBad[0] && atmos[i][1] >= veryBad[1]){
						break;
					}
				}
			}
		}
		return mask;
	}
}