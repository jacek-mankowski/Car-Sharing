package carsharing.model.dao;

import carsharing.model.entity.Customer;

public interface CustomerDao extends BaseDao<Customer> {
    /**
     * Save changes to Customer.
     * @param customer - Customer entity.
     * @return - 1 if success or 0 if not.
     */
    int update(Customer customer);
}
