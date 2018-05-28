package joont92;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class DomainUtilTest {
    @Test
    public void Make_Lotto_Test(){
        Scanner scanner = setStdin("1,2,3,4,5,6");
        Lotto lotto = DomainUtil.makeLottoFromStdin(scanner);

        Assert.assertTrue(Arrays.equals(lotto.getNumbers().toArray(), new Integer[]{1,2,3,4,5,6}));
    }

    @Test
    public void Make_Bonus_Test(){
        Scanner scanner = setStdin("1");
        Bonus bonus = DomainUtil.makeBonusFromStdin(new Lotto(Arrays.asList(2,3,4,5,6,7)), scanner);

        Assert.assertEquals(new Integer(1), bonus.getNumber());
    }

    private Scanner setStdin(String text){
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(text.getBytes()));
        Scanner scanner = new Scanner(System.in);
        System.setIn(stdin);

        return scanner;
    }
}
