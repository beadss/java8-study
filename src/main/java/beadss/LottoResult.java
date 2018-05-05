package beadss;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;

public class LottoResult {
	private Map<Rank, Long> winCountEachRank;
	private Long reward;

	public LottoResult(Map<Rank, Long> winCountEachRank) {
		this.winCountEachRank = winCountEachRank;
		this.reward = calculateReward(winCountEachRank);
	}


	public long getReward() {
		return reward;
	}

	public long getWinCount(Rank rank) {
		return MapUtils.getLongValue(winCountEachRank, rank, 0);
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
