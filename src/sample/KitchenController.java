package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.*;
import java.util.Scanner;

public class KitchenController extends Application {
    public void start(Stage stage) throws Exception {
        KitchenModel model = new KitchenModel(); //Creating Initial Model
        Boolean loaded = false; // Variable stores whether a saved kitchen is being loaded or not

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Would you like to load a kitchen? If so, drop the filepath below (may have to specifc '.txt'). If not, enter the letter n");
        String filepath = myObj.nextLine();
        myObj.close();// Read user input for the file they want to load

        if (!filepath.equals("n")) {
            try {
                ObjectInputStream in;
                in = new ObjectInputStream(new FileInputStream(filepath));

                model = (KitchenModel) in.readObject();
                loaded = true;
                in.close();
            } catch (FileNotFoundException e) {
                System.out.println("Sorry, We didn't find your file.");
            }
        }

        KitchenModel finalModel = model; //creating the final model and initializing it to the temporary model above
        KitchenView view = new KitchenView(finalModel); //initializing the view with the model

        //Creating the Stages
        Stage fridgeStage = new Stage();
        fridgeStage.setScene(new Scene(view.getFridge()));
        fridgeStage.setMinHeight(400);
        fridgeStage.setMinWidth(280);


        Stage freezerStage = new Stage();
        freezerStage.setScene(new Scene(view.getFreezer()));
        freezerStage.setMinHeight(400);
        freezerStage.setMinWidth(280);

        Stage pantryStage = new Stage();
        pantryStage.setScene(new Scene(view.getPantry()));
        pantryStage.setMinHeight(400);
        pantryStage.setMinWidth(280);

        Stage addFridge = new Stage();
        addFridge.setScene(new Scene(view.getAddFridgeItem()));
        addFridge.setResizable(false);
        addFridge.setTitle("Add Item to Fridge");

        Stage addFreezer = new Stage();
        addFreezer.setScene(new Scene(view.getAddFreezerItem()));
        addFreezer.setResizable(false);
        addFreezer.setTitle("Add Item to Freezer");

        Stage addPantry = new Stage();
        addPantry.setScene(new Scene(view.getAddPantryItem()));
        addPantry.setResizable(false);
        addPantry.setTitle("Add Item to Pantry");

        Stage addGrocery = new Stage();
        addGrocery.setScene(new Scene(view.getAddGrocery()));
        addGrocery.setResizable(false);
        addGrocery.setTitle("Add Item to Grocery list");


        Stage groceryStage = new Stage();
        groceryStage.setScene(new Scene(view.getGrocerypane()));
        groceryStage.setTitle("Groceries");
        groceryStage.setMinHeight(200);
        groceryStage.setMinWidth(315);

        Stage addRecipe = new Stage();
        addRecipe.setScene(new Scene(view.getAddRecipeItem()));
        addRecipe.setTitle("Recipes");
        addRecipe.setMinHeight(385);
        addRecipe.setMinWidth(200);

        Stage recipes = new Stage();
        recipes.setScene(new Scene(view.getRecipePane()));
        recipes.setMinHeight(200);
        recipes.setMinWidth(315);

        //Creating a pane for the main stage
        Pane pane1 = new Pane();
        pane1.getChildren().add(view);


        //Action Listener that closes all the substages once an unimportant background has been clicked
        view.getKitchen().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                fridgeStage.close();
                pantryStage.close();
                freezerStage.close();
                addFridge.close();
                addGrocery.close();
                addFreezer.close();
                addRecipe.close();
                groceryStage.close();
                addPantry.close();
            }
        });

        //Adding ActionListener to the image
        view.getGroceries().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //Checking if mouseEvent is between the fridge,freezer and top left pantry  x coordinates
                if (mouseEvent.getX() < 403 && mouseEvent.getX() > 170) {
                    if (mouseEvent.getY() > 468) {
                        freezerStage.show();
                        fridgeStage.close();
                        pantryStage.close();
                        recipes.close();
                    } else if (mouseEvent.getY() > 167) {
                        System.out.println("Clicked on the Fridge");
                        fridgeStage.show();
                        freezerStage.close();
                        pantryStage.close();
                        recipes.close();

                    } else {
                        System.out.println("Clicked on the Pantry");
                        pantryStage.show();
                        freezerStage.close();
                        fridgeStage.close();
                        recipes.close();
                    }
                }
                //Checking if mouseEvent is on the middle + right side of the pantry
                else if (mouseEvent.getX() > 403 && (mouseEvent.getY() < 307 && mouseEvent.getY() > 30)) {
                    System.out.println("Clicked on the pantry from tha MIDDLE");
                    pantryStage.show();
                    freezerStage.close();
                    fridgeStage.close();
                }
                //Checking if mouseEvent is on the grocery icon
                else if ((mouseEvent.getX() > 827 && mouseEvent.getX() < 960) && (mouseEvent.getY() > 433.3 && mouseEvent.getY() < 592)) {
                    System.out.println("Groceries were clicked");
                    groceryStage.show();
                }
                //Checking if mouseEvent is on the counter/island
                else if ((mouseEvent.getX() > 416 && mouseEvent.getX() < 827) && mouseEvent.getY() > 400) {
                    System.out.println("Clicked on recipes");
                    recipes.show();
                }

                //If the grocery view was clicked, but no important areas were clicked - program closes substages
                else {
                    fridgeStage.close();
                    pantryStage.close();
                    freezerStage.close();
                    addFridge.close();
                    addGrocery.close();
                    addFreezer.close();
                    addPantry.close();
                    addRecipe.close();
                    recipes.close();
                }
            }
        });

        //Adding ActionListener to the MenuBar and Menu Items

        view.getGeneralInstructions().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Displaying instructions by calling instructionsDialogue() function in view
                view.outputDialogue(view.text.getInstructionsTitle(), view.text.getInstructionsHeader() + view.name, view.text.getInstructionsText());
            }
        });

        view.getRecipeInstructions().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.outputDialogue(view.text.getInstructionsTitle(), view.text.getRecipeHeader(), view.text.getRecipeContent());
            }
        });
        view.getGroceryInstructions().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.outputDialogue(view.text.getInstructionsTitle(), view.text.getGroceryHeader(), view.text.getGroceryContent());
            }
        });
        view.getContentInstructions().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.outputDialogue(view.text.getInstructionsTitle(), view.text.getContentHeader(), view.text.getContentContent());
            }
        });
        view.getExpired().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.expire();
            }
        });

        view.getSave().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.save(finalModel);
            }
        });

        //Adding ActionListener to changeName menuItem
        view.getChangeName().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Resetting Kitchen Title and Substage titles to user input by calling userName function in view
                finalModel.setName(view.inputDialogue(view.text.getNameTitle(), view.text.getNameHeader(), view.text.getNameContent()));
                view.setName(finalModel.getName());
                stage.setTitle(view.getName() + "'s Kitchen");
                freezerStage.setTitle(view.getName() + "'s Freezer");
                fridgeStage.setTitle(view.getName() + "'s Fridge");
                pantryStage.setTitle(view.getName() + "'s Pantry");
            }
        });

        //Action Listeners for each AddItemPane's ADD BUTTON

        view.getAddFridgeItem().getAddItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.addItemToFridge(finalModel);
            }
        });

        view.getAddFreezerItem().getAddItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.addItemToFreezer(finalModel);
            }
        });

        view.getAddPantryItem().getAddItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.addItemToPantry(finalModel);
            }
        });

        //ActionListener for each ContentPane's ADD BUTTON

        view.getFridge().getAdd().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.setEdit(view.getAddFridgeItem(), true); //Calling on the view to change the editability of the components in the given pane
                view.getAddFridgeItem().clear();
                addFridge.show();

            }
        });
        view.getFreezer().getAdd().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.setEdit(view.getAddFreezerItem(),true);

                view.getAddFreezerItem().clear();
                addFreezer.show();

            }
        });

        view.getPantry().getAdd().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.setEdit(view.getAddPantryItem(),true);
                view.getAddPantryItem().clear();
                addPantry.show();
            }});


        //Action Listener for each ContentPane's CONSUME BUTTON

        view.getFridge().getConsume().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.consume("Fridge");
            }
        });
        view.getFreezer().getConsume().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.consume("Freezer");
            }
        });
        view.getPantry().getConsume().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.consume("Pantry");
            }
        });


        //Action Listener for each ContentPane's CONSUMEALL BUTTON

        view.getFridge().getConsumeAll().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.consumeAll("Fridge");
            }
        });
        view.getFreezer().getConsumeAll().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.consumeAll("Freezer");
            }
        });
        view.getPantry().getConsumeAll().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.consumeAll("Pantry");
            }
        });


        //Action Listener for each ContentPane's ThrowOut BUTTON
        view.getFridge().getThrowOut().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.throwOut("Fridge");
            }
        });
        view.getFreezer().getThrowOut().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.throwOut("Freezer");
            }
        });
        view.getPantry().getThrowOut().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.throwOut("Pantry");
            }
        });

        //Action Listener for each ContentPane's ThrowOutALL BUTTON
        view.getFridge().getThrowOutAll().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.throwOutAll("Fridge");
            }
        });
        view.getFreezer().getThrowOutAll().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.throwOutAll("Freezer");
            }
        });
        view.getPantry().getThrowOutAll().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.throwOutAll("Pantry");
            }
        });

        //Action Listener for the ContentPane's DONATE Button
        view.getFridge().getDonate().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (finalModel.getFridgeContents().isEmpty()) {
                    view.outputDialogue(view.text.getErrorTitle(), view.text.getErrorHeader(), view.text.getErrorContent());
                } else {
                    view.donate("Fridge");
                }
            }
        });

        view.getFreezer().getDonate().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (finalModel.getFreezerContents().isEmpty()) {
                    view.outputDialogue(view.text.getErrorTitle(), view.text.getErrorHeader(), view.text.getErrorContent());
                } else {
                    view.donate("Freezer");
                }
            }
        });

        view.getPantry().getDonate().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (finalModel.getPantryContents().isEmpty()) {
                    view.outputDialogue(view.text.getErrorTitle(), view.text.getErrorHeader(), view.text.getErrorContent());
                } else {
                    view.donate("Pantry");
                }
            }
        });

        //Adding an Action Listener to each ContentPane's GROCERY BUTTON

        view.getFridge().getGrocery().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (finalModel.getFridgeContents().isEmpty()) { //Making sure the user isn't trying to perform an action on an empty list
                    view.outputDialogue(view.text.getErrorTitle(), view.text.getErrorHeader(), view.text.getErrorContent());
                } else {
                    view.grocery("Fridge");
                }
            }
        });
        view.getFreezer().getGrocery().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (finalModel.getFreezerContents().isEmpty()) {
                    view.outputDialogue(view.text.getErrorTitle(), view.text.getErrorHeader(), view.text.getErrorContent());
                } else {
                    view.grocery("Freezer");
                }
            }
        });
        view.getPantry().getGrocery().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (finalModel.getPantryContents().isEmpty()) {
                    view.outputDialogue(view.text.getErrorTitle(), view.text.getErrorHeader(), view.text.getErrorContent());
                } else {
                    view.grocery("Pantry");
                }
            }
        });

        //Adding an Action Listener to each ContentPane's MOVE BUTTON

        view.getFridge().getMove().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.moveItem("Fridge");
            }
        });
        view.getFreezer().getMove().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.moveItem("Freezer");
            }
        });
        view.getPantry().getMove().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.moveItem("Pantry");
            }
        });

        //Adding an Action Listener for each ContentPane's enter("search") button

        view.getFridge().getEnter().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.searchFridge();
                view.getFridge().getEnter().setVisible(false);
                view.getFridge().getClear().setVisible(true);
            }
        });
        view.getFreezer().getEnter().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.searchFreezer();
                view.getFreezer().getEnter().setVisible(false);
                view.getFreezer().getClear().setVisible(true);
            }
        });
        view.getPantry().getEnter().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.searchPantry();
                view.getPantry().getEnter().setVisible(false);
                view.getPantry().getClear().setVisible(true);
            }
        });

        //Adding an Action Listener for each ContentPane's CLEAR BUTTON

        view.getFridge().getClear().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getFridge().getSearchTextField().setText("");
                view.getFridge().getClear().setVisible(false);
                view.getFridge().getEnter().setVisible(true);
                view.update();
            }
        });
        view.getFreezer().getClear().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getFreezer().getSearchTextField().setText("");
                view.getFreezer().getClear().setVisible(false);
                view.getFreezer().getEnter().setVisible(true);
                view.update();
            }
        });
        view.getPantry().getClear().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getPantry().getSearchTextField().setText("");
                view.getPantry().getClear().setVisible(false);
                view.getPantry().getEnter().setVisible(true);
                view.update();
            }
        });

        //Adding an Action Listener to each ContentPane's CONTENTS LISTVIEW

        view.getFridge().getContents().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.V && view.getFridge().getContents().getSelectionModel().getSelectedItem() != null) { //Making sure the user has an item selected
                    view.show("Fridge");
                    if (view.getFridge().getContents().getSelectionModel().getSelectedItem() instanceof Recipe) {
                        addRecipe.show();
                    } else {
                        addFridge.show();
                    }

                }

            }
        });
        view.getFreezer().getContents().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.V && view.getFreezer().getContents().getSelectionModel().getSelectedItem() != null) {
                    view.show("Freezer");
                    if (view.getFreezer().getContents().getSelectionModel().getSelectedItem() instanceof Recipe) {
                        addRecipe.show();
                    } else {
                        addFreezer.show();
                    }

                }

            }
        });
        view.getPantry().getContents().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.V && view.getPantry().getContents().getSelectionModel().getSelectedItem() != null) {
                    view.show("Pantry");
                    if (view.getPantry().getContents().getSelectionModel().getSelectedItem() instanceof Recipe) {
                        addRecipe.show();
                    } else {
                        addPantry.show();
                    }

                }
            }
        });

        //Adding an ActionListener to RecipePane's RECIPE LISTVIEW

        view.getRecipePane().getRecipeList().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.C) {
                    view.cook();
                }
            }
        });

        //Adding an ActionListener to RecipePane's ADD BUTTON

        view.getRecipePane().getAdd().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getAddRecipeItem().getName().setDisable(false);
                view.getAddRecipeItem().getTime().setDisable(false);
                view.getAddRecipeItem().getIngredients().setDisable(false);
                view.getAddRecipeItem().getInstructions().setDisable(false);
                view.getAddRecipeItem().getQuantity().setDisable(false);
                view.getAddRecipeItem().clear();
                addRecipe.show();
            }
        });

        //Adding an ActionListener to RecipePane's FINISH BUTTON

        view.getAddRecipeItem().getFinish().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.addToRecipes();
            }
        });

        //Adding an ActionListener to RecipePane's INGREDIENTS LISTVIEW

        view.getAddRecipeItem().getIngredients().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.addIngredient();
            }
        });

        //Adding an ActionListener to GroceryPane's GROCERY LISTVIEW

        view.getGrocerypane().getGroceries().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.A) {
                    addGrocery.show();
                } else if (keyEvent.getCode() == KeyCode.B && view.getGrocerypane().getGroceries().getItems().size() > 0) {
                    view.buyGrocery();
                } else if (keyEvent.getCode() == KeyCode.R && view.getGrocerypane().getGroceries().getItems().size() > 0) {
                    view.removeGrocery();
                }
            }
        });

        //Adding an ActionListener to AddGrocery's ADD BUTTON

        view.getAddGrocery().getAddItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.addGrocery();
            }
        });
        view.getGrocerypane().getWishList().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.A) {
                    view.addWishList();
                } else if (keyEvent.getCode() == KeyCode.R && view.getGrocerypane().getWishList().getItems().size() > 0) {
                    view.removeWishList();
                }
            }
        });

        //Closing all substages when the main stage is closed
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                fridgeStage.close();
                pantryStage.close();
                freezerStage.close();
                addFridge.close();
                addGrocery.close();
                addFreezer.close();
                addPantry.close();
                addRecipe.close();
                groceryStage.close();
            }
        });

        freezerStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                addFreezer.close();
            }
        });
        fridgeStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                addFridge.close();
            }
        });
        pantryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                addPantry.close();
            }
        });

        recipes.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                addRecipe.close();
            }
        });


        //Prepping, setting and displaying the stage
        stage.setResizable(false);
        stage.setWidth(1200);
        stage.setHeight(680);
        stage.setScene(new Scene(pane1));
        stage.show();


        if (!loaded) {
            try {//Exception Handling if user cancels the dialogue
                finalModel.setName(view.inputDialogue(view.text.getNameTitle(), view.text.getNameHeader(), view.text.getNameContent()));
                view.setName(finalModel.getName());
                view.outputDialogue(view.text.getInstructionsTitle(), view.text.getInstructionsHeader() + view.getName(), view.text.getInstructionsText());


            } catch (RuntimeException e) {
                stage.setTitle("Come on mane, answer the question");
                view.setName("Hacker");
                System.out.println("Thought you'd catch me lacking huh");
            }
        } else {
            view.outputDialogue("You again?", "Welcome back, " + model.getName(), "Have a great day!");

        }
        stage.setTitle(view.getName());


        //Settig the titles of the substages
        freezerStage.setTitle(view.getName() + " s Freezer");
        fridgeStage.setTitle(view.getName() + "'s Fridge");
        pantryStage.setTitle(view.getName() + " 's Pantry");


    }

    public static void main(String args[]) {
        launch(args);
    }
}
