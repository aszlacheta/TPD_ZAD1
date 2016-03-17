package criterias;

import java.util.Collections;
import java.util.List;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public class MinimaksUzytecznosci extends CriteriaInterface {

    public MinimaksUzytecznosci() {
        this.data = this.defaultData;
    }
    public MinimaksUzytecznosci(List data) {
        this.data = data;
    }

    @Override
    public Result find() {
        Result bestResult = new Result();

        for (int i = 0; i < this.data.size(); i++) {
            double bestResultInARow = Collections.min(this.data.get(i));
            if (bestResultInARow > bestResult.getValue()) {
                bestResult.setValue(bestResultInARow);
                bestResult.setRow(i);
            }
        }
        return bestResult;
    }
}
