package sample;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KitchenModel implements Serializable {
    //Declaring class attributes
    private String name;
    private List<Consumable> fridgeContents, freezerContents, pantryContents,expiryList;
    private List<Perishable> perishableList;
    private List<Ingredient> wishList;
    private List<Product> groceries;
    private ArrayList<Ingredient> missing;
    private List<Recipe> recipes;
    private LocalDate currentDay;

    //Getter Methods
    public List<Consumable> getFridgeContents(){return fridgeContents;}
    public List<Consumable> getFreezerContents(){return freezerContents;}
    public List<Consumable> getPantryContents(){return pantryContents;}
    public List<Consumable> getExpiryList(){return expiryList;}

    public List<Product> getGroceries(){return groceries;}
    public List<Recipe> getRecipes(){return recipes;}
    public List<Perishable> getPerishableList(){return perishableList;}
    public ArrayList<Ingredient> getMissing() {return missing;}
    public LocalDate getCurrentDay(){return currentDay;}
    public List<Ingredient> getWishList() {
        return wishList;
    }
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    //Class Constructor
    public KitchenModel(){
        freezerContents = new ArrayList<Consumable>();
        fridgeContents = new ArrayList<Consumable>();
        pantryContents = new ArrayList<Consumable>();
        groceries = new ArrayList<Product>();
        recipes = new ArrayList<Recipe>();
        perishableList = new ArrayList<Perishable>();
        currentDay = LocalDate.now();
        expiryList = new ArrayList<Consumable>();
        wishList = new ArrayList<Ingredient>();
        name = "";
        missing = new ArrayList<Ingredient>();
    }

    //Function that checks if any Perishable item is near expiry, and adds it to the expiry list (along with any recipe it may be contained in
    public void expire() {
        currentDay = LocalDate.now();
        for (Perishable p : perishableList) {
            if (currentDay.plusDays(4).isAfter(p.getExpiryDate()) && !expiryList.contains(p)){
                expiryList.add(p);
                }
            }
        }



    //COULD POLYMORPHM WITH ADDPRODUCT
    public boolean cook(Recipe r){
        missing.clear();
        if(cookHelper(r)){
            for(Ingredient i: missing){
                addWishList(i);
            }
            return false;
        }
        if (r.getStorage().equals(Storage.Freezer)){
            freezerContents.add(r);
        }
        else if(r.getStorage().equals(Storage.Fridge)){
            fridgeContents.add(r);
        }
        //UNSAFE
        else{pantryContents.add(r);}

        return true;
    }
    //ArrayList<Ingredient>
    private  boolean cookHelper(Recipe r){
        for(Ingredient i: r.getIngredients()){

            ArrayList<Consumable> result = new ArrayList<Consumable>();
            result.addAll(searchFor(i.getName(),fridgeContents));
            result.addAll(searchFor(i.getName(),pantryContents));
            result.addAll(searchFor(i.getName(),freezerContents));
            if (result.size()<1){
                missing.add(i);
            }
        }
        if(missing.size()>0){
            return true;
        }
        return false;
    }

    public void addRecipe(Recipe r){
        recipes.add(r);
    }

    //Adding a product to the appropriate lists
    public void addProduct(Product p){
        if (p.getStorage().equals(Storage.Freezer)){
            freezerContents.add(p);
        }
        else if(p.getStorage().equals(Storage.Fridge)){
            fridgeContents.add(p);
        }
        else if(p.getStorage().equals(Storage.Pantry)){pantryContents.add(p);}

        if(p instanceof Perishable){perishableList.add((Perishable)p);}
    }

    //Function that removes products from the appropriate lists
    public void removeProduct(Consumable p){
        if(freezerContents.contains(p)){
            freezerContents.remove(p);
        }
        else if(fridgeContents.contains(p)){
            fridgeContents.remove(p);
        }
        else{pantryContents.remove(p);}

        if(perishableList.contains(p)){perishableList.remove(p);}

        if(expiryList.contains(p)){expiryList.remove(p);}
        if(p instanceof Product){
            ((Product) p).setQuantity(((Product) p).getDefaultQuantity());
            if (((Product) p).getValue()>0.70){
                addGrocery((Product) p);
            }
        }
    }

    public void addGrocery(Product p){

        if(!groceries.contains(p) && p != null){groceries.add(p);}
    }

    public void removeGrocery(Product p){
        if(groceries.contains(p)){groceries.remove(p);}

    }
    public void removeWishlist(Ingredient i){

        if (wishList.contains(i)){
            wishList.remove(i);
        }
    }


    public void consume(Consumable c, int quantity){
        c.consume(quantity);
        if(c.getQuantity() == 0){
                removeProduct(c);
            }

    }
    //Remember to add to GROCERY
    public void consumeAll(Consumable c){
        c.consumeAll();
        removeProduct(c);


    }
    public void throwOutAll(Consumable c){
        c.throwOutAll();
        removeProduct(c);

    }

    public void throwOut(Consumable c, int quantity){
        c.throwOut(quantity);
        if(c.getQuantity() == 0){
                removeProduct(c);
            }
    }

    //MAY NEED TO REFACTOR
    public void buy(Product p, int q) {
        removeGrocery(p);
        Product c = contains(p,fridgeContents);
        if(c != null){
            c.replenish(q);
            c.setPrice((c.getPrice()+p.getPrice())/2);
            return;
        }
        c = contains(p,freezerContents);
        if(c!=null){
            c.replenish(q);
            c.setPrice((c.getPrice()+p.getPrice())/2);
            return;
        }
        c = contains(p,pantryContents);
        if(c!=null){
            c.replenish(q);
            c.setPrice((c.getPrice()+p.getPrice())/2);
        }
        else{addProduct(p);}

    }
    private Product contains(Consumable p, List<Consumable> l){
        if(p instanceof Perishable) {
            for (Consumable c : l) {
                if (c instanceof Perishable) {
                    if (((Perishable) p).compareTo(c) > 0) {
                        return (Perishable) c;
                    }
                }
            }
        }
        else if(p instanceof NonPerishable){
            for (Consumable c : l) {
                if (c instanceof NonPerishable) {
                    if (((NonPerishable) p).compareTo(c) > 0) {
                        return (NonPerishable) c;
                    }
                }
            }
        }
        return null;
    }

    public void donate(NonPerishable p, int q){
        p.donate(q);
        if(p.getQuantity() == 0){
            removeProduct(p);
        }
    }

    public ArrayList<Consumable> searchFor(String field, List<Consumable> search){
        ArrayList<Consumable> result = new ArrayList<Consumable>();
        for (Consumable p : search){
            if(p instanceof Perishable){
                if(((Perishable) p).getName().equals(field)){
                    result.add(p);
                }
            }
            if(p instanceof NonPerishable){
                if(((NonPerishable) p).getName().equals(field)){
                    result.add(p);
                }
            }
            else if(p instanceof Recipe){
                if (((Recipe) p).getName().equals(field)){
                    result.add(p);
                }
            }
        }
        return result;
    }


    public void move(String s, Consumable p){
        if(p.getStorage().equals(Storage.Fridge)){fridgeContents.remove(p);}
        else if(p.getStorage().equals(Storage.Freezer)){freezerContents.remove(p);}
        else if(p.getStorage().equals(Storage.Pantry)){pantryContents.remove(p);}

        p.setStorage(s);

        if(p.getStorage().equals(Storage.Fridge)){fridgeContents.add(p);}
        else if(p.getStorage().equals(Storage.Freezer)){freezerContents.add(p);}
        else if(p.getStorage().equals(Storage.Pantry)){pantryContents.add(p);}


    }
    public void addWishList(Ingredient i){
        if (wishList.contains(i)){return;}
        wishList.add(i);
    }

    public String toString(){
        return "Freezer List: " + freezerContents + "\nFridge List: " + fridgeContents + "\nPantry List: " + pantryContents +
                "\nPerishable List: " + perishableList + "\nExpiry List: " + expiryList + "\nRecipes: " + recipes + "\nGroceries: " + groceries + "\nToday is : " + currentDay;
    }
    }
