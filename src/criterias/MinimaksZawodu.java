package criterias;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public class MinimaksZawodu extends AbstractCriteria {

    public MinimaksZawodu(List data) {
        this.data = data;
    }

    @Override
    public Result find() {
        Result bestResult = new Result();
        ArrayList<Double> maxValues = this.getMaxColumnValues();

        for (int i = 0; i < this.getRowsNumber(); i++) {
            double lossValue = 0;

            for (int j = 0; j < this.getColumnsNumber(); j++) {
                double localLossValue = maxValues.get(j) - this.data.get(i).get(j);

                if (localLossValue > lossValue) {
                    lossValue = localLossValue;
                }
            }
            if (i == 0 || lossValue < bestResult.getValue()) {
                bestResult.setValue(lossValue);
                bestResult.setRow(i);
            }
        }
        return bestResult;
    }

    private ArrayList<Double> getMaxColumnValues() {
        ArrayList<Double> maxValues = new ArrayList<>();

        for (int j = 0; j < this.getColumnsNumber(); j++) {
            double maxValue = 0;
            for (int i = 0; i < this.getRowsNumber(); i++) {
                if (this.data.get(i).get(j) > maxValue) {
                    maxValue = this.data.get(i).get(j);
                }
            }
            maxValues.add(maxValue);
        }
        return maxValues;
    }
}
