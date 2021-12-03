package carsharing.controller;

import carsharing.model.dao.CustomerDao;
import carsharing.model.dao.CustomerDaoImpl;
import carsharing.model.entity.Customer;
import carsharing.view.data.CustomerView;
import carsharing.view.menu.MenuItems;
import carsharing.view.menu.MainMenuItems;
import carsharing.view.menu.Menu;

import java.util.Arrays;

public final class MainController extends Controller {
    private static final CustomerDao customerDao = new CustomerDaoImpl();

    public MainController() {
        menu = new Menu<>(Arrays.asList(MainMenuItems.values()));
    }

    @Override
    protected void doActionForItem(MenuItems item) {
        MainMenuItems loginItem = (MainMenuItems) item;

        switch (loginItem) {
            case LOG_IN_MANAGER:
                new CompanyController().run();
                break;
            case LOG_IN_CUSTOMER:
                goToCustomerMenu();
                break;
            case CREATE_CUSTOMER:
                createNewCustomer();
                break;
            case EXIT:
                doExit();
                break;
        }
    }

    private void goToCustomerMenu() {
        Customer customer = CustomerView.chooseFromList(customerDao.getAll());
        if (customer != null) {
            new CustomerController(customer).run();
        }
    }

    private void createNewCustomer() {
        String name = CustomerView.getCustomerName();

        if (!"".equals(name)) {
            if (customerDao.create(new Customer(name)) > 0) {
                System.out.println("The customer was added!");
            } else {
                System.out.println("The customer was not added!!!");
            }
        } else {
            System.out.println("Empty customer name!!!");
        }
    }
}
