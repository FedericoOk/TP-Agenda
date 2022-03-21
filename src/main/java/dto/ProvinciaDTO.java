package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ProvinciaDTO {

    @Id
    @GeneratedValue
    private int id;

    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provincia")
    @OrderBy(value = "nombre")
    List<LocalidadDTO> localidades = new ArrayList<>();

    @ManyToOne
    private PaisDTO pais;

    public ProvinciaDTO(String nombre, PaisDTO pais) {
        this.nombre = nombre;
        this.pais = pais;
        this.pais.addProvincia(this);
    }

    public void addLocalidad(LocalidadDTO localidad) {
        if (localidad != null && !localidades.contains(localidad)) { // TODO: ¿cambiar List por Set?
            localidades.add(localidad);
            localidad.setProvincia(this);
            // int idx = Collections.binarySearch(localidades, localidad, (l1, l2) -> l1.getNombre().compareTo(l2.getNombre()));
            // localidades.add(idx, localidad);
        }
        ordenarLocalidadesPorNombre(); // Esto de momento así hasta que use SortedSet
    }

    public void removeLocalidad(LocalidadDTO localidad) {
        this.localidades.remove(localidad);
        localidad.setProvincia(null);
    }

    public void ordenarLocalidadesPorNombre() {
        Collections.sort(localidades, (l1, l2) -> l1.getNombre().compareToIgnoreCase(l2.getNombre()));
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (o ==  this)
            return true;
        
        if (!(o instanceof ProvinciaDTO))
            return false;
        
        ProvinciaDTO provincia = (ProvinciaDTO) o;

        return provincia.getId() == this.id;
    }
    
}
