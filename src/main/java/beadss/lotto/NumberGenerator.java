package beadss.lotto;

import java.util.Random;

public class NumberGenerator {
	private Random random;
	
	public NumberGenerator() {
		random = new Random();
	}
	
	public int generate() {
		return random.nextInt(44) + 1;
	}
}
