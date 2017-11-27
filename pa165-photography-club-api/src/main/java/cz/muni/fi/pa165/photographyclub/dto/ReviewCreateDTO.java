package cz.muni.fi.pa165.photographyclub.dto;

import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DTO for creating Review.
 * @author Matus Kravec.
 */
public class ReviewCreateDTO {
    
    @NotNull
    private Long tourId;
    
    @NotNull
    private Long authorId;
    
    @NotNull
    private String comment;
    
    @NotNull
    @Min(0)
    @Max(10)
    private int rating;

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.tourId);
        hash = 17 * hash + Objects.hashCode(this.authorId);
        hash = 17 * hash + Objects.hashCode(this.comment);
        hash = 17 * hash + this.rating;
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
        final ReviewCreateDTO other = (ReviewCreateDTO) obj;
        if (this.rating != other.rating) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.tourId, other.tourId)) {
            return false;
        }
        if (!Objects.equals(this.authorId, other.authorId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReviewCreateDTO{" + "tourId=" + tourId + ", authorId=" +
                authorId + ", comment=" + comment + ", rating=" + rating + '}';
    }

}
