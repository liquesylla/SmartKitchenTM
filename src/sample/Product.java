package sample;

import java.io.Serializable;
import java.util.Date;

public abstract class Product implements Consumable,Serializable {
    //Declaring class attributes
    private String name;
    protected int quantity, defaultQuantity;
    private Double price, value, consumed, thrownOut;
    private Date lastUsed;
    private Storage storage;

    //Getter Methods
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDefaultQuantity() { return defaultQuantity;}

    public Double getPrice() {
        return price;
    }

    public Double getConsumed() {
        return consumed;
    }

    public Double getValue() {
        return value;
    }

    public Double getThrownOut() {
        return thrownOut;
    }

    public Storage getStorage(){return storage;}

    public void setPrice(Double price) {
        this.price = price;
    }

    //Class Constructor

    public Product(String n, int q, double p, String s) {
        name = n;
        quantity = q;
        price = p;
        thrownOut = 0.0;
        defaultQuantity = quantity;
        consumed = 0.0;
        value = 0.0;
        //Parsing string to enum value
        storage = Storage.valueOf(s);

    }

    //Overriding toString method - might need to rework for clarity
    public String toString(){return name + " x" + quantity ;}

    public void setStorage(String s){storage=Storage.valueOf(s);}
    public void setQuantity(int q){
        if (q>=0){quantity = q;}
    }

    //Implementation of Consumable interface - throw out specified number of items
    public void throwOut(int q) {
            if (q > quantity){
                throwOutAll();

            }
            else if (q >= 0) {
                quantity -= q;
                thrownOut += q;
            }
        setValue();
    }

    //Implementation of Consumable interface - throw out all remaining items
    public void throwOutAll(){thrownOut += quantity; quantity = 0; setValue();}


    //Implementation of Consumable interface - replenish the quantity with q items
    @Override
    public void replenish(int q){if (q > 0){ quantity += q; } setValue();}

    public void replenishAll(){quantity = defaultQuantity;}
    //Setting default quantity to d
    public void setDefaultQuantity(int d){if(d>0){defaultQuantity = d;} }

    //Implementation of Consumable interface - consuming c number of items
    public void consume(int c){
        if(c>quantity){consumeAll();}

        else if(c>0){consumed+=c; quantity -=c;}
        setValue();
    }

    //Implementation of Consumable interface - consuming all the remaining items
    public void consumeAll(){consumed += quantity; quantity = 0; setValue();}

        public void setValue(){
            System.out.println(value);
            value = ((consumed/defaultQuantity)-(thrownOut/defaultQuantity));
            System.out.println("THIS IS VALUE:" + name + " " + value);

        }

    }


