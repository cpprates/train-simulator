package app;

import java.util.Scanner;

import model.Detour;
import model.Railway;
import model.Station;
import model.Terminal;
import model.Train;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Train. Welcome :)");

        // sortear a quantidade de estacoes entre 10 e 30
        int quantEst = 10 + (int) (Math.random() * (30 - 10));

        // terminais A e B
        Terminal<Train> terminalA = new Terminal<>();
        Terminal<Train> terminalB = new Terminal<>();

        // Trilhos
        Railway<Train> rails = new Railway<>();

        // Train

        Scanner s = null;
        for (int times = 0; times < 571; times++) {
            s = new Scanner(System.in);
            if (s.hasNextLine()) {
                System.out.println(terminalA);
                for (int i = 0; i < quantEst; i++) {
                    for (int j = 0; j < 20; j++) {
                        System.out.print(rails);
                    }
                    System.out.print(new Detour<>());
                    System.out.print(new Station<Train>());
                    System.out.println(new Detour<>());
                    System.out.println();
                }
                System.out.println(terminalB);
            }
        }
        s.close();
    }
}
