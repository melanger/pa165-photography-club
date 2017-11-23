package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.enums.EquipmentType;

import java.util.List;
import java.util.Map;

public interface EquipmentService extends GenericService<Equipment> {

    List<Equipment> findEquipmentByOwner(Member m);

    /**
     * Method gets all equipment and sort it by type
     * @return map containing EquipmentType key and List of associated equipment
     */
    Map<EquipmentType,List<Equipment>> getEquipmentInventoryByType();


}
