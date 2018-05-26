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
				.map(expected -> expected.match(correctLotto, bonusNumber))
				.filter(this::atLeast)
				.collect(Collectors.collectingAndThen(
						Collectors.groupingBy(this::getRank, Collectors.counting()),
						LottoResult::new));
	}

	private boolean atLeast(Lotto.Match match) {
		return match.getCount() >= Rank.Fifth.getMatchCount();
	}

	private Rank getRank(Lotto.Match match) {
		if(match.getCount() == Rank.First.getMatchCount()) {
			return Rank.First;
		} else if(match.getCount() == Rank.Second.getMatchCount() && match.isBonusMatched()) {
			return Rank.Second;
		} else if(match.getCount() == Rank.Third.getMatchCount()) {
			return Rank.Third;
		} else if(match.getCount() == Rank.Fourth.getMatchCount()) {
			return Rank.Fourth;
		} else if(match.getCount() == Rank.Fifth.getMatchCount()) {
			return Rank.Fifth;
		} else {
			throw new IllegalArgumentException("알 수 없는 Rank임");
		}
	}

	private Stream<Lotto> buyLotto(int count) {
		return Stream.generate(Lotto::generateRandomLotto)
						.limit(count);
	}
}
