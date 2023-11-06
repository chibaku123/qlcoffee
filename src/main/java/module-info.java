module com.mycompany.coffeql {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;

    opens com.mycompany.coffeql.controller to javafx.fxml;
    opens com.mycompany.coffeql.repository to javafx.base;

    exports com.mycompany.coffeql;
    exports com.mycompany.coffeql.controller to javafx.fxml;
}
