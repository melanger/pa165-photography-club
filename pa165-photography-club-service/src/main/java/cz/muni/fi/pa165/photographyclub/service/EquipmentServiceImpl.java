package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.dao.EquipmentDao;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.enums.EquipmentType;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipmentServiceImpl implements EquipmentService {

    @Inject
    EquipmentDao equipmentDao;

    @Override
    public void create(Equipment e) {
        equipmentDao.create(e);
    }

    @Override
    public Equipment findById(Long id) {
        return equipmentDao.findById(id);
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentDao.findAll();
    }

    @Override
    public void remove(Equipment e) {
        equipmentDao.remove(e);
    }

    @Override
    public List<Equipment> findEquipmentByOwner(Member m) {
        return equipmentDao.findByOwner(m);
    }

    @Override
    public Map<EquipmentType, List<Equipment>> getEquipmentInventoryByType() {
        Map<EquipmentType, List<Equipment>> inventory = new HashMap<>();

        for (EquipmentType t : EquipmentType.values()) {
            List<Equipment> equipmentList = new ArrayList<>();
            for (Equipment e : equipmentDao.findAll())
                if (e.getType().equals(t)) equipmentList.add(e);
            inventory.put(t,equipmentList);
        }
        return inventory;
    }
}
