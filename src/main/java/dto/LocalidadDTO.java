package dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class LocalidadDTO {
    
    @Id
    @GeneratedValue
    private int id;

    private String nombre;

    @ManyToOne
    private ProvinciaDTO provincia;

    public LocalidadDTO(String nombre, ProvinciaDTO provincia) {
        this.nombre = nombre;
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
}
