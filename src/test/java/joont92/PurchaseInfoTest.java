package joont92;

import org.junit.Assert;
import org.junit.Test;

public class PurchaseInfoTest {
    @Test
    public void Valid_Test(){
        PurchaseInfo a1 = new PurchaseInfo(999);
        Assert.assertEquals(a1.getManualCnt(), 0);
        Assert.assertEquals(a1.getAutoCnt(), 0);

        PurchaseInfo a2 = new PurchaseInfo(100001);
        Assert.assertEquals(a2.getManualCnt(), 0);
        Assert.assertEquals(a2.getAutoCnt(), 100);

        PurchaseInfo a3 = new PurchaseInfo(999, 1);
        Assert.assertEquals(a3.getManualCnt(), 0);
        Assert.assertEquals(a3.getAutoCnt(), 0);
    }

    @Test
    public void Manual_Auto_Test(){
        PurchaseInfo a1 = new PurchaseInfo(10000);
        Assert.assertEquals(a1.getManualCnt(), 0);
        Assert.assertEquals(a1.getAutoCnt(), 10);

        PurchaseInfo a2 = new PurchaseInfo(10000, 5);
        Assert.assertEquals(a2.getManualCnt(), 5);
        Assert.assertEquals(a2.getAutoCnt(), 5);
    }
}
