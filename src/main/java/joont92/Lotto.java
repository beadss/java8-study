package joont92;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Lotto {
    public static final int NUMBER_COUNT = 6;
    public static final int MIN_NUM = 1;
    public static final int MAX_NUM = 45;

    private List<Integer> numbers;

    public Lotto() {
        numbers = Stream.generate(Math::random)
                .map(i -> (int)(i*MAX_NUM) + MIN_NUM)
                .distinct()
                .limit(NUMBER_COUNT)
                .sorted()
                .collect(toList());
    }

    public Lotto(List<Integer> numbers){
        this.numbers = numbers.stream()
                .distinct()
                .filter(Lotto::rangeCheck)
                .limit(NUMBER_COUNT)
                .collect(Collectors.toList());

        if(this.numbers.size() < NUMBER_COUNT){
            throw new IllegalArgumentException("invalid range");
        }
    }

    public static boolean rangeCheck(Integer number){
        return number >= MIN_NUM && number <= MAX_NUM;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
