package criterias;

import java.util.List;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public class KryteriumLaplacea extends AbstractCriteria {

    public KryteriumLaplacea(List data) {
        this.data = data;
    }

    @Override
    public Result find() {
        Result bestResult = new Result();
        double factor = (double)1/this.getColumnsNumber();

        for (int i = 0; i < this.getRowsNumber(); i++) {
            double value = 0;
            for (int j = 0; j < this.getColumnsNumber(); j++) {
                value += factor * this.data.get(i).get(j);
            }
            if (value > bestResult.getValue()) {
                bestResult.setValue(value);
                bestResult.setRow(i);
            }
        }
        return bestResult;
    }
}
