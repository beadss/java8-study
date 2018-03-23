package beadss;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGame {
	private static int price = 1000;

	private int amount;
	private List<Lotto> expectedLottoList;

	public LottoGame(int amount) {
		this.amount = amount;
		this.expectedLottoList = buyLotto(amount);
	}

	public void processResult(Lotto correctLotto) {
		Map<Rank, Long> base = Stream.of(Rank.values()).collect(Collectors.toMap(key->key,(key)->0L));
		Map<Rank, Long> result = expectedLottoList
				.stream()
				.map(correctLotto::matchedCount)
				.filter(Rank::hasValue)
				.collect(Collectors.groupingBy(Rank::getRank, Collectors.counting()));

		/*
		TODO: 매치된 적이 없는 Rank를 위해 기본값 Map과 매치된 Map을 머지하는데, 영 어색한 느낌이 들으므로 수정이 필요함
		 */
		result = Stream.of(base, result)
				.map(Map::entrySet)
				.flatMap(Collection::stream)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::max));


		System.out.println("당첨 통계");
		System.out.println("---------");

		final Long totalReward = result
				.entrySet()
				.stream()
				.sorted((v1, v2)->v1.getKey().getMatchCount()>v2.getKey().getMatchCount()?1:-1)
				.peek(LottoGame::printResult)
				.map(entry -> entry.getKey().getReward() * entry.getValue())
				.mapToLong(Long::longValue).sum();

		System.out.println(String.format("총 수익률은 %d%%입니다.", totalReward/amount*100));
	}

	private List<Lotto> buyLotto(int amount) {
		return Stream.generate(Lotto::generateRandomLotto)
						.limit(amount/price)
						.peek(System.out::println)
						.collect(Collectors.toList());
	}

	private static void printResult(Map.Entry<Rank, Long> result) {
		final Rank rank = result.getKey();
		final Long hitCount = result.getValue();
		System.out.println(String.format("%d개 일치(%d원)- %d개", rank.getMatchCount(), rank.getReward(), hitCount));
	}
}
