package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
public class Test extends Application {
    public void start(Stage primaryStage) throws FileNotFoundException {
        //Creating the Pane
        GridPane aPane = new GridPane();
        aPane.setPadding(new Insets(15, 15, 15, 15));

        //Creating all components
        TextField search = new TextField("What are you looking for?...");
        search.setMaxWidth(Integer.MAX_VALUE);
        search.setPrefWidth(225);

        Button enter = new Button("Search");
        enter.setPrefWidth(110);
        enter.setMaxWidth((Integer.MAX_VALUE));
        Button add = new Button("Add Item");
        add.setPrefSize(125,50);
        add.setMaxWidth(Integer.MAX_VALUE);

        Button consume = new Button("Consume");
        consume.setPrefSize(125,50);
        consume.setMaxWidth(Integer.MAX_VALUE);

        Button consumeAll = new Button("Consume all");
        consumeAll.setPrefSize(125,50);
        consumeAll.setMaxWidth(Integer.MAX_VALUE);

        Button throwOut = new Button("Throw Out");
        throwOut.setPrefSize(125,50);
        throwOut.setMaxWidth(Integer.MAX_VALUE);

        Button donate = new Button("Donate");
        donate.setPrefSize(125,50);
        donate.setMaxWidth(Integer.MAX_VALUE);

        Button grocery = new Button("Add to Grocery List");
        grocery.setPrefSize(125,50);
        grocery.setMaxWidth(Integer.MAX_VALUE);
        Button move = new Button ("Move to..");
        move.setPrefSize(125,50);
        move.setMaxWidth(Integer.MAX_VALUE);
        ListView contents = new ListView();
        contents.setPrefSize(300,340);


        //Adding components to grid
        aPane.add(search,0,0,1,1);
        aPane.setVgap(9);
        aPane.setHgap(5);
        aPane.add(contents,0,1,2,8);
        aPane.add(enter,1,0);
        aPane.addRow(1,add);
        aPane.addColumn(2,consume,consumeAll,throwOut,donate,grocery,move);

        ColumnConstraints col0 = new ColumnConstraints(50, 225, Integer.MAX_VALUE);
        ColumnConstraints col1 = new ColumnConstraints(50,115,Integer.MAX_VALUE);
        ColumnConstraints col2 = new ColumnConstraints(100,150, Integer.MAX_VALUE);
        col0.setHgrow(Priority.ALWAYS);
        col2.setHgrow(Priority.ALWAYS);
        aPane.getColumnConstraints().addAll(col0,col1,col2);


        RowConstraints row0 = new RowConstraints(25);
        RowConstraints row1 = new RowConstraints(30,30,Integer.MAX_VALUE);
        RowConstraints row2 = new RowConstraints(30,30,Integer.MAX_VALUE);
        RowConstraints row3 = new RowConstraints(30,30,Integer.MAX_VALUE);
        RowConstraints row4 = new RowConstraints(30,30,Integer.MAX_VALUE);
        RowConstraints row5 = new RowConstraints(30,30,Integer.MAX_VALUE);
        RowConstraints row6 = new RowConstraints(30,30,Integer.MAX_VALUE);
        RowConstraints row7 = new RowConstraints(30,30,Integer.MAX_VALUE);



        row0.setVgrow(Priority.ALWAYS);
        row1.setVgrow(Priority.ALWAYS);
        row2.setVgrow(Priority.ALWAYS);
        row3.setVgrow(Priority.ALWAYS);
        row4.setVgrow(Priority.ALWAYS);
        row5.setVgrow(Priority.ALWAYS);
        row6.setVgrow(Priority.ALWAYS);
        row7.setVgrow(Priority.ALWAYS);

        aPane.getRowConstraints().addAll(row0,row1, row2, row3, row4,row5,row6,row7);

        primaryStage.setTitle("Simple GridPane Example");
        primaryStage.setScene(new Scene(aPane, 500,350));
        primaryStage.show();


    }

    public static void main(String args[]) {
        launch(args);
    }
}
