package sseonmi.step1;

import java.util.Set;

public class Lotto {
    private Set<Integer> randomNumberList;

    private int matchCount;

    public Set<Integer> getRandomNumberList() {
        return randomNumberList;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public void setRandomNumberList(Set<Integer> randomNumberList) {
        this.randomNumberList = randomNumberList;
    }

    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
    }
}
