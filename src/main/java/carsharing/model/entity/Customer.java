package carsharing.model.entity;

public class Customer {
    private int id;
    private String name;
    private Car car;

    public Customer() {
        this("");
    }

    public Customer(String name) {
        this(0, name, null);
    }

    public Customer(int id, String name, Car car) {
        this.id = id;
        this.name = name;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
