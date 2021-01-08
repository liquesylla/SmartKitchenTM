package sample;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.Serializable;
import java.util.ArrayList;

public class AddRecipePane extends GridPane implements Serializable {
    //Class Attributes

    private TextField name, time,quantity;
    private TextArea instructions;
    private Button finish;
    private ListView ingredients;
    private ArrayList<String> inital;

    //Getter Methods

    public ArrayList<String> getInital() {
        return inital;
    }
    public Button getFinish() {
        return finish;
    }
    public ListView getIngredients() { return ingredients; }
    public TextArea getInstructions() {
        return instructions;
    }
    public TextField getName() {
        return name;
    }
    public TextField getQuantity() {
        return quantity;
    }
    public TextField getTime() {
        return time;
    }

    //Class Constructor

    public AddRecipePane(){
        setPadding(new Insets(10,20,20,10));

        name = new TextField("Recipe Name");
        name.setStyle("-fx-background-color: #f5e2c6");
        name.setPrefSize(500,30);
        name.relocate(50,20);

        time = new TextField("Prep Time(in minutes)");
        time.setPrefSize(500,30);

        time.relocate(50,90);

        quantity = new TextField("Servings");
        quantity.setStyle("-fx-background-color: #f5e2c6");
        quantity.setPrefSize(500,30);
        quantity.relocate(50,130);

        instructions = new TextArea("Instructions...");
        instructions.setPrefSize(500,60);
        instructions.setWrapText(true);
        instructions.relocate(50,200);

        inital = new ArrayList<String>();
        inital.add("Ingredients...");

        ingredients = new ListView(FXCollections.observableArrayList(inital));
        ingredients.setPrefSize(500,60);
        ingredients.setStyle("-fx-background-color: #f5e2c6");

        //Setting style for this GridPane
        setStyle("-fx-background-color: #c99b59");

        finish = new Button("Finish");
        finish = new Button("Finish");
        finish.setPrefSize(80,30);
        finish.setStyle("fx-background-color: #f7ecdc");
        setHalignment(finish, HPos.CENTER);

        setVgap(10);

        addColumn(0,name,time,quantity,instructions, ingredients,finish);

        ColumnConstraints col0 = new ColumnConstraints(45,500,Integer.MAX_VALUE);
        col0.setHgrow(Priority.ALWAYS);
        getColumnConstraints().add(col0);

        RowConstraints row0 = new RowConstraints(30);
        RowConstraints row1 = new RowConstraints(30);
        RowConstraints row2 = new RowConstraints(30);
        RowConstraints row3 = new RowConstraints(60,100, Integer.MAX_VALUE);
        RowConstraints row4 = new RowConstraints(60,60,Integer.MAX_VALUE);
        RowConstraints row5 = new RowConstraints(30);
        row3.setVgrow(Priority.ALWAYS);
        row4.setVgrow(Priority.ALWAYS);
        getRowConstraints().addAll(row0,row1,row2,row3,row4,row5);


        //Action Listeners for each Textfield/Area - they clear it when the user begins to type input

        name.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(name.isEditable()){
                    name.setText("");
                }

            }
        });
        time.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(time.isEditable()) {
                    time.setText("");
                }
            }
        });
        quantity.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(quantity.isEditable()) {
                    quantity.setText("");
                }
            }
        });
        instructions.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(instructions.isEditable()) {
                    instructions.setText("");
                }
            }
        });
    }
    public void clear(){
        name.setText("Recipe Name");
        quantity.setText("Serving Size");
        time.setText("Prep Time");
        instructions.setText("Instructions");
        ingredients.setItems(FXCollections.observableArrayList(getInital()));
    }
}
