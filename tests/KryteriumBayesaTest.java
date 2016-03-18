import criterias.KryteriumBayesa;
import criterias.Result;
import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public class KryteriumBayesaTest implements CriteriaTestsInterface {

    @Test
    public void testFind() throws Exception {
        //Given
        List<Double> factors = new ArrayList<>();
        factors.add((double) 1/3);
        factors.add((double) 1/6);
        factors.add((double) 1/2);
        KryteriumBayesa kryteriumBayesa = new KryteriumBayesa(defaultData, factors);
        //When
        Result result = kryteriumBayesa.find();
        //Then
        int winningZbozeIndex = 1;
        DecimalFormat format = new DecimalFormat("##.00");
        Assert.assertTrue(result.getRow() == (winningZbozeIndex - 1));
        Assert.assertTrue(format.format(result.getValue()).equals("30,67"));
    }
}