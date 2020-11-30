package model;

public class Train {
    private int passengers, id, wait;
    private static final int MAX_CAPACITY = 50;
    private static final int MIN_CAPACITY = 10;

    // sorteia a quantidade de passageiros iniciais
    public Train(int id) {
        this.id = id;
        while (true) {
            int even = 10 + (int) (Math.random() * (50 - 10));
            if (even % 2 == 0) {
                passengers = even;
                break;
            }
        }
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

    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public int getMinCapacity() {
        return MIN_CAPACITY;
    }

    public int getWait() {
        return wait;
    }

    @Override
    public String toString() {
        return "🚂" + id;
    }

}
