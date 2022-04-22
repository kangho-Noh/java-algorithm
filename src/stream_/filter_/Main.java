package stream_.filter_;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		Stream<Integer> stream = IntStream.range(1,10).boxed();
		stream.filter(v->((v%2) == 0))
			.forEach(System.out::println);
	}
}

