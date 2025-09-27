package org.example;

import org.example.entidades.*;
import org.example.repositorios.InMemoryRepository;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main{
    public static void main(String[] args) {
        // Inicializar repositorios
        InMemoryRepository<Empresa> empresaRepository = new InMemoryRepository<>();
        System.out.println(" ================================PROBANDO SISTEMA ================================");
        País argentina = País.builder().nombre("Argentina").
                build();

        // Crear provincias y localidades
        Provincia buenosAires = Provincia.builder()
                .id(1L)
                .nombre("Buenos Aires")
                .build();

        Localidad caba = Localidad.builder()
                .id(1L)
                .nombre("CABA")
                .build();

        Domicilio domicilio1 = Domicilio.builder()
                .id(1L)
                .calle("Calle 1")
                .numero(100)
                .cp(1000)
                .build();

        Localidad laPlata = Localidad.builder()
                .id(2L)
                .nombre("La Plata")
                .build();

        Domicilio domicilio2 = Domicilio.builder()
                .id(2L)
                .calle("Calle 2")
                .numero(200)
                .cp(2000)
                .build();


        Provincia cordoba = Provincia.builder()
                .id(2L)
                .nombre("Córdoba")
                .build();

        Localidad cordobaCapital = Localidad.builder()
                .id(3L)
                .nombre("Córdoba Capital")
                .build();

        Domicilio domicilio3 = Domicilio.builder()
                .id(3L)
                .calle("Calle 3")
                .numero(300)
                .cp(3000)
                .build();


        Localidad villaCarlosPaz = Localidad.builder()
                .id(4L)
                .nombre("Villa Carlos Paz")
                .build();


        Domicilio domicilio4 = Domicilio.builder()
                .id(4L)
                .calle("Calle 4")
                .numero(400)
                .cp(4000)
                .build();

        // ESTABLECER RELACIONES
        // Agregar domicilios a localidades
                caba.getDomicilios().add(domicilio1);
                laPlata.getDomicilios().add(domicilio2);
                cordobaCapital.getDomicilios().add(domicilio3);
                villaCarlosPaz.getDomicilios().add(domicilio4);

                // Agregar localidades a provincias
                buenosAires.getLocalidades().add(caba);
                buenosAires.getLocalidades().add(laPlata);
                cordoba.getLocalidades().add(cordobaCapital);
                cordoba.getLocalidades().add(villaCarlosPaz);

                // Agregar provincias al país
                argentina.getProvincias().add(buenosAires);
                argentina.getProvincias().add(cordoba);

        // Crear sucursales Para buenos Aires
        Sucursal sucursal1 = Sucursal.builder()
                .id(1L)
                .nombre("Sucursal 1")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .esCasaMatriz(true)
                .domicilio(domicilio1)
                .build();

        Sucursal sucursal2 = Sucursal.builder()
                .id(2L)
                .nombre("Sucursal 2")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .esCasaMatriz(false)
                .domicilio(domicilio2)
                .build();

        // Crear Sucursales Para Cordoba

        Sucursal sucursal3 = Sucursal.builder()
                .id(3L)
                .nombre("Sucursal 3")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .esCasaMatriz(true)
                .domicilio(domicilio3)
                .build();

        Sucursal sucursal4 = Sucursal.builder()
                .id(4L)
                .nombre("Sucursal 4")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .esCasaMatriz(false)
                .domicilio(domicilio4)
                .build();

        // Crear empresas y asociar sucursales
        Empresa empresa1 = Empresa.builder()
                .nombre("Empresa 1")
                .razonSocial("Razon Social 1")
                .cuil(12345678901L)
                .sucursales(new HashSet<>(Set.of(sucursal1, sucursal2)))
                .build();

        Empresa empresa2 = Empresa.builder()
                .nombre("Empresa 2")
                .razonSocial("Razon Social 2")
                .cuil(22225678901L)
                .sucursales(new HashSet<>(Set.of(sucursal3, sucursal4)))
                .build();

        // Guardar empresas en el repositorio
        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);

        // Mostrar todas las empresas
        System.out.println("Todas las empresas:");
        List<Empresa> todasLasEmpresas = empresaRepository.findAll();
        todasLasEmpresas.forEach(System.out::println);

        // Buscar empresa por ID
        Optional<Empresa> empresaEncontrada = empresaRepository.findById(1L);
        empresaEncontrada.ifPresent(e -> System.out.println("Empresa encontrada por ID 1: " + e));

        // Buscar empresa por nombre
        List<Empresa> empresasPorNombre = empresaRepository.genericFindByField("nombre", "Empresa 1");
        System.out.println("Empresas con nombre 'Empresa 1':");
        empresasPorNombre.forEach(System.out::println);

        // Actualizar empresa por ID
        Empresa empresaActualizada = Empresa.builder()
                .id(1L)
                .nombre("Empresa 1 Actualizada")
                .razonSocial("Razon Social 1 Actualizada")
                .cuil(12345678901L)
                .sucursales(empresa1.getSucursales())
                .build();

        empresaRepository.genericUpdate(1L, empresaActualizada);
        Optional<Empresa> empresaVerificada = empresaRepository.findById(1L);
        empresaVerificada.ifPresent(e -> System.out.println("Empresa despues de la actualizacion: " + e));

        // Eliminar empresa por ID
        empresaRepository.genericDelete(1L);
        Optional<Empresa> empresaEliminada = empresaRepository.findById(1L);
        if (empresaEliminada.isEmpty()) {
            System.out.println("La empresa con ID 1 ha sido eliminada.");
        }

        // Mostrar todas las empresas restantes
        System.out.println("Todas las empresas despues de la eliminacion:");
        List<Empresa> empresasRestantes = empresaRepository.findAll();
        empresasRestantes.forEach(System.out::println);
        System.out.println("====================================Mostrar las sucursales de una empresa determinada====================================");
// Mostrar las sucursales de una empresa deerminada
        Optional<Empresa> empresa = empresaRepository.findById(2L);
        if (empresa.isPresent()) {
            System.out.println("Sucursales de la empresa con ID " + ":");
            Set<Sucursal> sucursales = empresa.get().getSucursales();
            sucursales.forEach(System.out::println);
        } else {
            System.out.println("Empresa con ID " + " no encontrada.");
        }


    }
}