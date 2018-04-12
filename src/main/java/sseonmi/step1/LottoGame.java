package sseonmi.step1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGame {
    private static final int PRICE = 1000;
    private int totalPrice;
    private int totalCount;

    private List<Lotto> lottoList = new ArrayList<Lotto>();

    private List<Integer> winningNumberList;

    private static HashMap<Integer, Integer> priceMap = new HashMap<>();

    public LottoGame(int totalPrice, List<Integer> winningNumberList) {
        this.totalPrice = totalPrice;
        this.winningNumberList = winningNumberList;
        this.totalCount = totalPrice / PRICE;

        this.priceMap.put(3, 5000);
        this.priceMap.put(4, 50000);
        this.priceMap.put(5, 150000);
        this.priceMap.put(6, 2000000000);
    }

    public String start() {
        while (totalCount > 0) {
            lottoList.add(new Lotto(Stream.generate(() -> (int) (Math.random() * 45 + 1)).distinct().limit(6).sorted().collect(Collectors.toList()), winningNumberList));
            totalCount--;
        }
        lottoList.stream().forEach(s->System.out.println(s.getRandomNumberList()+ " match : "+s.getMatchCount()));

        int result = getResult(this.lottoList);
        return "수익률은 " + result + "% 입니다.";
    }

    public int getResult(List<Lotto> resultList) {
        int result = 0;
        
        for (Lotto lotto : resultList) {
            if (priceMap.containsKey(lotto.getMatchCount())) {
                result += priceMap.get(lotto.getMatchCount());
            }
        }
        return (result / totalPrice) * 100;
    }

}
