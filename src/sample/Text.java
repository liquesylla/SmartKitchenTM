package sample;

import java.io.Serializable;

public class Text implements Serializable {
    private String instructionsTitle = "Message To the User";
    private String instructionsHeader = "Welcome to your Kitchen, ";
    private String instructionsText = "The SmartKitchenTM allows you to quickly search through and perform normal functions that mimic your real kitchen. " +
            "To access the fridge and its contents, simply press on the fridge. " +
            "To access the freezer and its contents, simply press on the freezer. " +
            "To access the pantry and its contents, simply press on the pantry. " +
            "To access the groceries, press on the green grocery bag, and finally to access your recipes, press on the island. Enjoy!";

    private String errorTitle = "Improper entry";
    private String errorHeader = "Please fill in the data correctly";
    private String errorContent = "Make sure each field is filled with the appropriate information.";

    private String nameTitle = "Input Required";
    private String nameHeader = null;
    private String nameContent = "Please tell us your name:";

    private String consumeTitle = "How much are you consuming?";
    private String consumeHeader = "Enjoy:)";
    private String consumeContent = "Enter a quantity:";

    private String throwOutTitle = "Wasting food are we..";
    private String throwOutHeader = "Remember  not to be wasteful, ";
    private String throwOutContent = "How much are you throwing out?";


    private String donateTitle = "So charitable of you!";
    private String donateHeader = "You've got a heart of Gold, ";
    private String donateContent = "How much are you giving?";

    private String moveTitle = "Where would you like to move this item?";
    private String moveHeader = "Be sure to enter a valid location, starting with a capital letter";
    private String moveContent = "(Fridge, Freezer or Pantry):";

    private String cookTitle = "Cooking Time!";
    private String cookHeader = "Enjoy your ";
    private String cookContent = "Where are you storing it?(Fridge, Freezer or Pantry)";

    private String ingHeader = "Add an Ingredient";
    private String  ingContent ="Ingredient Name:";

    private String wishTitle = "Add an Item";
    private String wishHeader = "Whatcha craving?";
    private String  wishContent ="Item Name:";

    private String qHeader = "How many ";
    private String qContent = "Quantity";

    private String recipeHeader = "Welcome to your Cookbook!";
    private String recipeContent = "To use your cookbook you will need to make use of both keys and buttons. "+
            "You will see your recipes displayed on the screen after you add them. " +
            "To cook a recipe, click on it and press C. Happy Cooking!";

    private String contentHeader = "Maneuvering your Storages";
    private String contentContent = "Make us of the buttons present on the right hand side of each storage pane to accomplish your tasks. " +
            "To view an item, click on it and press V. If adding a new item, press and hold CTRL while choosing your category, that way you can add more than one.";

    private String groceryHeader = "Attention Shopper!";
    private String groceryContent = "To buy your groceries, click on the list, and press B. To add to either your grocery list or your wanted list: press A while clicking on it. " +
            "To remove any item from either list, simply select and click R.";

    private String noIngredientsHeader = "Uh Oh, seems you are missing a few things.";
    private String noIngredientsContent = "We've taken the liberty to add them to your wanted list. The list below is all the items you'll need to buy before cooking your ";

    private String dateErrorHeader = "The item you're about to enter has already expired.";
    private String dateErrorContent = "We can't let you do that";

    private String expireTitle = "Important!";
    private String expireHeader = "The following items are about to expire:";
    private String expireContent = "";

    public String getExpireContent() {
        return expireContent;
    }

    public String getExpireTitle() {
        return expireTitle;
    }

    public String getExpireHeader() {
        return expireHeader;
    }

    public String getDateErrorContent() {
        return dateErrorContent;
    }

    public String getDateErrorHeader() {
        return dateErrorHeader;
    }

    public String getNoIngredientsHeader() {
        return noIngredientsHeader;
    }

    public String getNoIngredientsContent() {
        return noIngredientsContent;
    }

    public String getGroceryContent() {
        return groceryContent;
    }

    public String getGroceryHeader() {
        return groceryHeader;
    }

    public String getContentContent() {
        return contentContent;
    }

    public String getContentHeader() {
        return contentHeader;
    }

    public String getRecipeHeader() {
        return recipeHeader;
    }

    public String getRecipeContent() {
        return recipeContent;
    }

    public String getWishContent() {
        return wishContent;
    }

    public String getWishHeader() {
        return wishHeader;
    }

    public String getWishTitle() {
        return wishTitle;
    }

    public String getIngContent() {
        return ingContent;
    }

    public String getIngHeader() {
        return ingHeader;
    }

    public String getqContent() {
        return qContent;
    }

    public String getqHeader() {
        return qHeader;
    }

    public String getCookContent() {
        return cookContent;
    }

    public String getCookHeader() {
        return cookHeader;
    }

    public String getCookTitle() {
        return cookTitle;
    }

    public String getErrorContent() {
        return errorContent;
    }

    public String getErrorHeader() {
        return errorHeader;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getInstructionsHeader() {
        return instructionsHeader;
    }

    public String getInstructionsText() {
        return instructionsText;
    }

    public String getInstructionsTitle() {
        return instructionsTitle;
    }

    public String getNameContent() {
        return nameContent;
    }

    public String getNameHeader() {
        return nameHeader;
    }

    public String getNameTitle() {
        return nameTitle;
    }

    public String getConsumeContent() {
        return consumeContent;
    }

    public String getConsumeHeader() {
        return consumeHeader;
    }

    public String getConsumeTitle() {
        return consumeTitle;
    }

    public String getDonateTitle() {
        return donateTitle;
    }

    public String getDonateContent() {
        return donateContent;
    }

    public String getDonateHeader() {
        return donateHeader;
    }

    public String getThrowOutTitle() {
        return throwOutTitle;
    }

    public String getThrowOutHeader() {
        return throwOutHeader;
    }

    public String getThrowOutContent() {
        return throwOutContent;
    }

    public String getMoveTitle() {
        return moveTitle;
    }

    public String getMoveHeader() {
        return moveHeader;
    }

    public String getMoveContent() {
        return moveContent;
    }
}
