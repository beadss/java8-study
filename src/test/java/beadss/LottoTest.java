package beadss;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LottoTest {

	@Test
	public void parse() {
		boolean ret = CollectionUtils.isEqualCollection(
				Lotto.parse("1 , 2 , 3 , 4 , 5 , 6").getNumberList(),
				Arrays.asList(1,2,3,4,5,6)
		);

		assertTrue(ret);
	}

	@Test(expected = IllegalArgumentException.class)
	public void parse_over_number_range() {
		new Lotto(Arrays.asList(1 , 2 , 3 , 4 , 5 , 46));
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

		Lotto.Match match1 = lotto1.match(lotto2, 6);
		Lotto.Match match2 = lotto2.match(lotto1, 1);

		assertTrue(match1.getCount() == match2.getCount());
		assertTrue(match1.isBonusMatched());
		assertFalse(match2.isBonusMatched());
		assertTrue(match1.getCount() == 5);
	}
}
