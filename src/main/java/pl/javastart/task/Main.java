package pl.javastart.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static final int EXIT = 0;
    private static final int ADD = 1;
    private static final int SERVICE = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String serviceFile = "service.csv";
        Queue<Vehicle> vehicles = readQueue(serviceFile);
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    Wybierz co chcesz zrobić:
                    0 - exit
                    1 - dodać nowy pojazd do kolejki
                    2 - obsłużyć pierwszy pojazd w kolejce""");
            int userChoose = scanner.nextInt();
            switch (userChoose) {
                case EXIT:
                    try {
                        if (!vehicles.isEmpty()) {
                            Service.saveQueue(vehicles);
                        } else {
                            new FileWriter("service.csv", false).close();
                        }
                    } catch (IOException e) {
                        System.out.println("Nie udało się zapisać danych w pliku");
                    }
                    exit = true;
                    break;
                case ADD:
                    vehicles.offer(Service.createNewVehicle(scanner));
                    break;
                case SERVICE:
                    Service.serveFirst(vehicles);
                    break;
                default:
                    System.out.println("Błędny wybór");
            }
        }
    }

    private static Queue<Vehicle> readQueue(String serviceFile) {
        try {
            return Service.readDataVehicles(serviceFile);
        } catch (FileNotFoundException e) {
            return new LinkedList<>();
        }
    }
}

