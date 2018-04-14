package beadss;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
        
        System.out.println("구입 금액을 입력해 주세요.");

        int amount = scan.nextInt();

		LottoGame game = new LottoGame(amount);

		scan.nextLine();
        System.out.println("지난 주 당첨 번호를 입력해주세요.");

		LottoResult result = game.processResult(Lotto.parse(scan.nextLine()));

		System.out.println("당첨 통계");
		System.out.println("---------");

		processAndPrint(result.getMatchInfo());

		System.out.println(String.format("총 수익률은 %d%%입니다.", result.getReward()/amount*100));

	}

	private static void printResult(Map.Entry<Rank, Long> result) {
		final Rank rank = result.getKey();
		final Long hitCount = result.getValue();
		System.out.println(String.format("%d개 일치(%d원)- %d개", rank.getMatchCount(), rank.getReward(), hitCount));
	}

	private static Map<Rank, Long> processAndPrint(Map<Rank, Long> matchedRankMap) {
		return Stream.of(makeAllRankMap(), matchedRankMap)
				.map(Map::entrySet)
				.flatMap(Collection::stream)
				.sorted((v1, v2)->v1.getKey().getMatchCount()>v2.getKey().getMatchCount()?1:-1)
				.peek(Main::printResult) // collect 전에 출력하면 안될거같음.. 중복된 값이 아직 남아있으니까
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::max));
	}

	private static Map<Rank, Long> makeAllRankMap() {
		return Stream.of(Rank.values()).collect(Collectors.toMap(key->key,(key)->0L));
	}
}
