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

        // Si el grafo es diferente de nulo es decir si se ah selecionado un grafo el lebel toma el titulo del Grafo  y lo muestra
        if(graph != null){
            graphNameLabel.setText(
                    "Grafo: " + graph.getName()
            );
        }
    }


    //Metodo para agregar aristas

    @FXML
    public void handleAddEdge() {

        try {

            //Coge los valores ingresados en los cuadros de texto y los inserta a una variable
            String source = sourceField.getText();
            String target = targetField.getText();
            int weight = Integer.parseInt(weightField.getText());

            //Utiliza el metodo agregar aristas de la Clase grafos
            graph.addEdges(source,target,weight);

            //Imprime la arista al final del TEXTAREA sin eliminar lo anterior
            outputArea.appendText(
                    source + " -> "
                            + target
                            + " ("
                            + weight
                            + ")\n"
            );

            //Limpea los cuadros donde se ingresan texto
            sourceField.clear();
            targetField.clear();
            weightField.clear();

        } catch (Exception e){

            //Alerta por si se ingresan valores Erroneos
            showAlert(
                    "Error",
                    "Datos inválidos"
            );
        }
    }


    //Metodo para mostrar Grafo en el cruadro de texto
    @FXML
    public void handleShowGraph() {

        outputArea.clear(); // limpemaos la area

        // ciclo para imprimir las aristas del Grafo en el text area
        for(String[] edge : graph.edges){

            outputArea.appendText(
                    edge[0] + " -> " + edge[1] + " (" + edge[2] + ")\n"
            );
        }
    }

    //Metodo para cargar archivos de grafo
    @FXML
    public void handleLoadFile() {

        FileChooser chooser = new FileChooser(); // se crea un objeto de clase selectora de arrchivos

        chooser.setTitle("Seleccionar CSV");

        File file = chooser.showOpenDialog(null);// se aabre una venntana donde se puede seleccionar los arrchivos

        if(file != null){

            graph.InsertTextFile(file.getAbsolutePath()); //se utiliza el metodo para insertar el path de el arrchivo en el metodo de inserta archivos

            outputArea.appendText("Archivo cargado correctamente\n");
        }else {
            showAlert("Error","El archivo no se pudo cargarr");//Alerrrta por Error
        }


    }

    //Metodo para calcular el grado de un Grafo
    @FXML
    public void handleDegree() {

        String node = degreeField.getText(); // se guarda el nodo escrito en el area de texto

        int degree = graph.calculateDegree(node); // se usa el motodo para calcular el grado de un nodo y se guarda el valor del grado en una variable

        outputArea.appendText("Grado de " + node + ": " + degree + "\n");// Se imprime el grado del nodo en el area de texto
    }

    //Metodo para definir un Grafo si es simple o complejo
    @FXML
    public void handleSimpleGraph() {

        boolean simple = graph.tipGraph(); //Se usa el metodo de la clase Graph para definir si es simpre

        outputArea.appendText(
                "¿Es simple?: "
                        + simple
                        + "\n"
        );
    }

    //Metodo para calcular si el grafo es completo
    @FXML
    public void handleCompleteGraph() {

        boolean complete = graph.isComplet(); //metodo para calcular si el grafo es completo

        outputArea.appendText(
                "¿Es completo?: "
                        + complete
                        + "\n"
        );
    }

    // Metodo generador de alertas
    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR); // Se genera un nuevo objeto de tipo alerta

        //Contennido de la alerta
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        //mostrar alerta mediante ventana emergente
        alert.showAndWait();
    }

    //Metodo para volver a la ventana de inicio
    @FXML
    public void handleBackHome() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/home.fxml"));

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) outputArea.getScene().getWindow();

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