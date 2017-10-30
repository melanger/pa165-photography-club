/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * implementation OF data access object for Member entity.
 * 
 * @author Matus Kravec
 */
@Repository
public class MemberDaoImpl implements MemberDao {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Member member) {
        entityManager.persist(member);
    }

    @Override
    public Member findMemberById(Long id) {
        return entityManager.find(Member.class, id);
    }

    @Override
    public Member findMemberByName(String name) {
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
    public List<Member> findAllMembers() {
        return entityManager.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }

    @Override
    public void update(Member member) {
        entityManager.merge(member);
    }

    @Override
    public void remove(Member member) {
        entityManager.remove(entityManager.merge(member));
    }
    
}
