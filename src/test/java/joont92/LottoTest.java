package joont92;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LottoTest {
    @Test
    public void Auto_Generate_Test(){
        Lotto lotto = new Lotto();

        Assert.assertEquals(lotto.getNumbers().stream()
                .distinct()
                .filter(n -> n >= 1 && n <= 45)
                .count(), 6);
    }

    @Test
    public void Lotto_Right_Bounding_Test(){
        new Lotto(Arrays.asList(1,2,3,4,5,6,7));
        new Lotto(Arrays.asList(1,10,20,30,40,45));
    }

    @Test(expected = IllegalArgumentException.class)
    public void Lotto_Wrong_Bounding_Test(){
        new Lotto(Arrays.asList(0,10,20,30,40,46));
    }

    @Test
    public void Bonus_Right_Bounding_Test(){
        new Bonus(new Lotto(Arrays.asList(1,2,3,4,5,6)), 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Bonus_Wrong_Bounding_Test(){
        new Bonus(new Lotto(Arrays.asList(1,2,3,4,5,6)), 1);
    }

    @Test
    public void Rank_Calculate_Test(){
        Assert.assertEquals(Rank.First, Rank.calculate(6, false));
        Assert.assertEquals(Rank.Second, Rank.calculate(5, true));
        Assert.assertEquals(Rank.Third, Rank.calculate(5, false));
        Assert.assertEquals(Rank.Fail, Rank.calculate(2, false));
    }
}
