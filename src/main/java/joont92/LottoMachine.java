package joont92;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;
    private static final int LIMIT_PRICE = 100000;

    public List<Lotto> purchase(int money){
        List<Lotto> lottoList = null;

        if(money < LOTTO_PRICE){
            System.out.printf("최소 구입금액 : %d\n", LOTTO_PRICE);
        } else if(money > LIMIT_PRICE){
            System.out.printf("최대 구입금액 : %d\n", LIMIT_PRICE);
        } else{
            int count = money / LOTTO_PRICE;
            System.out.printf("%d개를 구매했습니다\n", count);

            lottoList = Stream.generate(Lotto::new)
                    .limit(count)
                    .collect(Collectors.toList());

            lottoList.forEach(l -> System.out.println(l.getNumbers()));
        }

        return lottoList;
    }

    public List<Rank> draw(Lotto correctLotto, Integer bonusNumber, List<Lotto> purchasedLotto){
        return purchasedLotto.stream()
                .map(Lotto::getNumbers)
                .map(l -> Rank.calculateRank(CollectionUtils.intersection(correctLotto.getNumbers(), l).size(), l.contains(bonusNumber)))
                .collect(Collectors.toList());
    }

    public void printResult(List<Rank> ranks){
        System.out.println("당첨 통계");
        System.out.println("--------------------");

        Arrays.stream(Rank.values())
                .filter(r -> r != Rank.Fail)
                .forEach(r -> System.out.printf("%d개 일치"
                        + (r.isBonusHit() ? ", 보너스 볼 일치" : "") +  "(%d원) - %d개\n",
                            r.getHit(), r.getPrizeMoney(), Collections.frequency(ranks, r)));

        long sum = ranks.stream()
                .mapToLong(Rank::getPrizeMoney)
                .sum();

        System.out.printf("총 수익률은 %d%%입니다\n", (sum * 100) / (ranks.size() * LOTTO_PRICE));
    }
}
