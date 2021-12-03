package carsharing.view.data;

import carsharing.model.entity.Car;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CarView {

    public static String getCarName() {
        System.out.println("Enter the car name:");
        return new Scanner(System.in).nextLine();
    }

    public static Car chooseFromList(List<Car> list) {
        Scanner scanner = new Scanner(System.in);
        int i = -1;
        if (list != null && !list.isEmpty()) {
            do {
                System.out.println("Choose a car:");
                asList(list);
                System.out.println("0. Back");

                try {
                    i = scanner.nextInt();
                    if (!(0 <= i && i <= list.size())) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Incorrect value!!!");
                }
            } while (!(0 <= i && i <= list.size()));

            return i == 0 ? null : list.get(i - 1);
        } else {
            System.out.println("The car list is empty!");
            return null;
        }
    }

    public static void showList(List<Car> carList) {
        System.out.println("Car list:");
        asList(carList);
    }

    private static void asList(List<Car> carList) {
        if (carList != null && !carList.isEmpty()) {
            carList.stream()
                    .sorted(Comparator.comparingInt(Car::getId))
                    .reduce(1,
                            (x, s) -> {
                                System.out.println(x + ". " + s.getName());
                                return ++x;
                            },
                            (x, s) -> x);
        } else {
            System.out.println("The car list is empty!");
        }
    }
}
