package es.iesclaradelrey.da2d1e.shopvlcdio.api.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.api.services.XmlService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/xml")
public class ProductXmlController {

    private XmlService xmlService;

    public ProductXmlController(XmlService xmlSerice) {
        this.xmlService = xmlSerice;
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> export() throws
            ParserConfigurationException,
            TransformerException,
            IOException {

        // 1. Llamamos al servicio para obtener el Document (DOM)
        Document productsDocument = xmlService.exportAll();

        // 2. Preparamos el transformador para pasar de Document a String
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        // 3. Transformamos
        try (StringWriter writer = new StringWriter()) {
            DOMSource source = new DOMSource(productsDocument);
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);

            // 4. Nombre del archivo dinámico (requisito de la práctica)
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH-mm"));
            String fileName = "products-export." + timestamp + ".xml";

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(writer.toString());
        }
    }

    @PostMapping
    public ResponseEntity<?> importXml(@RequestParam("productsfile") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("El archivo está vacío");
        }

        try {
            xmlService.importProducts(file.getInputStream());
            return ResponseEntity.ok("Importación completada con éxito");
        } catch (RuntimeException e) {
            // Aquí es donde usamos ProblemDetail para el error 404
            ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
            pd.setTitle("Error de validación en Importación XML");
            return ResponseEntity.of(pd).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error procesando XML");
        }
    }
}
