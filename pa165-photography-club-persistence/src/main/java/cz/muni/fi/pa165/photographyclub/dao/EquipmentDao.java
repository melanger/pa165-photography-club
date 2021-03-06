package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import java.util.List;

/**
 * @author Pavel Brousek
 */
public interface EquipmentDao extends GenericDao<Equipment> {
    /**
     * Get equipments of a member.
     * @param m member whose equipments should be fetched
     * @return list of equipments of the member (might be empty)
     */
    public List<Equipment> findByOwner(Member m);
}