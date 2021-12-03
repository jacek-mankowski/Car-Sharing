package carsharing.view.menu;

public enum CustomerMenuItems implements MenuItems {
    RENT_CAR(1, "Rent a car"),
    RETURN_CAR(2, "Return a rented car"),
    RENTED_CAR(3, "My rented car"),
    BACK(0, "Back");

    private final int number;
    private final String label;

    CustomerMenuItems(int number, String label) {
        this.number = number;
        this.label = label;
    }

    public int getNumber() { return number; }
    public String getLabel() { return label; }
}
