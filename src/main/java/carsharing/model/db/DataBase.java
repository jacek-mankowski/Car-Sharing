package carsharing.model.db;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

abstract class DataBase implements Closeable {
    protected static Connection connection = null;

    /**
     * Initialize data-base.
     * @param name - data-base file name with path.
     */
    abstract void init(String name);

    /**
     * Checks if data-base is initialized.
     * @return true if yes or false if not.
     */
    public boolean isInit() {
        return connection != null;
    }

    /**
     * Close data-base.
     */
    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connection = null;
    }

    /**
     * Gets data-base Connection object.
     * @return Connection or null.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Begins transaction.
     */
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Commits changes to data-base.
     */
    public void commit() {
        endTransaction();
    }

    /**
     * Rolls back changes in data-base.
     */
    public void rollback() {
        try {
            connection.rollback();
            endTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ends transaction and commits changes to data-base.
     */
    public void endTransaction() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
