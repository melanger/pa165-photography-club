package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.dao.GenericDao;
import cz.muni.fi.pa165.photographyclub.dao.TourDao;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import javax.inject.Inject;

/**
 * Implementation of TourService.
 * @author Pavel Brousek
 */
public class TourServiceImpl extends GenericServiceImpl<Tour> implements TourService {
    @Inject
    protected TourDao dao;

    @Override
    protected GenericDao<Tour> getDao() {
        return dao;
    }
}