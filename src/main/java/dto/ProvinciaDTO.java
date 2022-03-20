package dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class ProvinciaDTO {

    @Id
    @GeneratedValue
    private int id;

    private String nombre;

    @OneToMany(cascade = CascadeType.ALL)
    List<LocalidadDTO> localidades;

    @ManyToOne
    private PaisDTO pais;

    public ProvinciaDTO(String nombre, PaisDTO pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
}
