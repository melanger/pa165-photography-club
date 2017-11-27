package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.dao.GenericDao;
import cz.muni.fi.pa165.photographyclub.dao.MemberDao;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import javax.inject.Inject;

/**
 *
 * @author Matus Kravec
 */
public class MemberServiceImpl extends GenericServiceImpl<Member> implements MemberService {

    @Inject
    protected MemberDao dao;
    
    @Override
    protected GenericDao<Member> getDao() {
        return dao;
    }

    @Override
    public Member findByName(String name) {
        return dao.findByName(name);
    }
    
}
