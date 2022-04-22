package stream_.map_;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		Stream<Integer> stream = IntStream.range(1,10).boxed();
		stream.filter(v -> ((v%2) == 0))
			.map(v->v*10)
			.forEach(System.out::println);
		// 20, 40, 60, 80
	}
}
