package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.entity.Member;

/**
 * Service for manipulation with Members.
 * @author Matus Kravec.
 */
public interface MemberService extends GenericService<Member> {
    
    /**
     * Method finds Member with name attribute same as in parameter.
     * @param name name of the member to be found.
     * @return found member or null if no such member was found.
     */
    public Member findByName(String name);
    
}
