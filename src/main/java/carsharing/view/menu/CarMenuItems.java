package carsharing.view.menu;

public enum CarMenuItems implements MenuItems {
    LIST(1, "Car list"),
    CREATE(2, "Create a car"),
    BACK(0, "Back");

    private final int number;
    private final String label;

    CarMenuItems(int number, String label) {
        this.number = number;
        this.label = label;
    }

    public int getNumber() { return number; }
    public String getLabel() { return label; }
}
