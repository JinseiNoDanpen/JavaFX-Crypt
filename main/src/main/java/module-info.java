module org.shinodanpen.main {
    requires javafx.controls;
    requires javafx.fxml;
            
                        
    opens org.shinodanpen.main to javafx.fxml;
    exports org.shinodanpen.main;
}