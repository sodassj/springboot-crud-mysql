package com.tecsup.demo.views;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.tecsup.demo.modelo.entidades.Cliente; // Asegúrate de importar la clase Cliente
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("cliente/ver.pdf") // Cambia el nombre del componente según tus rutas
public class ClientePdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Cliente> clientes = (List<Cliente>) model.get("clientes"); // Asegúrate de que este modelo esté configurado en tu controlador

        // Tabla para el título
        PdfPTable tablaTitulo = new PdfPTable(1);
        tablaTitulo.setSpacingAfter(20);

        PdfPCell cell = new PdfPCell(new Phrase("Lista de Clientes"));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        tablaTitulo.addCell(cell);

        // Tabla principal con las columnas necesarias
        PdfPTable tablaClientes = new PdfPTable(7); // Cambia a 7 columnas
        tablaClientes.addCell("ID");
        tablaClientes.addCell("Nombre");
        tablaClientes.addCell("Dirección");
        tablaClientes.addCell("Teléfono");
        tablaClientes.addCell("Email");
        tablaClientes.addCell("Fecha de Registro");
        tablaClientes.addCell("Autos de Interés");

        // Rellenar la tabla con los datos de los clientes
        for (Cliente cliente : clientes) {
            tablaClientes.addCell(String.valueOf(cliente.getId()));
            tablaClientes.addCell(cliente.getNombre());
            tablaClientes.addCell(cliente.getDireccion());
            tablaClientes.addCell(cliente.getTelefono());
            tablaClientes.addCell(cliente.getEmail());
            tablaClientes.addCell(cliente.getFechaRegistro().toString()); // Formato de fecha puede ser ajustado según tu necesidad
            tablaClientes.addCell(cliente.getAutosInteres());
        }

        // Agregar las tablas al documento
        document.add(tablaTitulo);
        document.add(tablaClientes);
    }
}
