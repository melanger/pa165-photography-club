package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.dao.EquipmentDao;
import cz.muni.fi.pa165.photographyclub.dao.MemberDao;
import cz.muni.fi.pa165.photographyclub.dao.ReviewDao;
import cz.muni.fi.pa165.photographyclub.dao.TourDao;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matus Kravec.
 */
@Service
@Transactional 
public class ServiceImpl {
    
    @Autowired
    private TourDao tourDao;
    
    @Autowired
    private ReviewDao reviewDao;
    
    @Autowired
    private MemberDao memberDao;
    
    @Autowired
    private EquipmentDao equipmentDao;
    
    /***********************************TOUR***********************************/
    
    public void createTour(Tour tour){
        tourDao.create(tour);
    }
    
    public void removeTour(Tour tour) {
        tourDao.remove(tour);
    }
 
    public void updateTour(Tour tour) {
        tourDao.update(tour);
    }
    
    public List<Tour> getAllTours() {
        return tourDao.findAll();
    }
    
    public Tour getTourByID(Long id) {
        return tourDao.findById(id);
    }
    
    public Tour getTourByName(String name) {
        return tourDao.findByName(name);
    }
    
    /**********************************REVIEW**********************************/
    
    public Review findReviewById(Long id){
        return reviewDao.findById(id);
    }

    public void createReview(Review r){
        reviewDao.create(r);
    }

    public void removeReview(Review r){
        reviewDao.remove(r);
    }

    public List<Review> findAllReviews(){
        return reviewDao.findAll();
    }

    public List<Review> findReviewByAuthor(Member m){
        return reviewDao.findByAuthor(m);
    }
    
    public List<Review> findReviewByTour(Tour t){
        return reviewDao.findByTour(t);
    }
    
    /**********************************MEMBER**********************************/
    
    public void createMember(Member member){
        memberDao.create(member);
    }
    
    public Member findMemberById(Long id){
        return memberDao.findById(id);
    }

    public Member findMemberByName(String name){
        return memberDao.findByName(name);
    }
    
    public List<Member> findAllMembers(){
        return memberDao.findAll();
    }
    
    public void updateMember(Member member){
        memberDao.update(member);
    }
    
    public void removeMember(Member member){
        memberDao.remove(member);
    }
    
    /*********************************EQUIPMENT********************************/
    
    public Equipment findEquipmentById(Long id){
        return equipmentDao.findById(id);
    }
 
    public void createEquipment(Equipment e){
        equipmentDao.create(e);
    }
   
    public void removeEquipment(Equipment e){
        equipmentDao.remove(e);
    }
   
    public List<Equipment> findAllEquipment(){
        return equipmentDao.findAll();
    }
    
    public List<Equipment> findEquipmentByOwner(Member m){
     return equipmentDao.findByOwner(m);
    }
}
