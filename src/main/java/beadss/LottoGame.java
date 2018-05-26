package beadss;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGame {
	public static final int PRICE = 1000;

	private List<Lotto> expectedLottoList;
	private int buyCount;

	public LottoGame(int amount) {
		this(amount, new ArrayList<>());
	}

	public LottoGame(int amount, List<Lotto> manualLottoList) {
		this.buyCount = amount / PRICE;

		if(manualLottoList.size() > buyCount) {
			throw new UnsupportedOperationException("돈 부족해요~");
		}

		final int autoLottoCount = buyCount - manualLottoList.size();

		System.out.println(String.format("수동으로 %d장, 자동으로 %d장을 구매했습니다.", manualLottoList.size(), autoLottoCount));

		this.expectedLottoList = Stream.concat(manualLottoList.stream(), buyLotto(autoLottoCount))
				.peek(System.out::println)
				.collect(Collectors.toList());
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

	private Stream<Lotto> buyLotto(int count) {
		return Stream.generate(Lotto::generateRandomLotto)
						.limit(count);
	}
}
