package es.iesclaradelrey.da2d1e.shopvlcdio.api.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.api.services.parsers.ProductSaxHandler;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.XmlCreationException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.BrandRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.CategoryRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

@Service
public class XmlServiceImpl implements XmlService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public XmlServiceImpl(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Document exportAll() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        doc.setXmlVersion("1.0");

        // Nodo raíz <products>
        Element rootElement = doc.createElement("products");
        doc.appendChild(rootElement);

        List<Product> products = productRepository.findAll();

        for (Product p : products) {
            Element productNode = doc.createElement("product");
            rootElement.appendChild(productNode);

            // Campos simples
            appendElement(doc, productNode, "code", p.getCode());
            appendElement(doc, productNode, "name", p.getName());
            appendElement(doc, productNode, "description", p.getDescription());
            appendElement(doc, productNode, "img", p.getImg());
            appendElement(doc, productNode, "price", String.valueOf(p.getPrice()));
            appendElement(doc, productNode, "discount", String.valueOf(p.getDiscount()));
            appendElement(doc, productNode, "stock", String.valueOf(p.getStock()));

            // Marca
            Element brandNode = doc.createElement("brand");
            brandNode.setAttribute("id", String.valueOf(p.getBrand().getId()));
            appendElement(doc, brandNode, "name", p.getBrand().getName());
            productNode.appendChild(brandNode);

            // Categorías
            Element categoriesNode = doc.createElement("categories");
            for (Category cat : p.getCategories()) {
                Element catNode = doc.createElement("category");
                catNode.setAttribute("id", String.valueOf(cat.getId()));
                appendElement(doc, catNode, "name", cat.getName());
                categoriesNode.appendChild(catNode);
            }
            productNode.appendChild(categoriesNode);
        }

        return doc;
    }

    private void appendElement(Document doc, Element parent, String tag, String value) {
        Element node = doc.createElement(tag);
        node.appendChild(doc.createTextNode(value != null ? value : ""));
        parent.appendChild(node);
    }

    @Override
    public byte[] transformDocToBytes(Document doc) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        // Configurar indentación para que el XML sea legible
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        transformer.transform(new DOMSource(doc), new StreamResult(bos));
        return bos.toByteArray();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importProducts(InputStream inputStream) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        ProductSaxHandler handler = new ProductSaxHandler(brandRepository, categoryRepository);
        saxParser.parse(inputStream, handler);

        // Si el parseo termina sin errores, guardamos todo
        productRepository.saveAll(handler.getProducts());
    }
}

