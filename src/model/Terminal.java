package model;

public class Terminal<E> extends Railway<E> {
    private static int trainID;

    public Terminal() {
        buildTrain();
    }

    public void buildTrain() {
        this.element = new Train(trainID++);
        System.out.println("Train with ID#" + getElement().getId() + " left the Terminal with "
                + getElement().getPassengers() + " passengers: " + getElement());
    }

    @Override
    public String toString() {
        return (element == null) ? (getNext() != null) ? "[A]" : "[B]" : "[" + element + "]";
    }
}
