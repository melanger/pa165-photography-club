package cz.muni.fi.pa165.photographyclub.entity;

import cz.muni.fi.pa165.photographyclub.enums.Gender;
import cz.muni.fi.pa165.photographyclub.enums.UserRole;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Denis.Figula
 */
@Entity
@Table(name = "ClubMember")
public class Member implements Serializable, PhotoEntity {

    @OneToMany(mappedBy = "author")
    private List<Review> reviews;
    
    @OneToMany(mappedBy = "owner")
    private List<Equipment> equipment;
    
    @ManyToMany(mappedBy = "participants")
    private List<Tour> tours;

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = new LinkedList<>(tours);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private LocalDate birthDate;

    @Column
    private  String address;

    @Column
    private String motivation;

    @Column
    private String experience;

    @Column
    private Gender gender;

    @Column
    private String photo;
    
    @Column
    private UserRole userRole;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    
    @Column
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = LocalDate.from(birthDate);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Member)) return false;
        final Member member = (Member) o;
        return Objects.equals(this.id, member.id);
    }

    @Override
    public int hashCode() {
        int hash = 13;
        return hash + Objects.hashCode(this.id);
    }
}
