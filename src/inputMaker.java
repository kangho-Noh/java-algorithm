public class inputMaker {
	public static void main(String[] args) {
		String str = "[\n"
			+ "\"2016-09-15 20:59:57.421 0.351s\",\n"
			+ "\"2016-09-15 20:59:58.233 1.181s\",\n"
			+ "\"2016-09-15 20:59:58.299 0.8s\",\n"
			+ "\"2016-09-15 20:59:58.688 1.041s\",\n"
			+ "\"2016-09-15 20:59:59.591 1.412s\",\n"
			+ "\"2016-09-15 21:00:00.464 1.466s\",\n"
			+ "\"2016-09-15 21:00:00.741 1.581s\",\n"
			+ "\"2016-09-15 21:00:00.748 2.31s\",\n"
			+ "\"2016-09-15 21:00:00.966 0.381s\",\n"
			+ "\"2016-09-15 21:00:02.066 2.62s\"\n"
			+ "]";

		String s = str.replaceAll("\\[", "{");
		s = s.replaceAll("]", "}");

		System.out.println(s);
	}
}