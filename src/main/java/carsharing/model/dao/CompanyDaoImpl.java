package carsharing.model.dao;

import carsharing.model.db.Repository;
import carsharing.model.entity.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

    @Override
    public Company map(ResultSet result) {
        Company company = null;
        try {
            if (result.next()) {
                company = new Company();
                company.setId(result.getInt("ID"));
                company.setName(result.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    @Override
    public Company getById(int id) {
        String sql = "select * from COMPANY company where company.ID = ?;";
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
    public List<Company> getAll() {
        String sql = "select * from COMPANY order by ID;";
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
    public int create(Company company) {
        String sql = "insert into COMPANY (NAME) values (?);";
        try {
            PreparedStatement statement = Repository.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, company.getName());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
