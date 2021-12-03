package carsharing.model.db;

public final class Repository extends H2DataBase {

    private static final Repository repository = new Repository();

    private Repository() {
    }

    public static Repository getInstance() {
        return repository;
    }

    @Override
    public void init(String name) {
        super.init(name);
        Tables.create();
    }
}
