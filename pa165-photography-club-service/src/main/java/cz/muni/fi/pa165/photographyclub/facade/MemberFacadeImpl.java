package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.beanmapping.BeanMappingService;
import cz.muni.fi.pa165.photographyclub.dto.MemberCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.MemberDTO;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import cz.muni.fi.pa165.photographyclub.service.MemberService;
import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of MemberFacade
 * @author Pavel Brousek
 */
public class MemberFacadeImpl implements MemberFacade {
    @Inject
    private MemberService memberService;
    
    @Inject
    private BeanMappingService beanMappingService;
    
    @Override
    public void createMember(MemberCreateDTO member) {
        Member m = new Member();
        m.setAddress(member.getAddress());
        m.setBirthDate(member.getBirthDate());
        m.setEquipment(beanMappingService.mapTo(member.getEquipment(), Equipment.class));
        m.setExperience(member.getExperience());
        m.setGender(member.getGender());
        m.setMotivation(member.getMotivation());
        m.setName(member.getName());
        m.setPhoto(member.getPhoto());
        m.setReviews(beanMappingService.mapTo(member.getReviews(), Review.class));
        m.setTours(beanMappingService.mapTo(member.getTours(), Tour.class));
        memberService.create(m);
    }

    @Override
    public void removeMember(MemberDTO member) {
        Member m = new Member();
        m.setId(member.getId());
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
    
}
