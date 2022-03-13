package dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TipoContacto {

    @Id
    @GeneratedValue
    private int id;

    private String description;

    public TipoContacto(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
    
}
