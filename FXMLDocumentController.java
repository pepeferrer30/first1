/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package activitat.ae01_t1_1_ficheros;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Joan_2k2
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button BotonLimpiar;
    @FXML
    private TextArea areaMostrar;
    @FXML
    private TextField RutaInicial;
    @FXML
    private Button botonInformacion;
    @FXML
    private Button botonCrearFichero;
    @FXML
    private Button botonCrearCarpeta;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonRenombrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void LimpiarArea(ActionEvent event) {
        File path = new File(RutaInicial.getText());
        areaMostrar.clear();
    }

    @FXML
    private void daLaInfo(ActionEvent event) {
        //creo la funcion para setear la informacion(nombre,si es oculto,su ruta absoluta,su ultima modificación y los espacios libres) compruebo si es directorio o file
        File path = new File(RutaInicial.getText());
        DateFormat dft = new SimpleDateFormat("MMMM dd, yyyy hh:mm a");
        File[] lista = path.listFiles();

        if (path.exists()) {

            if (path.isDirectory()) {

                if (path.isHidden()) {
                    areaMostrar.setText("");
                    areaMostrar.setText(areaMostrar.getText() + path.getName() + "\n\r" + "Es un directorio y esta oculto " + "\n\r" + path.getAbsolutePath() + "\n\r" + " Su ultima modificacion fue " + dft.format(path.lastModified()) + "\n\r" + "Su espacio libre" + path.getFreeSpace() + "\n\r" + " su espacio total es " + path.getTotalSpace() + "\n\r" + "Su espacio usable es " + path.getUsableSpace() + "\n\r" + "\n\r");
                } else {
                    areaMostrar.setText("");
                    areaMostrar.setText(areaMostrar.getText() + path.getName() + "\n\r" + "Es un directorio y no esta oculto " + "\n\r" + path.getAbsolutePath() + "\n\r" + " Su ultima modificacion fue " + dft.format(path.lastModified()) + "\n\r" + "Su espacio libre" + path.getFreeSpace() + "\n\r" + " su espacio total es " + path.getTotalSpace() + "\n\r" + "Su espacio usable es " + path.getUsableSpace() + "\n\r" + "\n\r");
                }
                for (File f : lista) {
                    areaMostrar.setText(areaMostrar.getText() + f.getName() + "\n\r");

                }

            }

            if (path.isFile()) {
                if (path.isHidden()) {

                    areaMostrar.setText("");
                    areaMostrar.setText(areaMostrar.getText() + path.getName() + "\n\r" + "Es un fichero y esta oculto " + "\n\r" + path.getAbsolutePath() + "\n\r" + " Su ultima modificacion fue " + dft.format(path.lastModified()) + "\n\r" + "El tamaño del fichero es " + path.length() + "\n\r");

                } else {

                    areaMostrar.setText("");
                    areaMostrar.setText(areaMostrar.getText() + path.getName() + "\n\r" + "Es un fichero y no esta oculto " + "\n\r" + path.getAbsolutePath() + "\n\r" + " Su ultima modificacion fue " + dft.format(path.lastModified()) + "\n\r" + "El tamaño del fichero es " + path.length() + "\n\r");

                }

            }
        }
    }

    @FXML
    private void CreaFichero(ActionEvent event) throws IOException {
        //creamos un dialog para poder introducir de manera manual el nombre del fichero el cual queremos crear,comprobando si existe o si no existe para hacerlo
        File path = new File(RutaInicial.getText());

        TextInputDialog tde = new TextInputDialog();

        tde.setTitle("Crear fichero");
        tde.setContentText("Indica Nombre del fichero");
        tde.showAndWait();
        String nombreArchivo = tde.getEditor().getText();

        File nuevoarchivo = new File(path.getAbsolutePath() + "\\" + nombreArchivo);
        if(path.isDirectory()){
        if (nuevoarchivo.exists()) {
            Alert Error = new Alert(Alert.AlertType.WARNING);
            Error.setHeaderText("Error");
            Error.setContentText("Ya existe");
            Error.showAndWait();

        } else {
            Alert Correcto = new Alert(Alert.AlertType.CONFIRMATION);
            nuevoarchivo.createNewFile();
            Correcto.setHeaderText("Todo ok");
            Correcto.setContentText("Se ha creado correctamente");
            Correcto.showAndWait();
        }
        }else{
        Alert Error = new Alert(Alert.AlertType.WARNING);
            Error.setHeaderText("Error");
            Error.setContentText("No es un directorio");
            Error.showAndWait();
        }
        

    }

    @FXML
    private void CreaCarpeta(ActionEvent event) {
         //creamos un dialog para poder introducir de manera manual el nombre del archivo el cual queremos crear,comprobando si existe o si no existe para hacerlo
        File path = new File(RutaInicial.getText());
        TextInputDialog tde = new TextInputDialog();

        tde.setTitle("Crear Carpeta");
        tde.setContentText("Indica Nombre de la carpeta");
        tde.showAndWait();

        String nombreCarpeta = tde.getEditor().getText();

        File nuevaCarpeta = new File(path.getAbsolutePath() + "\\" + nombreCarpeta);
        if(path.isDirectory()){
         if (nuevaCarpeta.exists()) {
            Alert Error = new Alert(Alert.AlertType.WARNING);
            Error.setHeaderText("Error");
            Error.setContentText("Ya existe");
            Error.showAndWait();

        } else {
            Alert Correcto = new Alert(Alert.AlertType.CONFIRMATION);
            nuevaCarpeta.mkdirs();
            Correcto.setHeaderText("Todo ok");
            Correcto.setContentText("Se ha creado correctamente");
            Correcto.showAndWait();
        }
        }else{
        Alert Error = new Alert(Alert.AlertType.WARNING);
            Error.setHeaderText("Error");
            Error.setContentText("No es un directorio");
            Error.showAndWait();
        }
       

    }

    @FXML
    private void Renombra(ActionEvent event) throws IOException {
         //creamos dos dialog para poder introducir de manera manual el nombre del archivo/fichero el cual queremos renombrar y el nombre que le queremos poner,comprobando si existe o si no existe para hacerlo
        File path = new File(RutaInicial.getText());

        TextInputDialog tde = new TextInputDialog();

        tde.setTitle("Renombrar fichero/directorio");
        tde.setContentText("Indica Nombre del fichero/directorio a renombrar");
        tde.showAndWait();
        String nombreArchivo = tde.getEditor().getText();

        File origen = new File(path.getAbsolutePath() + "\\" + nombreArchivo);
        
         TextInputDialog tde2 = new TextInputDialog();

        tde2.setTitle("Renombrar fichero/directorio");
        tde2.setContentText("Indica el nuevo Nombre del fichero/directorio");
        tde2.showAndWait();
        String nombreArchivo2 = tde2.getEditor().getText();

        File destino = new File(path.getAbsolutePath() + "\\" + nombreArchivo2);
        
        
        
        

        if (destino.exists()) {
            Alert Error = new Alert(Alert.AlertType.WARNING);
            Error.setHeaderText("Error");
            Error.setContentText("Ya existe");
            Error.showAndWait();

        } else {
            Alert Correcto = new Alert(Alert.AlertType.CONFIRMATION);
            origen.renameTo(destino);
            Correcto.setHeaderText("Todo ok");
            Correcto.setContentText("Se ha renombrado correctamente");
            Correcto.showAndWait();
        }

    }

    @FXML
    private void eliminarAlgo(ActionEvent event) {
         //creamos un dialog para poder introducir de manera manual el nombre del archivo/fichero el cual queremos eliminar,comprobando si existe o si no existe para hacerlo
        File path = new File(RutaInicial.getText());
        
        TextInputDialog tde = new TextInputDialog();

        tde.setTitle("Eliminar  fichero");
        tde.setContentText("Indica Nombre del fichero/directorio a eliminar");
        tde.showAndWait();
        String nombreArchivo = tde.getEditor().getText();

        File origen = new File(path.getAbsolutePath() + "\\" + nombreArchivo);
        
        if (origen.exists()) {
            Alert Error = new Alert(Alert.AlertType.CONFIRMATION);
            origen.delete();
            Error.setHeaderText("Todo correcto");
            Error.setContentText("Eliminado correctamente");
            Error.showAndWait();

        } else {
            Alert Correcto = new Alert(Alert.AlertType.ERROR);
            
            Correcto.setHeaderText("Error");
            Correcto.setContentText("No existe");
            Correcto.showAndWait();
        }
        
    }
}
