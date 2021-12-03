package carsharing.view.menu;

public enum CompanyMenuItems implements MenuItems {
    LIST(1, "Company list"),
    CREATE(2, "Create a company"),
    BACK(0, "Back");

    private final int number;
    private final String label;

    CompanyMenuItems(int number, String label) {
        this.number = number;
        this.label = label;
    }

    public int getNumber() { return number; }
    public String getLabel() { return label; }
}
