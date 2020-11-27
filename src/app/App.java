package app;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Train. Welcome :)");

        // sortear a quantidade de estacoes entre 10 e 30
        int quantEst = 10 + (int) (Math.random() * (30 - 10));

        String ptA = "[A]";
        String ptB = "[B]";

        System.out.println(ptA);
        for (int i = 0; i < quantEst; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print("[    ]");
            }
            System.out.println(estacao());
            System.out.println();
        }
        System.out.println(ptB);
    }

    public static String estacao() {
        return "[{d}estacao{d}]";
    }
}
