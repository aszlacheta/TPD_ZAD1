package criterias;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public class Result {

    private int row;
    private double value;

    public Result() {
        this.row = -1;
        this.value = -1;
    }

    public Result(int row, double value) {
        this.row = row;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
