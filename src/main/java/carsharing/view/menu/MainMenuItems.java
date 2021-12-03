package carsharing.view.menu;

public enum MainMenuItems implements MenuItems {
    LOG_IN_MANAGER(1, "Log in as a manager"),
    LOG_IN_CUSTOMER(2, "Log in as a customer"),
    CREATE_CUSTOMER(3, "Create a customer"),
    EXIT(0, "Exit");

    private final int number;
    private final String label;

    MainMenuItems(int number, String label) {
        this.number = number;
        this.label = label;
    }

    public int getNumber() { return number; }
    public String getLabel() { return label; }
}
