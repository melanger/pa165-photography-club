package cz.muni.fi.pa165.photographyclub.sample;

import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import cz.muni.fi.pa165.photographyclub.enums.EquipmentType;
import cz.muni.fi.pa165.photographyclub.enums.Gender;
import cz.muni.fi.pa165.photographyclub.enums.TourTheme;
import cz.muni.fi.pa165.photographyclub.enums.UserRole;
import cz.muni.fi.pa165.photographyclub.service.EquipmentService;
import cz.muni.fi.pa165.photographyclub.service.MemberService;
import cz.muni.fi.pa165.photographyclub.service.ReviewService;
import cz.muni.fi.pa165.photographyclub.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Loads some sample data to populate the eshop database.
 *
 * @author Pavel Brousek
 * @author Denis Figula
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {
    public static final String JPEG = "image/jpeg";
    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private TourService tourService;

    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        Review positive = review("great",10);
        Review neutral = review("ok",5);
        Review negative = review("bad",2);
        Review reviewsLouvre[] = {positive,neutral};
        Review reviewsUfizzi[] = {negative};
        log.info("Reviews loaded");
        Tour louvre = tour("Louvre",LocalDate.of(2015,5,5),TourTheme.PORTRAITS, Arrays.asList(reviewsLouvre));
        Tour ufizzi = tour("Ufizzi",LocalDate.of(2016,3,6),TourTheme.LANDSCAPE,Arrays.asList(reviewsUfizzi));
        Tour ufizziArr[] = {ufizzi};
        Tour allArr[] = {louvre,ufizzi};
        log.info("Tours loaded");
        Equipment lens = equipment("Nikon 4x",EquipmentType.LENS);
        Equipment camera = equipment("CanonG6",EquipmentType.CAMERA);
        Equipment lighting = equipment("MikonaZZ",EquipmentType.LIGHTNING);
        Equipment flash = equipment("Machong", EquipmentType.FLASH);
        Equipment camera2 = equipment("KODAK2X",EquipmentType.CAMERA);
        Equipment antonEq[] = {lens,camera,lighting};
        Equipment benEq[] = {flash,camera2};
        log.info("Equipments loaded");
        Member anton = member(Arrays.asList(allArr),Arrays.asList(reviewsLouvre),"Anton","to be the best","moderate",LocalDate.of(1995,11,9),
                Gender.MALE,"photolink",UserRole.ADMINISTRATOR,"unknown","anton@mail","AntonIsBest",Arrays.asList(antonEq));
        Member ben = member(Arrays.asList(ufizziArr),Arrays.asList(reviewsUfizzi),"Ben","for fun","beginner",LocalDate.of(1998,3,16),
                Gender.MALE,"profilepic",UserRole.USER,"address 11 Brno","ben@mail.com","BenisBen",Arrays.asList(benEq));
        Member cyril = member(Collections.emptyList(),Collections.emptyList(),"Cyril","want to try","none",LocalDate.of(1999,5,28),
                Gender.MALE,"link",UserRole.USER,"V Praze blaze","cyrul@mail","Cyrilko",Collections.emptyList());
        log.info("Members loaded");
    }

    private Review review(String commment, int rating){
        Review review = new Review();
        review.setRating(rating);
        review.setComment(commment);
        reviewService.create(review);
        return review;
    }

    private Member member(List<Tour> tours, List<Review> reviews, String name, String motivation, String experience, LocalDate birthdate, Gender gender, String photo,
                          UserRole userRole, String address, String email, String password,List<Equipment> equipments){
        Member member = new Member();
        member.setName(name);
        member.setAddress(address);
        member.setBirthDate(birthdate);
        member.setEmail(email);
        member.setEquipment(equipments);
        member.setGender(gender);
        member.setExperience(experience);
        member.setMotivation(motivation);
        member.setPhoto(photo);
        member.setReviews(reviews);
        member.setTours(tours);
        member.setPassword(password);
        member.setUserRole(userRole);
        memberService.create(member);
        return member;
    }


    private Tour tour(String name, LocalDate date, TourTheme theme, List<Review> reviews){
        Tour tour = new Tour();
        tour.setTheme(theme);
        tour.setDate(date);
        tour.setName(name);
        tour.setReviews(reviews);
        tourService.create(tour);
        return tour;
    }

    private Equipment equipment(String name, EquipmentType type){
        Equipment equipment = new Equipment();
        equipment.setType(type);
        equipment.setName(name);
        equipmentService.create(equipment);
        return equipment;
    }

}

