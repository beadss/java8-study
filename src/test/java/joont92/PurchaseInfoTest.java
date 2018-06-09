package joont92;

import org.junit.Assert;
import org.junit.Test;

public class PurchaseInfoTest {
    @Test
    public void Valid_Test(){
        Purchase a1 = new Purchase(999);
        Assert.assertEquals(a1.getManualCnt(), 0);
        Assert.assertEquals(a1.getAutoCnt(), 0);

        Purchase a2 = new Purchase(100001);
        Assert.assertEquals(a2.getManualCnt(), 0);
        Assert.assertEquals(a2.getAutoCnt(), 100);

        Purchase a3 = new Purchase(999, 1);
        Assert.assertEquals(a3.getManualCnt(), 0);
        Assert.assertEquals(a3.getAutoCnt(), 0);
    }

    @Test
    public void Manual_Auto_Test(){
        Purchase a1 = new Purchase(10000);
        Assert.assertEquals(a1.getManualCnt(), 0);
        Assert.assertEquals(a1.getAutoCnt(), 10);

        Purchase a2 = new Purchase(10000, 5);
        Assert.assertEquals(a2.getManualCnt(), 5);
        Assert.assertEquals(a2.getAutoCnt(), 5);
    }
}
