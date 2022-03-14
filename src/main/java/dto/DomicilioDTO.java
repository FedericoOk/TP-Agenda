package dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class DomicilioDTO {

    @Id
    @GeneratedValue
    private int id;

    private String calle;
    private String altura;
    private String piso;
    private String depto;
    
    @OneToOne
    private LocalidadDTO localidad;

    public DomicilioDTO(String calle, String altura, String piso, String depto, LocalidadDTO localidad) {
        this.calle = calle;
        this.altura = altura;
        this.piso = piso;
        this.depto = depto;
        this.localidad = localidad;
    }

}
