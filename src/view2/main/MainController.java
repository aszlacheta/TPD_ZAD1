package view2.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Tab tab1;

    public final static double HURWICZ_FACTOR = 0.25;
    public final static List<Double> BAYES_FACTORS = new ArrayList<Double>() {{
        add((double) 1 / 3);
        add((double) 1 / 6);
        add((double) 1 / 2);
    }};
    public final static List DEFAULT_DATA = new ArrayList<ArrayList>() {{
        add(new ArrayList<Double>() {{
            add(24.0);
            add(28.0);
            add(36.0);
        }});
        add(new ArrayList<Double>() {{
            add(31.0);
            add(30.0);
            add(28.0);
        }});
        add(new ArrayList<Double>() {{
            add(28.0);
            add(34.0);
            add(29.0);
        }});
        add(new ArrayList<Double>() {{
            add(27.0);
            add(29.0);
            add(33.0);
        }});
        add(new ArrayList<Double>() {{
            add(31.0);
            add(30.0);
            add(29.0);
        }});
    }};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        CustomControl2 customControl2 = new CustomControl2();
//        customControl2.setText("Hello!");
//        tab1.setContent(customControl2);
    }

}
