package util;

import java.util.List;

public interface IDao<T> {
	void create(T t);
	T get(int id); 	
    List<T> getAll();
	void update(T t);
	void delete(T t);
}



