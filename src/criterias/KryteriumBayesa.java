package criterias;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public class KryteriumBayesa extends CriteriaInterface {

    public KryteriumBayesa() {
        this.data = this.defaultData;
        this.setFactors();
    }
    public KryteriumBayesa(List data) {
        if (data.get(0) instanceof ArrayList) {
            this.data = data;
            this.setFactors();
        } else {
            this.data = this.defaultData;
            this.factors = data;
        }
    }
    public KryteriumBayesa(List data, List factors) {
        this.data = data;
        this.factors = factors;
    }

    @Override
    public Result find() {
        Result bestResult = new Result();

        for (int i = 0; i < this.data.size(); i++) {
            double value = 0;
            for (int j = 0; j < this.data.get(i).size(); j++) {
                value += this.factors.get(j) * this.data.get(i).get(j);
            }
            if (value > bestResult.getValue()) {
                bestResult.setValue(value);
                bestResult.setRow(i);
            }
        }
        return bestResult;
    }

    public void setFactors(List<Double> factors) {
        this.factors = factors;
    }

    private void setFactors() {
        Random generator = new Random();
        double summedUpFactors = 0;
        for (int i = 0; i < this.data.get(0).size(); i++) {
            double factor = generator.nextDouble();
            double value = (1 - summedUpFactors + factor) < 0 ? 1 - summedUpFactors : (1 - summedUpFactors + factor);
            System.out.println(factor);
            System.out.println("Factor[" + i + "] = " + value);
            factors.add(value);
        }
    }
}
