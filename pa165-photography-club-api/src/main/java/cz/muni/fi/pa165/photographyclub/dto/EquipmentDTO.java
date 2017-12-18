package cz.muni.fi.pa165.photographyclub.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import cz.muni.fi.pa165.photographyclub.enums.EquipmentType;
import java.util.Objects;

/**
 * DTO for trasnfering Equipment.
 * @author Pavel Brousek
 */
public class EquipmentDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @JsonManagedReference
    private MemberDTO owner;

    private String name;
    
    private EquipmentType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

    public MemberDTO getOwner() {
        return owner;
    }

    public void setOwner(MemberDTO owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 83 * 5;
        if (id != null)
            hash += Objects.hashCode(id);
        else
            hash += Objects.hash(owner, name, type);
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
        if (!(obj instanceof EquipmentDTO)) {
            return false;
        }
        final EquipmentDTO other = (EquipmentDTO) obj;
        if (id != null || other.getId() != null) {
            return Objects.equals(this.id, other.getId());
        }
        if (!Objects.equals(this.owner, other.getOwner())) {
            return false;
        }
        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }
        return Objects.equals(this.type, other.getType());
    }
    
    
}
