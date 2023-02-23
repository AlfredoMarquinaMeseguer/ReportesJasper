package es.alfredomarquinameseguer.jasper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;
/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, JRException, ClassNotFoundException, SQLException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
//        ReportViewer reportViewer = new ReportViewer();
//        Scene scene = new Scene(reportViewer, 800, 600);
//        stage.setScene(scene);
//        stage.show();
//        try {
////        Class.forName("com.mysql.jdbc.Driver");
//
//            Connection conexion = null;
//            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306"
//                    + "/sakila", "root", "");
//            JasperReport reporte = null;
//            reporte = (JasperReport) JRLoader.loadObject(
//                    "Ejer2.xml");
//
//            JasperPrint jasperPrint;
//            jasperPrint = JasperFillManager.fillReport(reporte,
//                    null,
//                    conexion);
//
//            /*Map<String, String> parametros = new HashMap<>();
//            parametros.put("ReportTitle",
//                    "List of Contacts");
//            parametros.put("Author",
//                    "Prepared By Manisha");
//            
//            jasperPrint = JasperFillManager.fillReport(reporte,
//                    parametros,
//                    conexion);*/
//            // Creo un fichero temporal para el informe
//            File file = File.createTempFile("report", ".html");
//
//// Añado el nodo Swing a la vista JavaFX. No es
//// recomendable por mezclar dos tecnologías
//// distintas (Swing + JavaFX). Visualmente no son
//// iguales, pero el funcionamiento interno tampoco
//// es el mismo.
//            var swingNode = new SwingNode();
//
//            swingNode.setContent(new JRViewer(jasperPrint));
//
//        } catch (JRException ex) {
//            java.util.logging.Logger.getLogger(PrimaryController.class.getName()).
//                    log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            java.util.logging.Logger.getLogger(PrimaryController.class.getName()).
//                    log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            java.util.logging.Logger.getLogger(PrimaryController.class.getName()).
//                    log(Level.SEVERE, null, ex);
//        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
