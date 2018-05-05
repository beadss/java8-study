package beadss;

public enum Rank {
	Fifth(5000),
	Fourth(50000),
	Third(150000),
	Second(30000000),
	First(2000000000);

	private int reward;

	Rank(int reward) {
		this.reward = reward;
	}

	public int getReward() {
		return reward;
	}


}