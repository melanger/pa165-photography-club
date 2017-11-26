package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.dao.EquipmentDao;
import cz.muni.fi.pa165.photographyclub.dao.GenericDao;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.enums.EquipmentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 * Implementation of EquipmentService
 * @author Denis.Figula
 */
public class EquipmentServiceImpl extends GenericServiceImpl<Equipment> implements EquipmentService {

    @Inject
    protected EquipmentDao dao;
    
    @Override
    protected GenericDao<Equipment> getDao() {
        return dao;
    }

    @Override
    public List<Equipment> findByOwner(Member m) {
        return dao.findByOwner(m);
    }

    @Override
    public Map<EquipmentType, List<Equipment>> getEquipmentInventoryByType() {
        Map<EquipmentType, List<Equipment>> inventory = new HashMap<>();

        for (EquipmentType t : EquipmentType.values()) {
            List<Equipment> equipmentList = new ArrayList<Equipment>();
            for (Equipment e : dao.findAll())
                if (e.getType() == t){
                    equipmentList.add(e);
                }
            inventory.put(t,equipmentList);
        }
        return inventory;
    }
}
