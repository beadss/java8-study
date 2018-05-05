package beadss;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
        
        System.out.println("구입 금액을 입력해 주세요.");

        int amount = scan.nextInt();

		LottoGame game = new LottoGame(amount);

		scan.nextLine();
        System.out.println("지난 주 당첨 번호를 입력해주세요.");

        Lotto correctLotto = Lotto.parse(scan.nextLine());

		System.out.println("보너스 볼을 입력해주세요.");
		int bonusNumber = scan.nextInt();

		LottoResult result = game.processResult(correctLotto, bonusNumber);

		System.out.println("당첨 통계");
		System.out.println("---------");

		printResult(result);

		System.out.println(String.format("총 수익률은 %d%%입니다.", result.getReward()/amount*100));

	}

	private static void printResult(LottoResult result) {
		Arrays.stream(Rank.values())
				.sorted((v1, v2)->v1.getReward()>v2.getReward()?1:-1)
				.forEach(rank -> printEachRankResult(rank, result));
	}

	private static void printEachRankResult(Rank rank, LottoResult result) {
		if(Rank.Second == rank) {
			System.out.println(String.format("%d개 일치, 보너스 볼 일치(%d원)- %d개",
					LottoGame.getMatchCount(rank), rank.getReward(), result.getWinCount(rank)));
		} else {
			System.out.println(String.format("%d개 일치(%d원)- %d개",
					LottoGame.getMatchCount(rank), rank.getReward(), result.getWinCount(rank)));
		}

	}
}
