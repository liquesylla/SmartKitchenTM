package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.Serializable;


public class groceryPane extends GridPane implements Serializable {
  //Class Attributes
  private ListView groceries, wishList;
  private Button list,wish;

  //Getter Methods
  public ListView getGroceries() {
      return groceries;
  }
  public ListView getWishList() {return wishList;}

  //Class Constructor
  public groceryPane(){
    setPadding(new Insets(20,10,20,20));

    list = new Button("Needed");
    list.setStyle("-fx-font-size: 17px;;-fx-background-color: #c9e6ba");
    list.relocate(50,40);
    list.setPrefSize(100,30);
    list.setPadding(new Insets(5,5,5,5));

    wish = new Button("Wanted");
    wish.setStyle("-fx-font-size: 17px;;-fx-background-color: #e3e8e4");
    wish.relocate(160,40);
    wish.setPrefSize(100,30);
    wish.setPadding(new Insets(5,5,5,5));


    setStyle("-fx-background-color: #52ab5d");

    groceries = new ListView();

    wishList = new ListView();
    wishList.setVisible(false);

    add(list,0,0,1,1);
    add(wish,1,0,1,1);
    add(groceries,0,1,3,6);
    add(wishList,0,1,3,6);


    ColumnConstraints col0 = new ColumnConstraints(100);
    ColumnConstraints col1 = new ColumnConstraints(10,100,100);
    ColumnConstraints col2 = new ColumnConstraints(23,100,Integer.MAX_VALUE);
    ColumnConstraints col3 = new ColumnConstraints(23);

    col0.setHgrow(Priority.SOMETIMES);
    col1.setHgrow(Priority.SOMETIMES);
    col2.setHgrow(Priority.ALWAYS);
    getColumnConstraints().addAll(col0,col1,col2,col3);

    RowConstraints row0 = new RowConstraints(30);
    RowConstraints row1 = new RowConstraints(60,310,Integer.MAX_VALUE);
    row1.setVgrow(Priority.ALWAYS);
    getRowConstraints().addAll(row0,row1);


    //Action Listener that changes the style of components
    list.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        list.setStyle("-fx-font-size: 17px;;-fx-background-color: #c9e6ba");
        wish.setStyle("-fx-font-size: 17px;;-fx-background-color: #e3e8e4");

        wishList.setVisible(false);
        groceries.setVisible(true);

      }
    });

    //Action Listener that changes the style of components
    wish.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        wish.setStyle("-fx-font-size: 17px;;-fx-background-color: #c9e6ba");
        list.setStyle("-fx-font-size: 17px;;-fx-background-color: #e3e8e4");

        groceries.setVisible(false);
        wishList.setVisible(true);
      }
    });
    }
}
