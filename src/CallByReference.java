public class CallByReference {
	public static void main(String[] args) {

		int[] a = new int[]{0};

		System.out.println(a[0]);

		extracted(a);

		System.out.println(a[0]);
	}

	private static void extracted(int[] a) {
		a[0]+=1;
		System.out.println("func: " + a[0]);
	}
}
