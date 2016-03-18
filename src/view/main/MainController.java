package view.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import view.reusable.tab.TabView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TabPane tabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < DefaultData.TABS_NUMBER; i++) {
            Tab tab = new Tab(DefaultData.TAB_NAMES[i], new TabView(DefaultData.CRITERIAS[i]));
            tabPane.getTabs().add(tab);
        }
    }
}
