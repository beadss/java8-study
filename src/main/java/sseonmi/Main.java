package sseonmi;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import sseonmi.step1.LottoGame;

public class Main {

    public static void main(String[] args) {

        System.out.println("구입금액 : 5000원");
        List<Integer> winningNumberList = Stream.generate(() -> (int) (Math.random() * 45 + 1)).distinct().limit(6).sorted().collect(Collectors.toList());

        System.out.print("지난주 당첨 번호 : ");
        winningNumberList.forEach(s -> System.out.print(s + ", "));
        System.out.println();
        LottoGame lottoGame = new LottoGame(5000, winningNumberList);

        System.out.println(lottoGame.start());
    }
}
