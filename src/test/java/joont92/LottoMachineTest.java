package joont92;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LottoMachineTest {
    LottoMachine lottoMachine = new LottoMachine();

    @Test
    public void LottoMachine_Purchase_Test(){
        Assert.assertEquals(lottoMachine.purchase(new Purchase(900)).size(), 0);
        Assert.assertEquals(lottoMachine.purchase(new Purchase(10000)).size(), 10);
    }

    @Test
    public void LottoMachine_Draw_Test(){
        List<Lotto> purchaseLotto = new ArrayList<>();
        purchaseLotto.add(new Lotto(Arrays.asList(1,2,3,4,5,6)));
        purchaseLotto.add(new Lotto(Arrays.asList(2,3,4,5,6,7)));
        purchaseLotto.add(new Lotto(Arrays.asList(3,4,5,6,7,8)));

        Lotto correctLotto = new Lotto(Arrays.asList(1,2,3,4,5,9));
        Bonus bonus = new Bonus(correctLotto, 6);

        List<Rank> ranks = lottoMachine.draw(correctLotto, bonus, purchaseLotto);

        Assert.assertEquals(Collections.frequency(ranks, Rank.First), 0);
        Assert.assertEquals(Collections.frequency(ranks, Rank.Second), 1);
        Assert.assertEquals(Collections.frequency(ranks, Rank.Fourth), 1);
        Assert.assertEquals(Collections.frequency(ranks, Rank.Fifth), 1);
    }
}
