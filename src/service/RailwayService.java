package service;

import java.util.Scanner;

import dao.RailwayDAO;
import model.Train;

public class RailwayService {
    public static void main(String[] args) {
        RailwayDAO<Train> dao = new RailwayDAO<Train>();

        int hours = 8;
        int minutes = 0;
        boolean A = false;
        boolean B = false;

        // System.out.println(dao.toString());

        // Scanner scanner = null;
        for (int count = 0; count < 571; count++) {
            // Scanner scanner = new Scanner(System.in);
            if (count == 0) {
                System.out.println(dao.moveTrain(A, B, count));
                // String arr[] = dao.moveTrain(A, B, count).split(" ");
                // System.out.println(arr);
                System.out.println("Time: " + hours + ":" + minutes);
                if (minutes == 60) {
                    minutes = 0;
                    hours++;
                }
                // A = Boolean.parseBoolean(arr[0]);
                // B = Boolean.parseBoolean(arr[1]);
            } else {
                System.out.println("nao entrou no if");
            }
        }

    }

}
