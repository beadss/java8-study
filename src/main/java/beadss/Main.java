package beadss;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
        
        System.out.println("구입 금액을 입력해 주세요.");

		LottoGame game = new LottoGame(scan.nextInt());

		scan.nextLine();
        System.out.println("지난 주 당첨 번호를 입력해주세요.");

		game.processResult(Lotto.parse(scan.nextLine()));
	}
}
