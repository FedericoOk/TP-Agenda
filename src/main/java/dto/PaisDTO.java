package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PaisDTO {

    @Id
    @GeneratedValue
    private int id;

    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
    @OrderBy(value = "nombre")
    private List<ProvinciaDTO> provincias = new ArrayList<>(); // TODO: probar con SortedSet -> TreeSet

    public PaisDTO(String nombre) {
        this.nombre = nombre;
    }

    public void addProvincia(ProvinciaDTO provincia) {
        if (provincia != null && !provincias.contains(provincia)) { // TODO: ¿cambiar List por Set?
            provincias.add(provincia);
            provincia.setPais(this);
            // int idx = Collections.binarySearch(provincias, provincia, (p1, p2) -> p1.getNombre().compareTo(p2.getNombre()));
            // provincias.add(idx, provincia);
        }
        ordenarProvinciasPorNombre(); // Esto de momento así hasta que use SortedSet
    }

    public void removeProvincia(ProvinciaDTO provincia) {
        this.provincias.remove(provincia);
        provincia.setPais(null);
    }

    public void ordenarProvinciasPorNombre() {
        Collections.sort(provincias, (p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (o ==  this)
            return true;
        
        if (!(o instanceof PaisDTO))
            return false;
        
        PaisDTO pais = (PaisDTO) o;

        return pais.getId() == this.id;
    }
    
}
