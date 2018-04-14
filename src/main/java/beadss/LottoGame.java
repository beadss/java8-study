package beadss;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGame {
	private static int price = 1000;

	private List<Lotto> expectedLottoList;

	public LottoGame(int amount) {
		this.expectedLottoList = buyLotto(amount);
	}

	public LottoResult processResult(Lotto correctLotto) {
		return expectedLottoList
				.stream()
				.map(correctLotto::matchedCount)
				.filter(Rank::hasValue)
				.collect(Collectors.collectingAndThen(
						Collectors.groupingBy(Rank::getRank, Collectors.counting()),
						LottoResult::new));
	}

	public static int getPrice() {
		return price;
	}

	private List<Lotto> buyLotto(int amount) {
		return Stream.generate(Lotto::generateRandomLotto)
						.limit(amount/price)
						.peek(System.out::println)
						.collect(Collectors.toList());
	}
}
