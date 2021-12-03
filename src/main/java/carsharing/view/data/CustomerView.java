package carsharing.view.data;

import carsharing.model.entity.Customer;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CustomerView {

    public static String getCustomerName() {
        System.out.println("Enter the customer name:");
        return new Scanner(System.in).nextLine();
    }

    public static Customer chooseFromList(List<Customer> list) {
        Scanner scanner = new Scanner(System.in);
        int i = -1;
        if (list != null && !list.isEmpty()) {
            do {
                System.out.println("Choose a customer:");
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
            System.out.println("The customer list is empty!");
            return null;
        }
    }

    public static void showList(List<Customer> customerList) {
        System.out.println("Customer list:");
        asList(customerList);
    }

    private static void asList(List<Customer> customerList) {
        if (customerList != null && !customerList.isEmpty()) {
            customerList.stream()
                    .sorted(Comparator.comparingInt(Customer::getId))
                    .reduce(1,
                            (x, s) -> {
                                System.out.println(x + ". " + s.getName());
                                return ++x;
                            },
                            (x, s) -> x);
        } else {
            System.out.println("The customer list is empty!");
        }
    }

    public static void showRentedCar(Customer customer) {
        if (customer.getCar() != null) {
            System.out.println("Your rented car:");
            System.out.println(customer.getCar().getName());
            System.out.println("Company:");
            System.out.println(customer.getCar().getCompany().getName());
        } else {
            System.out.println("You didn't rent a car!");
        }
    }
}
