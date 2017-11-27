package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.dao.GenericDao;
import cz.muni.fi.pa165.photographyclub.dao.MemberDao;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Matus Kravec
 */
@Service
public class MemberServiceImpl extends GenericServiceImpl<Member> implements MemberService {

    @Inject
    private MemberDao memberDao;
    
    @Override
    protected GenericDao<Member> getDao() {
        return memberDao;
    }

    @Override
    public Member findByName(String name) {
        return memberDao.findByName(name);
    }
    
}
