package com.tecsup.demo.views;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.tecsup.demo.modelo.entidades.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("usuario/ver.pdf")
public class UsuarioPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Usuario> usuarios = (List<Usuario>) model.get("usuarios");

        PdfPTable tabla = new PdfPTable(1);
        tabla.setSpacingAfter(20);

        PdfPCell cell = new PdfPCell(new Phrase("Lista de Usuarios"));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        tabla.addCell(cell);

        // Cambiar el número de columnas a 7
        PdfPTable tabla2 = new PdfPTable(8);
        tabla2.addCell("ID");
        tabla2.addCell("Nombre");
        tabla2.addCell("Apellido");
        tabla2.addCell("Correo");
        tabla2.addCell("Teléfono");
        tabla2.addCell("Dirección");
        tabla2.addCell("Rol");
        tabla2.addCell("Estado");

        for (Usuario usuario : usuarios) {
            tabla2.addCell(String.valueOf(usuario.getId()));
            tabla2.addCell(usuario.getNombre());
            tabla2.addCell(usuario.getApellido());
            tabla2.addCell(usuario.getEmail());
            tabla2.addCell(usuario.getTelefono());
            tabla2.addCell(usuario.getDireccion());
            tabla2.addCell(usuario.getRol());
            tabla2.addCell(usuario.getEstado());
        }

        document.add(tabla);
        document.add(tabla2);
    }
}
