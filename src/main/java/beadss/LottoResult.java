package beadss;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;

public class LottoResult {
	private Map<Rank, Long> winCountEachRank;
	private Long totalReward;

	public LottoResult(Map<Rank, Long> winCountEachRank) {
		this.winCountEachRank = winCountEachRank;
		this.totalReward = calculateTotalReward(winCountEachRank);
	}


	public long getTotalReward() {
		return totalReward;
	}

	public long getWinCount(Rank rank) {
		return MapUtils.getLongValue(winCountEachRank, rank, 0);
	}


	private long calculateTotalReward(Map<Rank, Long> result) {
		return result
				.entrySet()
				.stream()
				.map(entry -> entry.getKey().getReward() * entry.getValue())
				.mapToLong(Long::longValue)
				.sum();
	}
}
