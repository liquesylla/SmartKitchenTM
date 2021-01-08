package sample;
import java.io.Serializable;
import java.util.*;
public class Recipe implements Consumable, Serializable {
    //Declaring class attributes
    private int prepTime, quantity;
    private String instructions,name;
    private Storage storage;
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    //Getter Methods
    public int getPrepTime(){return prepTime;}
    public String getInstructions(){return instructions;}
    public String getName(){return name;}
    public int getQuantity(){return quantity;}


    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(String s) {
        //SHOULD PROBABLY HAVE ERROR CHECKING HERE INSTEAD OF IN GUI
        this.storage = Storage.valueOf(s);
    }

    public Recipe(String n, int p, int s,String instructions, ArrayList<Ingredient> g){
        name= n;
        prepTime = p;
        quantity = s;
        ingredients.addAll(g);
        this.instructions = instructions;

    }

    @Override
    //EDIT THIS
    public void throwOut(int q) {
        if(q>quantity) {
            throwOutAll();
        }
        else if(q>0){
            quantity-=q;
        }
    }

    @Override
    public void throwOutAll() {
        quantity = 0;
    }

    @Override
    public void replenish(int q) {
        replenishAll();
    }

    @Override
    public void consume(int c) {
        if (c>quantity) {
            consumeAll();
        }
       else if (c>0){
           quantity-=c;
        }
    }
    public void replenishAll(){
        //Might have to refactor; not sure if replenish is needed at all
    }

    public void consumeAll(){
        quantity = 0;
    }

    //Function that decreases the quantity of each ingredient in the recipe - kin to cooking and using ingredients irl


    public String toString(){
        return  name ;
    }


}
