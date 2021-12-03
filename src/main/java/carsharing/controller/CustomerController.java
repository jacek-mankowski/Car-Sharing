package carsharing.controller;

import carsharing.model.dao.*;
import carsharing.model.entity.Car;
import carsharing.model.entity.Company;
import carsharing.model.entity.Customer;
import carsharing.view.data.CarView;
import carsharing.view.data.CompanyView;
import carsharing.view.data.CustomerView;
import carsharing.view.menu.CustomerMenuItems;
import carsharing.view.menu.Menu;
import carsharing.view.menu.MenuItems;

import java.util.Arrays;
import java.util.List;

final class CustomerController extends Controller {
    private static final CustomerDao customerDao = new CustomerDaoImpl();
    private static final CompanyDao companyDao = new CompanyDaoImpl();
    private static final CarDao carDao = new CarDaoImpl();
    private final Customer customer;

    public CustomerController(Customer customer) {
        menu = new Menu<>(Arrays.asList(CustomerMenuItems.values()));
        this.customer = customer;
    }

    @Override
    protected void doActionForItem(MenuItems item) {
        CustomerMenuItems customerItem = (CustomerMenuItems) item;

        switch (customerItem) {
            case RENT_CAR:
                rentCar();
                break;
            case RETURN_CAR:
                returnCar();
                break;
            case RENTED_CAR:
                CustomerView.showRentedCar(this.customer);
                break;
            case BACK:
                doLeave();
                break;
        }
    }

    private void rentCar() {
        if (customer.getCar() != null) {
            System.out.println("You've already rented a car!");
        } else {
            Company company = CompanyView.chooseFromList(companyDao.getAll());
            if (company != null) {
                List<Car> carList = carDao.getAvailableForCompany(company);
                if (carList != null) {
                    Car car = CarView.chooseFromList(carList);
                    if (car != null) {
                        customer.setCar(car);
                        if (customerDao.update(customer) != 0) {
                            System.out.printf("You rented '%s'.\n", car.getName());
                        } else {
                            customer.setCar(null);
                            System.out.println("You didn't rent a car!!!");
                        }
                    }
                } else {
                    System.out.printf("No available cars in the '%s' company.\n", company.getName());
                }
            }
        }
    }

    private void returnCar() {
        if (customer.getCar() != null) {
            Car car = customer.getCar();
            customer.setCar(null);
            if (customerDao.update(customer) != 0) {
                System.out.println("You've returned a rented car!");
            } else {
                customer.setCar(car);
                System.out.println("You've not returned a rented car!!!");
            }
        } else {
            System.out.println("You didn't rent a car!");
        }
    }
}
