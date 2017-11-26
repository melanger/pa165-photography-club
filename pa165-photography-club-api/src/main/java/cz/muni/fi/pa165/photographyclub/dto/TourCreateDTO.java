package cz.muni.fi.pa165.photographyclub.dto;

import cz.muni.fi.pa165.photographyclub.enums.TourTheme;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Matus Kravec.
 */
public class TourCreateDTO {
    
    @NotNull
    @Size(min = 5, max = 100)
    private String name;
    
    @NotNull
    private TourTheme theme;
    
    @NotNull
    private LocalDate date;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.theme);
        hash = 89 * hash + Objects.hashCode(this.date);
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
        final TourCreateDTO other = (TourCreateDTO) obj;
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

    @Override
    public String toString() {
        return "TourCreateDTO{" + "name=" + name + ", theme=" + theme +
                ", date=" + date + '}';
    }    
    
}