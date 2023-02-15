
module es.alfredomarquinameseguer.jasper {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires mysql.connector.java;
    requires jasperreports ;
    requires javafx.swing;
    
    
    opens es.alfredomarquinameseguer.jasper to javafx.fxml, javafx.web, 
            javafx.swing, javafx.controls;
    exports es.alfredomarquinameseguer.jasper;
}
