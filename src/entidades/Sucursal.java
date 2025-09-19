package entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.lang.reflect.Method;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "empresa")
@SuperBuilder

public class Sucursal {
    private Long id;
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean esCasaMatriz;
    private Domicilio domicilio;
    private Empresa empresa;
}
