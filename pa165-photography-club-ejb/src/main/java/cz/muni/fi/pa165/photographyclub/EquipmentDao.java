package cz.muni.fi.pa165.photographyclub;

import java.util.List;

/**
 * @author Pavel Brousek
 */
public interface EquipmentDao {
    /**
     * Retrieve an equipment based on id.
     * @param id equipment id
     * @return equipment if found, null otherwise
     */
    public Equipment findById(Long id);
    /**
     * Create (store) a new equipment.
     * @param e equipment to be stored
     */
    public void create(Equipment e);
    /**
     * Remove (delete) an equipment.
     * @param e equipment to be deleted
     */
    public void remove(Equipment e);
    /**
     * Get all equipments.
     * @return list of equipments (might be empty)
     */
    public List<Equipment> findAll();
    /**
     * Get equipments of a member.
     * @param m member whose equipments should be fetched
     * @return list of equipments of the member (might be empty)
     */
    public List<Equipment> findByOwner(Member m);
}