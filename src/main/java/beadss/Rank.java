package beadss;

public enum Rank {
	Three(3, 5000),
	Four(4, 50000),
	Five(5, 150000),
	Six(6, 2000000000);

	private long matchCount;
	private int reward;

	private Rank(long matchCount, int reward) {
		this.matchCount = matchCount;
		this.reward = reward;
	}

	public int getReward() {
		return reward;
	}

	public long getMatchCount() {
		return matchCount;
	}

	public static boolean hasValue(long value) {
		return value >= Three.matchCount;
	}

	public static Rank getRank(long matchCount) {
		if(matchCount == Six.getMatchCount()) {
			return Six;
		} else if(matchCount == Five.getMatchCount()) {
			return Five;
		} else if(matchCount == Four.getMatchCount()) {
			return Four;
		} else if(matchCount == Three.getMatchCount()) {
			return Three;
		} else {
			return null;
		}
 	}
}