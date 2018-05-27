package joont92;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGameTest {
    @Test
    public void Lotto_Construct_Test(){
        Lotto lotto = new Lotto();

        Assert.assertEquals(lotto.getNumbers().stream()
                .distinct()
                .filter(n -> n >= 1 && n <= 45)
                .count(), 6);
    }

    @Test
    public void Rank_Calculate_Test(){
        Assert.assertEquals(Rank.First, Rank.calculateRank(6, false));
        Assert.assertEquals(Rank.Second, Rank.calculateRank(5, true));
        Assert.assertEquals(Rank.Third, Rank.calculateRank(5, false));
        Assert.assertEquals(Rank.Fail, Rank.calculateRank(2, false));
    }

    @Test
    public void LottoMachine_Purchase_Test(){
        LottoMachine lottoMachine = new LottoMachine();
        Assert.assertEquals(lottoMachine.purchase(14000).size(), 14);
    }

    @Test
    public void LottoMachine_Draw_Test(){
        LottoMachine lottoMachine = new LottoMachine();

        List<Lotto> purchaseLotto = new ArrayList<>();
        purchaseLotto.add(new Lotto(Arrays.asList(1,2,3,4,5,6)));
        purchaseLotto.add(new Lotto(Arrays.asList(2,3,4,5,6,7)));
        purchaseLotto.add(new Lotto(Arrays.asList(3,4,5,6,7,8)));

        Lotto correctLotto = new Lotto(Arrays.asList(1,2,3,4,5,9));
        Integer bonusNumber = 6;

        List<Rank> ranks = lottoMachine.draw(correctLotto, bonusNumber, purchaseLotto);

        Assert.assertEquals(Collections.frequency(ranks, Rank.First), 0);
        Assert.assertEquals(Collections.frequency(ranks, Rank.Second), 1);
        Assert.assertEquals(Collections.frequency(ranks, Rank.Fourth), 1);
        Assert.assertEquals(Collections.frequency(ranks, Rank.Fifth), 1);
    }
}
