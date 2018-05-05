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

	public LottoResult processResult(Lotto correctLotto, int bonusNumber) {
		return expectedLottoList
				.stream()
				.map(expected -> correctLotto.match(expected, bonusNumber))
				.filter(this::atLeast)
				.collect(Collectors.collectingAndThen(
						Collectors.groupingBy(this::getRank, Collectors.counting()),
						LottoResult::new));
	}


	public static long getMatchCount(Rank rank) {
		if(rank == Rank.First) {
			return 6;
		} else if(rank == Rank.Second || rank == Rank.Third) {
			return 5;
		} else if(rank == Rank.Fourth) {
			return 4;
		} else if(rank == Rank.Fifth) {
			return 3;
		} else {
			return 0;
		}
	}

	private boolean atLeast(Lotto.Match match) {
		return match.getCount() >= 3;
	}

	private Rank getRank(Lotto.Match match) {
		if(match.getCount() == 6) {
			return Rank.First;
		} else if(match.getCount() == 5 && match.isBonusMatched()) {
			return Rank.Second;
		} else if(match.getCount() == 5) {
			return Rank.Third;
		} else if(match.getCount() == 4) {
			return Rank.Fourth;
		} else if(match.getCount() == 3) {
			return Rank.Fifth;
		} else {
			return null;
		}
	}

	private List<Lotto> buyLotto(int amount) {
		return Stream.generate(Lotto::generateRandomLotto)
						.limit(amount/price)
						.peek(System.out::println)
						.collect(Collectors.toList());
	}
}
