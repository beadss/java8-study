package beadss;

import java.util.Map;

public class LottoResult {
	private Map<Rank, Long> matchInfo;
	private Long reward;

	public LottoResult(Map<Rank, Long> matchInfo) {
		this.matchInfo = matchInfo;
		this.reward = calculateReward(matchInfo);
	}

	public Map<Rank, Long> getMatchInfo() {
		return matchInfo;
	}

	public long getReward() {
		return reward;
	}


	private long calculateReward(Map<Rank, Long> result) {
		return result
				.entrySet()
				.stream()
				.map(entry -> entry.getKey().getReward() * entry.getValue())
				.mapToLong(Long::longValue)
				.sum();
	}
}
