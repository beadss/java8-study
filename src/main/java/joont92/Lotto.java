package joont92;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    final static int NUMBER_COUNT = 6;
    final static int MIN_NUM = 1;
    final static int MAX_NUM = 45;

    private List<Integer> numbers = new ArrayList<Integer>();
    private Rank rank = null;

    public Lotto() {
        while(numbers.size() < NUMBER_COUNT){
            Integer num = (int)(Math.random() * MAX_NUM) + MIN_NUM;

            if(!numbers.contains(num)){
                numbers.add(num);
            }
        }

        Collections.sort(numbers);
    }

    public void setRank(List<Integer> winningNumber){
        int winCount = 0;
        for (Integer number : numbers) {
            for (Integer i : winningNumber) {
                if(number == i){
                    winCount++;
                }
            }
        }

        rank = Rank.getRankFromCount(winCount);
    }

    public Rank getRank(){
        return rank;
    }

    public void print(){
        System.out.println(numbers.toString());
    }

    public static enum Rank{
        First(6, 2000000000), Second(5, 1500000), Third(4, 50000), Fourth(3, 5000);

        private int count;
        private long prizeMoney;

        Rank(int count, long prizeMoney){
            this.count = count;
            this.prizeMoney = prizeMoney;
        }

        public int getCount() {
            return count;
        }
        public long getPrizeMoney() {
            return prizeMoney;
        }

        public static Rank getRankFromCount(int count){
            for (Rank rank : Rank.values()) {
                if(rank.getCount() == count){
                    return rank;
                }
            }
            return null;
        }
    }
}
