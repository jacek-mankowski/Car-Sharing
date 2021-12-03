package carsharing.model.dao;

import carsharing.model.db.Repository;
import carsharing.model.entity.Car;
import carsharing.model.entity.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarDaoImpl implements CarDao {

    private final static CompanyDao companyDao = new CompanyDaoImpl();

    @Override
    public Car map(ResultSet result) {
        Car car = null;
        try {
            if (result.next()) {
                car = new Car();
                car.setId(result.getInt("ID"));
                car.setName(result.getString("NAME"));
                int companyId = result.getInt("COMPANY_ID");
                car.setCompany(companyDao.getById(companyId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public Car getById(int id) {
        String sql = "select * from CAR car where car.ID = ?;";
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
    public List<Car> getAll() {
        String sql = "select * from CAR order by ID;";
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
    public List<Car> getAllForCompany(Company company) {
        String sql = "select * from CAR car where car.COMPANY_ID = ? order by car.ID;";
        try {
            PreparedStatement statement = Repository.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1, company.getId());
            ResultSet result = statement.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> getAvailableForCompany(Company company) {
        String sql = "select * from CAR car where car.COMPANY_ID = ? and " +
                "not exists (select * from CUSTOMER customer where customer.RENTED_CAR_ID = car.ID) " +
                "order by car.ID;";
        try {
            PreparedStatement statement = Repository.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1, company.getId());
            ResultSet result = statement.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int create(Car car) {
        String sql = "insert into CAR (NAME, COMPANY_ID) values (?, ?);";
        try {
            PreparedStatement statement = Repository.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, car.getName());
            statement.setInt(2, car.getCompany().getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
