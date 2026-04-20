package es.iesclaradelrey.da2d1e.shopvlcdio.api.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.api.services.XmlProductService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;

@RestController
@RequestMapping("/api/v1/xml")
public class ProductXmlController {

    private XmlProductService xmlProductService;

    public ProductXmlController(XmlProductService xmlSerice) {
        this.xmlProductService = xmlSerice;
    }

    @GetMapping(value = "/export", produces = "text/xml")
    public ResponseEntity<String> export() throws
            ParserConfigurationException,
            TransformerException,
            IOException {

        Document productsDocument = xmlProductService.exportAll();

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        DOMSource source = new DOMSource(productsDocument);

        try (StringWriter writer = new StringWriter()) {

            StreamResult result = new StreamResult(writer);

            transformer.setOutputProperty("indent", "4");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(source, result);

            String fileName = "products-export-" +
                    java.time.LocalDateTime.now()
                            .toString()
                            .replace(":", "-") +
                    ".xml";

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Disposition", "attachement; " + fileName)
                    .body(writer.toString());
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> importFile(
            @RequestParam("productsfile") MultipartFile multipartFile
    ) throws Exception {

        if (multipartFile.isEmpty()) {
            return ResponseEntity.badRequest().body("El fichero no se ha recibido");
        }

        if (multipartFile.getSize() == 0) {
            return ResponseEntity.badRequest().body("El fichero recibido está vacío");
        }

        System.out.printf("Tamaño del fichero recibido: %s\n", multipartFile.getSize());
        System.out.printf("Nombre original del fichero recibido: %s\n", multipartFile.getOriginalFilename());

        xmlProductService.importProducts(multipartFile.getInputStream());

        return ResponseEntity.ok("Fichero recibido correctamente");
    }
}
