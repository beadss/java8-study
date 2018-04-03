package beadss;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lotto {

	private static int lottoNumberCount = 6;

	private List<Integer> numberList;

	public Lotto(List<Integer> numberList) {
		if(numberList == null || numberList.size() < lottoNumberCount) {
			throw new IllegalArgumentException("use brain");
		}

		this.numberList = numberList;
	}

	public List<Integer> getNumberList() {
		return numberList;
	}

	public long matchedCount(Lotto other) {
		return getNumberList().stream().filter(other::contains).count();
	}

	private boolean contains(int number) {
		return getNumberList().contains(number);
	}

	@Override
	public String toString() {
		return getNumberList().toString();
	}

	public static Lotto parse(String numberString) {
		return Arrays.stream(numberString.split(","))
				.map(String::trim)
				.map(Integer::parseInt)
				.distinct() //TODO: distinct 이하를 공용 로직으로 빼야함
				.limit(lottoNumberCount)
				.collect(Lotto.make());
	}

	public static Lotto generateRandomLotto() {
		return Stream
				.generate(() -> new Random().nextInt(44) + 1)
				.distinct() //TODO: distinct 이하를 공용 로직으로 빼야함
				.limit(lottoNumberCount)
				.collect(Lotto.make());
	}

	public static Collector<Integer, ?, Lotto> make() {
		//TODO: limit랑 distinct 걸어야되는데, 어떻게 걸지?!
		return Collectors.collectingAndThen(Collectors.toList(), Lotto::new);
		/*
		return numberStream
			.distinct()
			.limit(lottoNumberCount)
			.collect();
			*/
	}
}
