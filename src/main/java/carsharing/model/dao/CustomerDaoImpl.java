package carsharing.model.dao;

import carsharing.model.db.Repository;
import carsharing.model.entity.Customer;

import java.sql.*;
import java.util.List;


public class CustomerDaoImpl implements CustomerDao {
    
    private final static CarDao carDao = new CarDaoImpl();

    @Override
    public Customer map(ResultSet result) {
        Customer customer = null;
        try {
            if (result.next()) {
                customer = new Customer();
                customer.setId(result.getInt("ID"));
                customer.setName(result.getString("NAME"));
                int rentedCarId = result.getInt("RENTED_CAR_ID");
                customer.setCar(carDao.getById(rentedCarId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public Customer getById(int id) {
        String sql = "select * from CUSTOMER customer where customer.ID = ?;";
        try {
            PreparedStatement statement = Repository.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return map(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        String sql = "select * from CUSTOMER order by ID;";
        try {
            PreparedStatement statement = Repository.getInstance().getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int create(Customer customer) {
        String sql = "insert into CUSTOMER (NAME) values (?);";
        try {
            PreparedStatement statement = Repository.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, customer.getName());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Customer customer) {
        String sql = "update CUSTOMER set NAME = ?, RENTED_CAR_ID = ? where ID = ?;";
        try {
            PreparedStatement statement = Repository.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, customer.getName());
            if(customer.getCar() != null) {
                statement.setInt(2, customer.getCar().getId());
            } else {
                statement.setNull(2, Types.INTEGER);
            }
            statement.setInt(3, customer.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
