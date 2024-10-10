package com.tecsup.demo.views;

import java.util.List;
import java.util.Map;

import com.tecsup.demo.modelo.entidades.Cliente;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("cliente/ver.xlsx")
public class ClienteXlsView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // Establecer el nombre del archivo
        response.setHeader("Content-Disposition", "attachment; filename=\"clientes_view.xlsx\"");

        // Obtener la lista de clientes desde el modelo
        List<Cliente> clientes = (List<Cliente>) model.get("clientes");

        // Crear la hoja de cálculo
        Sheet sheet = workbook.createSheet("Lista de Clientes");

        // Crear la primera fila para el título
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Lista de Clientes");

        // Estilo del encabezado de la tabla
        CellStyle theaderStyle = workbook.createCellStyle();
        theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        theaderStyle.setBorderTop(BorderStyle.MEDIUM);
        theaderStyle.setBorderRight(BorderStyle.MEDIUM);
        theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
        theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
        theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Estilo para las celdas del cuerpo
        CellStyle tbodyStyle = workbook.createCellStyle();
        tbodyStyle.setBorderBottom(BorderStyle.THIN);
        tbodyStyle.setBorderTop(BorderStyle.THIN);
        tbodyStyle.setBorderRight(BorderStyle.THIN);
        tbodyStyle.setBorderLeft(BorderStyle.THIN);

        // Crear la fila de encabezado en la posición 4
        Row header = sheet.createRow(4);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Nombre");
        header.createCell(2).setCellValue("Dirección");
        header.createCell(3).setCellValue("Teléfono");
        header.createCell(4).setCellValue("Email");
        header.createCell(5).setCellValue("Fecha de Registro");
        header.createCell(6).setCellValue("Coches de Interés");

        // Aplicar estilo de encabezado
        for (int i = 0; i <= 6; i++) {
            header.getCell(i).setCellStyle(theaderStyle);
        }

        // Empezar a llenar los datos desde la fila 6
        int rownum = 6;

        for (Cliente cliente : clientes) {
            Row fila = sheet.createRow(rownum++);

            // Llenar cada celda con los datos del cliente
            cell = fila.createCell(0);
            cell.setCellValue(cliente.getId());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(1);
            cell.setCellValue(cliente.getNombre());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(2);
            cell.setCellValue(cliente.getDireccion());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(3);
            cell.setCellValue(cliente.getTelefono());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(4);
            cell.setCellValue(cliente.getEmail());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(5);
            cell.setCellValue(cliente.getFechaRegistro().toString()); // Ajusta el formato de la fecha si es necesario
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(6);
            cell.setCellValue(cliente.getAutosInteres());
            cell.setCellStyle(tbodyStyle);
        }

        // Ajustar automáticamente el ancho de las columnas
        for (int i = 0; i <= 6; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
