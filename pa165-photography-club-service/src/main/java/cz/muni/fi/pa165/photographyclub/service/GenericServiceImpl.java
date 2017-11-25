package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.dao.GenericDao;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 * @author Pavel Brousek
 */
@Service
public abstract class GenericServiceImpl<T> implements GenericService<T> {
	protected abstract GenericDao<T> getDao();
  
        @Override
	public void create(T e) {
		getDao().create(e);
	}

	@Override
	public T findById(Long id) {
		return getDao().findById(id);
	}

	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public void remove(T e) {
		getDao().remove(e);
	}
}
