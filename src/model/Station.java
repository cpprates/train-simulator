package model;

public class Station<E> extends Railway<E> {
    private int boarding, exiting;

    public int getBoarding() {
        return boarding;
    }

    public int getExiting() {
        return exiting;
    }

    public void boardingPassengers() {
        while (true) {
            int quant = (int) (Math.random() * 10);
            if (quant % 2 == 0) {
                System.out.println("There's " + element.getPassengers() + " passengers to get on board of Train ID#"
                        + element.getId());
                if (element.getPassengers() + quant > element.getMaxCapacity()) {
                    quant += element.getMaxCapacity() - element.getPassengers();
                    element.setPassengers(element.getMaxCapacity());
                    boarding += quant;
                } else {
                    element.setPassengers(element.getPassengers() + quant);
                    boarding += quant;
                }
                System.out.println("Boarding: " + boarding);
                element.setWait((quant == 0) ? element.getWait() + 1 : element.getWait() + (quant / 2));
                return;
            }
        }
    }

    public void exitingPassengers() {
        while (true) {
            int quant = (int) (Math.random() * 10);
            if (quant % 2 == 0) {
                System.out.println("There's " + element.getPassengers() + " passengers to get off of the Train ID#"
                        + element.getId());
                if (element.getPassengers() - quant < element.getMinCapacity()) {
                    quant += element.getPassengers() - element.getMinCapacity();
                    element.setPassengers(element.getMinCapacity());
                    exiting += quant;
                } else {
                    element.setPassengers(element.getPassengers() - quant);
                    exiting += quant;
                }
                System.out.println("Exiting: " + exiting);
                element.setWait((quant == 0) ? element.getWait() + 1 : element.getWait() + (quant / 2));
                return;
            }
        }
    }

    @Override
    public String toString() {
        return (element != null) ? "{" + element + "}" : "|  🚉  |";
    }

}
