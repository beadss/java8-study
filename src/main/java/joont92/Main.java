package joont92;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		LottoMachine lottoMachine = new LottoMachine();
		List<Lotto> lottoList;

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
			} finally {
				scanner.nextLine();
			}
		}

		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		List<Integer> hitNumbers = Arrays.stream(scanner.nextLine().split(","))
				.distinct()
				.limit(6)
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(Collectors.toList());

		System.out.println("보너스 볼을 입력해 주세요.");
		Integer bonusNumber = Integer.parseInt(scanner.nextLine());

		WinningNumber winningNumber = new WinningNumber(hitNumbers, bonusNumber);

		// 당첨여부 확인!
		lottoMachine.draw(winningNumber, lottoList);
	}
}
