package com.renzo.dawii.controller;

import com.renzo.dawii.bean.Authority;
import com.renzo.dawii.bean.EstadoCivil;
import com.renzo.dawii.bean.Usuario;
import com.renzo.dawii.service.DepartamentoService;
import com.renzo.dawii.service.EstadoCivilService;
import com.renzo.dawii.service.TipoEmpleadoService;
import com.renzo.dawii.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EstadoCivilService estadoCivilService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private TipoEmpleadoService tipoEmpleadoService;

    @GetMapping({"/", "/login"})
    public String getIndex() {
        return "login";
    }

    @GetMapping("/list")
    public String getList(Authentication authentication, Model model) {

        model.addAttribute("list",usuarioService.findAll());

        // No puedo encontrar los Authority de forma normal, estoy dando esta solucion negativa, arreglarlo despues.
        Authority authority = usuarioService.findbyName(authentication.getName()).getAuthority().stream().findFirst().get();
        if (authority.getAuthority().equals("ADMIN")){

            model.addAttribute("options",true);
        }

        return "list";
    }

    @GetMapping("/save/{id}")
    public String getSave(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("listEstCil", estadoCivilService.getAll());
        model.addAttribute("listDepart", departamentoService.getAll());
        model.addAttribute("listTipoEmp",tipoEmpleadoService.getAll());

        if (id != null && id != 0) {
            model.addAttribute("usuario", usuarioService.getUsuario(id));
        } else {
            model.addAttribute("usuario", new Usuario());
        }

        return "save";
    }

    @PostMapping("/save")
    public String save(Usuario user, Model model) {
        System.out.println(user.toString());

        usuarioService.saveUser(user);

        return "redirect:/list";
    }

}
