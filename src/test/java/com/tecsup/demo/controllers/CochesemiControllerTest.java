package com.tecsup.demo.controllers;

import com.tecsup.demo.modelo.entidades.Cochesemi;
import com.tecsup.demo.services.CochesemiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

public class CochesemiControllerTest {

    private MockMvc mockMvc;  // Eliminar @Autowired

    @Mock
    private CochesemiService cochesemiService;

    @InjectMocks
    private CochesemiController cochesemiController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cochesemiController).build();
    }

    @Test
    public void testListarCochesemi() throws Exception {
        List<Cochesemi> cochesemiList = Arrays.asList(new Cochesemi(), new Cochesemi());

        when(cochesemiService.listar()).thenReturn(cochesemiList);

        mockMvc.perform(get("/listarCochesemi"))
                .andExpect(status().isOk())
                .andExpect(view().name("listarCochesemiView"))
                .andExpect(model().attributeExists("cochesemiList"))
                .andExpect(model().attribute("titulo", "Listado de Coches MotorMarket"));

        verify(cochesemiService, times(1)).listar();
    }

    @Test
    public void testCrearCochesemi() throws Exception {
        mockMvc.perform(get("/formCochesemi"))
                .andExpect(status().isOk())
                .andExpect(view().name("formCochesemiView"))
                .andExpect(model().attributeExists("cochesemi"))
                .andExpect(model().attribute("titulo", "Formulario de Coche"));
    }

    @Test
    public void testEditarCochesemi() throws Exception {
        Cochesemi cochesemi = new Cochesemi();
        cochesemi.setId(1);

        when(cochesemiService.buscar(1)).thenReturn(cochesemi);

        mockMvc.perform(get("/formCochesemi/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("formCochesemiView"))
                .andExpect(model().attributeExists("cochesemi"))
                .andExpect(model().attribute("titulo", "Editar Coche"));

        verify(cochesemiService, times(1)).buscar(1);
    }

    @Test
    public void testEliminarCochesemi() throws Exception {
        mockMvc.perform(get("/eliminarCochesemi/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/listarCochesemi"));

        verify(cochesemiService, times(1)).eliminar(1);
    }

    @Test
    public void testVerCochesemi() throws Exception {
        mockMvc.perform(get("/cochesemi"))
                .andExpect(status().isOk())
                .andExpect(view().name("cochesemi/ver"))
                .andExpect(model().attributeExists("cochesemi"))
                .andExpect(model().attribute("titulo", "Lista de Coches"));
    }

    @Test
    public void testVerCochesemiFormatoPdf() throws Exception {
        mockMvc.perform(get("/cochesemi/ver?format=pdf"))
                .andExpect(status().isOk())
                .andExpect(view().name("cochesemi/ver.pdf"));
    }

    @Test
    public void testVerCochesemiFormatoXlsx() throws Exception {
        mockMvc.perform(get("/cochesemi/ver?format=xlsx"))
                .andExpect(status().isOk())
                .andExpect(view().name("cochesemi/ver.xlsx"));
    }
}

