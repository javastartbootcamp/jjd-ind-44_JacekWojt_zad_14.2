package pl.javastart.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String serviceFile = "service.csv";
        File file = new File(serviceFile);
        Queue<Vehicle> vehicles = new LinkedList<>();
        try {
            vehicles = checkFileExist(serviceFile, file, vehicles);
        } catch (FileNotFoundException e) {
            System.out.println("Plik nie został utworzony");
        }
        boolean onOff = true;
        while (onOff) {
            System.out.println("""
                    Wybierz co chcesz zrobić:
                    0 - zakończyć
                    1 - wczytać nowy pojazd do kolejki
                    2 - obsłużyć pierwszy pojazd w kolejce""");
            int userChoose = scanner.nextInt();
            switch (userChoose) {
                case 0:
                    if (!vehicles.isEmpty()) {
                        try {
                            Service.saveQueue(vehicles);
                        } catch (IOException e) {
                            System.out.println("Plik nie został utworzony");
                        }
                        onOff = false;
                        break;
                    } else {
                        onOff = false;
                        break;
                    }
                case 1:
                    vehicles.offer(Service.addNewVehicle(scanner));
                    break;
                case 2:
                    Service.serveFirst(vehicles);
                    break;
                default:
                    System.out.println("Błędny wybór");
            }
        }
    }

    private static Queue<Vehicle> checkFileExist(String serviceFile, File file, Queue<Vehicle> vehicles) throws FileNotFoundException {
        if (file.exists()) {
            vehicles = Service.readDataVehicles(serviceFile);
        }
        return vehicles;
    }
}

