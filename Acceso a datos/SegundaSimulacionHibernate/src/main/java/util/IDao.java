package util;

import java.util.List;
import java.util.Optional;

/**
 * Esta interfaz contiene las operaciones del CRUD Create a element T Retrieve a
 * element and all elements: get Update a element T Delete a element T
 * 
 * @param <T>
 */
public interface IDao<T> {
	void create(T t);

	T get(int id);

	List<T> getAll();

	void update(T t);

	void delete(T t);
}
