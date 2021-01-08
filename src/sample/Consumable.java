package sample;

public interface Consumable {
    public void throwOut(int q);
    public void throwOutAll();
    public void replenish(int q);
    public void replenishAll();
    public void consume(int c);
    public void consumeAll();
    public Storage getStorage();
    public void setStorage(String s);
    public int getQuantity();

}
