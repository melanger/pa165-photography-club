package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;


import java.sql.Date;

/**
 * @author Denis.Figula
 */
public class MemberDaoTest {

    @Autowired
    private MemberDao memberService;

    private Member createMember(){
        Member member = new Member();
        member.setName("Bernard");
        member.setBirthDate(new Date(1995, 5, 13));
        return member;
    }


    @Test
    public void getAllMembers(){
        Member member = createMember();
        memberService.create(member);
        assertThat(memberService.findAllMembers()).containsExactly(member);
    }

    @Test
    public void updateMember(){
        Member member = createMember();
        member.setName("Cyril");
        memberService.update(member);
        assertThat(memberService.findMemberByName("Anton")).isNull();
        assertThat(memberService.findMemberByName("Cyril")).isNotNull();
    }

    @Test
    public void deleteMember(){
        Member member = createMember();
        memberService.create(member);
        assertThat(memberService.findMemberById(member.getId())).isNotNull();
        memberService.remove(createMember());
        assertThat(memberService.findMemberById(member.getId())).isNull();
    }


}
