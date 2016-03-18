package criterias;

import java.util.List;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public class KryteriumBayesa extends AbstractCriteria {

    public KryteriumBayesa(List data, List factors) {
        this.data = data;
        this.factors = factors;
    }

    @Override
    public Result find() {
        Result bestResult = new Result();

        for (int i = 0; i < this.getRowsNumber(); i++) {
            double value = 0;
            for (int j = 0; j < this.getColumnsNumber(); j++) {
                value += this.factors.get(j) * this.data.get(i).get(j);
            }
            if (value > bestResult.getValue()) {
                bestResult.setValue(value);
                bestResult.setRow(i);
            }
        }
        return bestResult;
    }
}
