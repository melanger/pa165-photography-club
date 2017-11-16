package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.Member;

/**
 * Data access object interface for Member entity.
 * 
 * @author Matus Kravec
 */
public interface MemberDao extends GenericDao<Member> {
    /**
     * Method for finding members by name attribute.
     * @param name name of searched member.
     * @return found member or null if no such member was found.
     */
    public Member findByName(String name);
}
