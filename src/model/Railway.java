package model;

public class Railway<E> {
    protected Train element;
    protected Railway<E> next;
    protected Railway<E> previous;

    public Railway() {
        element = null;
        next = previous = null;
    }

    public Train getElement() {
        return element;
    }

    public void setElement(Train element) {
        this.element = element;
    }

    public Railway<E> getNext() {
        return next;
    }

    public void setNext(Railway<E> next) {
        this.next = next;
    }

    public Railway<E> getPrevious() {
        return previous;
    }

    public void setPrevious(Railway<E> previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return (element != null) ? "" + element : "-";
    }

}
