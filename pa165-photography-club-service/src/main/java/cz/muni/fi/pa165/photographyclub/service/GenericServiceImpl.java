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
	@Inject
	protected GenericDao<T> dao;
  
        @Override
	public void create(T e) {
		dao.create(e);
	}

	@Override
	public T findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}

	@Override
	public void remove(T e) {
		dao.remove(e);
	}
}
