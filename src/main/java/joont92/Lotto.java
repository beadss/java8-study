package joont92;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Lotto {
    final static int NUMBER_COUNT = 6;
    final static int MIN_NUM = 1;
    final static int MAX_NUM = 45;

    private List<Integer> numbers;
    private Rank rank = null;

    public Lotto() {
        numbers = Stream.generate(Math::random)
                .map(i -> (int)(i*MAX_NUM) + MIN_NUM)
                .distinct()
                .limit(NUMBER_COUNT)
                .sorted()
                .collect(toList());
    }

    public void setRank(List<Integer> winningNumber){
        int hit = (int)winningNumber.stream()
                .filter(numbers::contains)
                .count();

        rank = Rank.getRankFromCount(hit);
    }

    public Rank getRank(){
        return rank;
    }

    public void print(){
        System.out.println(numbers.toString());
    }
}
