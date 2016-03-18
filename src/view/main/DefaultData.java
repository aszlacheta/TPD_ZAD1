package view.main;

import criterias.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandra on 2016-03-18.
 */
public class DefaultData {

    public final static List TASK_DATA = new ArrayList<ArrayList>() {{
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
    public final static List LECTURE_DATA = new ArrayList<ArrayList>() {{
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

    public final static double HURWICZ_DEFAULT_FACTOR = 0.25;
    public final static List<Double> BAYES_DEFAULT_FACTORS = new ArrayList<Double>() {{
        add((double) 1 / 3);
        add((double) 1 / 6);
        add((double) 1 / 2);
    }};

    public static final List DEFAULT_ON_RUNTIME = LECTURE_DATA;

    public static final String[] TAB_NAMES = {
            "Minimaks użyeczności",
            "Minimaks zawodu",
            "Kryterium Hurwicza",
            "Kryterium Bayesa",
            "Kryterium Laplace'a"
    };

    public static final AbstractCriteria[] CRITERIAS = {
            new MinimaksUzytecznosci(DEFAULT_ON_RUNTIME),
            new MinimaksZawodu(DEFAULT_ON_RUNTIME),
            new KryteriumHurwicza(DEFAULT_ON_RUNTIME, HURWICZ_DEFAULT_FACTOR),
            new KryteriumBayesa(DEFAULT_ON_RUNTIME, BAYES_DEFAULT_FACTORS),
            new KryteriumLaplacea(DEFAULT_ON_RUNTIME)
    };

    public static final int TABS_NUMBER = TAB_NAMES.length;
}
