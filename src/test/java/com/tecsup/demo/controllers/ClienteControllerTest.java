package com.tecsup.demo.controllers;

import com.tecsup.demo.modelo.entidades.Cliente;
import com.tecsup.demo.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private SessionStatus sessionStatus;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarClientes() {
        // Datos simulados
        List<Cliente> clientes = Arrays.asList(new Cliente(), new Cliente());
        when(clienteService.listar()).thenReturn(clientes);

        // Llamar al método
        String viewName = clienteController.listar(model);

        // Validaciones
        verify(clienteService).listar();
        verify(model).addAttribute("titulo", "Listado de Clientes MotorMarket");
        verify(model).addAttribute("clientes", clientes);
        assertEquals("listarClientesView", viewName);
    }

    @Test
    void testCrearCliente() {
        // Llamar al método
        Map<String, Object> mockMap = mock(Map.class);
        String viewName = clienteController.crear(mockMap);

        // Validaciones
        verify(mockMap).put(eq("cliente"), any(Cliente.class));
        verify(mockMap).put("titulo", "Formulario de Cliente");
        assertEquals("formClienteView", viewName);
    }

    @Test
    void testGuardarCliente() {
        // Configurar datos simulados
        Cliente cliente = new Cliente();
        when(bindingResult.hasErrors()).thenReturn(false);

        // Llamar al método
        String viewName = clienteController.guardar(cliente, bindingResult, model, sessionStatus);

        // Validaciones
        verify(clienteService).grabar(cliente);
        verify(sessionStatus).setComplete();
        assertEquals("redirect:/listarClientes", viewName);
    }

    @Test
    void testGuardarClienteConErrores() {
        // Configurar datos simulados
        Cliente cliente = new Cliente();
        when(bindingResult.hasErrors()).thenReturn(true);

        // Llamar al método
        String viewName = clienteController.guardar(cliente, bindingResult, model, sessionStatus);

        // Validaciones
        verify(model).addAttribute("titulo", "Formulario de Cliente");
        assertEquals("formClienteView", viewName);
    }

    @Test
    void testEliminarCliente() {
        // Llamar al método
        String viewName = clienteController.eliminar(1);

        // Validaciones
        verify(clienteService).eliminar(1);
        assertEquals("redirect:/listarClientes", viewName);
    }
}
