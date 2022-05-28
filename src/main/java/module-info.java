module com.example.graphictest {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
  


    opens com.example.graphictest to javafx.fxml ,javafx.media;
    exports com.example.graphictest;
    exports  com.example.graphictest.views;
    opens com.example.graphictest.views to javafx.fxml;
   
    

}