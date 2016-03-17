package criterias;

import java.util.Collections;
import java.util.List;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public class KryteriumHurwicza extends CriteriaInterface implements HasFactors {

    public KryteriumHurwicza() {
        this.data = this.defaultData;
    }
    public KryteriumHurwicza(double factor) {
        this.data = this.defaultData;
        this.factors.add(factor);
    }
    public KryteriumHurwicza(List data) {
        this.data = data;
    }
    public KryteriumHurwicza(List data, double factor) {
        this.data = data;
        this.factors.add(factor);
    }

    @Override
    public void setFactors(List<Double> factors) {
        this.factors = factors;
    }

    @Override
    public Result find() {
        Result bestResult = new Result();
        double onlyOneFactor = this.factors.get(0);

        for (int i = 0; i < data.size(); i++) {
            double minValue = Collections.min(this.data.get(i));
            double maxValue = Collections.max(this.data.get(i));
            double result = onlyOneFactor * minValue + (1 - onlyOneFactor) * maxValue;

            if (result > bestResult.getValue()) {
                bestResult.setValue(result);
                bestResult.setRow(i);
            }
        }
        return bestResult;
    }
}
