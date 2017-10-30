package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import static org.assertj.core.api.Assertions.*;


import java.sql.Date;
import org.springframework.test.annotation.DirtiesContext;

/**
 * @author Denis.Figula
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext
public class MemberDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ServiceImpl service;

    @PersistenceUnit
    private EntityManagerFactory emf;

    private Member createMember(){
        Member member = new Member();
        member.setName("Bernard");
        member.setBirthDate(new Date(1995, 5, 13));
        return member;
    }


    @Test
    public void getAllMembers(){
        Member member = createMember();
        service.createMember(member);
        assertThat(service.findAllMembers()).containsExactly(member);
    }

    @Test
    public void updateMember(){
        Member member = createMember();
        member.setName("Cyril");
        service.updateMember(member);
        assertThat(service.findMemberByName("Anton")).isNull();
        assertThat(service.findMemberByName("Cyril")).isNotNull();
    }

    @Test
    public void deleteMember(){
        Member member = createMember();
        service.createMember(member);
        assertThat(service.findMemberById(member.getId())).isNotNull();
        service.removeMember(createMember());
        assertThat(service.findMemberById(member.getId())).isNull();
    }
}
