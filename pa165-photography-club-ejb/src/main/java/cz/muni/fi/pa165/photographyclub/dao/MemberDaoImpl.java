package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.Member;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 * implementation OF data access object for Member entity.
 * 
 * @author Matus Kravec
 */
@Repository
public class MemberDaoImpl extends GenericDaoImpl<Member> implements MemberDao {
    @Override
    public Member findById(Long id) {
        return entityManager.find(Member.class, id);
    }

    @Override
    public Member findByName(String name) {
        Member result = null;
        try{
        result = entityManager.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
                .setParameter("name", name).getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
        return result;
    }

    @Override
    public List<Member> findAll() {
        return entityManager.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }
}
