package persistencia.dao.interfaz;

import java.util.List;

public interface DAO<T> {

    public T insert(T t);

	public T update(T t);

	public void delete(T t);
	
	public List<T> readAll();
    
}
