package pl.javastart.task;

public class Vehicle {

    private String vehicleType;
    private String producer;
    private String model;
    private int year;
    private int millage;
    private String vin;

    public Vehicle(String vehicleType, String producer, String model, int year, int millage, String vin) {
        this.vehicleType = vehicleType;
        this.producer = producer;
        this.model = model;
        this.year = year;
        this.millage = millage;
        this.vin = vin;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getProducer() {
        return producer;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getMillage() {
        return millage;
    }

    public String getVin() {
        return vin;
    }

    @Override
    public String toString() {
        return vin;
    }
}
