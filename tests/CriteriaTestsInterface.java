import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandra on 2016-03-15.
 */
public interface CriteriaTestsInterface {
    List defaultData = new ArrayList<ArrayList>() {{
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
}
