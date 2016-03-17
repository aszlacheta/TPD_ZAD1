import criterias.KryteriumHurwicza;
import criterias.Result;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public class KryteriumHurwiczaTest implements CriteriaTestsInterface {

    @Test
    public void testFind() throws Exception {
        //Given
        double factor = 0.25;
        KryteriumHurwicza kryteriumHurwicza = new KryteriumHurwicza(defaultData, factor);
        //When
        Result result = kryteriumHurwicza.find();
        //Then
        int winningZbozeIndex = 1;
        Assert.assertTrue(result.getRow() == (winningZbozeIndex - 1));
        Assert.assertTrue(result.getValue() == 33);
    }
}