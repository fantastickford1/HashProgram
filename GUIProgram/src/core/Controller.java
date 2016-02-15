package core;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
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

    Funciones_Hash fh;
    Colisiones cl;
    FileChooser fileChooser;
    File textFile;
    Stage stage;
    String data;
    int[] idsList; //Ids

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hashCodeCh.getItems().addAll("Funcion Modulo","Funcion Cuadratica","Funcion Truncamiento","Funcion Plegamiento");
        hashCodeCh.getSelectionModel().selectFirst();
        ColisionCh.getItems().addAll("Prueba Lineal","Prueba Cuadratica","Doble Dirección Hash");
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
        int[] indexs = new int[idsList.length];
        cl = new Colisiones(indexs,fh);
        String auxPrint = "";
        int index;

        String option = (String) hashCodeCh.getSelectionModel().getSelectedItem();
        String colisionOption = (String) ColisionCh.getSelectionModel().getSelectedItem();
        switch (option){
            case "Funcion Modulo":
                for (int o = 0; o < indexs.length;o++){
                    auxPrint += fh.func_Modulo(idsList[o], idsList.length) + "\n";
                }
                indicesArea.appendText(auxPrint);
                switch (colisionOption){
                    case "Prueba Lineal":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_lineal(1,idsList[i]);
                            indexs[index] = idsList[i];
                        }
                        for (int w = 0; w < idsList.length; w++){
                            System.out.println(indexs[w]);
                        }
                        break;
                    case "Prueba Cuadratica":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_cuadratica(1,idsList[i]);
                            indexs[index] = idsList[i];
                        }
                        for (int w = 0; w < idsList.length; w++){
                            System.out.println(indexs[w]);
                        }
                        break;
                    case "Doble Dirección Hash":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.dobleDireccion(1,idsList[i]);
                            indexs[index] = idsList[i];
                        }
                        for (int w = 0; w < idsList.length; w++){
                            System.out.println(indexs[w]);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case "Funcion Cuadratica":
                for (int o = 0; o < indexs.length;o++){
                    auxPrint += fh.func_Cuadrado(idsList[o], idsList.length) + "\n";
                }
                indicesArea.appendText(auxPrint);
                switch (colisionOption){
                    case "Prueba Lineal":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_lineal(2,idsList[i]);
                            indexs[index] = idsList[i];
                        }
                        for (int w = 0; w < idsList.length; w++){
                            System.out.println(indexs[w]);
                        }
                        break;
                    case "Prueba Cuadratica":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_cuadratica(2,idsList[i]);
                            indexs[index] = idsList[i];
                        }
                        for (int w = 0; w < idsList.length; w++){
                            System.out.println(indexs[w]);
                        }
                        break;
                    case "Doble Dirección Hash":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.dobleDireccion(2,idsList[i]);
                            indexs[index] = idsList[i];
                        }
                        for (int w = 0; w < idsList.length; w++){
                            System.out.println(indexs[w]);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case "Funcion Truncamiento":
                for (int o = 0; o < indexs.length;o++){
                    auxPrint += fh.func_Truncamiento(idsList[o], idsList.length) + "\n";
                }
                indicesArea.appendText(auxPrint);
                switch (colisionOption){
                    case "Prueba Lineal":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_lineal(3,idsList[i]);
                            indexs[index] = idsList[i];
                        }
                        for (int w = 0; w < idsList.length; w++){
                            System.out.println(indexs[w]);
                        }
                        break;
                    case "Prueba Cuadratica":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_cuadratica(3,idsList[i]);
                            indexs[index] = idsList[i];
                        }
                        for (int w = 0; w < idsList.length; w++){
                            System.out.println(indexs[w]);
                        }
                        break;
                    case "Doble Dirección Hash":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.dobleDireccion(3,idsList[i]);
                            indexs[index] = idsList[i];
                        }
                        for (int w = 0; w < idsList.length; w++){
                            System.out.println(indexs[w]);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case "Funcion Plegamiento":
                for (int o = 0; o < indexs.length;o++){
                    auxPrint += fh.func_Plegamiento(idsList[o], idsList.length) + "\n";
                }
                indicesArea.appendText(auxPrint);
                switch (colisionOption){
                    case "Prueba Lineal":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_lineal(4,idsList[i]);
                            indexs[index] = idsList[i];
                        }
                        for (int w = 0; w < idsList.length; w++){
                            System.out.println(indexs[w]);
                        }
                        break;
                    case "Prueba Cuadratica":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.prueba_cuadratica(4,idsList[i]);
                            indexs[index] = idsList[i];
                        }
                        for (int w = 0; w < idsList.length; w++){
                            System.out.println(indexs[w]);
                        }
                        break;
                    case "Doble Dirección Hash":
                        for (int i = 0; i < idsList.length;i++){
                            index = cl.dobleDireccion(4,idsList[i]);
                            indexs[index] = idsList[i];
                        }
                        for (int w = 0; w < idsList.length; w++){
                            System.out.println(indexs[w]);
                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
