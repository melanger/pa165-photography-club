package cz.muni.fi.pa165.photographyclub.dto;

import cz.muni.fi.pa165.photographyclub.enums.EquipmentType;
import java.util.Objects;

/**
 * DTO for creating Equipment.
 * @author Pavel Brousek
 */
public class EquipmentCreateDTO {
    private long ownerId;

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

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int hashCode() {
        return 83 * 5 + Objects.hash(ownerId, name, type);
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
        if (!Objects.equals(this.ownerId, other.getOwnerId())) {
            return false;
        }
        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }
        return Objects.equals(this.type, other.getType());
    }
    
    
}
