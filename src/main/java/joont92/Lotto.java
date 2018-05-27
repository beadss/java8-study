package joont92;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Lotto {
    private final static int NUMBER_COUNT = 6;
    private final static int MIN_NUM = 1;
    private final static int MAX_NUM = 45;

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
                .filter(n -> n >= MIN_NUM && n <= MAX_NUM)
                .collect(Collectors.toList());

        if(this.numbers.size() != NUMBER_COUNT){
            throw new IllegalArgumentException("wrong number");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
