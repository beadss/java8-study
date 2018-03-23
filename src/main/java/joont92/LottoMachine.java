package joont92;

import java.util.*;

public class LottoMachine {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int LOTTO_PRICE = 1000;
    private static final int LIMIT_PRICE = 1000000;

    private List<Integer> winningNumber = null;

    public List<Lotto> purchase(int money){
        List<Lotto> lottoList = null;

        if(money < LOTTO_PRICE){
            System.out.printf("최소 구입금액 : %d\n", LOTTO_PRICE);
        } else if(money > LIMIT_PRICE){
            System.out.printf("최대 구입금액 : %d\n", LIMIT_PRICE);
        } else{
            lottoList = new ArrayList<Lotto>();

            int count = money / LOTTO_PRICE;
            System.out.printf("%d개를 구매했습니다\n", count);

            for (int i = 0; i < count; i++) {
                Lotto lotto = new Lotto();

                lottoList.add(lotto);
                lotto.print();
            }
        }

        return lottoList;
    }

    public void setWinningNumber(){
        System.out.println("당첨 번호를 입력해 주세요.");

        winningNumber = new ArrayList<Integer>();
        do{
            try{
                Integer num = scanner.nextInt();

                if(num < Lotto.MIN_NUM || num > Lotto.MAX_NUM){
                    System.out.printf("범위를 벗어남 : %d (%d 부터 %d 까지의 값을 입력하셔야 합니다.)\n", num, Lotto.MIN_NUM, Lotto.MAX_NUM);
                } else{
                    if(!winningNumber.contains(num)){
                        winningNumber.add(num);
                    }
                }
            } catch(InputMismatchException e){
                scanner.nextLine();
                System.out.println("숫자만 입력 가능");
            }
        } while(winningNumber.size() < 6);
    }

    public void draw(List<Lotto> lottoList){
        if (winningNumber == null) {
            System.out.println("아직 추첨 기간이 아닙니다.");
        } else{
            for (Lotto lotto : lottoList) {
                lotto.setRank(winningNumber);
            }
        }
    }

    public void stats(List<Lotto> lottoList){
        Map<String, List<Lotto>> group = new HashMap<String, List<Lotto>>();

        for (Lotto lotto : lottoList) {
            if (lotto.getRank() != null) {
                String key = lotto.getRank().name();
                if(!group.containsKey(key)){
                    group.put(key, new ArrayList<Lotto>());
                }

                group.get(key).add(lotto);
            }
        }

        System.out.println("당첨 통계");
        System.out.println("--------------------");
        long sum = 0;
        for (Lotto.Rank rank : Lotto.Rank.values()) {
            if(group.containsKey(rank.name())){
                List<Lotto> list = group.get(rank.name());
                System.out.printf("%d개 일치 (%d원) - %d개\n", rank.getCount(), rank.getPrizeMoney(), list.size());

                sum += rank.getPrizeMoney() * list.size();
            } else{
                System.out.printf("%d개 일치 (%d원) - 0개\n", rank.getCount(), rank.getPrizeMoney());
            }
        }

        System.out.printf("총 수익률은 %d%%입니다\n", (sum * 100) / (lottoList.size() * LOTTO_PRICE));
    }
}
