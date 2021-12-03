package carsharing.model.db;

import java.sql.SQLException;
import java.sql.Statement;

class Tables {
    public static void create() {
        createCompany();
        createCar();
        createCustomer();
    }

    private static void createCompany() {
        String sql = "create table if not exists COMPANY (\n" +
                "ID INTEGER generated always as identity, \n" +
                "NAME VARCHAR(255) not null unique, \n" +
                "constraint pk_company primary key (id) \n" +
                ");";
        executeUpdate(sql);
    }

    private static void createCar() {
        String sql = "create table if not exists CAR (\n" +
                "ID INTEGER generated always as identity, \n" +
                "NAME VARCHAR(255) not null unique, \n" +
                "COMPANY_ID INTEGER not null, \n" +
                "constraint pk_car primary key (id), \n" +
                "constraint fk_company foreign key (company_id) \n" +
                "references COMPANY (id) \n" +
                "on delete set null \n" +
                "on update cascade \n" +
                ");";
        executeUpdate(sql);
    }

    private static void createCustomer() {
        String sql = "create table if not exists CUSTOMER (\n" +
                "ID INTEGER generated always as identity, \n" +
                "NAME VARCHAR(255) not null unique, \n" +
                "RENTED_CAR_ID INTEGER null, \n" +
                "constraint pk_customer primary key (id), \n" +
                "constraint fk_car foreign key (rented_car_id) \n" +
                "references CAR (id) \n" +
                "on delete set null \n" +
                "on update cascade \n" +
                ");";
        executeUpdate(sql);
    }

    private static void executeUpdate(String sql) {
        try {
            Statement statement = Repository.getInstance().getConnection().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
