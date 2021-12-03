package carsharing.model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface BaseDao<E> {
    /**
     * Get entity E by ID.
     * @param id - id of entity E.
     * @return - entity E or null.
     */
    E getById(int id);

    /**
     * Get all E entities.
     * @return - list of entities E.
     */
    List<E> getAll();

    /**
     * Create new entity E.
     * @param e - new entity E without set id.
     * @return - 1 if success or 0 if not.
     */
    int create(E e);

    /**
     * Map ResultSet to entity E.
     * @param result - ResultSet from executed query.
     * @return - entity E from ResultSet.
     */
    E map(ResultSet result);

    /**
     * Get list of entities E from ResultSet.
     * @param result - ResultSet from executed query.
     * @return - list of entities E from ResultSet.
     */
    default List<E> mapToList(ResultSet result) {
        E e;
        List<E> list = new ArrayList<>();
        while ((e = map(result)) != null) {
            list.add(e);
        }
        return list.isEmpty() ? null : list;
    }
}
