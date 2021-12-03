package carsharing.model.db;

import java.sql.DriverManager;
import java.sql.SQLException;

class H2DataBase extends DataBase {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String URL_PREFIX = "jdbc:h2:";
    private static final String USER = "";
    private static final String PASS = "";

    public void init(String name) {
        String url = URL_PREFIX + name;

        try {
            close();
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(url, USER, PASS);
            connection.setAutoCommit(true);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
