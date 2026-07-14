package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Graph;

import java.io.IOException;
import java.util.HashMap;

public class HomeController {

    @FXML
    public void initialize() {

        graphListView.getItems().clear();

        graphListView.getItems().addAll(
                graphs.keySet()
        );
    }

    public static HashMap<String, Graph> graphs =
            new HashMap<>();

    public static Graph selectedGraph;

    @FXML
    private TextField graphNameField;

    @FXML
    private ListView<String> graphListView;

    @FXML
    public void handleCreateGraph() {

        String name = graphNameField.getText();

        if(name == null || name.isBlank()){
            return;
        }

        Graph graph = new Graph(name);

        graphs.put(name, graph);

        graphListView.getItems().add(name);

        graphNameField.clear();
    }

    @FXML
    public void handleSelectGraph() throws IOException {

        String selectedName =
                graphListView.getSelectionModel()
                        .getSelectedItem();

        if(selectedName == null){
            return;
        }

        selectedGraph = graphs.get(selectedName);

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource("/views/main-view.fxml")
                );

        Scene scene = new Scene(loader.load());

        Stage stage =
                (Stage) graphListView.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
}