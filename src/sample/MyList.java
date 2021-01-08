package sample;

import java.util.*;

public class MyList implements List {
    ArrayList<Consumable> arr;
    int size;

    public void add(Consumable p){
        arr.add(p);
        size++;
    }

    public void remove(Consumable p){
        arr.remove(p);
        size--;
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size==0){return true;}
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (o instanceof Perishable){
            for(Consumable c: arr){
                if (c instanceof Perishable){

                }
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        if (o instanceof Consumable){
            add((Consumable)o);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof Consumable){
            remove((Consumable)o);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        if(index<size && index>=0){
            return arr.get(index);
        }
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
