package carsharing.controller;

import carsharing.model.dao.CompanyDao;
import carsharing.model.dao.CompanyDaoImpl;
import carsharing.model.entity.Company;
import carsharing.view.data.CompanyView;
import carsharing.view.menu.CompanyMenuItems;
import carsharing.view.menu.MenuItems;
import carsharing.view.menu.Menu;

import java.util.Arrays;

final class CompanyController extends Controller {

    private final static CompanyDao companyDao = new CompanyDaoImpl();

    public CompanyController() {
        menu = new Menu<>(Arrays.asList(CompanyMenuItems.values()));
    }

    @Override
    protected void doActionForItem(MenuItems item) {
        CompanyMenuItems companyItem = (CompanyMenuItems) item;

        switch (companyItem) {
            case LIST:
                goToCarMenu();
                break;
            case CREATE:
                createNewCompany();
                break;
            case BACK:
                doLeave();
                break;
        }
    }

    private void goToCarMenu() {
        Company company = CompanyView.chooseFromList(companyDao.getAll());
        if (company != null) {
            new CarController(company).run();
        }
    }

    private void listAllCompanies() {
        CompanyView.showList(companyDao.getAll());
    }

    private void createNewCompany() {
        String name = CompanyView.getCompanyName();

        if (!"".equals(name)) {
            if (companyDao.create(new Company(0, name)) > 0) {
                System.out.println("The company was created!");
            } else {
                System.out.println("The company was not created!!!");
            }
        } else {
            System.out.println("Empty company name!!!");
        }
    }
}
