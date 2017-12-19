package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.beanmapping.BeanMappingService;
import cz.muni.fi.pa165.photographyclub.dto.LoginDTO;
import cz.muni.fi.pa165.photographyclub.dto.MemberCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.MemberDTO;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import cz.muni.fi.pa165.photographyclub.service.EquipmentService;
import cz.muni.fi.pa165.photographyclub.service.MemberService;
import cz.muni.fi.pa165.photographyclub.service.ReviewService;
import cz.muni.fi.pa165.photographyclub.service.TourService;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * Implementation of MemberFacade
 *
 * @author Pavel Brousek
 */
@Transactional
@Service
public class MemberFacadeImpl implements MemberFacade {

    @Inject
    private MemberService memberService;
    
    @Inject
    private ReviewService reviewService;
    
    @Inject
    private EquipmentService equipmentService;
    
    @Inject
    private TourService tourService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public long createMember(MemberCreateDTO member) {
        Member m = new Member();
        m.setAddress(member.getAddress());
        m.setBirthDate(member.getBirthDate());
        if(member.getEquipment() != null){
            m.setEquipment(beanMappingService.mapTo(member.getEquipment(), Equipment.class));
        } else {
            m.setEquipment(new LinkedList<Equipment>());
        }
        m.setExperience(member.getExperience());
        m.setGender(member.getGender());
        m.setMotivation(member.getMotivation());
        m.setName(member.getName());
        m.setPhoto(member.getPhoto());
        m.setUserRole(member.getUserRole());
        m.setEmail(member.getEmail());
        m.setPassword(member.getPassword());
        if(member.getReviews() != null){
            m.setReviews(beanMappingService.mapTo(member.getReviews(), Review.class));
        } else {
            m.setReviews(new LinkedList<Review>());
        }        
        if(member.getTours() != null){
            m.setTours(beanMappingService.mapTo(member.getTours(), Tour.class));
        } else {
            m.setTours(new LinkedList<Tour>());
        }
        
        memberService.create(m);
        return m.getId();
    }

    @Override
    public void removeMember(Long memberId) {
        Member m = memberService.findById(memberId);        
        if (m == null) throw new EntityNotFoundException();
        if(m.getEquipment() != null){
            if(m.getEquipment().size() > 0){
                for (Equipment equipment : m.getEquipment()) {
                    equipmentService.remove(equipment);
                }                
            }
        }
        if(m.getReviews() != null){
            if(m.getReviews().size() > 0){
                for (Review review : m.getReviews()) {
                    reviewService.remove(review);
                }
            }
        }
        if(m.getTours() != null){
            if(m.getTours().size() > 0){
                for (Tour tour : m.getTours()) {
                    tourService.removeMemberFromAllTours(m.getId());
                }
            }
        }
        memberService.remove(m);
    }

    @Override
    public List<MemberDTO> findAllMembers() {
        return beanMappingService.mapTo(memberService.findAll(), MemberDTO.class);
    }

    @Override
    public MemberDTO findById(Long id) {
        Member m = memberService.findById(id);
        return (m == null) ? null : beanMappingService.mapTo(m, MemberDTO.class);
    }

    @Override
    public MemberDTO findByName(String name) {
        Member m = memberService.findByName(name);
        return (m == null) ? null : beanMappingService.mapTo(m, MemberDTO.class);
    }

    @Override
    public MemberDTO login(LoginDTO loginInfo) {
        List<Member> members = memberService.findAll();
        for (Member m : members) {
            if (m.getEmail().equals(loginInfo.getEmail())) {
                if (bCryptPasswordCorrect(loginInfo.getPassword(), m.getPassword())) {
                    return beanMappingService.mapTo(m, MemberDTO.class);
                } else {
                    return null;
                }
            }
        }
        return null;
    }
    
    private boolean bCryptPasswordCorrect(String candidate_password, String stored_hash) {
        return BCrypt.checkpw(candidate_password, stored_hash);
    }

}
