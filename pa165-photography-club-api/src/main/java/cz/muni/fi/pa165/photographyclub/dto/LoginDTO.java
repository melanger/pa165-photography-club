package cz.muni.fi.pa165.photographyclub.dto;

import java.util.Objects;

/**
 * DTO for login info.
 * @author Pavel Brousek
 */
public class LoginDTO {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + Objects.hashCode(this.password);
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
        final LoginDTO other = (LoginDTO) obj;
        if (!Objects.equals(this.email, other.getEmail())) {
            return false;
        }
        return Objects.equals(this.password, other.getPassword());
    }
    
    
}
