package carsharing.view.menu;

import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class Menu<T extends MenuItems> {

    private static final Scanner scanner = new Scanner(System.in);
    private final Hashtable<Integer, T> menuTable = new Hashtable<>();
    private final String description;

    public Menu(List<T> list) {
        this(list, "");
    }

    public Menu(List<T> list, String description) {
        list.forEach(x -> menuTable.put(x.ordinal(), x));
        this.description = description;
    }

    public void showMenu() {
        System.out.println();
        System.out.println(description);
        menuTable.forEach((k, v) -> System.out.println(v.getNumber() + ". " + v.getLabel()));
    }

    public T choose() {
        T item = null;
        do {
            showMenu();
            try {
                int action = scanner.nextInt();
                item = menuTable
                        .values()
                        .stream()
                        .filter(x -> x.getNumber() == action)
                        .findFirst()
                        .orElse(null);
            } catch (Exception e) {
                System.out.println("Incorrect value.");
            }
        } while (item == null);
        return item;
    }
}
