package dao;

import model.Detour;
import model.Railway;
import model.Station;
import model.Terminal;

public class RailwayDAO<E> {
    private Terminal<E> a;
    private Terminal<E> b;
    // private Train[] trains;

    public RailwayDAO() {
        // trains = new Train[21];
        buildRailroad();
    }

    public void buildRailroad() {
        for (int i = 0; i < 232; i++) {
            if (i == 0)
                buildStart();
            else if (i % 21 == 0 && i < 230) {
                buildStation(i);
                buildDetour(i);
            } else if (i < 231)
                buildHalfway(i);
        }
        buildDetour(232);
    }

    public void buildStart() {
        a = new Terminal<E>();
        b = new Terminal<E>();
        a.setNext(b); // vai de a para b
        b.setNext(a); // vai de b para a
    }

    public void buildHalfway(int pos) {
        Railway<E> previous = a;
        for (int i = 0; i < pos - 1; i++) {
            previous = previous.getNext();
        }

        Railway<E> piece = new Railway<>();

        piece.setPrevious(previous);
        piece.setNext(previous.getNext());

        previous.getNext().setPrevious(piece);
        previous.setNext(piece);
    }

    public void buildStation(int pos) {
        Railway<E> previous = a;
        for (int i = 0; i < pos - 1; i++) {
            previous = previous.getNext();
        }

        Railway<E> newNode = new Station<>();

        newNode.setPrevious(previous);
        newNode.setNext(previous.getNext());

        previous.getNext().setPrevious(newNode);
        previous.setNext(newNode);
    }

    public void buildDetour(int pos) {
        Railway<E> current = a;
        for (int j = 0; j < pos - 1; j++) {
            current = current.getNext(); // set current
            if (current.getNext() instanceof Station) { // de A p B
                Railway<E> newDet = new Detour<>();

                newDet.setPrevious(current.getPrevious());
                newDet.setNext(current.getNext());

                ((Detour<E>) newDet).setDetour(new Railway<>()); // casting detour no pedaco de trilho

                current.getPrevious().setNext(newDet);
                current.getNext().setPrevious(newDet);

                ((Detour<E>) newDet).getDetour().setNext(newDet.getNext());
                ((Detour<E>) newDet).getDetour().setPrevious(newDet);
            } else if (current.getPrevious() instanceof Station) { // de B p A
                Railway<E> newDet = new Detour<>();

                newDet.setPrevious(current.getPrevious());
                newDet.setNext(current.getNext());

                ((Detour<E>) newDet).setDetour(new Railway<>()); // casting detour no trilho

                current.getPrevious().setNext(newDet);
                current.getNext().setPrevious(newDet);

                ((Detour<E>) newDet).getDetour().setPrevious(newDet);
                ((Detour<E>) newDet).getDetour().setNext(newDet.getPrevious());
            }
        }
    }

}
