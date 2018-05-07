package joont92;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		LottoMachine lottoMachine = new LottoMachine();
		List<Lotto> purchasedLotto;

		while(true){
			try{
				System.out.println("구입 금액을 입력해 주세요.");
				Integer money = scanner.nextInt();

				purchasedLotto = lottoMachine.purchase(money);
				if (purchasedLotto != null) {
					break;
				}

			} catch(InputMismatchException e){
				scanner.nextLine();
				System.out.println("숫자만 입력 가능");
			} finally {
				scanner.nextLine();
			}
		}

		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		Lotto correctLotto = Arrays.stream(scanner.nextLine().split(","))
				.map(String::trim)
				.distinct()
				.limit(6)
				.map(Integer::parseInt)
				.collect(Collectors.collectingAndThen(Collectors.toList(), Lotto::new));

		System.out.println("보너스 볼을 입력해 주세요.");
		Integer bonusNumber = Integer.parseInt(scanner.nextLine());

		// 당첨여부 확인!
		lottoMachine.printResult(lottoMachine.draw(correctLotto, bonusNumber, purchasedLotto));
	}
}
