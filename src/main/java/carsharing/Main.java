package carsharing;

import carsharing.controller.MainController;
import carsharing.model.db.Repository;

public class Main {

    private static final String DB_FILE_NAME_PARAM = "-databaseFileName";
    private static final String DB_DEFAULT_FILE_NAME = "carsharing";
    private static final String DB_CATALOG = "./";

    private static String getDataBaseName(String[] args) {
        String name = DB_CATALOG + DB_DEFAULT_FILE_NAME;

        for (int i = 0; i < args.length; i++) {
            if (DB_FILE_NAME_PARAM.equals(args[i]) && args[i + 1] != null) {
                name = DB_CATALOG + args[i + 1];
                break;
            }
        }
        return name;
    }

    public static void main(String[] args) {
        String dbName = getDataBaseName(args);

        try (Repository repository = Repository.getInstance()) {
            repository.init(dbName);
            new MainController().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}