package com.tecsup.demo.controllers;

import com.tecsup.demo.modelo.entidades.Cochesemi;
import com.tecsup.demo.services.CochesemiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("cochesemi")
public class CochesemiController {

    @Autowired
    private CochesemiService cochesemiService;

    @RequestMapping(value = "/listarCochesemi", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Coches MotorMarket");
        model.addAttribute("cochesemiList", cochesemiService.listar());
        return "listarCochesemiView"; // Asegúrate de que esta vista exista
    }

    @RequestMapping(value = "/formCochesemi", method = RequestMethod.GET)
    public String crear(Map<String, Object> model) {
        Cochesemi cochesemi = new Cochesemi();
        model.put("cochesemi", cochesemi);
        model.put("titulo", "Formulario de Coche");
        return "formCochesemiView"; // Asegúrate de que esta vista exista
    }

    @RequestMapping(value = "/formCochesemi/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Cochesemi cochesemi = null;

        if (id > 0) {
            cochesemi = cochesemiService.buscar(id);
        } else {
            return "redirect:/listarCochesemi";
        }
        model.put("cochesemi", cochesemi);
        model.put("titulo", "Editar Coche");
        return "formCochesemiView"; // Asegúrate de que esta vista exista
    }

    @RequestMapping(value = "/formCochesemi", method = RequestMethod.POST)
    public String guardar(@Valid @ModelAttribute("cochesemi") Cochesemi cochesemi,
                          BindingResult result,
                          Model model,
                          SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Coche");
            return "formCochesemiView"; // Asegúrate de que esta vista exista
        }

        // Guardar y redirigir
        cochesemiService.grabar(cochesemi);
        status.setComplete();
        return "redirect:/listarCochesemi";
    }

    @RequestMapping(value = "/eliminarCochesemi/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            cochesemiService.eliminar(id);
        }
        return "redirect:/listarCochesemi";
    }

    @GetMapping("/cochesemi")
    public String ver(Model model) {
        model.addAttribute("cochesemi", cochesemiService.listar());
        model.addAttribute("titulo", "Lista de Coches");
        return "cochesemi/ver"; // Asegúrate de que esta vista exista en la carpeta correcta
    }

    @GetMapping("/cochesemi/ver")
    public String verCochesemi(@RequestParam(required = false) String format, Model model) {
        model.addAttribute("cochesemi", cochesemiService.listar());
        model.addAttribute("titulo", "Lista de Coches");

        if ("pdf".equals(format)) {
            return "cochesemi/ver.pdf";
        } else if ("xlsx".equals(format)) {
            return "cochesemi/ver.xlsx";
        }

        return "cochesemi/ver";
    }
}
