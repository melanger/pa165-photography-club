package cz.muni.fi.pa165.photographyclub.service;

import java.util.List;

/**
 * @author Pavel Brousek
 */
public interface GenericService<T> {
    void create(T e);
    T findById(Long id);
    List<T> findAll();
    void remove(T e);
}
