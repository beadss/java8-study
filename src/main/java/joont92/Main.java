package joont92;

import java.util.*;

public class Main {
	static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		LottoMachine lottoMachine = new LottoMachine();
		List<Lotto> lottoList = null;

		while(true){
			try{
				System.out.println("구입 금액을 입력해 주세요.");
				Integer money = scanner.nextInt();

				lottoList = lottoMachine.purchase(money);
				if (lottoList != null) {
					break;
				}

			} catch(InputMismatchException e){
				scanner.nextLine();
				System.out.println("숫자만 입력 가능");
			}
		}

		// 당첨번호 set
		lottoMachine.setWinningNumber();

		// 당첨여부 확인!
		lottoMachine.draw(lottoList);
	}
}
