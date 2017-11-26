/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.ServiceTestApplicationContext;
import cz.muni.fi.pa165.photographyclub.dao.MemberDao;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import java.time.LocalDate;
import java.time.Month;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author matus
 */
@ContextConfiguration(classes = ServiceTestApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MemberServiceTest extends AbstractTestNGSpringContextTests{
 
    @Mock
    private MemberDao memberDao;
    
    @InjectMocks
    private MemberServiceImpl memberService;
    
    private Member member;
    
    @BeforeClass
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void setupMethod(){
        member = new Member();
        member.setName("John Smith");
        member.setBirthDate(LocalDate.of(1990, Month.JUNE, 15));        
    }
        
    @Test
    public void findByIdTest(){
       memberDao.create(member);
       
       Assert.assertEquals(memberService.findById(member.getId()), member);
    }
    
    @Test
    public void createTest(){
        memberService.create(member);
        
        Assert.assertEquals(memberDao.findById(member.getId()), member);
    }
    
    @Test
    public void findAllTest(){
        memberService.create(member);
        Member member2 = new Member();
        member2.setName("Test");
        member2.setBirthDate(LocalDate.of(1990, Month.MARCH, 5));
        memberService.create(member2);
        
        Assert.assertEquals(memberService.findAll().size(), 2);
    }
    
    @Test
    public void removeTest(){
        memberService.create(member);
        memberService.remove(member);
        
        Assert.assertEquals(memberDao.findAll().size(), 0);        
    }
    
    @Test
    public void findByNameTest(){
        memberService.create(member);
        
        Assert.assertEquals(memberService.findByName("John Smith"), member);
    }
    
}
