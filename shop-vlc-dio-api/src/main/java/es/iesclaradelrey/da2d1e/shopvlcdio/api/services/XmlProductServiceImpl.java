package es.iesclaradelrey.da2d1e.shopvlcdio.api.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.api.services.parsers.ProductImportSaxHandler;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.ProductRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.BrandService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Service
public class XmlProductServiceImpl implements XmlProductService {

    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;


    public XmlProductServiceImpl(ProductRepository productRepository, BrandService brandService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.brandService = brandService;
        this.categoryService = categoryService;
    }


    @Override
    public Document exportAll() throws ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        document.setXmlVersion("1.0");

        // Obtener productos
        List<Product> products = productRepository.findAll();

        // Exportar productos
        exportProducts(products, document);

        return document;
    }

    private void exportProducts(List<Product> products, Document document) {

        Element productsElement = document.createElement("products");

        for (Product product : products) {

            Element productElement = document.createElement("product");
            productsElement.appendChild(productElement);

            createElement(document, productElement, "id", product.getId());
            createElement(document, productElement, "code", product.getCode());
            createElement(document, productElement, "name", product.getName());
            createElement(document, productElement, "description", product.getDescription());
            createElement(document, productElement, "img", product.getImg());
            createElement(document, productElement, "price", product.getPrice());
            createElement(document, productElement, "discount", product.getDiscount());
            createElement(document, productElement, "stock", product.getStock());

            Element brandElement = document.createElement("brand");

            createElement(document, brandElement, "id", product.getBrand().getId());
            createElement(document, brandElement, "name", product.getBrand().getName());

            productElement.appendChild(brandElement);

            Element categoriesElement = document.createElement("categories");

            for (Category category : product.getCategories()) {

                Element categoryElement = document.createElement("category");

                createElement(document, categoryElement, "id", category.getId());
                createElement(document, categoryElement, "name", category.getName());

                categoriesElement.appendChild(categoryElement);
            }

            productElement.appendChild(categoriesElement);
        }

        document.appendChild(productsElement);
    }

    private void createElement(Document doc, Element parent, String tag, Object value) {
        Element element = doc.createElement(tag);
        element.setTextContent(value != null ? value.toString() : "");
        parent.appendChild(element);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importProducts(InputStream productsStream)
            throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        ProductImportSaxHandler handler =
                new ProductImportSaxHandler(brandService, categoryService);

        parser.parse(productsStream, handler);

        System.out.printf("Se han encontrado %d productos en el XML\n",
                handler.getProducts().size());

        handler.getProducts().forEach(product ->
                System.out.println(product.getName())
        );

        productRepository.saveAll(handler.getProducts());
    }
}

