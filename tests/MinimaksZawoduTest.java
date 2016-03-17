import criterias.MinimaksZawodu;
import criterias.Result;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public class MinimaksZawoduTest implements CriteriaTestsInterface {

    @Test
    public void testFind() throws Exception {
        //Given
        MinimaksZawodu minimaksZawodu = new MinimaksZawodu(defaultData);
        //When
        Result result = minimaksZawodu.find();
        //Then
        int winningZbozeIndex = 4;
        Assert.assertTrue(result.getRow() == (winningZbozeIndex - 1));
        Assert.assertTrue(result.getValue() == 5);
    }
}