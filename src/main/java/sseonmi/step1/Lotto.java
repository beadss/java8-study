package sseonmi.step1;

import java.util.List;

public class Lotto {

    public Lotto(List<Integer> randomNumberList, List<Integer> winningNumberList) {
        this.randomNumberList = randomNumberList;
        this.matchCount = (int) this.randomNumberList.stream().filter(winningNumberList::contains).count();
    }

    private List<Integer> randomNumberList;

    private int matchCount;


    public int getMatchCount() {
        return matchCount;
    }

    public List<Integer> getRandomNumberList() {
        return this.randomNumberList;
    }
}
