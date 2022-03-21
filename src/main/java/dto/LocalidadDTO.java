package dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
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
        this.provincia.addLocalidad(this);
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (o ==  this)
            return true;
        
        if (!(o instanceof LocalidadDTO))
            return false;
        
        LocalidadDTO localidad = (LocalidadDTO) o;

        return localidad.getId() == this.id;
    }
    
}
