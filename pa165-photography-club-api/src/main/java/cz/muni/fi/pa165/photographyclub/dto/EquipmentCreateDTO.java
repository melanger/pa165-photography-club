package cz.muni.fi.pa165.photographyclub.dto;

import cz.muni.fi.pa165.photographyclub.enums.EquipmentType;
import java.util.Objects;

/**
 * DTO for creating Equipment.
 * @author Pavel Brousek
 */
public class EquipmentCreateDTO {
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
        return 83 * 5 + Objects.hash(owner, name, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof EquipmentCreateDTO)) {
            return false;
        }
        final EquipmentCreateDTO other = (EquipmentCreateDTO) obj;
        if (!Objects.equals(this.owner, other.getOwner())) {
            return false;
        }
        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }
        return Objects.equals(this.type, other.getType());
    }
    
    
}
