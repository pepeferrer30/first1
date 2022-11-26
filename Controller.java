import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FXMLCrearController implements Initializable {

    @FXML
    private TextField ruta;
    @FXML
    private TextField directorios;
    @FXML
    private TextField ficheros;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ruta.setText("C:\\holamundo");
    }

    @FXML
    private void ejecutar(ActionEvent event) throws IOException {

        //falla delete
        File f = new File(ruta.getText());

        if (f.exists()) {     //si existe borra y luego lo vuelev a crear el directorio
            borrarContenido(f);
        } else {
            f.mkdirs();
        }

        int numDirectorios = Integer.parseInt(directorios.getText());
        //int numFicheros = Integer.parseInt(ficheros.getText());

        for (int i = 0; i < numDirectorios; i++) {

            File crear = new File(f.getAbsolutePath() + "\\Nacho" + i);

            crear.mkdirs();

        }

        int numficheros = Integer.parseInt(ficheros.getText());
        //int numFicheros = Integer.parseInt(ficheros.getText());

        for (int i = 0; i < numficheros; i++) {

            File fichero = new File(f.getAbsolutePath() + "\\Momparler" + i);

            fichero.createNewFile();

        }

    }

    

    public void borrarRecursivoContenido(File f) {

        File[] contenido = f.listFiles();

        for (int i = 0; i < contenido.length; i++) {
            if (contenido[i].isFile()) {
                contenido[i].delete();
            } else {
                File nuevo = contenido[i].getAbsoluteFile();
                this.borrarContenido(nuevo);
                contenido[i].delete();
            }
        }

    }

}
