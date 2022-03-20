package dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PaisDTO {

    @Id
    @GeneratedValue
    private int id;

    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
    private List<ProvinciaDTO> provincias;

    public PaisDTO(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
}
