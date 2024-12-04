package com.tecsup.demo.services;

import com.tecsup.demo.modelo.daos.ClienteRepository;
import com.tecsup.demo.modelo.entidades.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Juan Perez");
        cliente.setDireccion("Av. Siempre Viva 123");
        cliente.setTelefono("987654321");
        cliente.setEmail("juan.perez@example.com");
        cliente.setAutosInteres("Toyota Corolla, Honda Civic");
    }

    @Test
    void testListar() {
        // Arrange
        List<Cliente> clientes = Arrays.asList(cliente);
        when(clienteRepository.findAll()).thenReturn(clientes);

        // Act
        List<Cliente> result = clienteService.listar();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Juan Perez", result.get(0).getNombre());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testGrabar() {
        // Act
        clienteService.grabar(cliente);

        // Assert
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testBuscar() {
        // Arrange
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = clienteService.buscar(1);

        // Assert
        assertNotNull(result);
        assertEquals("Juan Perez", result.getNombre());
        verify(clienteRepository, times(1)).findById(1);
    }

    @Test
    void testBuscarNotFound() {
        // Arrange
        when(clienteRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Cliente result = clienteService.buscar(1);

        // Assert
        assertNull(result);
        verify(clienteRepository, times(1)).findById(1);
    }

    @Test
    void testEliminar() {
        // Act
        clienteService.eliminar(1);

        // Assert
        verify(clienteRepository, times(1)).deleteById(1);
    }
}