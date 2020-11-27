package model;

public class Railway<E> {
    private E element;
    private Railway<E> next;
    private Railway<E> previous;

    public Railway(E element) {
        this.element = element;
        next = previous = null;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
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

}
