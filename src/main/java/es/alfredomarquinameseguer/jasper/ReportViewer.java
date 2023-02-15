/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfredomarquinameseguer.jasper;

/**
 *
 * @author Alfre
 */
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javafx.embed.swing.SwingNode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JComponent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportViewer extends VBox {

    private JasperReport reporte;

    private static final String REPORT_JXML = "Ejer2.jrxml";
    private static final String CONEXION_STR = "jdbc:mysql://localhost:3306/sakila";
    private static final String USER = "root";
    private static final String PASS = "";

    public ReportViewer() throws JRException, ClassNotFoundException, SQLException {
        // cargar el archivo de informe Jasper
        File file = new File("Ejer2.jasper");

        JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream(REPORT_JXML)); //new File(REPORT_JXML)
        reporte = JasperCompileManager.compileReport(jasperDesign);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);

        // establecer parámetros de informe
        HashMap<String, Object> parameters = new HashMap<>();

        // establecer conexión a la base de datos (opcional)
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(CONEXION_STR,
                USER, PASS);

        // generar el informe
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);

        // crear un SwingNode para el visor de informes JasperViewer
        SwingNode swingNode = new SwingNode();
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
        swingNode.setContent((JComponent) jasperViewer.getContentPane());

        // agregar el visor de informes al contenedor JavaFX
        AnchorPane anchorPane = new AnchorPane();
        AnchorPane.setTopAnchor(swingNode, 0.0);
        AnchorPane.setRightAnchor(swingNode, 0.0);
        AnchorPane.setBottomAnchor(swingNode, 0.0);
        AnchorPane.setLeftAnchor(swingNode, 0.0);
        anchorPane.getChildren().add(swingNode);
        getChildren().add(anchorPane);
    }
}
