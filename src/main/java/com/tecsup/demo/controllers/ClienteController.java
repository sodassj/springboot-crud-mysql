package com.tecsup.demo.controllers;

import com.tecsup.demo.modelo.entidades.Cliente;
import com.tecsup.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/listarClientes", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Clientes MotorMarket");
        model.addAttribute("clientes", clienteService.listar());
        return "listarClientesView"; // Asegúrate de que esta vista exista
    }

    @RequestMapping(value = "/formCliente")
    public String crear(Map<String, Object> model) {
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");
        return "formClienteView"; // Asegúrate de que esta vista exista
    }

    @RequestMapping(value = "/formCliente/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Cliente cliente = null;

        if (id > 0) {
            cliente = clienteService.buscar(id);
        } else {
            return "redirect:/listarClientes";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");
        return "formClienteView"; // Asegúrate de que esta vista exista
    }

    @RequestMapping(value = "/formCliente", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Cliente");
            return "formClienteView"; // Asegúrate de que esta vista exista
        }
        clienteService.grabar(cliente);
        status.setComplete();
        return "redirect:/listarClientes";
    }

    @RequestMapping(value = "/eliminarCliente/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            clienteService.eliminar(id);
        }
        return "redirect:/listarClientes";
    }

    // Ver clientes
    @GetMapping("/cliente")
    public String ver(Model model) {
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("titulo", "Lista de Clientes");
        return "cliente/ver"; // Asegúrate de que esta vista exista en la carpeta correcta
    }

    @GetMapping("/cliente/ver")
    public String verClientes(@RequestParam(required = false) String format, Model model) {
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("titulo", "Lista de Clientes");

        if ("pdf".equals(format)) {
            return "cliente/ver.pdf"; // Asegúrate de que este archivo exista
        } else if ("xlsx".equals(format)) {
            return "cliente/ver.xlsx"; // Asegúrate de que este archivo exista
        }

        return "cliente/ver";
    }


}
