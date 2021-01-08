package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableStringValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.io.Serializable;

public class ContentPane extends GridPane implements Serializable {
    //Declaring class attributes

    private TextField searchTextField;
    private ListView contents;
    private Button enter,add,consume,consumeAll,donate,throwOut, move,grocery,throwOutAll,clear;

    //Getter methods

    public TextField getSearchTextField(){return searchTextField;}
    public Button getAdd(){return add;}
    public Button getEnter(){return enter;}
    public Button getConsume(){return consume;}
    public Button getConsumeAll(){return consumeAll;}
    public Button getDonate(){ return donate;}
    public Button getThrowOut(){return throwOut;}
    public Button getMove(){return move;}
    public Button getGrocery(){return grocery;}
    public Button getThrowOutAll(){return throwOutAll;}
    public Button getClear() {return clear; }
    public ListView getContents(){return contents;}

   //Class Constructor

    public ContentPane() {
        //Setting margins around components and around borders
        setPadding(new Insets(15, 15, 15, 15));

        //Creating all components
        contents = new ListView();
        contents.setPrefSize(300, 340);


        searchTextField = new TextField("What are you looking for?...");
        searchTextField.setMaxWidth(Integer.MAX_VALUE);
        searchTextField.setPrefWidth(225);


        enter = new Button("Search");
        enter.setPrefWidth(110);
        enter.setMaxWidth((Integer.MAX_VALUE));
        enter.disableProperty().bind(Bindings.isEmpty(searchTextField.textProperty())); //Binding its disable property to whether or not there is anything in the search textfield

        clear = new Button("Clear");
        clear.setPrefWidth(110);
        clear.setMaxWidth((Integer.MAX_VALUE));
        clear.setVisible(false);


        add = new Button("Add Item");
        add.setPrefSize(125, 50);
        add.setMaxWidth(Integer.MAX_VALUE);

        consume = new Button("Consume");
        consume.setPrefSize(125, 50);
        consume.setMaxWidth(Integer.MAX_VALUE);
        consume.disableProperty().bind(contents.getSelectionModel().selectedItemProperty().isNull()); //Binding the disable property to whether or not there are any contents to display

        consumeAll = new Button("Consume all");
        consumeAll.setPrefSize(125, 50);
        consumeAll.setMaxWidth(Integer.MAX_VALUE);
        consumeAll.disableProperty().bind(contents.getSelectionModel().selectedItemProperty().isNull());

        throwOut = new Button("Throw Out");
        throwOut.setPrefSize(125, 50);
        throwOut.setMaxWidth(Integer.MAX_VALUE);
        throwOut.disableProperty().bind(contents.getSelectionModel().selectedItemProperty().isNull());

        throwOutAll = new Button("Throw Out All");
        throwOutAll.setPrefSize(125, 50);
        throwOutAll.setMaxWidth(Integer.MAX_VALUE);
        throwOutAll.disableProperty().bind(contents.getSelectionModel().selectedItemProperty().isNull());

        donate = new Button("Donate");
        donate.setPrefSize(125, 50);
        donate.setMaxWidth(Integer.MAX_VALUE);
        donate.setDisable(true);

        grocery = new Button("Add to Grocery List");
        grocery.setPrefSize(125, 50);
        grocery.setMaxWidth(Integer.MAX_VALUE);
        grocery.setDisable(true);

        move = new Button("Move to..");
        move.setPrefSize(125, 50);
        move.setMaxWidth(Integer.MAX_VALUE);
        move.disableProperty().bind(contents.getSelectionModel().selectedItemProperty().isNull());


        //Adding components to grid
        add(searchTextField, 0, 0, 1, 1);
        setVgap(9);
        setHgap(5);
        add(contents, 0, 1, 2, 8);
        add(enter, 1, 0);
        add(clear,1,0);
        addRow(1, add);
        addColumn(2, consume, consumeAll, throwOut, throwOutAll, donate, grocery, move);

        //Adding Column constraints for horizontal resizing
        ColumnConstraints col0 = new ColumnConstraints(50, 225, Integer.MAX_VALUE);
        ColumnConstraints col1 = new ColumnConstraints(50, 115, Integer.MAX_VALUE);
        ColumnConstraints col2 = new ColumnConstraints(100, 150, Integer.MAX_VALUE);
        col0.setHgrow(Priority.ALWAYS); //Allowing Columns 0 and 2 to grow, but not column 1
        col2.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(col0, col1, col2);

        //Adding Row constraints for vertical resizing
        RowConstraints row0 = new RowConstraints(25);
        RowConstraints row1 = new RowConstraints(30, 30, 1000);
        RowConstraints row2 = new RowConstraints(30, 30, 1000);
        RowConstraints row3 = new RowConstraints(30, 30, 1000);
        RowConstraints row4 = new RowConstraints(30, 30, 1000);
        RowConstraints row5 = new RowConstraints(30, 30, 1000);
        RowConstraints row6 = new RowConstraints(30, 30, 1000);
        RowConstraints row7 = new RowConstraints(30, 30, 1000);
        RowConstraints row8 = new RowConstraints(30, 30, 1000);
        row0.setVgrow(Priority.ALWAYS);
        row1.setVgrow(Priority.ALWAYS);
        row2.setVgrow(Priority.ALWAYS);
        row3.setVgrow(Priority.ALWAYS);
        row4.setVgrow(Priority.ALWAYS);
        row5.setVgrow(Priority.ALWAYS);
        row6.setVgrow(Priority.ALWAYS);
        row7.setVgrow(Priority.ALWAYS);
        row8.setVgrow(Priority.ALWAYS);

        getRowConstraints().addAll(row0, row1, row2, row3, row4, row5, row6, row7, row8);


        searchTextField.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                searchTextField.setText("");
            }
        });

        contents.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (contents.getSelectionModel().getSelectedItem() instanceof NonPerishable) {
                    donate.setDisable(false);
                } else {
                    donate.setDisable(true);
                }
                if(contents.getSelectionModel().getSelectedItem() instanceof Product) {
                    grocery.setDisable(false);
                }
                else{
                    grocery.setDisable(true);
                }
                if (contents.getSelectionModel().selectedItemProperty() == null){
                    //Can't bind these two components due to added criteria, so they are manually disabled if there isnt anything in the listview
                    grocery.setDisable(true);
                    donate.setDisable(true);
                }
            }
        });
    }
}
