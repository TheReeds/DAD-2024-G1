package com.example.mscatalogo.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public class PdfUtils {

    public static ByteArrayOutputStream generatePdfStream(List<Map<String, Object>> queryResults) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Write column names (assuming the first row contains the column names)
        if (!queryResults.isEmpty()) {
            Map<String, Object> firstRow = queryResults.get(0);
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            for (String column : firstRow.keySet()) {
                Paragraph paragraph = new Paragraph(column, boldFont);
                document.add(paragraph);
            }
            document.add(new Paragraph("\n"));

            // Write data rows
            for (Map<String, Object> row : queryResults) {
                for (Object value : row.values()) {
                    Paragraph paragraph = new Paragraph(value.toString());
                    document.add(paragraph);
                }
                document.add(new Paragraph("\n"));
            }
        }

        document.close();
        return outputStream;
    }

    public static List<Map<String, Object>> executeQuery(Map<String, Object> request) {
        // Implement your query logic here and return the results
        return List.of(Map.of("sampleKey", "sampleValue")); // Sample return for demonstration
    }
}
