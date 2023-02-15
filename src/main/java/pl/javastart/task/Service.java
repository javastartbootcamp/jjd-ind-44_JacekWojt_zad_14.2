package pl.javastart.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Service {

    public static Vehicle createNewVehicle(Scanner scanner) {
        System.out.println("Podaj typ pojazdu: ");
        String vehicleType = scanner.next();
        System.out.println("Podaj marke: ");
        String producer = scanner.next();
        System.out.println("Podaj model: ");
        String model = scanner.next();
        System.out.println("Podaj rok produkcji: ");
        int year = scanner.nextInt();
        System.out.println("Podaj przebieg: ");
        int millage = scanner.nextInt();
        System.out.println("Podaj numer VIN: ");
        String vin = scanner.next();
        return new Vehicle(vehicleType, producer, model, year, millage, vin);
    }

    public static void serveFirst(Queue<Vehicle> vehicles) {
        if (!vehicles.isEmpty()) {
            System.out.println("Pojazd o numerze VIN: " + vehicles.peek() + " obecnie przechodzi przegląd");
            System.out.println("Pojazd o numerze VIN: " + vehicles.poll() + " zakończył przegląd pomyślnie");
        } else {
            System.out.println("Kolejka jest pusta, żaden pojazd nie czeka na serwis.");
        }
    }

    public static void saveQueue(Queue<Vehicle> vehicles) throws IOException {
        File vehiclesFile = new File("service.csv");
        FileWriter fileWriter = new FileWriter(vehiclesFile);
        for (Vehicle vehicle : vehicles) {
            fileWriter.write(vehicle.getVehicleType() + ";" + vehicle.getProducer() + ";" + vehicle.getModel() + ";" +
                    vehicle.getYear() + ";" + vehicle.getMillage() + ";" + vehicle.getVin() + "\n");
        }
        fileWriter.close();
    }

    public static Queue<Vehicle> readDataVehicles(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        Queue<Vehicle> vehicles = new LinkedList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(";");
            int year = Integer.parseInt(split[3]);
            int millage = Integer.parseInt(split[3]);
            vehicles.offer(new Vehicle(split[0], split[1], split[2], year, millage, split[5]));
        }
        return vehicles;
    }
}
