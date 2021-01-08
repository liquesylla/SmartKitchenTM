package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.io.Serializable;
import java.util.ArrayList;

public class recipePane  extends GridPane  implements Serializable {
    //Class Attributes
    private ListView recipeList;
    private Button recipes, Add;

    //Getter methods
    public ListView getRecipeList() {
        return recipeList;
    }
    public Button getAdd() {
        return Add;
    }

    public recipePane(){
        setPadding(new Insets(20,10,20,20));

        recipes = new Button("My Recipes");
        recipes.setStyle("-fx-font-size: 17px;;-fx-background-color: #f5e2c6");
        recipes.relocate(50,40);
        recipes.setPrefSize(100,30);
        recipes.setPadding(new Insets(5,5,5,5));

        Add = new Button("Add a Recipe");
        Add.setStyle("-fx-font-size: 17px;;-fx-background-color: #e3e8e4");
        Add.relocate(160,40);
        Add.setPrefSize(200,30);
        Add.setPadding(new Insets(5,5,5,5));

        setStyle("-fx-background-color: #52ab5d");

        recipeList = new ListView();

        add(recipes,0,0,1,1);
        add(Add,1,0,1,1);
        add(recipeList,0,1,3,6);

        ColumnConstraints col0 = new ColumnConstraints(100);
        ColumnConstraints col1 = new ColumnConstraints(140,140,Integer.MAX_VALUE);
        ColumnConstraints col2 = new ColumnConstraints(23,180,Integer.MAX_VALUE);
        ColumnConstraints col3 = new ColumnConstraints(23);

        col0.setHgrow(Priority.SOMETIMES);
        col1.setHgrow(Priority.ALWAYS);
        col2.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(col0,col1,col2,col3);

        RowConstraints row0 = new RowConstraints(30);
        RowConstraints row1 = new RowConstraints(60,310,Integer.MAX_VALUE);
        row1.setVgrow(Priority.ALWAYS);
        getRowConstraints().addAll(row0,row1);

        setStyle("-fx-background-color: #c99b59");

        //ActionListener on buttons to change their appearance when they are clicked
        recipes.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                recipes.setStyle("-fx-font-size: 17px;;-fx-background-color: #f5e2c6");
                Add.setStyle("-fx-font-size: 17px;;-fx-background-color: #e3e8e4");
            }
        });
        Add.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Add.setStyle("-fx-font-size: 17px;;-fx-background-color:#f5e2c6");
                recipes.setStyle("-fx-font-size: 17px;;-fx-background-color: #e3e8e4");
            }
        });

    }

}