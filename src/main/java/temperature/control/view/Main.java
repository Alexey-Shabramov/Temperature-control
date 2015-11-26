package temperature.control.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = new URL("MainPane.fxml");
        fxmlLoader.setLocation(url);
        Parent root = (Parent) fxmlLoader.load();
        primaryStage.setTitle("Temperature control");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 966, 721));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
