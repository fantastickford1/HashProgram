package core;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextArea clavesText,auxTextArea,indicesArea;
    @FXML
    ChoiceBox hashCodeCh,ColisionCh;
    @FXML
    TextField claveEntry;

    Funciones_Hash fh;
    Colisiones cl;
    Busqueda bs;
    FileChooser fileChooser;
    File textFile;
    Stage stage;
    String data;
    int[] idsList; //Ids
    int[] indexs;
    int[][] doubleIndex;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hashCodeCh.getItems().addAll("Funcion Modulo","Funcion Cuadratica","Funcion Truncamiento","Funcion Plegamiento");
        hashCodeCh.getSelectionModel().selectFirst();
        ColisionCh.getItems().addAll("Prueba Lineal","Prueba Cuadratica","Doble Dirección Hash","Arreglos Anidados","Encadenamiento");
        ColisionCh.getSelectionModel().selectFirst();
    }

    @FXML private void openFile(){
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt","*.txt")
        );
        textFile = fileChooser.showOpenDialog(stage);
        if (textFile != null){
            ReadFile(textFile);
        }
    }

    private void ReadFile(File textFile) {
        data = "";
        String line = "";
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(textFile);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null){
                data+= line + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        clavesText.appendText(data);

        String[] splitedData = data.split("\\r?\\n");
        idsList = new int[splitedData.length];

        for (int i = 0; i < splitedData.length;i++) {
            idsList[i] = Integer.parseInt(splitedData[i]); //idsList has all the data that contain the txt document in Int statement
        }
    }

    @FXML private void idexTable(){

        fh = new Funciones_Hash();
        indexs = new int[idsList.length];
        doubleIndex = new int[idsList.length][idsList.length];
        cl = new Colisiones(indexs,fh);
        String auxPrint = "";
        int index;
        String cord;

        String option = (String) hashCodeCh.getSelectionModel().getSelectedItem();
        String colisionOption = (String) ColisionCh.getSelectionModel().getSelectedItem();
        switch (option){
            case "Funcion Modulo":
                auxTextArea.setText("");
                indicesArea.appendText("Tabla de indexacion:\n");
                for (int o = 0; o < indexs.length;o++){
                    auxPrint += fh.func_Modulo(idsList[o], idsList.length) + "\n";
                }
                indicesArea.appendText(auxPrint);
                switch (colisionOption){
                    case "Prueba Lineal":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_lineal(1,idsList[i]);
                            if (index != -1)
                                indexs[index] = idsList[i];
                        }
                        auxPrint = "";
                        for (int w = 0; w < idsList.length; w++){
                            auxPrint += indexs[w] + "->" + w + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Prueba Cuadratica":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_cuadratica(1,idsList[i]);
                            if (index != -1)
                                indexs[index] = idsList[i];
                        }
                        auxPrint = "";
                        for (int w = 0; w < idsList.length; w++){
                            auxPrint += indexs[w] + "->" + w + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Doble Dirección Hash":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.dobleDireccion(1,idsList[i]);
                            if (index != -1)
                                indexs[index] = idsList[i];
                        }
                        auxPrint = "";
                        for (int w = 0; w < idsList.length; w++){
                            auxPrint += indexs[w] + "->" + w + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Arreglos Anidados":
                        cl.setBidimensionalArray(doubleIndex);
                        auxPrint = "";
                        for (int i = 0; i < idsList.length ; i++) {
                            cord = cl.arreglosAnidados(1,idsList[i]);
                            String[] auxW = cord.split(",");
                            doubleIndex[Integer.parseInt(auxW[0])][Integer.parseInt(auxW[1])] = idsList[i];
                            auxPrint += idsList[i] + "->" + cord + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Encadenamiento":
                        auxPrint = "";
                        String[] Salida = cl.Encadenarminto(idsList.length,idsList,1);

                        for (String a:Salida) {
                            auxPrint+= a +"\n";
                        }

                        auxTextArea.appendText(auxPrint);
                        break;
                    default:
                        break;
                }
                break;
            case "Funcion Cuadratica":
                auxTextArea.setText("");
                indicesArea.appendText("Tabla de indexacion:\n");
                for (int o = 0; o < indexs.length;o++){
                    auxPrint += fh.func_Cuadrado(idsList[o], idsList.length) + "\n";
                }
                indicesArea.appendText(auxPrint);
                switch (colisionOption){
                    case "Prueba Lineal":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_lineal(2,idsList[i]);
                            if (index != -1)
                                indexs[index] = idsList[i];
                        }
                        auxPrint = "";
                        for (int w = 0; w < idsList.length; w++){
                            auxPrint += indexs[w] + "->" + w + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Prueba Cuadratica":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_cuadratica(2,idsList[i]);
                            if (index != -1)
                                indexs[index] = idsList[i];
                        }
                        auxPrint = "";
                        for (int w = 0; w < idsList.length; w++){
                            auxPrint += indexs[w] + "->" + w + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Doble Dirección Hash":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.dobleDireccion(2,idsList[i]);
                            if (index != -1)
                                indexs[index] = idsList[i];
                        }
                        auxPrint = "";
                        for (int w = 0; w < idsList.length; w++){
                            auxPrint += indexs[w] + "->" + w + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Arreglos Anidados":
                        cl.setBidimensionalArray(doubleIndex);
                        auxPrint = "";
                        for (int i = 0; i < idsList.length ; i++) {
                            cord = cl.arreglosAnidados(2,idsList[i]);
                            String[] auxW = cord.split(",");
                            doubleIndex[Integer.parseInt(auxW[0])][Integer.parseInt(auxW[1])] = idsList[i];
                            auxPrint += idsList[i] + "->" + cord + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Encadenamiento":
                        auxPrint = "";
                        String[] Salida = cl.Encadenarminto(idsList.length,idsList,2);

                        for (String a:Salida) {
                            auxPrint+= a +"\n";
                        }

                        auxTextArea.appendText(auxPrint);
                        break;
                    default:
                        break;
                }
                break;
            case "Funcion Truncamiento":
                auxTextArea.setText("");
                indicesArea.appendText("Tabla de indexacion:\n");
                for (int o = 0; o < indexs.length;o++){
                    auxPrint += fh.func_Truncamiento(idsList[o], idsList.length) + "\n";
                }
                indicesArea.appendText(auxPrint);
                switch (colisionOption){
                    case "Prueba Lineal":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_lineal(3,idsList[i]);
                            if (index != -1)
                                indexs[index] = idsList[i];
                        }
                        auxPrint = "";
                        for (int w = 0; w < idsList.length; w++){
                            auxPrint += indexs[w] + "->" + w + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Prueba Cuadratica":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_cuadratica(3,idsList[i]);
                            if (index != -1)
                                indexs[index] = idsList[i];
                        }
                        auxPrint = "";
                        for (int w = 0; w < idsList.length; w++){
                            auxPrint += indexs[w] + "->" + w + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Doble Dirección Hash":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.dobleDireccion(3,idsList[i]);
                            if (index != -1)
                                indexs[index] = idsList[i];
                        }
                        auxPrint = "";
                        for (int w = 0; w < idsList.length; w++){
                            auxPrint += indexs[w] + "->" + w + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Arreglos Anidados":
                        cl.setBidimensionalArray(doubleIndex);
                        auxPrint = "";
                        for (int i = 0; i < idsList.length ; i++) {
                            cord = cl.arreglosAnidados(3,idsList[i]);
                            String[] auxW = cord.split(",");
                            doubleIndex[Integer.parseInt(auxW[0])][Integer.parseInt(auxW[1])] = idsList[i];
                            auxPrint += idsList[i] + "->" + cord + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Encadenamiento":
                        auxPrint = "";
                        String[] Salida = cl.Encadenarminto(idsList.length,idsList,3);

                        for (String a:Salida) {
                            auxPrint+= a +"\n";
                        }

                        auxTextArea.appendText(auxPrint);
                        break;
                    default:
                        break;
                }
                break;
            case "Funcion Plegamiento":
                auxTextArea.setText("");
                indicesArea.appendText("Tabla de indexacion:\n");
                for (int o = 0; o < indexs.length;o++){
                    auxPrint += fh.func_Plegamiento(idsList[o], idsList.length) + "\n";
                }
                indicesArea.appendText(auxPrint);
                switch (colisionOption){
                    case "Prueba Lineal":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_lineal(4,idsList[i]);
                            if (index != -1)
                                indexs[index] = idsList[i];
                        }
                        auxPrint = "";
                        for (int w = 0; w < idsList.length; w++){
                            auxPrint += indexs[w] + "->" + w + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Prueba Cuadratica":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_cuadratica(4,idsList[i]);
                            if (index != -1)
                                indexs[index] = idsList[i];
                        }
                        auxPrint = "";
                        for (int w = 0; w < idsList.length; w++){
                            auxPrint += indexs[w] + "->" + w + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Doble Dirección Hash":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.dobleDireccion(4,idsList[i]);
                            if (index != -1)
                                indexs[index] = idsList[i];
                        }
                        auxPrint = "";
                        for (int w = 0; w < idsList.length; w++){
                            auxPrint += indexs[w] + "->" + w + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Arreglos Anidados":
                        cl.setBidimensionalArray(doubleIndex);
                        auxPrint = "";
                        for (int i = 0; i < idsList.length ; i++) {
                            cord = cl.arreglosAnidados(4,idsList[i]);
                            String[] auxW = cord.split(",");
                            doubleIndex[Integer.parseInt(auxW[0])][Integer.parseInt(auxW[1])] = idsList[i];
                            auxPrint += idsList[i] + "->" + cord + "\n";
                        }
                        auxTextArea.appendText(auxPrint);
                        break;
                    case "Encadenamiento":
                        auxPrint = "";
                        String[] Salida = cl.Encadenarminto(idsList.length,idsList,4);

                        for (String a:Salida) {
                            auxPrint+= a +"\n";
                        }

                        auxTextArea.appendText(auxPrint);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    @FXML private void search(){
        String option = (String) hashCodeCh.getSelectionModel().getSelectedItem();
        String colisionOption = (String) ColisionCh.getSelectionModel().getSelectedItem();
        String claveSearch = claveEntry.getText();
        int indexSearch = Integer.parseInt(claveSearch);
        bs = new Busqueda(indexs,fh);
        bs.setBidimensionalArray(doubleIndex);
        switch (option){
            case "Funcion Modulo":
                switch (colisionOption){
                    case "Prueba Lineal":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.prueba_lineal(1,indexSearch));
                        break;
                    case "Prueba Cuadratica":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.prueba_cuadratica(1,indexSearch));
                        break;
                    case "Doble Dirección Hash":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.dobleDireccion(1,indexSearch));
                        break;
                    case "Arreglos Anidados":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.arreglosAnidados(1,indexSearch));
                        break;
                    case "Encadenamiento":
                        String result;
                        result = bs.encadenamiento(Integer.parseInt(claveSearch),1,cl.getLista());
                        if (result.compareTo("") == 0) {
                            Result = new Alert(Alert.AlertType.ERROR);
                            Result.setTitle("Error");
                            Result.setHeaderText("Dato "+claveSearch+" no encontrado");

                            Result.showAndWait();
                        }else {
                            Result = new Alert(Alert.AlertType.CONFIRMATION);
                            Result.setTitle("Exito");
                            Result.setHeaderText("Dato "+claveSearch+" encontrado");
                            Result.setContentText("Indice: "+bs.getClave()+"\n" +
                                    "Complejidad: " + bs.getContador());
                            Result.showAndWait();
                        }
                        break;
                    default:
                        break;
                }
                break;
            case "Funcion Cuadratica":
                switch (colisionOption){
                    case "Prueba Lineal":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.prueba_lineal(2,indexSearch));
                        break;
                    case "Prueba Cuadratica":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.prueba_cuadratica(2,indexSearch));
                        break;
                    case "Doble Dirección Hash":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.dobleDireccion(2,indexSearch));
                        break;
                    case "Arreglos Anidados":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.arreglosAnidados(2,indexSearch));
                        break;
                    default:
                        break;
                }
                break;
            case "Funcion Truncamiento":
                switch (colisionOption){
                    case "Prueba Lineal":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.prueba_lineal(3,indexSearch));
                        break;
                    case "Prueba Cuadratica":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.prueba_cuadratica(3,indexSearch));
                        break;
                    case "Doble Dirección Hash":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.dobleDireccion(3,indexSearch));
                        break;
                    case "Arreglos Anidados":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.arreglosAnidados(3,indexSearch));
                        break;
                    default:
                        break;
                }
                break;
            case "Funcion Plegamiento":
                switch (colisionOption){
                    case "Prueba Lineal":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.prueba_lineal(4,indexSearch));
                        break;
                    case "Prueba Cuadratica":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.prueba_cuadratica(4,indexSearch));
                        break;
                    case "Doble Dirección Hash":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.dobleDireccion(4,indexSearch));
                        break;
                    case "Arreglos Anidados":
                        auxTextArea.appendText("\nDato encontrado en la posicion ->" + bs.arreglosAnidados(4,indexSearch));
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    @FXML private void clean(){
        indicesArea.setText("");
    }

    @FXML private void reset(){
        indexs = null;
        doubleIndex = null;
    }
}
