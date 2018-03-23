package sseonmi.step1;

import java.util.*;

public class LottoGame {
    private static final int PRICE = 1000;
    private int totalPrice;
    private int totalCount;

    private List<Lotto> lottoList = new ArrayList<Lotto>();

    private Set<Integer> winningNumberList;

    private static HashMap<Integer, Integer> priceMap = new HashMap();

    public LottoGame(int totalPrice, Set<Integer> winningNumberList) {
        super();
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
            Lotto lotto = new Lotto();
            lotto.setRandomNumberList(getRandomNumber());
            lotto.setMatchCount(getMatchCount(lotto.getRandomNumberList()));
            lottoList.add(lotto);
            System.out.println("List : " + lotto.getRandomNumberList());
            System.out.println("count : " + lotto.getMatchCount());
            totalCount--;
        }

        return getResult(this.lottoList) + "";
    }

    public double getResult(List<Lotto> resultList) {
        int result = 0;
        for (Lotto lotto : resultList) {
            if (priceMap.containsKey(lotto.getMatchCount()))
                result += priceMap.get(lotto.getMatchCount());
        }

        return (result / totalPrice) * 100;
    }

    /**
     * 당첨 개수
     */
    public int getMatchCount(Set<Integer> randomNumberList) {
        int count = 0;
        for (Integer winningNumber : winningNumberList) {
            if (randomNumberList.contains(winningNumber)) {
                count++;
            }
        }
        return count;
    }

    /**
     * random number
     */
    public Set<Integer> getRandomNumber() {
        Set<Integer> randomNumberSet = new HashSet<Integer>();
        while (randomNumberSet.size() < 6) {
            randomNumberSet.add((int) (Math.random() * 45 + 1));
        }

        return randomNumberSet;
    }
}
