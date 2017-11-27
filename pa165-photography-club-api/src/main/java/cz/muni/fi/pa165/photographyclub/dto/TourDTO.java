package cz.muni.fi.pa165.photographyclub.dto;

import cz.muni.fi.pa165.photographyclub.enums.TourTheme;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * DTO for trasnfering Tour.
 * @author Matus Kravec.
 */
public class TourDTO {
     
    private Long Id;
    
    private String name;
    
    private TourTheme theme;
    
    private LocalDate date;
    
    private List<ReviewDTO> reviews;    
    
    private List<MemberDTO> participants;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

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

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public List<MemberDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<MemberDTO> participants) {
        this.participants = participants;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.theme);
        hash = 59 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TourDTO other = (TourDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.theme != other.theme) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
 
    
    
}
