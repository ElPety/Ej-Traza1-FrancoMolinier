package entidades;
import java.lang.reflect.Method;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@ToString(exclude = "sucursales")

public class Empresa {
    private Long id;
    private String nombre;
    private String razonSocial;
    private Long cuil;
    private String logo;

    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();

}
