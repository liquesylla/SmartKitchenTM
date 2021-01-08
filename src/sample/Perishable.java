package sample;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Perishable extends Product implements Serializable {
    //Declaring class attributes
    LocalDate expiryDate;
    ArrayList<Perishables> category;


    //Getter methods
    public LocalDate getExpiryDate(){return expiryDate;}

    public ArrayList<Perishables> getCategory(){return category;}

    //Perishable Constructor

    public Perishable(String name, int q, double p,LocalDate exp, ArrayList<Perishables> c, String s){
        super(name,q,p,s);
        expiryDate = exp;
        category = new ArrayList<Perishables>();

        //Manually adding the array list of categories so they don't point to the same place in memory, just in case
        addCategory(c);


    }

    public void addCategory(ArrayList<Perishables> c){
        for(Perishables d: c){
            if (d!=null){
                category.add(d);
            }
        }
    }

    //If another Perishable has the same name, expiration date storage and category - they should be the same. Huge implications for functionality
    public int compareTo(Consumable p){
        if (p instanceof Perishable) {
            p = (Perishable) p;
            if (getName().equals(((Perishable) p).getName()) && getExpiryDate().equals(((Perishable) p).getExpiryDate()) && ((Perishable) p).getStorage().equals(getStorage()) && ((Perishable) p).category.equals(category)) {
                return 1;
            }
        }
        return -1;
    }


}
