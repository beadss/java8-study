package joont92;

public class Bonus {
    private final static int MIN_NUM = 1;
    private final static int MAX_NUM = 45;
    private Integer number;

    public Bonus(Lotto correctLotto, Integer number) {
        if(number >= MIN_NUM && number < MAX_NUM){
            this.number = number;
        }

        if(this.number == null){
            throw new IllegalArgumentException();
        }

        if(correctLotto.getNumbers().contains(number)){
            throw new IllegalArgumentException();
        }
    }

    public Integer getNumber() {
        return number;
    }
}
