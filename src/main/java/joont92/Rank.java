package joont92;

public enum Rank{
    First(6, 2000000000), Second(5, 1500000), Third(4, 50000), Fourth(3, 5000);

    private int hit;
    private long prizeMoney;

    Rank(int count, long prizeMoney){
        this.hit = count;
        this.prizeMoney = prizeMoney;
    }

    public int getCount() {
        return hit;
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
