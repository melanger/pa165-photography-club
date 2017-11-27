package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.dto.MemberCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.MemberDTO;

import java.util.List;

/**
 * @author Denis.Figula
 */
public interface MemberFacade {

    void createMember(MemberCreateDTO member);

    void removeMember(MemberDTO member);

    List<MemberDTO> findAllMembers();

    MemberDTO findById(Long id);

    MemberDTO findByName(String name);
}
