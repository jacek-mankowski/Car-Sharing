package carsharing.view.data;

import carsharing.model.entity.Company;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CompanyView {

    public static String getCompanyName() {
        System.out.println("Enter the company name:");
        return new Scanner(System.in).nextLine();
    }

    public static Company chooseFromList(List<Company> list) {
        Scanner scanner = new Scanner(System.in);
        int i = -1;
        if (list != null && !list.isEmpty()) {
            do {
                System.out.println("Choose a company:");
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
            System.out.println("The company list is empty!");
            return null;
        }
    }

    public static void showList(List<Company> companyList) {
        System.out.println("Company list:");
        asList(companyList);
    }

    private static void asList(List<Company> companyList) {
        if (companyList != null && !companyList.isEmpty()) {
            companyList
                    .stream()
                    .sorted(Comparator.comparingInt(Company::getId))
                    .reduce(1,
                            (x, s) -> {
                                System.out.println(x + ". " + s.getName());
                                return ++x;
                            },
                            (x, s) -> x);
        } else {
            System.out.println("The company list is empty!");
        }
    }
}
