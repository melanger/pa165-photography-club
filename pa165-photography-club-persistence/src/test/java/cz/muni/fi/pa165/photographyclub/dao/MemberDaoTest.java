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
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

/**
 * @author Denis.Figula
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class MemberDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ServiceImpl service;

    @PersistenceUnit
    private EntityManagerFactory emf;

    private Member createMember() {
        Member member = new Member();
        member.setName("Bernard");
        member.setBirthDate(LocalDate.of(1995, 5, 13));
        return member;
    }

    @Test
    public void createMemberTest() {
        Member member = createMember();
        service.createMember(member);
        assertThat(service.findAllMembers()).contains(member);
    }

    @Test
    public void getAllMembersTest() {
        Member member = createMember();
        service.createMember(member);
        assertThat(service.findAllMembers()).containsExactly(member);
    }

    @Test
    public void updateMemberTest() {
        Member member = createMember();
        member.setName("Cyril");
        service.updateMember(member);
        assertThat(service.findMemberByName("Anton")).isNull();
        assertThat(service.findMemberByName("Cyril")).isNotNull();
    }

    @Test
    public void deleteMemberTest() {
        Member member = createMember();
        service.createMember(member);
        assertThat(service.findMemberById(member.getId())).isNotNull();
        service.removeMember(member);
        assertThat(service.findMemberById(member.getId())).isNull();
    }
}
