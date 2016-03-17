import criterias.MinimaksUzytecznosci;
import criterias.Result;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public class MinimaksUzytecznosciTest implements CriteriaTestsInterface {

    @Test
    public void testFind() throws Exception {
        //Given
        MinimaksUzytecznosci minmaksUzyt = new MinimaksUzytecznosci(defaultData);
        //When
        Result result = minmaksUzyt.find();
        //Then
        int winningZbozeIndex = 5;
        Assert.assertTrue(result.getRow() == (winningZbozeIndex - 1));
        Assert.assertTrue(result.getValue() == 29);
    }
}