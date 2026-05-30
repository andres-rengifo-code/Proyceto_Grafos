package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Graph;

import java.io.File;
import java.io.IOException;

public class MainController {

    private Graph graph;

    @FXML
    private Label graphNameLabel;

    @FXML
    private TextField sourceField;

    @FXML
    private TextField targetField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField degreeField;

    @FXML
    private TextArea outputArea;

    @FXML
    public void initialize() {

        graph = HomeController.selectedGraph;

        if(graph != null){
            graphNameLabel.setText(
                    "Grafo: " + graph.getName()
            );
        }
    }

    @FXML
    public void handleAddEdge() {

        try {

            String source = sourceField.getText();
            String target = targetField.getText();
            int weight = Integer.parseInt(weightField.getText());

            graph.addEdges(source,target,weight);

            outputArea.appendText(
                    source + " -> "
                            + target
                            + " ("
                            + weight
                            + ")\n"
            );

            sourceField.clear();
            targetField.clear();
            weightField.clear();

        } catch (Exception e){

            showAlert(
                    "Error",
                    "Datos inválidos"
            );
        }
    }

    @FXML
    public void handleShowGraph() {

        outputArea.clear();

        for(String[] edge : graph.edges){

            outputArea.appendText(
                    edge[0]
                            + " -> "
                            + edge[1]
                            + " ("
                            + edge[2]
                            + ")\n"
            );
        }
    }

    @FXML
    public void handleLoadFile() {

        FileChooser chooser = new FileChooser();

        chooser.setTitle("Seleccionar CSV");

        File file = chooser.showOpenDialog(null);

        if(file != null){

            graph.InsertTextFile(
                    file.getAbsolutePath()
            );

            outputArea.appendText(
                    "Archivo cargado correctamente\n"
            );
        }
    }

    @FXML
    public void handleDegree() {

        String node = degreeField.getText();

        int degree =
                graph.calculateDegree(node);

        outputArea.appendText(
                "Grado de "
                        + node
                        + ": "
                        + degree
                        + "\n"
        );
    }

    @FXML
    public void handleSimpleGraph() {

        boolean simple =
                graph.tipGraph();

        outputArea.appendText(
                "¿Es simple?: "
                        + simple
                        + "\n"
        );
    }

    @FXML
    public void handleCompleteGraph() {

        boolean complete =
                graph.isComplet();

        outputArea.appendText(
                "¿Es completo?: "
                        + complete
                        + "\n"
        );
    }

    // Generador
    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
    @FXML
    public void handleBackHome() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/views/home.fxml"
                            )
                    );

            Scene scene =
                    new Scene(loader.load());

            Stage stage =
                    (Stage) outputArea
                            .getScene()
                            .getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {

            showAlert(
                    "Error",
                    "No se pudo abrir la pantalla principal"
            );
        }
    }
}