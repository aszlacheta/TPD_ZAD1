package view2.tabs.tab5;

import criterias.KryteriumLaplacea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import view2.main.MainController;
import view2.reusable.content.ContentView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TabController extends VBox implements Initializable {

    @FXML
    private ContentView contentView;

    public TabController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contentView.setCriteria(new KryteriumLaplacea(MainController.DEFAULT_DATA));
        contentView.drawTable();
        contentView.createFactorsContainer();
    }
}
