package cz.muni.fi.pa165.photographyclub.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.muni.fi.pa165.photographyclub.enums.Gender;
import cz.muni.fi.pa165.photographyclub.enums.UserRole;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Denis.Figula
 */
public class MemberDTO {

    private Long id;

    private String name;

    private LocalDate birthDate;

    private String address;

    private String motivation;

    private String experience;

    private Gender gender;

    private String photo;
    
    private String email;
    
    private String password;
    
    private UserRole userRole;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    
    @JsonBackReference(value="review-member")
    private List<ReviewDTO> reviews;
    
    @JsonBackReference(value="equipment-member")
    private List<EquipmentDTO> equipment;
    
    @JsonBackReference(value="tour-member")
    private List<TourDTO> tours;

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
        this.birthDate = birthDate;
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

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public List<EquipmentDTO> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<EquipmentDTO> equipment) {
        this.equipment = equipment;
    }

    public List<TourDTO> getTours() {
        return tours;
    }

    public void setTours(List<TourDTO> tours) {
        this.tours = tours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDTO memberDTO = (MemberDTO) o;
        if (id != null || memberDTO.getId() != null)
            return Objects.equals(id, memberDTO.getId());
        else
            return Objects.equals(name, memberDTO.getName()) && Objects.equals(birthDate, memberDTO.getBirthDate()) && Objects.equals(address, memberDTO.getAddress());
    }

    @Override
    public int hashCode() {
        return 69 + ((id == null) ? Objects.hash(name, birthDate, address) : Objects.hashCode(id));
    }
}
