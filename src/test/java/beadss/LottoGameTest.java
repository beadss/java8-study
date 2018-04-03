package beadss;

import org.junit.Test;

public class LottoGameTest {

	@Test
	public void test() {
		LottoGame game = new LottoGame(14000);
		game.processResult(Lotto.parse("1, 2, 3, 4, 5, 6"));
	}
}

