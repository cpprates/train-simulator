package model;

public class Station<E> extends Railway<E> {
    private int boarding, exiting;

    public int getBoarding() {
        return boarding;
    }

    public int getExiting() {
        return exiting;
    }

}
