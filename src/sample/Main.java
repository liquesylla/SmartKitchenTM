package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        AddItemPane test = new AddItemPane(false);
        primaryStage.setScene(new Scene(test, 390, 280));
        primaryStage.show();

        Stage stage2 = new Stage();

        AddItemPane test2 = new AddItemPane(true);

        stage2.setScene(new Scene(test2, 390,300));
        stage2.show();
    }


    public static void main(String[] args) {
        launch(args);



    }

}
