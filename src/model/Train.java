package model;

public class Train {
    private int passengers;

    public Train() {
        passengers = 10 + (int) (Math.random() * (50 - 10));
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

}
