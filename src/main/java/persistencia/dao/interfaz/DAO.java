package persistencia.dao.interfaz;

import java.util.List;

public interface DAO<T> {

    public boolean insert(T t);

	public T update(T t);

	public boolean delete(T t);
	
	public List<T> readAll();
    
}
