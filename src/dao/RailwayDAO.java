package dao;

import model.Detour;
import model.Railway;
import model.Station;
import model.Terminal;
import model.Train;

public class RailwayDAO<E> {
    private Terminal<E> a;
    private Terminal<E> b;
    private Train[] trains;

    public RailwayDAO() {
        trains = new Train[21];
        buildRailroad();
    }

    public void buildRailroad() {
        int quant = 10 + (int) (Math.random() * (30 - 10));
        for (int i = 0; i < quant; i++) {
            if (i == 0)
                buildStart();
            else if (i % 21 == 0 && i < (quant - 2)) {
                buildStation(i);
                buildDetour(i);
            } else if (i < (quant - 1))
                buildHalfway(i);
        }
        buildDetour(quant);
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

    public String moveTrain(boolean trainA, boolean trainB, int count) {
        if (count <= 570) {
            if (trainB || (count % 60 == 0 && count != 0)) {
                if (canMove(b)) {
                    b.buildTrain();
                    trainB = false;
                    // print train B and infos
                } else {
                    trainB = true;
                }
            } else if (trainA || (count % 30 == 0 && count != 0)) {
                if (canMove(a)) {
                    a.buildTrain();
                    trainA = false;
                } else {
                    trainA = true;
                }
            }
        }

        Railway<E> currA = a;
        Railway<E> currB = b;

        System.out.println("is it workin?" + this);

        while (currA != null || currB != null) {
            boolean onHold = false;

            // trens de A => pares, para desviar
            // verifica caminho de tail (B) para head (A)
            if (currB.getElement() != null && currB.getElement().getId() % 2 == 0
                    && currB.getElement().getWait() == 0) {
                // print trem
                if (currB instanceof Detour) {
                    onHold = detourAbound(currB);
                }
                // move train
                if (!onHold && currB.getElement().getWait() == 0) {
                    // if estacao => embarque e desembarque de passageiros
                    if (currB instanceof Station) {
                        ((Station<E>) currB).getExiting();
                        ((Station<E>) currB).getBoarding();
                    }
                    // se nao tem espera, move para proximo pedaco trilho
                    if (currB.getElement().getWait() == 0) {
                        currB.getNext().setElement(currB.getElement());
                        currB.setElement(null);
                    }
                }
            } else if (currB.getElement() != null && currB.getElement().getId() % 2 == 0
                    && currB.getElement().getWait() != 0) { // reduz tempo
                // print train
                currB.getElement().setWait(currB.getElement().getWait() - 1);
                // move trem
                if (currB.getElement().getWait() == 0) {
                    currB.getNext().setElement(currB.getElement());
                    currB.setElement(null);
                }
            }

            onHold = false;

            // trens de B => ímpares, para desviar
            // verifica caminho de head (A) para tail (B)
            if (currA.getElement() != null && currA.getElement().getId() % 2 != 0
                    && currA.getElement().getWait() == 0) {
                // print train
                if (currA instanceof Detour)
                    onHold = detourBbound(currA);
                // move trem
                if (!onHold && currA.getElement().getWait() == 0) {
                    // se estacao
                    if (currA instanceof Station) {
                        ((Station<E>) currA).getExiting();
                        ((Station<E>) currA).getBoarding();
                    }
                    // sem tempo de espera
                    if (currA.getElement().getWait() == 0) {
                        currA.getPrevious().setElement(currA.getElement());
                        currA.setElement(null);
                    }
                }
            } else if (currA.getElement() != null && currA.getElement().getId() % 2 != 0
                    && currA.getElement().getWait() != 0) { // reduz tempo espera
                // print trem
                currA.getElement().setWait(currA.getElement().getWait() - 1);
                // if wait time if over
                if (currA.getElement().getWait() == 0) {
                    currA.getPrevious().setElement(currA.getElement());
                    currA.setElement(null);
                }
            }

            // verifica trem no detour
            if (currA instanceof Detour && ((Detour<E>) currA).getDetour().getElement() != null) {
                if (((Detour<E>) currA).getDetour().getElement().getWait() > 0) {
                    ((Detour<E>) currA).getDetour().getElement()
                            .setWait(((Detour<E>) currA).getDetour().getElement().getWait() - 1);
                }
                if (((Detour<E>) currA).getDetour().getElement().getWait() == 0
                        && ((Detour<E>) currA).getDetour().getNext().getElement() == null
                        && currA.getNext().getElement() == null && currA.getPrevious().getElement() == null) {
                    ((Detour<E>) currA).getDetour().getNext().setElement(((Detour<E>) currA).getDetour().getElement());
                    ((Detour<E>) currA).getDetour().setElement(null);
                }
            }

            // move current
            currA = currA.getNext();
            currB = currB.getPrevious();

            // salvar trens para relatorio
            if (a.getElement() != null && a.getElement().getId() % 2 != 0) {
                trains[a.getElement().getId()] = a.getElement();
                System.out.println("Train ID#" + a.getElement().getId() + " arrived at terminal A");
                a.setElement(null);
            }
            if (b.getElement() != null && b.getElement().getId() % 2 == 0) {
                trains[b.getElement().getId()] = b.getElement();
                System.out.println("Train ID#" + b.getElement().getId() + " arrived at terminal B");
                b.setElement(null);
            }
        }
        return "" + trainA + " " + trainB;
    }

    // A => id par | B => id ímpar pq comeca em 0
    public boolean canMove(Railway<E> current) {
        if (current.getNext() == null)
            for (int i = 0; i <= 21; i++) {
                if (current.getElement() != null && current.getElement().getId() % 2 == 0) // se par (A)
                    return false;
                current = current.getPrevious();
            }
        else {
            for (int i = 0; i <= 21; i++) {
                if (current.getElement() != null && current.getElement().getId() % 2 != 0) // ímpar (B)
                    return false;
                current = current.getNext();
            }
        }
        return true;
    }

    // proximas 20 (A)
    public boolean isEmptyNext20(Railway<E> current) {
        Railway<E> curr = current.getNext();
        for (int i = 0; i <= 21 && curr.getNext() != null; i++) {
            if (curr.getElement() != null && ((Detour<E>) current).getDetour().getElement() == null) {
                current.getElement().setWait(i + 2 + curr.getElement().getWait());
                return false;
            }
            curr = curr.getNext();
        }
        return true;
    }

    // detour train A
    public boolean detourAbound(Railway<E> curr) {
        if (!isEmptyNext20(curr) && ((Detour<E>) curr).getDetour().getElement() == null) {
            ((Detour<E>) curr).getDetour().setElement(curr.getElement());
            System.out.println("Train ID#" + curr.getElement().getId() + " is on hold");
            curr.setElement(null);
            return true;
        }
        return false;
    }

    // previous 20 (B)
    public boolean isEmptyPrevious20(Railway<E> current) {
        Railway<E> curr = current.getPrevious();
        for (int i = 0; i <= 21 && curr.getPrevious() != null; i++) {
            if (curr.getElement() != null && ((Detour<E>) current).getDetour().getElement() == null) {
                current.getElement().setWait(i + 2 + curr.getElement().getWait());
                return false;
            }
            curr = curr.getPrevious();
        }
        return true;
    }

    // detour train B
    public boolean detourBbound(Railway<E> curr) {
        if (!isEmptyPrevious20(curr) && ((Detour<E>) curr).getDetour().getElement() == null) {
            ((Detour<E>) curr).getDetour().setElement(curr.getElement());
            System.out.println("Train ID#" + curr.getElement().getId() + " is on hold");
            curr.setElement(null);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String ret = "";
        Railway<E> rail = a;
        while (rail != null) {
            ret += rail;
            rail = rail.getNext();
        }
        printArray();
        return ret;
    }

    public void printArray() {
        for (int i = 0; i < trains.length; i++) {
            System.out.println(trains[i]);
        }
    }

}
