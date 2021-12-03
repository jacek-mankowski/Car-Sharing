package carsharing.model.dao;

import carsharing.model.entity.Car;
import carsharing.model.entity.Company;

import java.util.List;

public interface CarDao extends BaseDao<Car> {
    /**
     * Get list of all Cars for Company.
     * @param company - Company entity.
     * @return - list of all Cars for given Company.
     */
    List<Car> getAllForCompany(Company company);

    /**
     * Get list of all available Cars for Company.
     * @param company - Company entity.
     * @return - list of all available (not rented) Cars for given Company.
     */
    List<Car> getAvailableForCompany(Company company);
}
