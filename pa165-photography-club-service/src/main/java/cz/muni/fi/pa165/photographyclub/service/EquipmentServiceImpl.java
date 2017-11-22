package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.dao.EquipmentDao;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;

import javax.inject.Inject;
import java.util.List;

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
}
