package joont92;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoMachine {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int LOTTO_PRICE = 1000;
    private static final int LIMIT_PRICE = 100000;

    private List<Integer> winningNumber = null;

    public List<Lotto> purchase(int money){
        List<Lotto> lottoList = null;

        if(money < LOTTO_PRICE){
            System.out.printf("최소 구입금액 : %d\n", LOTTO_PRICE);
        } else if(money > LIMIT_PRICE){
            System.out.printf("최대 구입금액 : %d\n", LIMIT_PRICE);
        } else{
            int count = money / LOTTO_PRICE;
            System.out.printf("%d개를 구매했습니다\n", count);

            lottoList = Stream.generate(Lotto::new)
                    .limit(count)
                    .collect(Collectors.toList());

            lottoList.stream()
                    .forEach(l -> l.print());
        }

        return lottoList;
    }

    public void setWinningNumber(){
        System.out.println("당첨 번호를 입력해 주세요.");

        String input = scanner.nextLine();
        // 예외처리 해야함
        winningNumber = Arrays.stream(input.split(","))
                .distinct()
                .limit(6)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        /*
        winningNumber = new ArrayList<>();
        do{
            try{
                Integer num = scanner.nextInt();

                if(num < Lotto.MIN_NUM || num > Lotto.MAX_NUM){
                    System.out.printf("범위를 벗어남 : %d (%d 부터 %d 까지의 값을 입력하셔야 합니다.)\n", num, Lotto.MIN_NUM, Lotto.MAX_NUM);
                } else{
                    if(!winningNumber.contains(num)){
                        winningNumber.add(num);
                    } else{
                        System.out.println("이미 등록된 번호입니다.");
                    }
                }
            } catch(InputMismatchException e){
                scanner.nextLine();
                System.out.println("숫자만 입력 가능");
            }
        } while(winningNumber.size() < 6);
        */
    }

    public void draw(List<Lotto> lottoList){
        if (winningNumber == null) {
            System.out.println("아직 추첨 전 입니다.");
        } else{
            lottoList.stream()
                    .forEach(l -> l.setRank(winningNumber));
        }

        Map<Rank, List<Lotto>> group = lottoList.stream()
                .filter(l -> l.getRank() != null)
                .collect(Collectors.groupingBy(Lotto::getRank));

        System.out.println("당첨 통계");
        System.out.println("--------------------");

        Arrays.stream(Rank.values())
            .forEach(r -> System.out.printf("%d개 일치 (%d원) - %d개\n", r.getCount(), r.getPrizeMoney(), group.containsKey(r) ? group.get(r).size() : 0));

        long sum = Arrays.stream(Rank.values())
                .filter(group::containsKey)
                .mapToLong(r -> r.getPrizeMoney() * group.get(r).size())
                .sum();

        System.out.printf("총 수익률은 %d%%입니다\n", (sum * 100) / (lottoList.size() * LOTTO_PRICE));
    }
}
