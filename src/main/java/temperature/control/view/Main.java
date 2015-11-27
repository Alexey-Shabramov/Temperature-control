package temperature.control.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ListResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
         Parent root = FXMLLoader.load(getClass().getResource("/MainPane.fxml"));
         primaryStage.setTitle("Temperature control");
         primaryStage.setResizable(false);
         primaryStage.setScene(new Scene(root, 966, 721));
         primaryStage.show();
    }

    private static class ResourceWrapper extends ListResourceBundle {
        @Override
        protected Object[][] getContents() {
            return new Object[0][];
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
