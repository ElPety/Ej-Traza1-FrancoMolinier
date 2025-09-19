import entidades.*;
import repositorio.Repositorio;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        // Inicializar repositorios
        Repositorio<Empresa> empresaRepository = new Repositorio();

        //Creamos el pais argentina
        Pais argentina = Pais.builder().nombre("Argentina").
                build();


        // Creamos  la provincia BSAS y lo relacionamos con Arg
        Provincia buenosAires = Provincia.builder()
                .id(1L)
                .nombre("Buenos Aires")
                .pais(argentina)
                .build();

        // Creamos la localidad de caba y establecemos relacion con BSAS
        Localidad caba = Localidad.builder()
                .id(1L)
                .nombre("CABA")
                .provincia(buenosAires)
                .build();

        //Creamos domicilio para caba
        Domicilio domiciliocaba = Domicilio.builder()
                .id(1L)
                .calle("La Rioja")
                .numero(1800)
                .cp(9002)
                .localidad(caba)
                .build();

        //Creamos otra localidad: La Plata
        Localidad laPlata = Localidad.builder()
                .id(2L)
                .nombre("La Plata")
                .provincia(buenosAires)
                .build();

        //Creamos domicilio para La Plata
        Domicilio domicilioLP = Domicilio.builder()
                .id(2L)
                .calle("Los Olivos")
                .numero(3131)
                .cp(9950)
                .localidad(laPlata)
                .build();

        //Creamos Cordoba como provincia y lo relacionamos con ARG
        Provincia cordoba = Provincia.builder()
                .id(3L)
                .nombre("Cordoba")
                .pais(argentina)
                .build();

        //Creamos localiudad CordobaCapital
        Localidad cordobacapital = Localidad.builder()
                .id(3L)
                .nombre("Cordoba Capital")
                .provincia(cordoba)
                .build();

        //Creamos domicilio para cordoba capital
        Domicilio domicilioCC = Domicilio.builder()
                .id(3L)
                .calle("Fernet")
                .numero(7030)
                .cp(5050)
                .localidad(cordobacapital)
                .build();

        //Creamos localiudad Villa Carlos Paz en Cordoba
         Localidad villacarlospaz = Localidad.builder()
                .id(4L)
                .nombre("Villa Carlos Paz")
                .provincia(cordoba)
                .build();

        //Creamos domicilio para Villa Carlos Paz
        Domicilio domicilioVCP = Domicilio.builder()
                .id(4L)
                .calle("Egresados")
                .numero(2017)
                .cp(41217)
                .localidad(villacarlospaz)
                .build();


        //Creamos Sucursal1 con domicilio en CABA
        Sucursal sucursal1 = Sucursal.builder()
                .id(1L)
                .nombre("Sucursal1")
                .horarioApertura(LocalTime.of(7, 0))
                .horarioApertura(LocalTime.of(13,0))
                .esCasaMatriz(true)
                .domicilio(domiciliocaba)
                .build();

        //Creamos Sucursal2 con domicilio en La Plata
        Sucursal sucursal2 = Sucursal.builder()
                .id(2L)
                .nombre("Sucursal2")
                .horarioApertura(LocalTime.of(11, 0))
                .horarioApertura(LocalTime.of(16,0))
                .esCasaMatriz(true)
                .domicilio(domicilioLP)
                .build();

        //Creamos Sucursal3 con domicilio en Cordoba Capital
        Sucursal sucursal3 = Sucursal.builder()
                .id(3L)
                .nombre("Sucursal3")
                .horarioApertura(LocalTime.of(6, 30))
                .horarioApertura(LocalTime.of(12,30))
                .esCasaMatriz(true)
                .domicilio(domicilioCC)
                .build();

        //Creamos Sucursal4 con domicilio en Villa Carlos Paz
        Sucursal sucursal4 = Sucursal.builder()
                .id(4L)
                .nombre("Sucursal4")
                .horarioApertura(LocalTime.of(7, 0))
                .horarioApertura(LocalTime.of(13,0))
                .esCasaMatriz(true)
                .domicilio(domicilioVCP)
                .build();

        //Creamos empresa1
        Empresa empresa1 = Empresa.builder()
                .id(1L)
                .nombre("Empresa1")
                .razonSocial("Razon social 1")
                .cuil(12313156456L)
                .sucursales(new HashSet<>(Set.of(sucursal1, sucursal2)))
                .build();

        //Creamos empresa2
        Empresa empresa2 = Empresa.builder()
                .id(2L)
                .nombre("Empresa2")
                .razonSocial("Razon social 2")
                .cuil(848464646L)
                .sucursales(new HashSet<>(Set.of(sucursal1, sucursal2)))
                .build();

        //Asignacion de Sucursales a Empresas
        sucursal1.setEmpresa(empresa1);
        sucursal2.setEmpresa(empresa1);
        sucursal3.setEmpresa(empresa2);
        sucursal4.setEmpresa(empresa2);

        //Añadimos las empresas al repositorio
        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);

        //Mostramos todas las empresas
        System.out.println("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("    Listado de Empresas: ");
        List<Empresa> listadoempresas = empresaRepository.findAll();
        listadoempresas.forEach(e -> System.out.println("\t\t" + e));
        System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- \n");

        //Buscar empresa por id
        Optional<Empresa> empresaEncontrada = empresaRepository.findById(1L);
        System.out.println("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        empresaEncontrada.ifPresent(e -> System.out.println("   La empresa con ID = 1 es : " + e));
        System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");


        // Buscar empresa por nombre
        List<Empresa> empresasPorNombre = empresaRepository.genericFindByField("nombre", "Empresa1");
        System.out.println("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("    La empresa con nombre Empresa1 : " );
        empresasPorNombre.forEach(e -> System.out.println("\t\t" + e));
        System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");


        // Actualizar empresa por ID(Podremos modificar todo menos el id)
        Empresa empresaActualizada = Empresa.builder()
                .id(1L)
                .nombre("Empresa 1 Actualizada")
                .razonSocial("Razon Social 1 Actualizada")
                .cuil(12313156456L)
                .sucursales(empresa1.getSucursales())
                .build();

        //Mostramos la empresa que ha sido modificada
        empresaRepository.genericUpdate(1L, empresaActualizada);
        Optional<Empresa> empresaVerificada = empresaRepository.findById(1L);
        System.out.println("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        empresaVerificada.ifPresent(e -> System.out.println("   Luego de la modificacion la empresa1 quedara de la siguiente manera: " + e));
        System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

        // En Caso de Querer eliminar una empresa:
        empresaRepository.genericDelete(1L);
        Optional<Empresa> empresaEliminada = empresaRepository.findById(1L);
        if (empresaEliminada.isEmpty()) {
            System.out.println("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("    La empresa con ID=1 ha sido eliminada correctamente");
            System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        }

        // Mostrar todas las empresas restantes
        System.out.println("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("    Todas las empresas después de la eliminación:");
        List<Empresa> empresasRestantes = empresaRepository.findAll();
        empresasRestantes.forEach(e -> System.out.println("\t\t" + e));
        System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");


        //Mostramos las sucursales de determinadas empresas:
        System.out.println("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("    Mostrar las sucursales de una empresa determinada");
        Optional<Empresa> empresa = empresaRepository.findById(2L);
        if (empresa.isPresent()) {
            System.out.println("    Sucursales de la empresa con ID " + empresa.get().getId() + ":");
            Set<Sucursal> sucursales = empresa.get().getSucursales();
            sucursales.forEach(e -> System.out.println("\t\t" + e));
        } else {
            System.out.println("    Empresa con ID " + empresa.get().getId() + " no encontrada.");
        }
        System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

        System.out.println("---------- Codigo Por Franco Molinier ----------");
    }
}