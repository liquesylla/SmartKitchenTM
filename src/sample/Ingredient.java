package sample;
import java.io.Serializable;

public final class Ingredient implements Serializable {
    //Class Attributes
    private int quantity;
    private String name;

    //Getter Methods
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }

    //Class Constructor
    public Ingredient(String name, int quantity){
        this.quantity = quantity;
        this.name = name;
    }

    @Override
    public String toString() {
        return quantity + " " + name;
    }
}
