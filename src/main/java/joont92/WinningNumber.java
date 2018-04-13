package joont92;

import java.util.List;

public class WinningNumber {
    private List<Integer> hitNumbers;
    private Integer bonusNumber;

    public WinningNumber(List<Integer> hitNumbers, Integer bonusNumber){
        this.hitNumbers = hitNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int getHitCount(List<Integer> numbers){
        return (int)numbers.stream()
                .filter(hitNumbers::contains)
                .count();
    }
    public boolean isBonusHit(List<Integer> numbers){
        return numbers.stream()
                .anyMatch(n -> n.equals(bonusNumber));
    }
}
