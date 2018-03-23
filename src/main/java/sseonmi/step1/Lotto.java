package sseonmi.step1;

import java.util.Set;

public class Lotto {
    private Set<Integer> randomNumberList;

    private long matchCount;

    public Set<Integer> getRandomNumberList() {
        return randomNumberList;
    }

    public long getMatchCount() {
        return matchCount;
    }

    public void setRandomNumberList(Set<Integer> randomNumberList) {
        this.randomNumberList = randomNumberList;
    }

    public void setMatchCount(long matchCount) {
        this.matchCount = matchCount;
    }
}
