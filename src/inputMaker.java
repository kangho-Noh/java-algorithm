public class inputMaker {
	public static void main(String[] args) {
		String str = "[\"Rotate\", \"ShiftRow\"]";

		String s = str.replaceAll("\\[", "{");
		s = s.replaceAll("]", "}");

		System.out.println(s);
	}
}
