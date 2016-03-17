import criterias.KryteriumLaplacea;
import criterias.Result;
import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public class KryteriumLaplaceaTest implements CriteriaTestsInterface {

    @Test
    public void testFind() throws Exception {
        //Given
        KryteriumLaplacea kryteriumLaplacea = new KryteriumLaplacea(defaultData);
        //When
        Result result = kryteriumLaplacea.find();
        //Then
        int winningZbozeIndex = 3;
        DecimalFormat format = new DecimalFormat("##.00");
        Assert.assertTrue(result.getRow() == (winningZbozeIndex - 1));
        Assert.assertTrue(format.format(result.getValue()).equals("30,33"));
    }
}