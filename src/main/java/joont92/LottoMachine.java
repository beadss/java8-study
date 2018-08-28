package joont92;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoMachine {
    public List<Lotto> purchase(Purchase availableLottoCnt){
        if(availableLottoCnt.getManualCnt() > 0){
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        }

        return Stream.concat(
                Stream.generate(DomainUtil::makeLottoFromStdin)
                .limit(availableLottoCnt.getManualCnt()),
                Stream.generate(Lotto::new)
                .limit(availableLottoCnt.getAutoCnt()))
                .collect(Collectors.toList());
    }

    public List<Rank> draw(Lotto correctLotto, Bonus bonus, List<Lotto> purchasedLotto){
        return purchasedLotto.stream()
                .map(Lotto::getNumbers)
                .map(l -> Rank.calculate(CollectionUtils.intersection(correctLotto.getNumbers(), l).size(), l.contains(bonus.getNumber())))
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

        System.out.printf("총 수익률은 %d%%입니다\n", (sum * 100) / (ranks.size() * Purchase.LOTTO_PRICE));
    }
}
