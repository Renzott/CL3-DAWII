package com.renzo.dawii.util;

import com.renzo.dawii.bean.*;
import com.renzo.dawii.repository.AuthorityRepository;
import com.renzo.dawii.service.DepartamentoService;
import com.renzo.dawii.service.EstadoCivilService;
import com.renzo.dawii.service.TipoEmpleadoService;
import com.renzo.dawii.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class DataLoader {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EstadoCivilService estadoCivilService;

    @Autowired
    private TipoEmpleadoService tipoEmpleadoService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @PostConstruct
    private void init(){

        Authority admin = new Authority();
        admin.setAuthority("ADMIN");

        Authority user = new Authority();
        user.setAuthority("USER");

        authorityRepository.save(admin);
        authorityRepository.save(user);

        String[] tipoEmp = {"Tiempo Completo","Parcial"};

        Arrays.stream(tipoEmp).forEach(e -> {
            TipoEmpleado bean = new TipoEmpleado();
            bean.setNombre(e);
            tipoEmpleadoService.setTipoEmpleado(bean);
        });

        String[] depart = {"Sistemas","Bases de Datos","Operaciones","Administracion"};

        Arrays.stream(depart).forEach(e -> {
            Departamento bean = new Departamento();
            bean.setNombre(e);
            departamentoService.setDepartamento(bean);
        });

        String[] estcil = {"Soltero","Casado"};

        Arrays.stream(estcil).forEach(e -> {
            EstadoCivil bean = new EstadoCivil();
            bean.setNombre(e);
            estadoCivilService.setEstadoCivil(bean);
        });

        Usuario u1 = new Usuario();
        u1.setAuthority(new HashSet<>(Arrays.asList(admin)));
        u1.setNombre("Paco");
        u1.setPassword("ABC123");
        u1.setApellidoMaterno("Arrez");
        u1.setApellidoPaterno("Maca");
        u1.setDireccion("AV. CALLE 11");
        u1.setTelefono(123456789);
        u1.setEmail("mail@hotmail.com");
        u1.setFechaNacimiento(new Date());
        System.out.println(tipoEmpleadoService.getAll().get(0).getId());
        u1.setTipoEmpleado(tipoEmpleadoService.getAll().get(0));
        u1.setDepartamento(departamentoService.getAll().get(0));
        u1.setEstadoCivil(estadoCivilService.getAll().get(0));

        usuarioService.adminUsuario(u1);
    }

}
