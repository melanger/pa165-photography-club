package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.dto.MemberCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.MemberDTO;

import java.util.List;

/**
 * @author Denis.Figula
 */
public interface MemberFacade {
    /**
     * creating memeber
     * @param member to be created
     */
    long createMember(MemberCreateDTO member);

    /**
     * deleting member
     * @param member to be deleted
     */
    void removeMember(Long memberId);

    /**
     * finding all available members
     * @return list of existing members
     */
    List<MemberDTO> findAllMembers();

    /**
     * getting member by id
     * @param id of searched member
     * @return member if found
     */
    MemberDTO findById(Long id);

    /**
     * getting member by his name
     * @param name of searched member
     * @return member if found
     */
    MemberDTO findByName(String name);
}
