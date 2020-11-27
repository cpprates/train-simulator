package app;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Train. Welcome :)");

        // sortear a quantidade de estacoes entre 10 e 30
        int quantEst = 10 + (int) (Math.random() * (30 - 10));

        String ptA = "[A]";
        String ptB = "[B]";

        Scanner s = null;
        for (int times = 0; times < 571; times++) {
            s = new Scanner(System.in);
            if (s.hasNextLine()) {
                System.out.println(ptA);
                for (int i = 0; i < quantEst; i++) {
                    for (int j = 0; j < 20; j++) {
                        if (times == j)
                            System.out.print("[ ** ]");
                        System.out.print("[    ]");
                    }
                    System.out.println(estacao());
                    System.out.println();
                }
                System.out.println(ptB);
            }
        }
        s.close();
    }

    public static String estacao() {
        return "[{d}estacao{d}]";
    }
}
