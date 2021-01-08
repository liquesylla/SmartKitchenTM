package sample;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class NonPerishable extends Product implements Donatable, Serializable {
    //Declaring class attributes
    private ArrayList<NonPerishables> category;
    private Integer donated;

    //Getter methods
    public ArrayList<NonPerishables> getCategory() {
        return category;
    }

    //NonPerishable constructor
    public NonPerishable(String n, int q, double p, ArrayList<NonPerishables> c, String s) {
        super(n, q, p, s);
        category = new ArrayList<NonPerishables>();

        //Making use of a function to reduce code
        addCategory(c);
        //Parsing string to enum value

        donated = 0;

    }
    //Setter method that changes the value of storage


    //Implementation of Donatable interface - donating c number of items
    public void donate(int c) {
        if (c > quantity) {
            donateAll();
        } else if (c > 0) {
            donated += c;
            quantity -= c;
        }
    }

    //Implementation of Donatable interface - donating all the remaining items
    public void donateAll() {
        donated += quantity;
        quantity = 0;
    }

    //Function that adds the values of an ArrayList of NonPerishables to the category Array List - doing it like this to avoid dual pointers in memory just in case
    public void addCategory(ArrayList<NonPerishables> c) {
        for (NonPerishables d : c) {
            if (d != null) {
                category.add(d);
            }
        }
    }

    public int compareTo(Consumable p) {

        if (p instanceof NonPerishable) {
            if (getName().equals(((NonPerishable) p).getName()) && getStorage().equals(p.getStorage())&& ((NonPerishable) p).category.equals(category)) {
                return 1;
            }
        }
        return -1;
    }

}

