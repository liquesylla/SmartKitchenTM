package sample;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import java.io.Serializable;
import java.util.ArrayList;

public class AddItemPane extends GridPane implements Serializable {
    //Declaring Class attributes
    private ComboBox type,storage;
    private ListView category;
    private TextField name,quantity,price;
    private Button addItem;
    private DatePicker datePicker;


    //Getter Methods - Encapsulation
    public ComboBox getType(){return type;}
    public ComboBox getStorage() {
        return storage;
    }
    public ListView getCategory(){return category;}
    public TextField getName(){return name;}
    public TextField getQuantity(){return quantity;}
    public TextField getPrice(){return price;}
    public Button getAddItem(){return addItem;}
    public DatePicker getDatePicker(){return datePicker;}



    //Class Constructor
    public AddItemPane(boolean isGrocery) {
        setPadding(new Insets(30, 30, 30, 30));

        type = new ComboBox();
        type.getItems().add("Perishable");
        type.getItems().add("Non-Perishable");
        type.setPrefSize(180, 30);

        name = new TextField();
        name.setPrefSize(180, 30);

        quantity = new TextField();
        quantity.setPrefSize(180, 30);

        price = new TextField();
        price.setPrefSize(180, 30);

        category = new ListView();
        category.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        category.setPrefSize(180, 100);

        addItem = new Button("Add");
        addItem.setPrefSize(100, 20);
        addItem.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        storage = new ComboBox();
        storage.setPrefSize(180, 30);

        //Adding the choosable values of the storage ComboBox
        ArrayList<String> s = new ArrayList<String>();
        s.add("Pantry");
        s.add("Fridge");
        s.add("Freezer");

        storage.setItems(FXCollections.observableArrayList(s));

        datePicker  = new DatePicker();

        Label nameL = new Label("Name:");
        nameL.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


        Label quantityL = new Label("Quantity:");
        quantityL.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


        Label typeL = new Label("Product Type:");
        typeL.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


        Label priceL = new Label("Price:");
        priceL.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


        Label categoryL = new Label("Category:");
        categoryL.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        //If the stage calling this container is for the grocery, a section for storage is included
        if(isGrocery) {
            Label storageL = new Label("Storage:");
            storageL.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

            addColumn(0, nameL, typeL,storageL, quantityL, priceL, categoryL);
            addColumn(1, name, type, storage,quantity, price, category);

        }
        else{
            addColumn(0, nameL, typeL, quantityL, priceL, categoryL);
            addColumn(1, name, type, quantity, price, category);
        }

        addRow(8, addItem,datePicker);
        setHgap(10);
        setVgap(10);

        //Action Listneer that switches the category list depending on the Product type
        type.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (type.getValue().equals("Non-Perishable")) {
                    //Populating the list with non-perishables (enum) and removing the datePicker
                    category.setItems(FXCollections.observableArrayList(NonPerishables.values()));
                    datePicker.setVisible(false);
                } else {
                    //Populating the list with perishables (enum) and showing datePicker
                    category.setItems(FXCollections.observableArrayList(Perishables.values()));
                    datePicker.setVisible(true);

                }
            }
        });
    }
    //Function that clears all the TextField values
    public void clear(){
       type.setValue("");
       name.setText("");
       quantity.setText("");
       price.setText("");
       datePicker.setVisible(false);
    }
    }



