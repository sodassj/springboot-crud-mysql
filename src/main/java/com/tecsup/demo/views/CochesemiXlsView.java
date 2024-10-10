package com.tecsup.demo.views;

import java.util.List;
import java.util.Map;

import com.tecsup.demo.modelo.entidades.Cochesemi; // Asegúrate de importar tu clase Cochesemi

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

@Component("cochesemi/ver.xlsx")
public class CochesemiXlsView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"cochesemi_view.xlsx\"");
        List<Cochesemi> cochesemiList = (List<Cochesemi>) model.get("cochesemi");
        Sheet sheet = workbook.createSheet("Lista de Coches Semi Nuevos");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Lista de Coches Semi Nuevos");

        CellStyle theaderStyle = workbook.createCellStyle();
        theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        theaderStyle.setBorderTop(BorderStyle.MEDIUM);
        theaderStyle.setBorderRight(BorderStyle.MEDIUM);
        theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
        theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
        theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle tbodyStyle = workbook.createCellStyle();
        tbodyStyle.setBorderBottom(BorderStyle.THIN);
        tbodyStyle.setBorderTop(BorderStyle.THIN);
        tbodyStyle.setBorderRight(BorderStyle.THIN);
        tbodyStyle.setBorderLeft(BorderStyle.THIN);

        Row header = sheet.createRow(4);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Descripción");
        header.createCell(2).setCellValue("Marca");
        header.createCell(3).setCellValue("Modelo");
        header.createCell(4).setCellValue("Tipo");
        header.createCell(5).setCellValue("Localización");
        header.createCell(6).setCellValue("Precio");

        // Establecer el estilo para el encabezado
        header.getCell(0).setCellStyle(theaderStyle);
        header.getCell(1).setCellStyle(theaderStyle);
        header.getCell(2).setCellStyle(theaderStyle);
        header.getCell(3).setCellStyle(theaderStyle);
        header.getCell(4).setCellStyle(theaderStyle);
        header.getCell(5).setCellStyle(theaderStyle);
        header.getCell(6).setCellStyle(theaderStyle);

        int rownum = 6; // Comienza desde la fila 6 para los datos

        // Agregar datos de coches semi nuevos
        for (Cochesemi coche : cochesemiList) {
            Row fila = sheet.createRow(rownum++);
            cell = fila.createCell(0);
            cell.setCellValue(coche.getId());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(1);
            cell.setCellValue(coche.getDescripcion());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(2);
            cell.setCellValue(coche.getMarca());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(3);
            cell.setCellValue(coche.getModelo());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(4);
            cell.setCellValue(coche.getTipo());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(5);
            cell.setCellValue(coche.getLocalizacion());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(6);
            cell.setCellValue(coche.getPrecio() + " USD");
            cell.setCellStyle(tbodyStyle);
        }
    }
}
