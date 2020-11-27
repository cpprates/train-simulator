package model;

public class Train {
    private int passengers, id, wait;
    private static final int MAX_CAPACITY = 50;
    private static final int MIN_CAPACITY = 10;

    // sorteia a quantidade de passageiros iniciais
    public Train(int id) {
        this.id = id;
        passengers = 10 + (int) (Math.random() * (50 - 10));
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public int getId() {
        return id;
    }

    public static int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public static int getMinCapacity() {
        return MIN_CAPACITY;
    }

    public int getWait() {
        return wait;
    }

}
