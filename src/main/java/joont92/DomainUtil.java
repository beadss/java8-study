package joont92;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DomainUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static Lotto makeLottoFromStdin(){
        return makeLottoFromStdin(scanner);
    }

    public static Lotto makeLottoFromStdin(Scanner scanner){ // scanner variable is for test
        Lotto lotto;
        while(true){
            try{
                lotto = Arrays.stream(scanner.nextLine().split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.collectingAndThen(Collectors.toList(), Lotto::new));
                break;
            } catch(IllegalArgumentException | InputMismatchException e){
                System.out.println("다시 입력하세요");
            }
        }

        return lotto;
    }

    public static Bonus makeBonusFromStdin(Lotto correctLotto){
        return makeBonusFromStdin(correctLotto, scanner);
    }

    public static Bonus makeBonusFromStdin(Lotto correctLotto, Scanner scanner){ // scanner variable is for test
        Bonus bonus;
        while(true){
            try{
                Integer num = Integer.parseInt(scanner.nextLine());

                bonus = new Bonus(correctLotto, num);
                break;
            } catch(IllegalArgumentException | InputMismatchException e){
                System.out.println("다시 입력하세요");
            }
        }

        return bonus;
    }
}
