package cz.muni.fi.pa165.photographyclub.entity;

import cz.muni.fi.pa165.photographyclub.enums.TourTheme;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * @author Pavel Brousek
 */
@Entity
public class Tour implements Serializable, PhotoEntity {

    @ManyToMany
    private List<Member> participants;

    public List<Member> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Member> participants) {
        this.participants = new LinkedList<>(participants);
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false)
    @NotNull
    private String name;
    
    @Column
    private TourTheme theme;
    
    @Column(nullable=false)
    @NotNull
    private LocalDate date;
    
    @OneToMany(mappedBy = "tour")
    private List<Review> reviews;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TourTheme getTheme() {
        return theme;
    }

    public void setTheme(TourTheme theme) {
        this.theme = theme;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof Tour)) {
            return false;
        }
        final Tour other = (Tour) object;
        return Objects.equals(this.id, other.getId());
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.photographyclub.Tour[ id=" + id + ",name=" + name + " ]";
    }
    
}
