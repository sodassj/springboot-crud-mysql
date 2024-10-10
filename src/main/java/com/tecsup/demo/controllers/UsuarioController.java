package com.tecsup.demo.controllers;

import com.tecsup.demo.modelo.entidades.Usuario;
import com.tecsup.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/listarUsuarios", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Usuarios MotorMarket");
        model.addAttribute("usuarios", usuarioService.listar());
        return "listarUsuariosView"; // Asegúrate de que esta vista exista
    }

    @RequestMapping(value = "/formUsuario")
    public String crear(Map<String, Object> model) {
        Usuario usuario = new Usuario();
        model.put("usuario", usuario);
        model.put("titulo", "Formulario de Usuario");
        return "formUsuarioView"; // Asegúrate de que esta vista exista
    }

    @RequestMapping(value = "/formUsuario/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Usuario usuario = null;

        if (id > 0) {
            usuario = usuarioService.buscar(id);
        } else {
            return "redirect:/listarUsuarios";
        }
        model.put("usuario", usuario);
        model.put("titulo", "Editar Usuario");
        return "formUsuarioView"; // Asegúrate de que esta vista exista
    }

    @RequestMapping(value = "/formUsuario", method = RequestMethod.POST)
    public String guardar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Usuario");
            return "formUsuarioView"; // Asegúrate de que esta vista exista
        }
        usuarioService.grabar(usuario);
        status.setComplete();
        return "redirect:/listarUsuarios";
    }

    @RequestMapping(value = "/eliminarUsuario/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            usuarioService.eliminar(id);
        }
        return "redirect:/listarUsuarios";
    }
    // Ver alumnos
    @GetMapping("/usuario")
    public String ver(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("titulo", "Lista de usuarios");
        return "usuario/ver"; // Asegúrate de que esta vista exista en la carpeta correcta
    }

    @GetMapping("/usuario/ver")
    public String verUsuarios(@RequestParam(required = false) String format, Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("titulo", "Lista de usuarios");

        if ("pdf".equals(format)) {
            return "usuario/ver.pdf";
        } else if ("xlsx".equals(format)) {
            return "usuario/ver.xlsx";
        }

        return "usuario/ver";
    }


    // Manejo de errores
    @GetMapping("/error")
    public String error() {
        return "error"; // Asegúrate de que este archivo HTML exista
    }
}
