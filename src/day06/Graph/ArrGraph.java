package day06.Graph;
//출처 : https://freestrokes.tistory.com/87
public class ArrGraph {
	private int[][] arrGraph;

	// 그래프 초기화
	public ArrGraph(int initSize) {
		// 그래프 초기화
		// put(int x, int y) 에서 입력되는 정점의 값은 0 이상의 정수이나
		// 배열의 index는 0 부터 시작이므로
		// ArrayIndexOutOfBoundsException 방지를 위해
		// 정점을 담는 인접행렬의 행과 열 size는 1을 더하여 초기화해줌
		this.arrGraph = new int[initSize+1][initSize+1];
	}

	// 그래프 return
	public int[][] getGraph() {
		return this.arrGraph;
	}

	// 그래프 추가 (양방향)
	public void put(int x, int y) {
		arrGraph[x][y] = arrGraph[y][x] = 1;
	}

	// 그래프 추가 (단방향)
	public void putSingle(int x, int y) {
		arrGraph[x][y] = 1;
	}

	// 그래프 출력 (인접행렬)
	public void printGraphToAdjArr() {
		for(int i=0; i<arrGraph.length; i++) {
			for(int j=0; j<arrGraph[i].length; j++) {
				System.out.print(" " + arrGraph[i][j]);
			}
			System.out.println();
		}
	}
}
