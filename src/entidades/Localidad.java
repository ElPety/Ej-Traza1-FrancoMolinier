package entidades;
import java.lang.reflect.Method;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "provincia")
@SuperBuilder

public class Localidad {
    private Long id;
    private String nombre;
    private Provincia provincia;
}
