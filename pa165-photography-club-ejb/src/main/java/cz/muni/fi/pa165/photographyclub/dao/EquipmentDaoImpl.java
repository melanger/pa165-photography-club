package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author Pavel Brousek
 */
public class EquipmentDaoImpl implements EquipmentDao {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Equipment findById(Long id) {
        return em.find(Equipment.class, id);
    }

    @Override
    public void create(Equipment e) {
        em.persist(e);
    }

    @Override
    public void remove(Equipment e) {
        em.remove(e);
    }

    @Override
    public List<Equipment> findAll() {
        return em.createQuery("SELECT e FROM Equipment e", Equipment.class).getResultList();
    }

    @Override
    public List<Equipment> findByOwner(Member m) {
        TypedQuery<Equipment> query = em.createQuery(
				"Select e from Equipment e where e.owner = :memberid",
				Equipment.class);
		
		query.setParameter("memberid", m);
        return query.getResultList();
    }
}