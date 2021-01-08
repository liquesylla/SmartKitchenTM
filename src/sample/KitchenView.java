package sample;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class KitchenView extends Pane implements Serializable {

    //Declaring class attributes
    private ImageClassApp kitchen;
    private ImageClassApp groceries;
    public String name;
    private Menu settings;
    private MenuBar menuBar;
    private Menu instructions;
    private MenuItem changeName,save, recipeInstructions,groceryInstructions,contentInstructions,generalInstructions, expired;
    private ContentPane fridge, freezer, pantry;
    private AddItemPane addFridgeItem, addGrocery, addPantryItem, addFreezerItem;
    public Text text;
    private groceryPane grocerypane;
    private AddRecipePane addRecipeItem;
    private recipePane recipepane;
    private KitchenModel model;



    //Getter methods for view components
    public ImageClassApp getKitchen() {
        return kitchen;
    }
    public ImageClassApp getGroceries() {
        return groceries;
    }

    public MenuItem getContentInstructions() {
        return contentInstructions;
    }
    public MenuItem getGroceryInstructions() {
        return groceryInstructions;
    }
    public MenuItem getExpired() {return expired; }
    public MenuItem getChangeName() {
        return changeName;
    }
    public MenuItem getSave() {return save; }
    public MenuItem getRecipeInstructions() { return recipeInstructions; }
    public MenuItem getGeneralInstructions() {return generalInstructions; }

    public ContentPane getFridge() {
        return fridge;
    }
    public ContentPane getFreezer() {
        return freezer;
    }
    public ContentPane getPantry() {
        return pantry;
    }

    public AddItemPane getAddGrocery() {
        return addGrocery;
    }
    public AddItemPane getAddFreezerItem() {
        return addFreezerItem;
    }
    public AddItemPane getAddFridgeItem() {
        return addFridgeItem;
    }
    public AddItemPane getAddPantryItem() {
        return addPantryItem;
    }


    public groceryPane getGrocerypane() {
        return grocerypane;
    }

    public AddRecipePane getAddRecipeItem() {
        return addRecipeItem;
    }

    public recipePane getRecipePane() {
        return recipepane;
    }

    public String getName(){return name; }


    //Setter methods
    public void setName(String s) {
        name = s;
    }

    //KitchenView constructor
    public KitchenView(KitchenModel m) throws FileNotFoundException {

        //Initializing the two images with URL's from the web
        kitchen = new ImageClassApp("https://www.homelight.com/blog/wp-content/uploads/2020/02/do-stainless-steel-appliances-increase-home-value-header1.png", 0, 0, 1200, 640);
        groceries = new ImageClassApp("https://0357ef9.netsolhost.com/fff/wp-content/uploads/2012/09/grocery-bag.png", 833, 435, 130, 169);
        getChildren().addAll(kitchen, groceries);

        //Initializing the menu, menu bar and menu items - also adjusting their appearances
        settings = new Menu("_Settings");
        menuBar = new MenuBar();
        menuBar.getMenus().add(settings);
        settings.setStyle("-fx-font-size: 14px;");
        menuBar.setStyle("-fx-background-color: #F4D3B1");

        recipeInstructions = new MenuItem("Recipe Instructions");
        groceryInstructions = new MenuItem("Grocery Instructions");
        contentInstructions = new MenuItem("Storage Instructions");
        expired = new MenuItem("Expiring Items");

        changeName = new MenuItem("Change your name");
        instructions = new Menu("Instructions");
        save = new MenuItem("Save");
        generalInstructions = new MenuItem("General");
        instructions.getItems().addAll(recipeInstructions, groceryInstructions,contentInstructions,generalInstructions);
        settings.getItems().addAll(changeName, instructions, save,expired);
        getChildren().addAll(menuBar);

        model = m;
        name = model.getName();

        //Initalizing the ContentPanes' attributes
        fridge = new ContentPane();

        freezer = new ContentPane();
        freezer.setStyle("-fx-background-color: #9c9892");

        pantry = new ContentPane();
        pantry.setStyle("-fx-background-color: #c7ac75");

        //Initializing the AddItemPanes

        addFridgeItem = new AddItemPane(false);
        addFridgeItem.setStyle("-fx-background-color: #c5e0e3");

        addGrocery = new AddItemPane(true);
        addGrocery.setStyle("-fx-background-color: #9dc793");

        addFreezerItem = new AddItemPane(false);
        addFreezerItem.setStyle("-fx-background-color: #d6d0ba");

        addPantryItem = new AddItemPane(false);
        addPantryItem.setStyle("-fx-background-color: #d6b683");

        grocerypane = new groceryPane();
        addRecipeItem = new AddRecipePane();
        text = new Text();

        recipepane = new recipePane();

        update();

    }

    //Method that displays a text input dialogue, requests the user's name, updates the class name attribute, and returns a message
    public String inputDialogue(String title, String header, String text) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(text);
        Optional<String> result = dialog.showAndWait();

        //Error control: repeatedly asking the user for input if none is entered
        while (result.get().isEmpty()) {
            result = dialog.showAndWait();
        }
        //Updating the class name attribute
        return result.get();

    }

    //Method that displays an alert to the user containing its instructions
    public void outputDialogue(String title, String header, String text) {
        Alert instructions = new Alert(Alert.AlertType.INFORMATION);
        instructions.setTitle(title);
        instructions.setHeaderText(header);
        instructions.setContentText(text);
        instructions.showAndWait();
    }

    //Updating the view
    public void update() {
        fridge.getContents().setItems(FXCollections.observableArrayList(model.getFridgeContents()));
        freezer.getContents().setItems(FXCollections.observableArrayList(model.getFreezerContents()));
        pantry.getContents().setItems(FXCollections.observableArrayList(model.getPantryContents()));
        grocerypane.getGroceries().setItems(FXCollections.observableArrayList(model.getGroceries()));
        grocerypane.getWishList().setItems(FXCollections.observableArrayList(model.getWishList()));
        recipepane.getRecipeList().setItems(FXCollections.observableArrayList(model.getRecipes()));
        model.expire();
    }

    //AddFunction that takes an AddItemPane and a storage location, then reads the appropriate infomation to add a product to the model
    private void addFunction(AddItemPane pane, String storage) {
        if (pane.getPrice().getText().isEmpty() || pane.getName().getText().isEmpty() || pane.getQuantity().getText().isEmpty() || pane.getCategory().getSelectionModel().getSelectedItems().isEmpty() || pane.getType().getSelectionModel().isEmpty()) {
            outputDialogue(text.getErrorTitle(), text.getErrorHeader() + " " + name, text.getErrorContent());
        }

        else {
            try {
                String name = pane.getName().getText();
                int quantity = Integer.parseInt(pane.getQuantity().getText());
                double price = Double.parseDouble(pane.getPrice().getText());

                if (pane.getType().getValue().equals("Perishable")) {
                    if (pane.getDatePicker().getValue() == null) {
                        outputDialogue(text.getErrorTitle(), text.getErrorHeader() + " " + this.name, text.getErrorContent());
                        return;
                    }
                    ArrayList<Perishables> category = new ArrayList<>();
                    category.addAll(pane.getCategory().getSelectionModel().getSelectedItems());
                    LocalDate exp = pane.getDatePicker().getValue();

                    if(!exp.isBefore(LocalDate.now())){
                        Perishable p = new Perishable(name, quantity, price, exp, category, storage);
                        model.addProduct(p);
                        update();
                    }
                    else{
                        outputDialogue(text.getErrorTitle(), text.getDateErrorHeader(), text.getDateErrorContent());
                    }

                }else {
                    ArrayList<NonPerishables> category = new ArrayList<>();
                    category.addAll(pane.getCategory().getSelectionModel().getSelectedItems());
                    NonPerishable p = new NonPerishable(name, quantity, price, category, storage);
                    model.addProduct(p);
                    update();
                }
            } catch (NumberFormatException e) {
                outputDialogue(text.getErrorTitle(), text.getErrorHeader() + " " + name, text.getErrorContent());
            } finally {
                pane.getName().setText("");
                pane.getQuantity().setText("");
                pane.getPrice().setText("");
                pane.getCategory().getSelectionModel().select(-1);
                pane.getDatePicker().setValue(null);
            }
        }
    }

    //Consume Function that takes a location and consumes the selected item
    public void consume(String location) {
        try {
            int quantity = Integer.parseInt(inputDialogue(text.getConsumeTitle(), text.getConsumeHeader(), text.getConsumeContent()));
            if (location.equals("Fridge")) {
                Consumable p = (Consumable) fridge.getContents().getSelectionModel().getSelectedItem();
                model.consume(p, quantity);

            } else if (location.equals("Freezer")) {
                Consumable p = (Consumable) freezer.getContents().getSelectionModel().getSelectedItem();
                model.consume(p, quantity);
            } else {
                Consumable p = (Consumable) pantry.getContents().getSelectionModel().getSelectedItem();
                model.consume(p, quantity);
            }
            update();
        } catch (NumberFormatException e) {
            outputDialogue(text.getErrorTitle(), text.getErrorHeader(), text.getErrorContent());
        } catch (RuntimeException e) {
        }
    }


    public void consumeAll(String location) {
        if (location.equals("Fridge")) {
            Consumable p = (Consumable) fridge.getContents().getSelectionModel().getSelectedItem();
            model.consumeAll(p);
        } else if (location.equals("Freezer")) {
            Consumable p = (Consumable) freezer.getContents().getSelectionModel().getSelectedItem();
            model.consumeAll(p);
        } else {
            Consumable p = (Consumable) pantry.getContents().getSelectionModel().getSelectedItem();
            model.consumeAll(p);
        }
        update();

    }


    public void throwOutAll(String location) {
        if (location.equals("Fridge")) {
            Consumable p = (Consumable) fridge.getContents().getSelectionModel().getSelectedItem();
            model.throwOutAll(p);
        } else if (location.equals("Freezer")) {
            Consumable p = (Consumable) freezer.getContents().getSelectionModel().getSelectedItem();
            model.throwOutAll(p);
        } else {
            Consumable p = (Consumable) pantry.getContents().getSelectionModel().getSelectedItem();
            model.throwOutAll(p);
        }
        update();
    }

    public void throwOut(String location) {
        try {
            int quantity = Integer.parseInt(inputDialogue(text.getThrowOutTitle(), text.getThrowOutHeader() + name, text.getThrowOutContent()));
            if (location.equals("Fridge")) {
                Consumable p = (Consumable) fridge.getContents().getSelectionModel().getSelectedItem();
                model.throwOut(p, quantity);

            } else if (location.equals("Freezer")) {
                Consumable p = (Consumable) freezer.getContents().getSelectionModel().getSelectedItem();
                model.throwOut(p, quantity);
            } else {
                Consumable p = (Consumable) pantry.getContents().getSelectionModel().getSelectedItem();
                model.throwOut(p, quantity);
            }
            update();
        } catch (NumberFormatException e) {
            outputDialogue(text.getErrorTitle(), text.getErrorHeader(), text.getErrorContent());
        } catch (RuntimeException e) {
        }
    }

    public void donate(String location) {
        try {
            int quantity = Integer.parseInt(inputDialogue(text.getDonateTitle(), text.getDonateHeader() + name, text.getDonateContent()));
            if (location.equals("Fridge")) {
                NonPerishable p = (NonPerishable) fridge.getContents().getSelectionModel().getSelectedItem();
                model.donate(p, quantity);

            } else if (location.equals("Freezer")) {
                NonPerishable p = (NonPerishable) freezer.getContents().getSelectionModel().getSelectedItem();
                model.donate(p, quantity);
            } else {
                NonPerishable p = (NonPerishable) pantry.getContents().getSelectionModel().getSelectedItem();
                model.donate(p, quantity);
            }
            update();
        } catch (NumberFormatException e) {
            outputDialogue(text.getErrorTitle(), text.getErrorHeader(), text.getErrorContent());
        } catch (RuntimeException e) {
        }
    }

    public void grocery(String location) {
        if (location.equals("Fridge")) {
            Product p = (Product) fridge.getContents().getSelectionModel().getSelectedItem();
            model.addGrocery(p);

        } else if (location.equals("Freezer")) {
            Product p = (Product) freezer.getContents().getSelectionModel().getSelectedItem();
            model.addGrocery(p);
        } else {
            Product p = (Product) pantry.getContents().getSelectionModel().getSelectedItem();
            model.addGrocery(p);
        }
        update();
    }

    public void moveItem(String location) {
        try {
            String s = inputDialogue(text.getMoveTitle(), text.getMoveHeader() + name, text.getMoveContent());
            Storage string = Storage.valueOf(s);
            if (location.equals("Fridge")) {
                Product p = (Product) fridge.getContents().getSelectionModel().getSelectedItem();
                model.move(s, p);

            } else if (location.equals("Freezer")) {
                Product p = (Product) freezer.getContents().getSelectionModel().getSelectedItem();
                model.move(s, p);
            } else {
                Product p = (Product) pantry.getContents().getSelectionModel().getSelectedItem();
                model.move(s, p);
            }
            update();


        } catch (IllegalArgumentException e) {
            outputDialogue(text.getErrorTitle(), text.getErrorHeader(), text.getErrorContent());
        } catch (RuntimeException e) {
        }
    }

    //Function that branches out to private methods to show a view displaying product/recipe information
    public void show(String location) {
        if (location.equals("Fridge")) {
            Consumable p = (Consumable) fridge.getContents().getSelectionModel().getSelectedItem();
            if (p instanceof Recipe) {
                recipeShow((Recipe) p);
            } else if (p instanceof Product) {
                productShow((Product) p, addFridgeItem);
            }
        } else if (location.equals("Freezer")) {
            Consumable p = (Consumable) freezer.getContents().getSelectionModel().getSelectedItem();
            if (p instanceof Recipe) {
                recipeShow((Recipe) p);
            } else if (p instanceof Product) {
                productShow((Product) p, addFreezerItem);
            }
        } else if (location.equals("Pantry")) {
            Consumable p = (Consumable) pantry.getContents().getSelectionModel().getSelectedItem();
            if (p instanceof Recipe) {
                recipeShow((Recipe) p);
            } else if (p instanceof Product) {
                productShow((Product) p, addPantryItem);
            }
        }

    }

    private void recipeShow(Recipe r) {
        addRecipeItem.getName().setText(r.getName());
        addRecipeItem.getName().setEditable(false);

        addRecipeItem.getTime().setText("Prep Time: " + Integer.toString(r.getPrepTime())+ " minutes");
        addRecipeItem.getTime().setEditable(false);

        addRecipeItem.getQuantity().setText("Servings: " + Integer.toString(r.getQuantity()));
        addRecipeItem.getQuantity().setEditable(false);

        addRecipeItem.getInstructions().setText(r.getInstructions());
        addRecipeItem.getInstructions().setEditable(false);

        addRecipeItem.getIngredients().setItems(FXCollections.observableArrayList(r.getIngredients()));
        addRecipeItem.getIngredients().setDisable(false);

        addRecipeItem.getFinish().setDisable(true);

    }

    private void productShow(Product p, AddItemPane pane) {
        pane.getName().setText(p.getName());
        pane.getQuantity().setText(Integer.toString(p.getQuantity()));
        pane.getPrice().setText(Double.toString(p.getPrice()) + "$");
        if (p instanceof Perishable) {
            pane.getCategory().setItems(FXCollections.observableArrayList(((Perishable) p).getCategory()));
            pane.getType().setValue("Perishable");
            pane.getDatePicker().setValue(((Perishable) p).getExpiryDate());
        } else {
            pane.getCategory().setItems(FXCollections.observableArrayList(((NonPerishable) p).getCategory()));
            pane.getType().setValue("Non-Perishable");
        }

        setEdit(pane,false);

    }

    //Cooking function that moves the recipe to the appropriate storage area
    public void cook() {
        Recipe r = (Recipe) recipepane.getRecipeList().getSelectionModel().getSelectedItem();
        try {
            String s = inputDialogue(text.getCookTitle(), text.getCookHeader() + r.getName() + ", " + name, text.getCookContent());
            Storage string = Storage.valueOf(s);
            r.setStorage(s);
            if(!model.cook(r)){
                outputDialogue(text.getInstructionsTitle(),text.getNoIngredientsHeader(),text.getNoIngredientsContent() + r.getName() + ":" + model.getMissing());
            }
            update();
        } catch (IllegalArgumentException e) {
            outputDialogue(text.getErrorTitle(), text.getErrorHeader(), text.getErrorContent());
        } catch (RuntimeException e) {
        }
    }

    //Function that reads a new recipe from the user, and updates the model
    public void addToRecipes() {
        if (addRecipeItem.getName().getText().isEmpty() || addRecipeItem.getQuantity().getText().isEmpty() || addRecipeItem.getTime().getText().isEmpty() || addRecipeItem.getIngredients().getItems().size() <= 1 || addRecipeItem.getInstructions().getText().isEmpty()) {
            outputDialogue(text.getErrorTitle(), text.getErrorHeader() + " " + name, text.getErrorContent());
        } else {
            try {
                String name = addRecipeItem.getName().getText();
                int quantity = Integer.parseInt(addRecipeItem.getQuantity().getText());
                int time = Integer.parseInt(addRecipeItem.getTime().getText());
                String instructions = addRecipeItem.getInstructions().getText();
                ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
                ingredients.addAll(addRecipeItem.getIngredients().getItems());
                ingredients.remove(0);
                Recipe r = new Recipe(name, time, quantity, instructions, ingredients);
                model.addRecipe(r);
                update();


            } catch (NumberFormatException e) {
                outputDialogue(text.getErrorTitle(), text.getErrorHeader() + " " + name, text.getErrorContent());
            } finally {
                addRecipeItem.clear();
            }
        }


    }
    //Function that reads in a new ingredient from the user
    public void addIngredient() {
        try {
            String s = inputDialogue(text.getNameTitle(), text.getIngHeader(), text.getIngContent());
            int q = Integer.parseInt(inputDialogue(text.getNameTitle(), text.getqHeader() + s + "?", text.getqContent()));
            Ingredient i = new Ingredient(s, q);
            addRecipeItem.getIngredients().getItems().add(i);


        } catch (IllegalArgumentException e) {
            outputDialogue(text.getErrorTitle(), text.getErrorHeader(), text.getErrorContent());
        } catch (RuntimeException e) {

        }
    }

    public void addItemToFridge(KitchenModel model) {
        addFunction(addFridgeItem, "Fridge");
    }

    public void addItemToPantry(KitchenModel model) {
        addFunction(addPantryItem, "Pantry");
    }

    public void addItemToFreezer(KitchenModel model) {
        addFunction(addFreezerItem, "Freezer");
    }

    //Function that saves the user's kitchen to a file
    public void save(KitchenModel m) {
        String filepath = name + "kitchen.txt";
        try {
            ObjectOutputStream out;
            out = new ObjectOutputStream(new FileOutputStream(filepath));

            out.writeObject(m);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, We didn't find you file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Function that reads a new grocery item and displays it
    public void addGrocery() {
        if (addGrocery.getPrice().getText().isEmpty() || addGrocery.getName().getText().isEmpty() || addGrocery.getQuantity().getText().isEmpty() || addGrocery.getCategory().getSelectionModel().getSelectedItems().isEmpty() || addGrocery.getType().getSelectionModel().isEmpty() || addGrocery.getStorage().getSelectionModel().isEmpty()) {
            outputDialogue(text.getErrorTitle(), text.getErrorHeader() + " " + name, text.getErrorContent());
        } else {
            try {
                String name = addGrocery.getName().getText();
                int quantity = Integer.parseInt(addGrocery.getQuantity().getText());
                double price = Double.parseDouble(addGrocery.getPrice().getText());
                String storage = (String) addGrocery.getStorage().getSelectionModel().getSelectedItem();

                if (addGrocery.getType().getValue().equals("Perishable")) {
                    if (addGrocery.getDatePicker().getValue() == null) {
                        outputDialogue(text.getErrorTitle(), text.getErrorHeader() + " " + this.name, text.getErrorContent());
                        return;
                    }
                    ArrayList<Perishables> category = new ArrayList<>();
                    category.addAll(addGrocery.getCategory().getSelectionModel().getSelectedItems());
                    LocalDate exp = addGrocery.getDatePicker().getValue();
                    if(!exp.isBefore(LocalDate.now())) {
                        Perishable p = new Perishable(name, quantity, price, exp, category, storage);
                        model.addGrocery(p);
                        update();
                    }
                    else{
                        outputDialogue(text.getErrorTitle(), text.getDateErrorHeader(), text.getDateErrorContent());
                    }
                } else {
                    ArrayList<NonPerishables> category = new ArrayList<>();
                    category.addAll(addGrocery.getCategory().getSelectionModel().getSelectedItems());
                    NonPerishable p = new NonPerishable(name, quantity, price, category, storage);
                    model.addGrocery(p);
                    update();

                }
            } catch (NumberFormatException e) {
                outputDialogue(text.getErrorTitle(), text.getErrorHeader() + " " + name, text.getErrorContent());
            } catch (IllegalArgumentException e) {
                outputDialogue(text.getErrorTitle(), text.getErrorHeader() + " " + name, text.getErrorContent());
            } finally {
                addGrocery.getName().setText("");
                addGrocery.getQuantity().setText("");
                addGrocery.getPrice().setText("");
                addGrocery.getCategory().getSelectionModel().select(-1);
                addGrocery.getDatePicker().setValue(null);
            }
        }
    }

    public void removeGrocery() {
        Product p = (Product)grocerypane.getGroceries().getSelectionModel().getSelectedItem();
        model.removeGrocery(p);
        update();
    }

    public void removeWishList() {
        Ingredient i = (Ingredient) grocerypane.getWishList().getSelectionModel().getSelectedItem();
        model.removeWishlist(i);
        update();
    }

    public void addWishList() {
        try {
            String s = inputDialogue(text.getWishTitle(), text.getWishHeader(), text.getWishContent());
            int q = Integer.parseInt(inputDialogue(text.getNameTitle(), text.getqHeader() + s + "?", text.getqContent()));
            Ingredient i = new Ingredient(s, q);
            model.addWishList(i);

        } catch (IllegalArgumentException e) {
            outputDialogue(text.getErrorTitle(), text.getErrorHeader(), text.getErrorContent());
        } catch (RuntimeException e) {
        }
        update();
    }

    public void buyGrocery(){
        for(int i = 0; i<grocerypane.getGroceries().getItems().size();i++){
            buyGrocery((Product) grocerypane.getGroceries().getItems().get(i));
        }
        update();
    }

    private void buyGrocery(Product p){
        model.buy(p,p.getDefaultQuantity());
    }



    public  void searchFridge(){
        String s = fridge.getSearchTextField().getText();
        search(s,model.getFridgeContents(),fridge);
    }
    public void searchFreezer(){
        String s = freezer.getSearchTextField().getText();
        search(s,model.getFreezerContents(),freezer);

    }
    public void searchPantry(){
        String s = pantry.getSearchTextField().getText();
        search(s,model.getPantryContents(),pantry);

    }
    private void search(String field, List<Consumable> search, ContentPane p){
        ArrayList<Consumable> result = new ArrayList<Consumable>();
        result.addAll(model.searchFor(field,search));
        p.getContents().setItems(FXCollections.observableArrayList(result));
    }
    public void setEdit(AddItemPane p, Boolean value){
        p.getAddItem().setDisable(!value);
        p.getName().setEditable(value);
        p.getQuantity().setEditable(value);
        p.getType().setDisable(!value);
        p.getPrice().setEditable(value);
        p.getCategory().setEditable(value);
        p.getDatePicker().setEditable(!value);
        p.getStorage().setEditable(value);
    }
    public void expire(){
        outputDialogue(text.getExpireTitle(),text.getExpireHeader(), text.getExpireContent()+model.getExpiryList());
    }

}
