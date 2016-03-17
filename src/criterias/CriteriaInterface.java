package criterias;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public abstract class CriteriaInterface {

    public List<List<Double>> data = new ArrayList<>();
    protected List<Double> factors = new ArrayList<>();

    protected List defaultData = new ArrayList<ArrayList>() {{
        add(new ArrayList<Double>() {{
            add(85.0);
            add(75.0);
            add(95.0);
        }});
        add(new ArrayList<Double>() {{
            add(85.0);
            add(90.0);
            add(75.5);
        }});
        add(new ArrayList<Double>() {{
            add(85.0);
            add(65.0);
            add(92.0);
        }});
    }};

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
    public void setFactors(List<Double> factors) {
        this.factors = factors;
    }

    public void updateData(int row, int column, double value) {
        this.data.get(row).set(column, value);
    }
}
