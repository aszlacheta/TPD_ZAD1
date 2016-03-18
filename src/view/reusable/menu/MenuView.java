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

package view.reusable.menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import view.main.DefaultData;
import view.main.Main;
import view.reusable.content.ContentView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Sample custom control hosting a text field and a button.
 */
public class MenuView extends VBox implements Initializable {

    final FileChooser fileChooser = new FileChooser();
    ContentView contentView;

    @FXML
    Button selectFile;

    @FXML
    Button importDataFromLecturesExample;

    @FXML
    Button importDefaultData;

    public MenuView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuView.fxml"));
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
//        this.setLoadFileHandler();
    }

    public void setController(ContentView controller) {
        this.contentView = controller;
    }


    @FXML
    private void loadDataFromFile() {
        File file = fileChooser.showOpenDialog(Main.scene.getWindow());
        if (file != null) {
            MenuView.this.openFile(file);
        }
    }

    @FXML
    private void loadDataFromLecturesExample() {
        contentView.redraw(DefaultData.LECTURE_DATA);
    }

    @FXML
    private void loadDefaultData() {
        contentView.redraw(DefaultData.TASK_DATA);
    }


    @FXML
    protected List<List<Double>> openFile(File file) {
        List<List<Double>> data = null;

        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            data = new ArrayList<>();
            String[] rows = content.split("\n");

            for (int i = 0; i < rows.length; i++) {
                String[] columns = rows[i].split(" ");
                data.add(new ArrayList<>());

                for (int j = 0; j < columns.length; j++) {
                    data.get(i).add(Double.parseDouble(columns[j]));
                }
            }
            contentView.redraw(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
