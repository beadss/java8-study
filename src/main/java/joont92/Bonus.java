package joont92;

public class Bonus {
    private Integer number;

    public Bonus(Lotto correctLotto, Integer number) {
        if(Lotto.rangeCheck(number)){
            this.number = number;
        }

        if(this.number == null || correctLotto.getNumbers().contains(number)){
            throw new IllegalArgumentException();
        }
    }

    public Integer getNumber() {
        return number;
    }
}
