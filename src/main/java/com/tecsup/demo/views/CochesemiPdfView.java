package com.tecsup.demo.views;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.tecsup.demo.modelo.entidades.Cochesemi; // Asegúrate de importar tu clase Cochesemi
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("cochesemi/ver.pdf")
public class CochesemiPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Cochesemi> cochesemiList = (List<Cochesemi>) model.get("cochesemi");

        // Crear la tabla para el encabezado
        PdfPTable tabla = new PdfPTable(1);
        tabla.setSpacingAfter(20);

        PdfPCell cell = new PdfPCell(new Phrase("Lista de Coches Semi Nuevos"));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        tabla.addCell(cell);

        // Crear la tabla con los campos de Cochesemi
        PdfPTable tabla2 = new PdfPTable(7); // Cambiado a 7 para incluir ID
        tabla2.addCell("ID");
        tabla2.addCell("Descripción");
        tabla2.addCell("Marca");
        tabla2.addCell("Modelo");
        tabla2.addCell("Tipo");
        tabla2.addCell("Localización");
        tabla2.addCell("Precio");

        // Agregar los datos de cada coche a la tabla
        for (Cochesemi coche : cochesemiList) {
            tabla2.addCell(String.valueOf(coche.getId())); // Añadido ID
            tabla2.addCell(coche.getDescripcion());
            tabla2.addCell(coche.getMarca());
            tabla2.addCell(coche.getModelo());
            tabla2.addCell(coche.getTipo());
            tabla2.addCell(coche.getLocalizacion());
            tabla2.addCell(String.valueOf(coche.getPrecio()) + " USD");
        }

        // Agregar las tablas al documento
        document.add(tabla);
        document.add(tabla2);
    }
}

