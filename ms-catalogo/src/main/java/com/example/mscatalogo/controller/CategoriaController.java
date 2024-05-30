package com.example.mscatalogo.controller;

import com.example.mscatalogo.entity.Categoria;
import com.example.mscatalogo.service.CategoriaService;
import com.example.mscatalogo.util.PdfUtils;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
        private CategoriaService categoriaService;
    @GetMapping
    public ResponseEntity<List<Categoria>> listar(){
        return ResponseEntity.ok(categoriaService.listar());
    }
    @PostMapping
    public ResponseEntity<Categoria> guardar(@RequestBody Categoria categoria){
        return ResponseEntity.ok(categoriaService.guardar(categoria));
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Categoria> busacarPorId(@PathVariable(required = true) Integer id){
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editar(@PathVariable(required = true) Integer id, @RequestBody Categoria categoria){
        categoria.setId(id);
        return  ResponseEntity.ok(categoriaService.editar(categoria));
    }
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable(required = true) Integer id){
        categoriaService.eliminar(id);
        return "Eliminacion completa";
    }
}
@RestController
@RequestMapping("/export")
class ExportController {

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportPdf(@RequestBody Map<String, Object> request) throws IOException, DocumentException {
        // Assuming executeQuery is a method to process the request and return the query results
        List<Map<String, Object>> queryResults = PdfUtils.executeQuery(request);
        ByteArrayOutputStream pdfStream = PdfUtils.generatePdfStream(queryResults);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=query_results.pdf");
        headers.setContentLength(pdfStream.size());
        return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
    }
}