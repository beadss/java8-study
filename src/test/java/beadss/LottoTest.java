package beadss;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

public class LottoTest {

	@Test
	public void parse() {
		boolean ret = CollectionUtils.isEqualCollection(
				Lotto.parse("1 , 2 , 3 , 4 , 5 , 6").getNumberList(),
				Arrays.asList(1,2,3,4,5,6)
		);

		assertTrue(ret);
	}

	@Test
	public void generate() {
		List<Integer> lottoNumberList = Lotto.generateRandomLottoNumber()
			.filter(val -> val >= 1 && val <= 45)
			.collect(Collectors.toList());

		assertTrue(lottoNumberList.size() == 6);
		assertTrue(new HashSet(lottoNumberList).size() == 6);
	}


	@Test(expected = NumberFormatException.class)
	public void parseFail() {
		Lotto.parse("1.2.3.4.5.6");
	}

	@Test(expected = IllegalArgumentException.class)
	public void instantiateFail() {
		new Lotto(Arrays.asList(1,2,3,4,5));
	}

	@Test
	public void matchCount() {
		Lotto lotto1 = new Lotto(Arrays.asList(1,2,3,4,5,6));
		Lotto lotto2 = new Lotto(Arrays.asList(2,3,4,5,6,7));

		long matchCount1 = lotto1.matchedCount(lotto2);
		long matchCount2 = lotto2.matchedCount(lotto1);

		assertTrue(matchCount1 == matchCount2);
		assertTrue(matchCount1 == 5);
	}
}
