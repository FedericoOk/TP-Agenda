package dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private String localidad;

}
