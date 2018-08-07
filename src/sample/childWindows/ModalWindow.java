package sample.childWindows;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModalWindow {
    public static void newWindow(String title){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Pane pane = new Pane();

        Label label = new Label("Пожалуйста, выберите другой штабель.");
        label.setTranslateX(15);
        label.setTranslateY(65);

        pane.getChildren().add(label);
        Scene scene = new Scene (pane, 242,150);
        window.setScene(scene);
        window.setTitle(title);
        window.showAndWait();
    }
}
