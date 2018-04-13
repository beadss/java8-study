package joont92;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGameTest {
    @Test
    public void Lotto_Construct_Test(){
        Lotto lotto = new Lotto();

        for(int i=0; i<100; i++){
            Assert.assertEquals(lotto.getNumbers().stream()
                    .distinct()
                    .filter(n -> n > 1 && n < 45)
                    .count(), 6);
        }
    }

    @Test
    public void Rank_Calculate_Test(){
        Assert.assertEquals(Rank.First, Rank.calculateRank(6, false));
        Assert.assertEquals(Rank.Second, Rank.calculateRank(5, true));
        Assert.assertEquals(Rank.Third, Rank.calculateRank(5, false));
        Assert.assertNull(Rank.calculateRank(2, false));
    }

    @Test
    public void LottoMachine_Purchase_Test(){
        LottoMachine lottoMachine = new LottoMachine();
        Assert.assertEquals(lottoMachine.purchase(14000).size(), 14);
    }

    @Test
    public void WinnigNumber_Hit_Test(){
        WinningNumber winningNumber = new WinningNumber(Arrays.asList(1,2,3,4,5,6), 7);

        List<Integer> customer = Arrays.asList(2,3,4,5,6,7);
        Assert.assertEquals(winningNumber.getHitCount(customer), 5);
        Assert.assertTrue(winningNumber.isBonusHit(customer));

        customer = Arrays.asList(10,11,12,13,14,15);
        Assert.assertEquals(winningNumber.getHitCount(customer), 0);
        Assert.assertFalse(winningNumber.isBonusHit(customer));
    }
}
