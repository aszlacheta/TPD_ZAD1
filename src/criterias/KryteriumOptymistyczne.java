package criterias;

import java.util.Collections;
import java.util.List;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public class KryteriumOptymistyczne extends AbstractCriteria {

    public KryteriumOptymistyczne(List data) {
        this.data = data;
    }

    @Override
    public Result find() {
        Result bestResult = new Result();

        for (int i = 0; i < this.getRowsNumber(); i++) {
            double bestResultInARow = Collections.max(this.data.get(i));
            if (bestResultInARow > bestResult.getValue()) {
                bestResult.setValue(bestResultInARow);
                bestResult.setRow(i);
            }
        }
        return bestResult;
    }
}
