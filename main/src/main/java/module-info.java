module org.shinodanpen.main {
    requires javafx.controls;
    requires javafx.fxml;
            
                        
    opens org.shinodanpen.algorithm to javafx.fxml;
    exports org.shinodanpen.algorithm;
}