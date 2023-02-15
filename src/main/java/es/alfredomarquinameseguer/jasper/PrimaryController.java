package es.alfredomarquinameseguer.jasper;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import com.mysql.jdbc.Driver;
import java.io.IOException;
import java.io.File;
import java.lang.System.Logger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.web.WebView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

public class PrimaryController implements Initializable {

    @FXML
    private Button button;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label info;

    @FXML
    private WebView webView;

    ObservableList<String> lista;

    HashMap<String , ReporteInfo> infoReportes;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        button.setOnAction((e) -> loadReport());

        infoReportes = new HashMap<>();
        infoReportes.put("Reporte 1", new ReporteInfo("Ej1v2.jrxml",
                "es/alfredomarquinameseguer/jasper/leaf_banner_violet.png"));
        infoReportes.put("Reporte 2", new ReporteInfo("Ejer2.jrxml",
                "es/alfredomarquinameseguer/jasper/waves.jpg"));
        lista = FXCollections.observableArrayList(infoReportes.keySet()
        );
        comboBox.setItems(lista);
        comboBox.setValue( "Reporte 1");

    }

    private void loadReport() {
        setLoadingUI();
        startLoadTask();
    }

    private void setLoadingUI() {
        info.setText("Cargando...");
        info.setStyle("-fx-text-fill: blue;");
        progressBar.setVisible(true);
        
        button.setDisable(true);
    }

    private void startLoadTask() {
        Task task = createTask();
        new Thread(task).start();
    }

    private Task createTask() {
        return new Task<Void>() {
            File file;

            @Override
            protected Void call() {
                try {
                    file = File.createTempFile("report", ".html");

                    // Crea el fichero HTML
                    JasperTestLoader jtl = new JasperTestLoader();
                    jtl.load(infoReportes.get(comboBox.valueProperty().get()));

                    JasperPrint jasperPrint = jtl.getJasperPrint();
                    JasperExportManager.exportReportToHtmlFile(jasperPrint,
                            file.getPath());
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(
                            PrimaryController.class.getName()).log(
                            Level.SEVERE, null, ex);
                } finally {
                    return null;
                }
            }

            @Override
            protected void cancelled() {
                System.out.println("cancelled");
            }

            @Override
            protected void succeeded() {
                try {
                    succeededOrFailed();
                    info.setText("");
                    // Muestra el fichero html
                    webView.setPrefHeight(5000);
                    webView.getEngine().load(file.toURI().toURL().toExternalForm());
                } catch (MalformedURLException ex) {
                    java.util.logging.Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                    failed();
                }
            }

            @Override
            protected void failed() {
                info.setStyle("-fx-text-fill: red;");
                info.setText("failed");
                succeededOrFailed();
            }

            private void succeededOrFailed() {
                
                progressBar.setVisible(false);
                button.setDisable(false);
            }
        };
    }

}
