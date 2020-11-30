package model;

public class Terminal<E> extends Railway<E> {
    private static int trainID;

    public Terminal() {
        buildTrain();
    }

    public void buildTrain() {
        this.element = new Train(trainID++);
        System.out.println("Train with ID#" + this.getElement().getId() + " left the Terminal with "
                + this.getElement().getPassengers() + " passengers: " + this.getElement());
    }

    @Override
    public String toString() {
        return (this.element == null) ? (getNext() != null) ? "[A]" : "[B]" : "[" + this.element + "]";
    }
}
