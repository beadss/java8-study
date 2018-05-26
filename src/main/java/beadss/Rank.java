package beadss;

public enum Rank {
	Fifth(3, 5000),
	Fourth(4, 50000),
	Third(5, 150000),
	Second(5, 30000000),
	First(6, 2000000000);

	private int matchCount;
	private int reward;

	Rank(int matchCount, int reward) {
		this.matchCount = matchCount;
		this.reward = reward;
	}

	public int getReward() {
		return reward;
	}


	public int getMatchCount() {
		return matchCount;
	}
}