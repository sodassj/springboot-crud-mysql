package com.tecsup.demo.services;

import com.tecsup.demo.modelo.daos.ClienteRepository;
import com.tecsup.demo.modelo.entidades.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente = new Cliente(
                1,
                "Juan Perez",
                "Av. Siempre Viva 123",
                "987654321",
                "juan.perez@example.com",
                new Date(),
                "Toyota, Nissan"
        );
    }

    @Test
    void testListar() {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));

        List<Cliente> clientes = clienteService.listar();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        assertEquals(cliente, clientes.get(0));

        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testGrabar() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        clienteService.grabar(cliente);

        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testBuscar() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        Cliente clienteEncontrado = clienteService.buscar(1);

        assertNotNull(clienteEncontrado);
        assertEquals(cliente, clienteEncontrado);

        verify(clienteRepository, times(1)).findById(1);
    }

    @Test
    void testBuscarNotFound() {
        when(clienteRepository.findById(2)).thenReturn(Optional.empty());

        Cliente clienteEncontrado = clienteService.buscar(2);

        assertNull(clienteEncontrado);

        verify(clienteRepository, times(1)).findById(2);
    }

    @Test
    void testEliminar() {
        doNothing().when(clienteRepository).deleteById(1);

        clienteService.eliminar(1);

        verify(clienteRepository, times(1)).deleteById(1);
    }
}