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
        if (this.element == null)
            return "[A]";
        else if (this.getNext() != null)
            return "[B]";
        else {
            return "[ " + element + " ]";
        }

        // return (this.element == null) ? (this.getNext() != null) ? "[A]" + "[B]" : "[
        // " + this.element + " ]";
    }
}
