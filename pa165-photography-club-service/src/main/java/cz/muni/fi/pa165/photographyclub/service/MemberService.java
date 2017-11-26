package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.entity.Member;

/**
 *
 * @author matus
 */
public interface MemberService extends GenericService<Member> {
    
    public Member findByName(String name);
    
}
