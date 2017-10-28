/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.Member;
import java.util.List;

/**
 * Data access object interface for Member entity.
 * 
 * @author Matus Kravec
 */
public interface MemberDao {
    
    /**
     * Method for adding new member to the Database.
     * @param member member to be added.
     */
    public void create(Member member);
    
    /**
     * ethod for finding members by id attribute.
     * @param id id of searched member.
     * @return found member or null if no such member was found.
     */
    public Member findMemberById(Long id);

    /**
     * Method for finding members by name attribute.
     * @param name name of searched member.
     * @return found member or null if no such member was found.
     */
    public Member findMemberByName(String name);
    
    /**
     * Method for finding all members stroed in the Database.
     * @return list of found members.
     */
    public List<Member> findAllMembers();
    
    /**
     * Method for updating member stored in the Database.
     * @param member member to be updated.
     */
    public void update(Member member);
    
    /**
     * Method for removing member from the Database.
     * @param member member to be removed.
     */
    public void remove(Member member);
    
}
