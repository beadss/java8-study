package joont92;

public class PurchaseInfo {
    public static final int LOTTO_PRICE = 1000;
    public static final int LIMIT_PRICE = 100000;
    private int manualCnt;
    private int autoCnt;

    public PurchaseInfo(int money){
        this.manualCnt = 0;
        this.autoCnt = moneyToPossibleCnt(money);
    }

    public PurchaseInfo(int money, int manualCnt){
        this.autoCnt = moneyToPossibleCnt(money);
        if(this.autoCnt >= manualCnt){
            this.autoCnt -= manualCnt;
            this.manualCnt = manualCnt;
        } else{
            this.manualCnt = 0;
        }
    }

    private int moneyToPossibleCnt(int money){
        if(money < LOTTO_PRICE){
            System.out.printf("최소 구입금액 : %d\n", LOTTO_PRICE);
            return 0;
        } else if(money > LIMIT_PRICE){
            System.out.printf("최대 구입금액 : %d\n", LIMIT_PRICE);
            return LIMIT_PRICE / LOTTO_PRICE;
        } else{
            return money / LOTTO_PRICE;
        }
    }

    public int getManualCnt() {
        return manualCnt;
    }

    public int getAutoCnt() {
        return autoCnt;
    }
}
