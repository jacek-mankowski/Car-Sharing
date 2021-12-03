package carsharing.controller;

import carsharing.model.dao.CarDao;
import carsharing.model.dao.CarDaoImpl;
import carsharing.model.entity.Car;
import carsharing.model.entity.Company;
import carsharing.view.data.CarView;
import carsharing.view.menu.CarMenuItems;
import carsharing.view.menu.Menu;
import carsharing.view.menu.MenuItems;

import java.util.Arrays;

final class CarController extends Controller {
    private static final CarDao carDao = new CarDaoImpl();
    private final Company company;

    public CarController(Company company) {
        String description = String.format("'%s' company", company.getName());
        menu = new Menu<>(Arrays.asList(CarMenuItems.values()), description);
        this.company = company;
    }

    @Override
    protected void doActionForItem(MenuItems item) {
        CarMenuItems carItem = (CarMenuItems) item;

        switch (carItem) {
            case LIST:
                listAllCars();
                break;
            case CREATE:
                createNewCar();
                break;
            case BACK:
                doLeave();
                break;
        }
    }

    private void listAllCars() {
        CarView.showList(carDao.getAllForCompany(this.company));
    }

    private void createNewCar() {
        String name = CarView.getCarName();

        if (!"".equals(name)) {
            if (carDao.create(new Car(0, name, this.company)) > 0) {
                System.out.println("The car was created!");
            } else {
                System.out.println("The car was not created!!!");
            }
        } else {
            System.out.println("Empty car name!!!");
        }
    }
}
