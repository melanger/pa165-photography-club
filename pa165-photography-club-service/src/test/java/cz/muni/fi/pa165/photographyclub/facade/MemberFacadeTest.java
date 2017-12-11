package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.facade.config.FacadeApplicationContext;
import cz.muni.fi.pa165.photographyclub.dto.MemberCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.MemberDTO;
import cz.muni.fi.pa165.photographyclub.beanmapping.BeanMappingService;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.service.MemberService;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for MemberFacadeImpl
 *
 * @author Pavel Brousek
 */
@ContextConfiguration(classes = FacadeApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MemberFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private MemberService memberService;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private MemberFacadeImpl memberFacade;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createMemberTest() {
        MemberCreateDTO m = new MemberCreateDTO();
        final String name = "Test";
        m.setName(name);
        final LocalDate birthDate = LocalDate.of(2017, Month.NOVEMBER, 27);
        m.setBirthDate(birthDate);
        final String address = null;
        m.setAddress(address);

        memberFacade.createMember(m);

        ArgumentCaptor<Member> argumentCaptor = ArgumentCaptor.forClass(Member.class);
        verify(memberService, times(1)).create(argumentCaptor.capture());
        Member arg = argumentCaptor.getValue();
        assertThat(arg)
                .hasFieldOrPropertyWithValue("name", name)
                .hasFieldOrPropertyWithValue("birthDate", birthDate)
                .hasFieldOrPropertyWithValue("address", address);
    }

    @Test
    public void removeMemberTest() {
        final long memberId = 42l;
        MemberDTO member = new MemberDTO();
        member.setId(memberId);

        memberFacade.removeMember(member);

        ArgumentCaptor<Member> argumentCaptor = ArgumentCaptor.forClass(Member.class);
        verify(memberService, times(1)).remove(argumentCaptor.capture());
        Member arg = argumentCaptor.getValue();
        assertThat(arg).hasFieldOrPropertyWithValue("id", memberId);
    }

    @Test
    public void getAllMembersTest() {
        final List<Member> members = new LinkedList<>();
        members.add(new Member());
        final List<MemberDTO> memberDTOs = new LinkedList<>();
        memberDTOs.add(new MemberDTO());
        when(memberService.findAll()).thenReturn(members);
        when(beanMappingService.mapTo(members, MemberDTO.class)).thenReturn(memberDTOs);

        assertThat(memberFacade.findAllMembers()).isEqualTo(memberDTOs);
    }

    @Test
    public void getMemberById() {
        final long memberId = 42l;
        final Member member = new Member();
        final MemberDTO memberDTO = new MemberDTO();
        member.setId(memberId);
        when(memberService.findById(memberId)).thenReturn(member);
        when(beanMappingService.mapTo(member, MemberDTO.class)).thenReturn(memberDTO);

        assertThat(memberFacade.findById(memberId)).isEqualTo(memberDTO);
    }

    @Test
    public void getMemberByName() {
        final String name1 = "Franta";
        final long id1 = 1l;
        final Member m1 = new Member();
        m1.setName(name1);
        m1.setId(id1);

        final long id2 = 2l;
        final String name2 = "Karel";
        final Member m2 = new Member();
        m2.setName(name2);

        final MemberDTO dto1 = new MemberDTO();
        dto1.setId(id1);
        final MemberDTO dto2 = new MemberDTO();
        dto2.setId(id2);

        when(memberService.findByName(name1)).thenReturn(m1);
        when(memberService.findByName(name2)).thenReturn(m2);
        when(beanMappingService.mapTo(m1, MemberDTO.class)).thenReturn(dto1);
        when(beanMappingService.mapTo(m2, MemberDTO.class)).thenReturn(dto2);

        assertThat(memberFacade.findByName(name1)).hasFieldOrPropertyWithValue("id", id1);
        assertThat(memberFacade.findByName(name2)).hasFieldOrPropertyWithValue("id", id2);
        assertThat(memberFacade.findByName("Other string")).isNull();
    }
}
