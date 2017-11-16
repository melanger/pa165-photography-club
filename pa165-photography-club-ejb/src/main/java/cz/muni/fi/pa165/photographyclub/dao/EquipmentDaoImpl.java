package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 * @author Pavel Brousek
 */
@Repository
public class EquipmentDaoImpl extends GenericDaoImpl<Equipment> implements EquipmentDao {
    @Override
    public Equipment findById(Long id) {
        return entityManager.find(Equipment.class, id);
    }

    @Override
    public List<Equipment> findAll() {
        return entityManager.createQuery("SELECT e FROM Equipment e", Equipment.class).getResultList();
    }

    @Override
    public List<Equipment> findByOwner(Member m) {
        TypedQuery<Equipment> query = entityManager.createQuery(
				"Select e from Equipment e where e.owner = :memberid",
				Equipment.class);
		
		query.setParameter("memberid", m);
        return query.getResultList();
    }
}