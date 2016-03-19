/*
 * Copyright (c) 2011, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package view.reusable.content;

import criterias.AbstractCriteria;
import criterias.Result;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import view.reusable.menu.MenuView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Sample custom control hosting a text field and a button.
 */
public class ContentView extends HBox implements Initializable {
    @FXML
    private TableView tableView;
    @FXML
    private VBox factorsContainer;
    @FXML
    private VBox generateButtonContainer;

    @FXML
    private Label bestDecisionIndex;
    @FXML
    private Label bestDecisionValue;
    @FXML
    private Button findDecision;

    @FXML
    private MenuView menuView;

    AbstractCriteria criteria;

    private boolean automatedEdit = false;

    public ContentView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contentView.fxml"));
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
        this.findDecision.setOnAction(new FindDecisionEventListener());
    }

    public void redraw(List<List<Double>> data) {
        this.criteria.data = data;
        this.tableView.getColumns().clear();
        this.tableView.getItems().clear();
        this.drawTable();
        this.createFactorsContainer();
        this.bestDecisionIndex.setText("?");
        this.bestDecisionValue.setText("?");
    }

    public void setCriteria(AbstractCriteria criteria) {
        this.criteria = criteria;
    }

    public void drawTable() {
        tableView.setEditable(true);

        for (int i = 0; i < criteria.getColumnsNumber(); i++) {
            String name = "condition " + (i + 1);
            TableColumn tableColumn = new TableColumn(name);
            tableView.getColumns().add(tableColumn);

            final int index = i;

            tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<List<Double>>, Double>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<List<Double>>, Double> p) {
                    return new SimpleStringProperty(p.getValue().get(0).get(index) + "");
                }
            });
            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            tableColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ObservableList<List<Double>>, Double>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<ObservableList<List<Double>>, Double> t) {
                    int row = t.getTablePosition().getRow();
                    int column = t.getTablePosition().getColumn();
                    double value = Double.parseDouble(t.getNewValue() + "");
                    ContentView.this.criteria.updateData(row, column, value);
                }
            });
        }

        for (int i = 0; i < criteria.getRowsNumber(); i++) {
            ObservableList<List<Double>> row = FXCollections.observableArrayList();
            row.addAll(criteria.data.get(i));
            tableView.getItems().add(row);
        }
    }

    public class FindDecisionEventListener implements EventHandler {

        @Override
        public void handle(Event e) {
            Result result = criteria.find();
            bestDecisionIndex.setText((result.getRow() + 1) + "");
            bestDecisionValue.setText((result.getValue()) + "");
        }
    }

    private class ChangeFactorEventListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
            try {
                if (!automatedEdit) {
                    ContentView.this.setFactors();
                }
            } catch (NumberFormatException ex) {
            }
        }
    }

    public void createFactorsContainer() {
        this.factorsContainer.getChildren().clear();
        this.generateButtonContainer.getChildren().clear();

        for (int i = 0; i < this.criteria.getFactors().size(); i++) {
            HBox row = new HBox();
            Label factorLabel = new Label("Factor " + (i + 1));
            TextField factorTextField = new TextField();
            factorTextField.setText(this.criteria.getFactors().get(i) + "");

            //styling
            factorLabel.setPadding(new Insets(0, 10, 0, 0));
            factorTextField.setPrefWidth(100);

            row.getChildren().add(factorLabel);
            row.getChildren().add(factorTextField);
            this.factorsContainer.getChildren().add(row);

            factorTextField.textProperty().addListener(new ChangeFactorEventListener());
        }
        if (this.criteria.getFactors().size() != 0) {
            this.drawGenerateRandomFactorsButton();
        }
    }

    private void drawGenerateRandomFactorsButton() {
        Button generateRandomFactors = new Button("Generate random factors");
        generateRandomFactors.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                automatedEdit = true;
                criteria.setRandomFactors();

                for (int i = 0; i < criteria.getFactors().size(); i++) {
                    ContentView.this.getFactorsTextField(i).setText(criteria.getFactors().get(i) + "");
                }
                automatedEdit = false;
            }
        });
        generateRandomFactors.setPrefWidth(160);
        this.generateButtonContainer.setAlignment(Pos.TOP_LEFT);

        this.generateButtonContainer.getChildren().add(generateRandomFactors);
    }

    protected void setFactors() {
        for (int i = 0; i < this.criteria.getFactors().size(); i++) {
            double value = Double.parseDouble(this.getFactorsTextField(i).getText());
            this.criteria.getFactors().set(i, value);
        }
    }

    protected TextField getFactorsTextField(int factorIndex) {
        return (TextField) ((HBox) this.factorsContainer.getChildren().get(factorIndex)).getChildren().get(1);
    }
}
