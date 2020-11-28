package model;

public class Detour<E> extends Railway<E> {

    private Railway<E> detour;
    private boolean ready;

    public Detour() {
        detour = new Railway<E>();
        ready = false;
    }

    public Railway<E> getDetour() {
        return detour;
    }

    public boolean isReady() {
        return ready;
    }

    public void setDetour(Railway<E> detour) {
        this.detour = detour;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    @Override
    public String toString() {
        return (element != null) ? "{" + element + "}" : "{   }";
    }

}
