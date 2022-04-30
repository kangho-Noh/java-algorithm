public class inputMaker {
	public static void main(String[] args) {
		String str = "[[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]";

		String s = str.replaceAll("\\[", "{");
		s = s.replaceAll("]", "}");

		System.out.println(s);
	}
}
