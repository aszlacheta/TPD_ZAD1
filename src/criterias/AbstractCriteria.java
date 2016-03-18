package criterias;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public abstract class AbstractCriteria {

    public static List<List<Double>> data = new ArrayList<>();
    protected List<Double> factors = new ArrayList<>();

    public abstract Result find();

    public int getColumnsNumber() {
        return data.get(0).size();
    }

    public int getRowsNumber() {
        return data.size();
    }

    public List<Double> getFactors() {
        return this.factors;
    }

    public void updateData(int row, int column, double value) {
        this.data.get(row).set(column, value);
    }


    public void setRandomFactors() {
        int factorsNumber = this.factors.size();
        this.factors.clear();

        Random generator = new Random();
        double summedUpFactors = 0;

        for (int i = 0; i < factorsNumber; i++) {
            double factor = generator.nextDouble();
            summedUpFactors += factor;
            this.factors.add(factor);
        }

        for (int i = 0; i < factorsNumber; i++) {
            double value = this.factors.get(i) / summedUpFactors;
            this.factors.set(i, value);
        }
    }
}
