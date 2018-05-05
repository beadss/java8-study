package beadss;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lotto {
	public class Match {
		private long count;
		private boolean bonusMatched;

		private Match(long count, boolean bonusMatched) {
			this.count = count;
			this.bonusMatched = bonusMatched;
		}

		public long getCount() {return count;}
		public boolean isBonusMatched() {return bonusMatched;}
	}

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

	public Match match(Lotto other, int bonusNumber) {
		final long matchCount = getNumberList().stream().filter(other::contains).count();
		final boolean bonusMatched = other.contains(bonusNumber);

		return new Match(matchCount, bonusMatched);
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
				.distinct()
				.limit(lottoNumberCount)
				.collect(Lotto.make());
	}

	public static Lotto generateRandomLotto() {
		return generateRandomLottoNumber()
				.collect(Lotto.make());
	}

	static Stream<Integer> generateRandomLottoNumber() {
		return Stream
				.generate(() -> new Random().nextInt(44) + 1)
				.distinct()
				.limit(lottoNumberCount);
	}

	public static Collector<Integer, ?, Lotto> make() {
		return Collectors.collectingAndThen(Collectors.toList(), Lotto::new);
	}
}
