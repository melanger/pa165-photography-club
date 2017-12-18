package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 * @author Denis.Figula
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class MemberDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private MemberDao memberDao;

    private Member createMember() {
        Member member = new Member();
        member.setName("Bernard");
        member.setBirthDate(LocalDate.of(1995, 5, 13));
        member.setUserRole(UserRole.MEMBER);
        member.setEmail("bernard@example.com");
        member.setPassword("random string");
        return member;
    }

    @Test
    public void createMemberTest() {
        Member member = createMember();
        memberDao.create(member);
        assertThat(memberDao.findAll()).contains(member);
    }

    @Test
    public void getAllMembersTest() {
        Member member = createMember();
        memberDao.create(member);
        assertThat(memberDao.findAll()).containsExactly(member);
    }

    @Test
    public void updateMemberTest() {
        Member member = createMember();
        member.setName("Cyril");
        memberDao.update(member);
        assertThat(memberDao.findByName("Anton")).isNull();
        assertThat(memberDao.findByName("Cyril")).isNotNull();
    }

    @Test
    public void deleteMemberTest() {
        Member member = createMember();
        memberDao.create(member);
        assertThat(memberDao.findById(member.getId())).isNotNull();
        memberDao.remove(member);
        assertThat(memberDao.findById(member.getId())).isNull();
    }
}
