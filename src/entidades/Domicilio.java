package entidades;
import java.lang.reflect.Method;


import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "localidad")
@SuperBuilder

public class Domicilio {
    private Long id;
    private String calle;
    private Integer numero;
    private Integer cp;

    private Localidad localidad;

}
