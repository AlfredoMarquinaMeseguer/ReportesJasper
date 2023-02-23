/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.alfredomarquinameseguer.jasper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author juanvi
 */
public class JasperTestLoader {

    private static final String CON_STR = "jdbc:mysql://localhost:3306/sakila";
    private static final String USER = "root";
    private static final String PASS = "";

    private JasperReport reporte;
    private JasperPrint jasperPrint;

    public void load(ReporteInfo info) throws ClassNotFoundException, SQLException, JRException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = (Connection) DriverManager.getConnection(
                                                            CON_STR,
                                                            USER,
                                                            PASS);

        JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream(info.getRuta())); //new File(REPORT_JXML)
        reporte = JasperCompileManager.compileReport(jasperDesign);

        HashMap params = new HashMap();
        // Cargar logo
        var logo = ClassLoader.getSystemResource(info.getLogo()).getFile();
        System.out.println(logo);
        params.put("logo", logo);
        // llamar reporte
        jasperPrint = JasperFillManager.fillReport(reporte, params, conexion);        
    }

    public JasperReport getReporte() {
        return reporte;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }
}
